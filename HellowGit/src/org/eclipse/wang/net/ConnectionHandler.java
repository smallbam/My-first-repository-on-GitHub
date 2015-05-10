package org.eclipse.wang.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author super
 * @version create on：2015年5月10日 下午8:18:08
 */
public class ConnectionHandler implements Runnable
{
	private Socket socketToHandle;

	public ConnectionHandler(Socket aSocketToHandle)
	{
		socketToHandle = aSocketToHandle;
	}

	@Override
	public void run()
	{
		try
		{
			PrintWriter streamWriter = new PrintWriter(socketToHandle.getOutputStream());
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				socketToHandle.getInputStream()));
			String fileToRead = streamReader.readLine();
			BufferedReader fileReader = new BufferedReader(new FileReader(fileToRead));
			String line = null;
			while ((line = fileReader.readLine()) != null)
				streamWriter.println(line);
			fileReader.close();
			streamWriter.close();
			streamReader.close();
		}
		catch (Exception e)
		{
			System.out.println("Error handling a client: " + e);
		}
	}
}
