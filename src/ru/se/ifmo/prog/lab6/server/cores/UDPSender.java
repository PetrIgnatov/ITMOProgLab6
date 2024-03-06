package ru.se.ifmo.prog.lab6.server.cores;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.awt.event.*;
import java.util.Iterator;
import ru.se.ifmo.prog.lab6.commands.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class UDPSender {
	private DatagramSocket datagramSocket;
	
	public UDPSender(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}

	public void send(Response response, InetAddress address, int port) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(response);
			byte[] arr = baos.toByteArray();
			DatagramPacket datagramPacket = new DatagramPacket(arr, arr.length, address, port);
			datagramSocket.send(datagramPacket);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
