package ru.se.ifmo.prog.lab6.cores;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import ru.se.ifmo.prog.lab6.commands.*;
import ru.se.ifmo.prog.lab6.exceptions.*;

public class ScriptReader {
	public static LinkedList<CommandShallow> readCommands(String filename, CommandManager commandmanager) {
		FileInputStream inputStream;
		InputStreamReader reader;
		LinkedList<CommandShallow> shallows = new LinkedList<CommandShallow>();
		try {
			inputStream = new FileInputStream(filename);
			reader = new InputStreamReader(inputStream);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return shallows;
		}
		if (inputStream != null)
		{
			int temp;
			String line = "";
			String[] parameters = new String[0];
			try {
				int parametersptr = -1;
				CommandShallow shallow = new CommandShallow();
				while ((temp = reader.read()) != -1) {
					if ((char)temp != '\n') {
						line += (char)temp;
					}
					else {
						if (parametersptr == -1) {
							String[] com = line.split(" ");
							line = "";
						/*
						if (commandsStack.size() == 0) {
							stacksize = 0;
							com = scanner.nextLine().split(" ");
						}
						else {
							this.println(commandsStack.peek());
							com = commandsStack.removeFirst().split(" ");
						}
						*/
							if (com.length > 0) {
								try {
									Command command = commandmanager.getCommand(com[0]);
									if (command.getParameterAdvices() != null) {
										parametersptr = 0;
										parameters = new String[command.getParameterAdvices().length];
									}
									shallow = new CommandShallow(command, com);
									//Случай add или update
									shallows.addLast(shallow);
								}
								catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}
						else {
							parameters[parametersptr] = line;
							line = "";
							parametersptr++;
							if (parametersptr == parameters.length) {
								parametersptr = -1;
								try {
									shallow.setDragon(parameters);
								}
								catch (ConvertationException e) {
									System.out.println(e.getMessage());
								}
							}
						}
					}
				}
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return shallows;
		}
		return shallows;
	}
}
