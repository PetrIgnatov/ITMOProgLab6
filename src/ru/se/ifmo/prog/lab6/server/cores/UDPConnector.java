package ru.se.ifmo.prog.lab6.server.cores;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.awt.event.*;

public class UDPConnector {
	private DatagramChannel datagramchannel;
	private InetSocketAddress address;
	private Selector selector;
	
	public boolean Connect(int port) {
		try {
			selector = Selector.open();
			address = new InetSocketAddress(port);
			datagramchannel = DatagramChannel.open();
			datagramchannel.bind(address);
			datagramchannel.configureBlocking(false);
			datagramchannel.register(selector, SelectionKey.OP_READ);
			System.out.println("Сервер запущен на IP " + address.getAddress().toString() + " с портом " + port);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public DatagramChannel getDatagramChannel() {
		return datagramchannel;
	}
	
	public InetSocketAddress getAddress() {
		return address;
	}
	
	public Selector getSelector() {
		return selector;
	}
}
