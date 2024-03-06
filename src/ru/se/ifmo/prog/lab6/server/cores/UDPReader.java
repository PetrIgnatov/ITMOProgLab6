package ru.se.ifmo.prog.lab6.server.cores;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.awt.event.*;
import java.util.Iterator;
import ru.se.ifmo.prog.lab6.commands.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class UDPReader {
	private DatagramSocket datagramSocket;
	private boolean active;
	private ByteBuffer buffer;
	private byte arr[];
	private DatagramPacket datagramPacket;
	private CollectionData collection;
	private CommandManager commandmanager;
	private UDPSender sender;

	public UDPReader(DatagramSocket datagramSocket, CollectionData collection, CommandManager commandmanager, UDPSender sender) {
		this.active = true;
		this.datagramSocket = datagramSocket;
		this.buffer = ByteBuffer.allocate(10000);
		this.arr = new byte[10000];
		this.collection = collection;
		this.commandmanager = commandmanager;
		this.sender = sender;
	}
	
	public void start() {
		while (this.active) {
			readCommand();
		}
	}

	private void readCommand() {
		try {
			datagramPacket = new DatagramPacket(arr, arr.length);
			datagramSocket.receive(datagramPacket);
			System.out.println("Packet Received!");
			ByteArrayInputStream bis = new ByteArrayInputStream(datagramPacket.getData());
			ObjectInput in = new ObjectInputStream(bis);
			CommandShallow shallow = (CommandShallow)in.readObject();
			System.out.println(shallow.getCommand().getName());
			for (String s : shallow.getParameters()) {
				System.out.println(s);
			}
			Response response = shallow.getCommand().execute(shallow.getArguments(), shallow.getParameters(), null, commandmanager, collection);
			sender.send(response, datagramPacket.getAddress(), datagramPacket.getPort());
			//showMessage(datagramPacket);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showMessage(DatagramPacket packet) {
		System.out.println(packet.getAddress().toString());
		System.out.println(new String(packet.getData(),0,packet.getLength()));
	}
}
