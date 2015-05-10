package org.eclipse.wang.net;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author super
 * @version create on：2015年5月10日 下午8:17:34
 */
public class MultithreadedRemoteFileServer
{
	private int listenPort;

	public MultithreadedRemoteFileServer(int aListenPort)
	{
		listenPort = aListenPort;
	}

	public void acceptConnections()
	{
		try
		{
			ServerSocket server = new ServerSocket(listenPort, Consts.BACKLOG);
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

	public void handleConnection(Socket connectionToHandle)
	{
		new Thread(new ConnectionHandler(connectionToHandle)).start();
	}

	public static void main(String[] args)
	{
		MultithreadedRemoteFileServer server = new MultithreadedRemoteFileServer(Consts.PORT);
		server.acceptConnections();
	}
}
