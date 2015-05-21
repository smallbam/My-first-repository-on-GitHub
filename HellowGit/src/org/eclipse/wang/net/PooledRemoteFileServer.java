package org.eclipse.wang.net;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.wang.Consts;

/**
 * @author super
 * @version create on：2015年5月10日 下午9:32:40
 */
public class PooledRemoteFileServer
{
	private int maxConnections;

	private int listenPort;

	private ServerSocket serverSocket;

	public PooledRemoteFileServer(int aListenPort, int maxConnections)
	{
		listenPort = aListenPort;
		this.maxConnections = maxConnections;
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

	protected void handleConnection(Socket connectionToHandle)
	{
		PooledConnectionHandler.processRequest(connectionToHandle);
	}

	public void setUpHandlers()
	{
		for (int i = 0; i < maxConnections; i++)
		{
			PooledConnectionHandler currentHandler = new PooledConnectionHandler();
			new Thread(currentHandler, "Handler " + i).start();
		}
	}

	public static void main(String[] args)
	{
		PooledRemoteFileServer server = new PooledRemoteFileServer(Consts.PORT, Consts.BACKLOG - 2);
		server.setUpHandlers();
		server.acceptConnections();
	}
}
