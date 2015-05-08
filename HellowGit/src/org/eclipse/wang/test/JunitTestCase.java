package org.eclipse.wang.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.wang.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * Java NIO has more classes and components than these, but the Channel, Buffer
 * and Selector forms the core of the API.
 * 
 * @see http://tutorials.jenkov.com/java-nio/index.html
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
	public void testNIOChannelAndBuffer() throws IOException
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

	/**
	 * The Channel must be in non-blocking mode to be used with a Selector. This
	 * means that you cannot use FileChannel's with a Selector since
	 * FileChannel's cannot be switched into non-blocking mode. Socket channels
	 * will work fine though.
	 * 
	 * A Selector is a Java NIO component which can examine one or more NIO
	 * Channel's, and determine which channels are ready for e.g. reading or
	 * writing. This way a single thread can manage multiple channels, and thus
	 * multiple network connections.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNIOSelector() throws IOException
	{
		SocketChannel channel = SocketChannel.open();
		Selector selector = Selector.open();
		channel.configureBlocking(false);
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
		while (true)
		{
			int readyChannels = selector.select();
			if (readyChannels == 0)
				continue;
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext())
			{
				SelectionKey selectionKey = keyIterator.next();
				if (selectionKey.isAcceptable())
				{
					// a connection was accepted by a ServerSocketChannel.
				}
				else if (selectionKey.isConnectable())
				{
					// a connection was established with a remote server.
				}
				else if (selectionKey.isReadable())
				{
					// a channel is ready for reading
				}
				else if (selectionKey.isWritable())
				{
					// a channel is ready for writing
				}
				keyIterator.remove();
			}
		}
	}

	@Test
	public void testBinaryTree()
	{
		BinaryTree biTree = new BinaryTree();
		int[] data = { 2, 1, 6, 7, 5, 4, 3 };
		for (int i = 0; i < data.length; i++)
		{
			biTree.insert(data[i]);
		}
		biTree.printTreeInorder();
		biTree.printTreePreorder();
		biTree.printTreePostorder();

		BinaryTree biTree2 = new BinaryTree();
		int[] data2 = { 1, 2, 3, 4, 5, 6, 7 };
		for (int i = 0; i < data2.length; i++)
		{
			biTree2.insert(data2[i]);
		}
		biTree2.printTreeInorder();

		BinaryTree biTree3 = new BinaryTree();
		int[] data3 = { 2, 1, 6, 7, 5, 4, 3 };
		for (int i = 0; i < data3.length; i++)
		{
			biTree3.insert(data3[i]);
		}
		biTree3.printTreeInorder();

		Assert.assertEquals(false, biTree.sameTree(biTree2));
		Assert.assertEquals(true, biTree.sameTree(biTree3));

	}
}
