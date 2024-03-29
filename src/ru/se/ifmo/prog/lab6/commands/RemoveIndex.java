package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.classes.*;

public class RemoveIndex extends Command {
	public RemoveIndex() {
		super("remove_at index", "удалить элемент, находящийся в заданной позиции коллекции (index)", 2);
	}
	@Override
	public Response execute(String[] args, Integer stacksize, Dragon dragon, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		if (stacksize > 10000) {
      return new Response(new String[0]);
    }
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
