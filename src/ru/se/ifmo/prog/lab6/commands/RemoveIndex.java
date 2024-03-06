package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class RemoveIndex extends Command {
	public RemoveIndex() {
		super("remove_at index", "удалить элемент, находящийся в заданной позиции коллекции (index)", 2);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		try {
			Integer.parseInt(args[1]);
		}
		catch (Exception e) {
			System.out.println("Error! Argument is not a number");
			return new Response(new String[0]);
		}
		collectiondata.removeIndex(Integer.parseInt(args[1]));
		return new Response(new String[0]);
	}
}
