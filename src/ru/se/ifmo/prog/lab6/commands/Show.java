package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.classes.*;
import java.util.stream.*;

public class Show extends Command {
	public Show() {
		super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 1);
	}
	@Override
	public Response execute(String[] args, Integer stacksize, Dragon dragon, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		if (stacksize > 10000) {
			return new Response(new String[0]);
		}
		//String[] response = {collectiondata.dragonsString()};
		String[] response = collectiondata.getDragons().stream().map(dr -> dr.toString()).toArray(String[]::new);
		return new Response(response);
	}
}
