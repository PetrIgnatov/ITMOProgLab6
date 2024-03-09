package ru.se.ifmo.prog.lab6.server.cores;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.awt.event.*;
import java.util.logging.*;

public class UDPConnector {
	private DatagramSocket datagramSocket;
	private InetAddress host;
	
	public boolean Connect(int port, Logger logger) {
		try {
			datagramSocket = new DatagramSocket(port);
			logger.fine("Сервер запущен на порте " + port);
		}
		catch (IOException e) {
			logger.severe(e.getMessage());
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
}
