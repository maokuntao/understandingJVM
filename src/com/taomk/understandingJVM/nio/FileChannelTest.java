package com.taomk.understandingJVM.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * http://www.iteye.com/magazines/132-Java-NIO
 * 
 * @author taomk 2017年8月8日 下午6:34:10
 *
 */
public class FileChannelTest {

	public static void main(String[] args) throws IOException {

		String filePath = "/Users/Dev/github/understandingJVM/README.md";
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filePath, "rw");
			FileChannel channel = raf.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024);

			// 将数据读取到buffer
			int bytesRead = channel.read(buffer);
			while (bytesRead != -1) {
				System.out.println("Read " + bytesRead);
				// 切换buffer模式，从写模式转为读模式
				buffer.flip();
				while (buffer.hasRemaining()) {
					// 读取数据
					System.out.println((char) buffer.get());
				}
				// clear或者compact，
				// clear会清空整个缓存，
				// 而compact只会清除已读的数据、未读的数据会移动到缓冲区的起始处
				buffer.clear();
				//将数据读取到buffer
				bytesRead = channel.read(buffer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				raf.close();
			}
		}
	}

}
