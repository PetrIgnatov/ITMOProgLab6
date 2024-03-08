package ru.se.ifmo.prog.lab6.client;

import ru.se.ifmo.prog.lab6.classes.*;
import ru.se.ifmo.prog.lab6.commands.*;
import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		if (args.length != 0) {
			throw new IllegalArgumentException("Error! Got " + Integer.valueOf(args.length) + " arguments when 0 required");
		}
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("\nВыключаем клиент");
			}));
		CommandManager commandmanager = new CommandManager();
		UDPConnector connector = new UDPConnector();
		UDPSender sender = new UDPSender(); 
		UDPReader reader = new UDPReader();
		Console console = new Console(commandmanager, sender, reader);
		commandmanager.createCommand("help", new Help());
		commandmanager.createCommand("info", new Info());
		commandmanager.createCommand("show", new Show());
		commandmanager.createCommand("add", new Add());
		commandmanager.createCommand("update", new UpdateID());
		commandmanager.createCommand("remove_by_id", new RemoveID());
		commandmanager.createCommand("clear", new Clear());
		commandmanager.createCommand("save", new Save());
		commandmanager.createCommand("execute_script", new ExecuteScript());
		commandmanager.createCommand("exit", new Exit());
		commandmanager.createCommand("remove_at", new RemoveIndex());	
		commandmanager.createCommand("sort", new Sort());
		commandmanager.createCommand("history", new History());
		commandmanager.createCommand("sum_of_age", new SumOfAge());	
		commandmanager.createCommand("print_field_ascending_character", new Ascending());
		commandmanager.createCommand("print_field_descending_character", new Descending());
		console.start(connector);
	}
}
