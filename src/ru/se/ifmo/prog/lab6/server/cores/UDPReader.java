package ru.se.ifmo.prog.lab6.server.cores;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.awt.event.*;
import java.util.Iterator;
import ru.se.ifmo.prog.lab6.server.commands.*;

public class UDPReader {
	private DatagramChannel datagramchannel;
	private InetSocketAddress address;
	private Selector selector;
	private boolean active;
	private ByteBuffer buffer;
	byte arr[];
	
	public UDPReader(DatagramChannel datagramchannel, InetSocketAddress address, Selector selector) {
		this.active = true;
		this.datagramchannel = datagramchannel;
		this.address = address;
		this.selector = selector;
		this.buffer = ByteBuffer.allocate(1024);
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
					readCommand(key);	
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void readCommand(SelectionKey key) throws IOException {
		DatagramChannel readChannel = (DatagramChannel)key.channel();
		buffer.clear();
		InetSocketAddress clientAddress = (InetSocketAddress)readChannel.receive(buffer);
		buffer.flip();
		showMessage(clientAddress, buffer);
	}

	private void showMessage(InetSocketAddress client, ByteBuffer buffer) {
		System.out.println("Client " + client.getAddress().toString() + " said: " + new String(buffer.array(), 0, buffer.limit()));
	}
}
