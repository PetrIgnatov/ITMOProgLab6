package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class Clear extends Command {
	public Clear() {
		super("clear", "очистить коллекцию", 1);
	}
	@Override
	public void execute(String[] args, Console console, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		collectiondata.clear();
	}
}
