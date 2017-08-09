package com.taomk.understandingJVM.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * http://www.iteye.com/magazines/132-Java-NIO
 * 
 * @author taomk 2017年8月8日 下午6:34:10
 *
 */
public class FileChannelTest {

	public static void main(String[] args) throws IOException {

//		String filePath = "/Users/Dev/github/understandingJVM/README.md";
//		readFile(filePath);
//
//		String toFile = "/Users/Dev/github/understandingJVM/nio-data.txt";
//		String content = "New String to write to file...\n当前毫秒数：" + System.currentTimeMillis();
//		writeToFile(content, toFile);
		
		String bigFile = "/Users/Tao/Downloads/Spring实战.pdf";
		readBigFile1(bigFile);
		readBigFile2(bigFile);

	}

	public static void readFile(String filePath) throws IOException {
		RandomAccessFile raf = null;
		try {
			// Java.nio.charset.Charset处理了字符转换问题。
			// 它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder(); 

			raf = new RandomAccessFile(filePath, "rw");
			FileChannel channel = raf.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024);
			CharBuffer charBuffer = CharBuffer.allocate(1024);

			// 将数据读取到buffer
			int bytesRead = channel.read(buffer);
			System.out.println("Size : " + channel.size());
			while (bytesRead != -1) {
				System.out.println("Read : " + bytesRead);
				// 切换buffer模式，从写模式转为读模式
				buffer.flip();
				
				decoder.decode(buffer, charBuffer, false); 
				
				charBuffer.flip();
				
				System.out.println("Content : ");
				while (charBuffer.hasRemaining()) {
					// 读取数据
					System.out.print(charBuffer.get());
				}
				// clear或者compact，
				// clear会清空整个缓存，
				// 而compact只会清除已读的数据、未读的数据会移动到缓冲区的起始处
				buffer.clear();
				// 将数据读取到buffer
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

	public static void writeToFile(String content, String file) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile(file, "rw");
		FileChannel channel = aFile.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(64);
		buffer.clear();
		buffer.put(content.getBytes());

		buffer.flip();

		while (buffer.hasRemaining()) {
			channel.write(buffer);
		}
		aFile.close();
	}
	
	/**
	 * 使用ByteBuffer来读取一个较大文件的效率
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void readBigFile1(String filePath) throws IOException {
		RandomAccessFile rfa = null;
		FileChannel fc = null;
		try {
			
			System.out.println("=============readBigFile1=============");

			rfa = new RandomAccessFile(filePath, "r");
			fc = rfa.getChannel();
			
			System.out.println("Size : " + rfa.length());
			
			long startTime = System.currentTimeMillis();
			
			ByteBuffer buffer = ByteBuffer.allocate((int) fc.size());
			buffer.clear();
			fc.read(buffer);
			System.out.println(buffer.getChar((int) (fc.size()/2)));
			
			long endTime = System.currentTimeMillis();
			System.out.println("readBigFile1(ms) : " + (endTime-startTime));
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			if(rfa!=null) {
				rfa.close();
			}
			if(fc!=null) {
				fc.close();
			}
		}
	}
	
	/**
	 * 使用MappedByteBuffer来读取一个较大文件
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void readBigFile2(String filePath) throws IOException {
		RandomAccessFile rfa = null;
		FileChannel fc = null;
		try {
			
			System.out.println("=============readBigFile2=============");
			
			rfa = new RandomAccessFile(filePath, "r");
			fc = rfa.getChannel();
			
			System.out.println("Size : " + rfa.length());
			
			long startTime = System.currentTimeMillis();
			
			MappedByteBuffer mappedBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size());
			
			System.out.println(mappedBuffer.getChar((int) (rfa.length()/2)));
			
			long endTime = System.currentTimeMillis();
			System.out.println("readBigFile1(ms) : " + (endTime-startTime));
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			if(rfa!=null) {
				rfa.close();
			}
			if(fc!=null) {
				fc.close();
			}
		}
	}

}
