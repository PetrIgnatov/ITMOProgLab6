package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Save extends Command {
	public Save() {
		super("save", "сохранить коллекцию в файл", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		collectiondata.save();
		return new Response(new String[0]);
	}
}
