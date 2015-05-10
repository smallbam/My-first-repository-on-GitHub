package org.eclipse.wang.net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * @author super
 * @version create on：2015年5月10日 下午9:33:34
 */
public class PooledConnectionHandler implements Runnable
{
	private Socket connection;

	private static List<Socket> pool = new LinkedList<Socket>();

	public PooledConnectionHandler()
	{}

	public void handleConnection()
	{
		try
		{
			PrintWriter streamWriter = new PrintWriter(connection.getOutputStream());
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
			String fileToRead = streamReader.readLine();
			BufferedReader fileReader = new BufferedReader(new FileReader(fileToRead));
			String line = null;
			while ((line = fileReader.readLine()) != null)
				streamWriter.println(line);
			fileReader.close();
			streamWriter.close();
			streamReader.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not find requested file on the server.");
		}
		catch (IOException e)
		{
			System.out.println("Error handling a client: " + e);
		}
	}

	public static void processRequest(Socket requestToHandle)
	{
		synchronized (pool)
		{
			pool.add(pool.size(), requestToHandle);
			pool.notifyAll();
		}
	}

	public void run()
	{
		while (true)
		{
			synchronized (pool)
			{
				while (pool.isEmpty())
				{
					try
					{
						pool.wait();
					}
					catch (InterruptedException e)
					{
						return;
					}
				}
				connection = (Socket) pool.remove(0);
			}
			handleConnection();
		}
	}
}
