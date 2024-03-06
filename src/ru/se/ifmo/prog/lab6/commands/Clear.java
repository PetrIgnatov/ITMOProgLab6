package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Clear extends Command {
	public Clear() {
		super("clear", "очистить коллекцию", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		collectiondata.clear();
		return new Response(new String[0]);
	}
}
