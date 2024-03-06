package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Exit extends Command {
	public Exit() {
		super("exit", "завершить программу (без сохранения в файл)", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		console.stop();	
		return new Response(new String[0]);
	}
}
