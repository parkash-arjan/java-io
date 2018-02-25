package com.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer_SingleThreadedBlocking {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(2379);
		while (true) {
			Socket socket = ss.accept();
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			int data;
			while ((data = in.read()) != -1) {
				out.write(transform(data));
			}
			// in.transferTo(out);
			out.close();
			in.close();
			socket.close();
			System.out.println("Closing");
		}
	}

	private static int transform(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
