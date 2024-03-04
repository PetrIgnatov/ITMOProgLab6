package ru.se.ifmo.prog.lab6.client.cores;

import java.util.*;
import java.io.*;
import java.awt.event.*;
import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.commands.*;
import ru.se.ifmo.prog.lab6.client.classes.*;

public class Console implements Serializable {
	private Scanner scanner;
	private boolean active;
	private CommandManager commandmanager;
	private LinkedList<Command> history;
	private LinkedList<String> commandsStack;
	private int stacksize;
	private UDPSender sender;

	public Console(CommandManager commandmanager, UDPSender sender)
	{
		this.scanner = new Scanner(System.in);
		this.active = true;
		this.history = new LinkedList<Command>();
		this.commandmanager = commandmanager;
		this.commandsStack = new LinkedList<String>();
		this.stacksize = 0;
		this.sender = sender;
	}
	
	public void start(UDPConnector connector) {
		this.print("Введите IP хоста: ");
		String host = this.scanner.nextLine();
		this.print("Введите порт хоста: ");
		int port = this.scanner.nextInt();
		connector.connect(host, port);
		sender = new UDPSender(connector.getDatagramChannel(), connector.getAddress());
		while (this.active) {
			readCommand();
		}
	}

	public void print(String line)
	{
		if (line.equals(null))
		{
			return;
		}
		System.out.print(line);
	}

	public void println(String line)
	{
		if (line.equals(null))
		{
			System.out.println();
			return;
		}
		System.out.println(line);
	}

	public void readCommand() {
		String[] com; 
		if (commandsStack.size() == 0) {
			stacksize = 0;
			com = scanner.nextLine().split(" ");
		}
		else {
			this.println(commandsStack.peek());
			com = commandsStack.removeFirst().split(" ");
		}
		if (com.length > 0) {
			try {
				Command command = commandmanager.getCommand(com[0]);
				if (command != null) {
					history.push(command);
					while (history.size() > 5) {
						history.pollLast();
					}
				}
				if (command.getName() != "exit") {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeObject(command);
					byte[] arr = baos.toByteArray();
					sender.send(arr);
				}
				else {
					command.execute(com);
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public String readln() {
		if (commandsStack.size() == 0) {
			stacksize = 0;
			return scanner.nextLine();
		}
		else {
			this.println(commandsStack.peek());
			return commandsStack.removeFirst();
		}
	}
	
	public void stop() {
		active = false;
	}
	/*
	public void printCollection() {
		for (Dragon dragon : collectiondata.getDragons()) {
			println(dragon.toString());
		}
	}
	
	public void printInfo() {
		println(collectiondata.toString());
	}

	public void printHistory() {
		for (int i = history.size()-1; i >= 0; i--) {
			println(history.get(i).getName());
		}	
	}
	*/
	public void readScript(String filename) {
		if (stacksize < 10000)
		{
			try {	
				ScriptReader scriptreader = new ScriptReader(filename);
				String inpfile = scriptreader.readFile();
				if (inpfile != null)
				{
					String[] commands = inpfile.split("\n");
					for (int i = commands.length-1; i >= 0; i--) {
						++stacksize;
						commandsStack.offerFirst(commands[i]);
					}
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else
		{
			System.out.println("Error! Too many commands!");
		}
	}
}

