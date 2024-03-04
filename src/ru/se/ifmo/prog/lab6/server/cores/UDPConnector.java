package ru.se.ifmo.prog.lab6.server.cores;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.awt.event.*;

public class UDPConnector {
	private DatagramSocket datagramSocket;
	private InetAddress host;
	private Selector selector;
	
	public boolean Connect(int port) {
		try {
			selector = Selector.open();
			datagramSocket = new DatagramSocket(port);
			System.out.println("Сервер запущен на порте " + port);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public DatagramSocket getDatagramSocket() {
		return datagramSocket;
	}
	
	public InetAddress getHostAddress() {
		return host;
	}
	
	public Selector getSelector() {
		return selector;
	}
}
