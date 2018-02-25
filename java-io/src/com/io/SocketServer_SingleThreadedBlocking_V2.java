package com.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer_SingleThreadedBlocking_V2 {
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(2379)) {
			while (true) {
				try (Socket socket = ss.accept(); InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream();) {
					int data;
					while ((data = in.read()) != -1) {
						out.write(transform(data));
					}
				} catch (IOException exp) {
					exp.printStackTrace();
				} finally {
					System.out.println("Closing Socket");
				}
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		} finally {
			System.out.println("Closing ServerSocket");
		}
	}

	private static int transform(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
