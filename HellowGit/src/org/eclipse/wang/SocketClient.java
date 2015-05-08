package org.eclipse.wang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient
{
	public static void main(String[] args) throws Exception
	{
		Socket socket = new Socket("localhost", Consts.SERVER_PORT);
		//		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			String msg = reader.readLine();
			System.out.println("client typein: " + msg);
			out.print(msg);
			out.flush();
			if (msg.equals("quit") || msg.equals("exit"))
			{
				break;
			}
			//			System.out.println(in.readLine());
		}
		socket.close();
	}
}
