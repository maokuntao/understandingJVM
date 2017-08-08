package com.taomk.understandingJVM.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * http://www.importnew.com/19816.html
 * 
 * @author taomk 2017年8月8日 下午10:29:54
 *
 */
public class SocketChannelTest {

	public static void main(String[] args) throws InterruptedException {

		Thread serverThread = new Thread(() -> server());
		serverThread.start();
		
		Thread clientThread = new Thread(() -> client());
		clientThread.start();
		
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
