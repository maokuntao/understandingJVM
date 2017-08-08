package com.taomk.understandingJVM.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * http://www.importnew.com/19816.html
 * 
 * @author taomk 2017年8月8日 下午10:29:54
 *
 */
public class SocketChannelTest {

	public static void main(String[] args) throws InterruptedException {

//		Thread serverThread = new Thread(() -> server());
		Thread serverThread = new Thread(() -> ServerConnect.main(args));
		serverThread.start();

		Thread clientThread1 = new Thread(() -> client());
//		Thread clientThread2 = new Thread(() -> client());
		clientThread1.start();
//		clientThread2.start();

		Thread.currentThread().join();
	}

	/**
	 * NIO方式实现的客户端
	 */
	public static void client() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = null;
		try {

			socketChannel = SocketChannel.open();// 打开SocketChannel
			socketChannel.configureBlocking(false);// 配置非阻塞
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));// 建立连接

			if (socketChannel.finishConnect()) {// 如果client与Server创建连接完毕
				int i = 0;
				while (true) {// 一直保持活跃状态
					TimeUnit.SECONDS.sleep(1);
					String info = "I'm " + i++ + "-th information from client";
					info += Thread.currentThread().getName();
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					// Write()方法无法保证能写多少字节到SocketChannel。
					// 所以，重复调用write()直到Buffer没有要写的字节为止
					while (buffer.hasRemaining()) {
						System.out.println(buffer);
						socketChannel.write(buffer);
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socketChannel != null) {
					socketChannel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 传统IO方式实现的Server端
	 */
	public static void server() {
		ServerSocket serverSocket = null;
		InputStream in = null;
		try {
			// 创建Socket
			serverSocket = new ServerSocket(8080);
			int recvMsgSize = 0;
			byte[] recvBuf = new byte[1024];
			while (true) {// 一直保持运行状态
				// 接受到消息
				Socket clntSocket = serverSocket.accept();
				SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);
				// 处理来自client的消息
				in = clntSocket.getInputStream();
				while ((recvMsgSize = in.read(recvBuf)) != -1) {
					byte[] temp = new byte[recvMsgSize];
					System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
					System.out.println(new String(temp));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * NIO方式实现的Server端
 * 
 * @author taomk 2017年8月8日 下午10:50:35
 *
 */
class ServerConnect {
	
	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8080;
	private static final int TIMEOUT = 3000;

	public static void main(String[] args) {
		selector();
	}

	public static void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
		SocketChannel sc = ssChannel.accept();
		sc.configureBlocking(false);
		sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
	}

	public static void handleRead(SelectionKey key) throws IOException {
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		long bytesRead = sc.read(buf);
		while (bytesRead > 0) {
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			System.out.println();
			buf.clear();
			bytesRead = sc.read(buf);
		}
		if (bytesRead == -1) {
			sc.close();
		}
	}

	public static void handleWrite(SelectionKey key) throws IOException {
		ByteBuffer buf = (ByteBuffer) key.attachment();
		buf.flip();
		SocketChannel sc = (SocketChannel) key.channel();
		while (buf.hasRemaining()) {
			sc.write(buf);
		}
		buf.compact();
	}

	public static void selector() {
		Selector selector = null;
		ServerSocketChannel ssc = null;
		try {
//			打开Serlector
			selector = Selector.open();
//			打开ServerSocketChannel
			ssc = ServerSocketChannel.open();
//			绑定本机指定端口
			ssc.socket().bind(new InetSocketAddress(PORT));
//			非阻塞，channel在与selector配合使用时，必须是非阻塞状态
			ssc.configureBlocking(false);
//			注册channel到selector，一旦有client与server接受就绪就会通知到selector
			ssc.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {//一直保持活跃状态
				if (selector.select(TIMEOUT) == 0) {
					System.out.println("==");
					continue;
				}
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					if (key.isAcceptable()) {
						handleAccept(key);
					}
					if (key.isReadable()) {
						handleRead(key);
					}
					if (key.isWritable() && key.isValid()) {
						handleWrite(key);
					}
					if (key.isConnectable()) {
						System.out.println("isConnectable = true");
					}
					iter.remove();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (selector != null) {
					selector.close();
				}
				if (ssc != null) {
					ssc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
