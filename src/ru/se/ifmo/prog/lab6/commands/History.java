package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.classes.*;

public class History extends Command {
	public History() {
		super("history", "вывести последние 5 команд (без их аргументов)", 1);
	}
	@Override
	public Response execute(String[] args, Integer stacksize, Dragon dragon, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		if (stacksize > 10000) {
      return new Response(new String[0]);
    }
		//console.printHistory();
		return new Response(new String[0]);
	}
}
