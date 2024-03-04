package ru.se.ifmo.prog.lab6.client.cores;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;
import java.nio.channels.*;

public class UDPSender {
	private DatagramChannel datagramChannel;
	private ByteBuffer buffer;
	private SocketAddress hostAddress;

	public UDPSender()
	{
		this.datagramChannel = null;
		this.hostAddress = null;
	}

	public UDPSender(DatagramChannel datagramChannel, SocketAddress hostAddress) {
		this.datagramChannel = datagramChannel;
		this.hostAddress = hostAddress;
		try {
			send((new String("hello")).getBytes());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void send(byte[] arr) throws IOException {
		buffer = ByteBuffer.wrap(arr);
		System.out.println(arr.length);
		datagramChannel.send(buffer, hostAddress);
		System.out.println("Успешно отправлено, " + arr.length);
	}
}
