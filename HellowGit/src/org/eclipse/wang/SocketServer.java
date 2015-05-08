package org.eclipse.wang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket server = new ServerSocket(Consts.SERVER_PORT);
		Socket socket = server.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//		PrintWriter out = new PrintWriter(socket.getOutputStream());

		while (true)
		{
			String msg = in.readLine();
			System.out.println("Server readline: " + msg);
			//			out.println("Server received: " + msg);
			//			out.flush();
			if (msg.equals("exit") || msg.equals("quit"))
			{
				break;
			}
		}
		socket.close();
	}
}
