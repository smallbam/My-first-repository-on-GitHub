package org.eclipse.wang.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author super
 * @version create on：2015年5月10日 上午10:15:57
 */
public class RemoteFileClient
{
	private BufferedReader socketReader;

	private PrintWriter socketWriter;

	private String hostIp;

	private int hostPort;

	public RemoteFileClient(String aHostIp, int aHostPort)
	{
		hostIp = aHostIp;
		hostPort = aHostPort;
	}

	public String getFile(String fileNameToGet)
	{
		StringBuffer fileLines = new StringBuffer();
		try
		{
			socketWriter.println(fileNameToGet);
			socketWriter.flush();
			String line = null;
			while ((line = socketReader.readLine()) != null)
				fileLines.append(line + "\n");
		}
		catch (IOException e)
		{
			System.out.println("Error reading from file: " + fileNameToGet);
		}
		return fileLines.toString();
	}

	public static void main(String[] args)
	{
		RemoteFileClient remoteFileClient = new RemoteFileClient("127.0.0.1", Consts.PORT);
		remoteFileClient.setUpConnection();
		String fileContents = remoteFileClient.getFile(Consts.FILE_PATH);
		remoteFileClient.tearDownConnection();
		System.out.println(fileContents);
	}

	public void setUpConnection()
	{
		try
		{
			Socket client = new Socket(hostIp, hostPort);
			socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			socketWriter = new PrintWriter(client.getOutputStream());
		}
		catch (UnknownHostException e)
		{
			System.out.println("Error setting up socket connection: unknown host at " + hostIp
					+ ":");
		}
		catch (IOException e)
		{
			System.out.println("Error setting up socket connection: " + e);
		}
	}

	public void tearDownConnection()
	{
		try
		{
			socketWriter.close();
			socketReader.close();
		}
		catch (IOException e)
		{
			System.out.println("Error tearing down socket connection: " + e);
		}
	}
}
