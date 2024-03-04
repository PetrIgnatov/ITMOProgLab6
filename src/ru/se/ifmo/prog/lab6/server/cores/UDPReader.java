package ru.se.ifmo.prog.lab6.server.cores;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.awt.event.*;
import java.util.Iterator;
import ru.se.ifmo.prog.lab6.server.commands.*;

public class UDPReader {
	private DatagramSocket datagramSocket;
	private InetAddress host;
	private Selector selector;
	private boolean active;
	private ByteBuffer buffer;
	private byte arr[];
	private DatagramPacket datagramPacket;

	public UDPReader(DatagramSocket datagramSocket, Selector selector) {
		this.active = true;
		this.datagramSocket = datagramSocket;
		this.selector = selector;
		this.buffer = ByteBuffer.allocate(1024);
		arr = new byte[1024];
	}
	
	public void start() {
		while (this.active) {
			checkSelector();
		}
	}

	private void checkSelector() {
		try {
			selector.select();
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				keyIterator.remove();
				if (key.isReadable()) {
					System.out.println("Found key!");
					readCommand(key);	
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void readCommand(SelectionKey key) throws IOException {
		arr = new byte[1024];
		datagramPacket = new DatagramPacket(arr, arr.length);
		datagramSocket.receive(datagramPacket);
		System.out.println("Packet Received!");
		showMessage(datagramPacket);
	}

	private void readCommand() {
		try {
			datagramPacket = new DatagramPacket(arr, arr.length);
			datagramSocket.receive(datagramPacket);
			System.out.println("Packet Received!");
			showMessage(datagramPacket);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showMessage(DatagramPacket packet) {
		System.out.println(new String(packet.getData(),0,packet.getLength()));
	}
}
