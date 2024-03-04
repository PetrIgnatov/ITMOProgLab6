package ru.se.ifmo.prog.lab6.server.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;

public class Sort extends Command {
	public Sort(CommandManager commandmanager, Console console, CollectionData collectiondata) {
		super("sort", "отсортировать коллекцию в естественном порядке", 1, commandmanager, console, collectiondata);
	}
	@Override
	public void execute(String[] args) {
		super.check(args.length);
		collectiondata.sort();
	}
}
