package ru.se.ifmo.prog.lab6.client.cores;

import java.util.*;
import java.io.*;
import java.net.*;
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
	private UDPReader reader;

	public Console(CommandManager commandmanager, UDPSender sender, UDPReader reader)
	{
		this.scanner = new Scanner(System.in);
		this.active = true;
		this.history = new LinkedList<Command>();
		this.commandmanager = commandmanager;
		this.commandsStack = new LinkedList<String>();
		this.stacksize = 0;
		this.sender = sender;
		this.reader = reader;
	}
	
	public void start(UDPConnector connector) {
		this.print("Введите IP хоста: ");
		String host = this.scanner.nextLine();
		this.print("Введите порт хоста: ");
		int port = this.scanner.nextInt();
		connector.connect(host, port);
		sender = new UDPSender(connector.getDatagramChannel(), connector.getAddress());
		reader = new UDPReader(connector.getDatagramChannel());
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
				if (command != null && command.getName().equals("save")) {
					System.out.println("Клиент не может сохранять данные");
					return;
				}
				if (command != null) {
					history.push(command);
					while (history.size() > 5) {
						history.pollLast();
					}
				}
				CommandShallow shallow = new CommandShallow(command, com);
				if (command.getName().equals("add {element}") || command.getName().equals("update id {element}")) {
					String[] advices = command.getParameterAdvices();
					String[] parameters = new String[advices.length];
					for (int i = 0; i < advices.length; ++i) {
						this.print(advices[i]);	
						boolean ok = false;
						while (!ok) {
							parameters[i] = scanner.nextLine();
							ok = true;
							if (parameters[i].split(" ").length > 1 && i != 0) {
								System.out.println("Требуется ввести только одно значение!");
								ok = false;
							}
							if (i != 0) {
								parameters[i] = parameters[i].split(" ")[0];
							}
							if ((i == 0 || i == 1 || i == 2 || i == 3 || i == 7) && (parameters[i].equals(""))) {
								System.out.println("Переменная не может иметь значение null!");
								ok = false;
							}
							if (ok) {
								try {
									switch(i) {
										case 1: {
											int x = Integer.parseInt(parameters[i]);
											if (x <= -32) {
												ok = false;
												System.out.println("X должен быть больше -32");
											} 
										}
										break;
										case 2:
											Float.parseFloat(parameters[i]);
											break;
										case 3: {
											int x = Integer.parseInt(parameters[i]);
											if (x <= 0) {
												ok = false;
												System.out.println("Возраст дракона должен быть больше 0");
											}
										}
										break;
										case 4:
											if (!parameters[i].equals("") && !parameters[i].equals("GREEN") && !parameters[i].equals("YELLOW") && !parameters[i].equals("ORANGE") && !parameters[i].equals("WHITE")) {
												ok = false;
												System.out.println("Введено неверное значение");
											}
										break;
										case 5:
											if (!parameters[i].equals("") && !parameters[i].equals("WATER") && !parameters[i].equals("UNDERGROUND") && !parameters[i].equals("AIR")) {
												ok = false;
												System.out.println("Введено неверное значение");
											}
											break;
										case 6:
											if (!parameters[i].equals("") && !parameters[i].equals("EVIL") && !parameters[i].equals("GOOD") && !parameters[i].equals("CHAOTIC") && !parameters[i].equals("CHAOTIC_EVIL") && !parameters[i].equals("FICKLE")) {
												ok = false;
												System.out.println("Введено неверное значение");
											}
											break;
										case 7:
											Double.parseDouble(parameters[i]);
											break;
										case 8: {
											float x = Float.parseFloat(parameters[i]);
											if (x <= 0) {
												System.out.println("Количество сокровищ должно быть больше 0");
												ok = false;
											}
										}
									}
								}
								catch (Exception e) {
									System.out.println("Введено неверное значение");
									ok = false;
								}
							}
						}
					}
					try {
						shallow.setDragon(parameters);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(shallow);
				byte[] arr = baos.toByteArray();
				sender.send(arr);
				Response response = reader.getResponse();
				for (String s: response.getMessage()) {
					if (s.equals("exit")) {
						this.stop();
						break;
					}
					System.out.println(s);
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (IOException e) {
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
	
	public void printHistory() {
		for (int i = history.size()-1; i >= 0; i--) {
			println(history.get(i).getName());
		}	
	}
}

