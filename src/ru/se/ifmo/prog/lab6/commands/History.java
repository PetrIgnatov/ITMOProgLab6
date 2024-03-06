package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class History extends Command {
	public History() {
		super("history", "вывести последние 5 команд (без их аргументов)", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		console.printHistory();
		return new Response(new String[0]);
	}
}
