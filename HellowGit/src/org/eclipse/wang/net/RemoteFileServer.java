package org.eclipse.wang.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author super
 * @version create on：2015年5月10日 下午8:00:59
 */
public class RemoteFileServer
{
	private int listenPort;

	public RemoteFileServer(int aListenPort)
	{
		listenPort = aListenPort;
	}

	public void acceptConnections()
	{
		try
		{
			ServerSocket server = new ServerSocket(listenPort);
			Socket incomingConnection = null;
			while (true)
			{
				incomingConnection = server.accept();
				handleConnection(incomingConnection);
			}
		}
		catch (BindException e)
		{
			System.out.println("Unable to bind to port " + listenPort);
		}
		catch (IOException e)
		{
			System.out.println("Unable to instantiate a ServerSocket on port: " + listenPort);
		}
	}

	public void handleConnection(Socket incomingConnection)
	{
		try
		{
			OutputStream outputToSocket = incomingConnection.getOutputStream();
			InputStream inputFromSocket = incomingConnection.getInputStream();
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputFromSocket));
			FileReader fileReader = new FileReader(new File(streamReader.readLine()));
			BufferedReader bufferedFileReader = new BufferedReader(fileReader);
			PrintWriter streamWriter = new PrintWriter(incomingConnection.getOutputStream());
			String line = null;
			while ((line = bufferedFileReader.readLine()) != null)
			{
				streamWriter.println(line);
			}
			fileReader.close();
			streamWriter.close();
			streamReader.close();
		}
		catch (Exception e)
		{
			System.out.println("Error handling a client: " + e);
		}
	}

	public static void main(String[] args)
	{
		RemoteFileServer server = new RemoteFileServer(Consts.PORT);
		server.acceptConnections();
	}
}
