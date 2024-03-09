package ru.se.ifmo.prog.lab6.server;
import ru.se.ifmo.prog.lab6.classes.*;
import ru.se.ifmo.prog.lab6.commands.*;
import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;
import java.util.*;
import ru.se.ifmo.prog.lab6.exceptions.*;
import java.util.logging.*;

public class Main {
	public static void main(String[] args) throws InputArgumentException {
		if (args.length != 1) {
			throw new InputArgumentException("Error! Got " + Integer.valueOf(args.length) + " arguments when 1 required (file name)");
		}
		Logger logger = Logger.getLogger(Main.class.getName());
		logger.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);
		CollectionData collection = new CollectionData(args[0]);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			logger.info("Сохраняем коллекцию");
			collection.save();
			}));
		CommandManager commandmanager = new CommandManager();
		/*
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
		*/
		String[] comnames = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script", "exit", "remove_at", "sort", "history", "sum_of_age", "print_field_ascending_character", "print_field_descending_character"};
		Command[] coms = {new Help(), new Info(), new Show(), new Add(), new UpdateID(), new RemoveID(), new Clear(), new Save(), new ExecuteScript(), new Exit(), new RemoveIndex(), new Sort(), new History(), new SumOfAge(), new Ascending(), new Descending()};
		for (int i = 0; i < coms.length; ++i)
		{
			try {
				commandmanager.createCommand(comnames[i], coms[i]);
			}
			catch (CommandIOException e) {
				logger.severe(e.getMessage());
			}
		}
		logger.fine("CommandManager Initialized!");
		UDPConnector connector = new UDPConnector();
		connector.Connect(6789, logger);
		UDPSender sender = new UDPSender(connector.getDatagramSocket()); 
		UDPReader reader = new UDPReader(connector.getDatagramSocket(), collection, commandmanager, sender);
		reader.start(logger);
	}
}
