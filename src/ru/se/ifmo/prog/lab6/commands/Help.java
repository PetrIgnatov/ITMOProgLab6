package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Help extends Command {
	public Help() {
		super("help", "вывести справку по доступным командам", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		String[] response = {commandmanager.getCommands()};
		return new Response(response);
	}
}
