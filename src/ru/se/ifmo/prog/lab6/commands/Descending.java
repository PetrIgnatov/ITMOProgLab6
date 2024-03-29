package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.classes.*;
import java.util.Comparator;

public class Descending extends Command {
	public Descending() {
		super("print_field_descending_character", "вывести значения поля character всех элементов в порядке убывания", 1);
	}
	@Override
	public Response execute(String[] args, Integer stacksize, Dragon dragon, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		if (stacksize > 10000) {
      return new Response(new String[0]);
    }		
    		/*
		String[] response = new String[collectiondata.getDragons().size()];
		for (int i = collectiondata.getDragons().size() - 1; i >= 0; i--) {
			response[i] = collectiondata.getDragons().get(i).getCharacter().toString();
		}
		*/
		String[] response = collectiondata.getDragons().stream().map(Dragon::getCharacter).sorted(new CharComparator()).map(ch -> ch.toString()).toArray(String[]::new);
		return new Response(response);
	}
}

class CharComparator implements Comparator<DragonCharacter> {
	public int compare(DragonCharacter a, DragonCharacter b) {
		return b.compareTo(a);
	}
}
