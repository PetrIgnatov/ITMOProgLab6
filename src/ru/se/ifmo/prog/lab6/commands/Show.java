package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Show extends Command {
	public Show() {
		super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		String[] response = {collectiondata.dragonsString()};
		return new Response(response);
	}
}
