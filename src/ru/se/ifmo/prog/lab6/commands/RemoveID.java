package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class RemoveID extends Command {
	public RemoveID() {
		super("remove_by_id id", "удалить элемент из коллекции по его id", 2);
	}
	@Override
	public void execute(String[] args, Console console, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		try {
			Integer.parseInt(args[1]);
		}
		catch (Exception e) {
			System.out.println("Error! Argument is not a number");
			return;
		}
		collectiondata.remove(Integer.parseInt(args[1]));
	}
}
