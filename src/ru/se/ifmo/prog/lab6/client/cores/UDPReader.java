package ru.se.ifmo.prog.lab6.client.cores;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;
import java.nio.channels.*;
import ru.se.ifmo.prog.lab6.commands.*;

public class UDPReader {
	private DatagramChannel datagramChannel;
	private ByteBuffer buffer;
	private byte[] arr;
	public UDPReader()
	{
		this.datagramChannel = null;
		arr = new byte[10000];
		buffer = ByteBuffer.wrap(arr);
	}

	public UDPReader(DatagramChannel datagramChannel) {
		this.datagramChannel = datagramChannel;
		arr = new byte[10000];
		buffer = ByteBuffer.wrap(arr);
	}

	public Response getResponse() {
		try {
			arr = new byte[10000];
			buffer = ByteBuffer.wrap(arr);
			buffer.clear();
			datagramChannel.receive(buffer);
			System.out.println("Packet Received!");
			ByteArrayInputStream bis = new ByteArrayInputStream(arr);
			ObjectInput in = new ObjectInputStream(bis);
			Response response = (Response)in.readObject();
			for (String s: response.getMessage()) {
				System.out.println(s);
			}
			return response;
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			return new Response(new String[0]);
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return new Response(new String[0]);
		}
	}
}
