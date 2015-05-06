package org.eclipse.wang.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * Java NIO has more classes and components than these, but the Channel, Buffer
 * and Selector forms the core of the API
 * 
 * @throws IOException
 */
public class JunitTestCase
{
	/**
	 * Here is a basic example that uses a FileChannel to read some data into a
	 * Buffer
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException
	{
		RandomAccessFile aFile = new RandomAccessFile("./data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48); // create buffer with capacity of 48 bytes
		int bytesRead = inChannel.read(buf); //read into buffer.
		while (bytesRead != -1)
		{
			System.out.println("Read " + bytesRead);
			buf.flip(); //make buffer ready for read
			while (buf.hasRemaining())
			{
				System.out.print((char) buf.get()); // read 1 byte at a time
			}
			buf.clear(); //make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

	/**
	 * In Java NIO you can transfer data directly from one channel to another
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNIOTransfers() throws IOException
	{
		RandomAccessFile fromFile = new RandomAccessFile("./data/fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("./data/toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		long position = 0;
		long count = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
		fromChannel.transferTo(position, count, toChannel);

		fromFile.close();
		toFile.close();
	}
}
