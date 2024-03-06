package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Sort extends Command {
	public Sort() {
		super("sort", "отсортировать коллекцию в естественном порядке", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		collectiondata.sort();
		return new Response(new String[0]);
	}
}
