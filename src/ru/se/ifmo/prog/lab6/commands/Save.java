package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class Save extends Command {
	public Save(CommandManager commandmanager, Console console, CollectionData collectiondata) {
		super("save", "сохранить коллекцию в файл", 1, commandmanager, console, collectiondata);
	}
	@Override
	public void execute(String[] args) {
		super.check(args.length);
		collectiondata.save();
	}
}
