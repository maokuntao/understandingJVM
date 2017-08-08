package com.taomk.understandingJVM.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
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

			java.nio.ByteBuffer buffer = ByteBuffer.allocate(1024);

			int bytesRead = channel.read(buffer);
			while (bytesRead != -1) {
				System.out.println("Read " + bytesRead);
				buffer.flip();
				while (buffer.hasRemaining()) {
					System.out.println((char) buffer.get());
				}
				buffer.clear();
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
