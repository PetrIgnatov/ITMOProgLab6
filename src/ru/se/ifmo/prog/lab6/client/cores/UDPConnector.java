package ru.se.ifmo.prog.lab6.client.cores;

import java.nio.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.channels.*;

public class UDPConnector {
	private DatagramChannel datagramChannel;
	private SocketAddress address;
	private InetAddress host;

	public void connect(String hostname, int port) {
		try {
			datagramChannel = DatagramChannel.open();
			host = InetAddress.getByName(hostname);
			address = new InetSocketAddress(host, port);
		}
		catch (UnknownHostException e) {
			System.out.println("ERROR! Unknown host!");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public DatagramChannel getDatagramChannel() {
		return datagramChannel;
	}

	public SocketAddress getAddress() {
		return address;
	}

	public InetAddress getHostAddress() {
		return host;
	}
}
