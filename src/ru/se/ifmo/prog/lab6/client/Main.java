package ru.se.ifmo.prog.lab6.client;

import ru.se.ifmo.prog.lab6.client.classes.*;
import ru.se.ifmo.prog.lab6.commands.*;
import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		if (args.length != 0) {
			throw new IllegalArgumentException("Error! Got " + Integer.valueOf(args.length) + " arguments when 1 required (file name)");
		}
		CommandManager commandmanager = new CommandManager();
		UDPConnector connector = new UDPConnector();
		UDPSender sender = new UDPSender(); 
		Console console = new Console(commandmanager, sender);
		commandmanager.createCommand("help", new Help());
		commandmanager.createCommand("info", new Info());
		commandmanager.createCommand("show", new Show());
		/*
		commandmanager.createCommand("add", new Add(commandmanager, console, collection));
		commandmanager.createCommand("update", new UpdateID(commandmanager, console, collection));
		commandmanager.createCommand("remove_by_id", new RemoveID(commandmanager, console, collection));
		commandmanager.createCommand("clear", new Clear(commandmanager, console, collection));
		commandmanager.createCommand("save", new Save(commandmanager, console, collection));
		*/
		commandmanager.createCommand("execute_script", new ExecuteScript());
		commandmanager.createCommand("exit", new Exit());
		/*
		commandmanager.createCommand("remove_at", new RemoveIndex(commandmanager, console, collection));	
		commandmanager.createCommand("sort", new Sort(commandmanager, console, collection));
		*/
		commandmanager.createCommand("history", new History());
		/*
		commandmanager.createCommand("sum_of_age", new SumOfAge(commandmanager, console, collection));	
		commandmanager.createCommand("print_field_ascending_character", new Ascending(commandmanager, console, collection));
		commandmanager.createCommand("print_field_descending_character", new Descending(commandmanager, console, collection));
		*/
		console.start(connector);
	}
}
