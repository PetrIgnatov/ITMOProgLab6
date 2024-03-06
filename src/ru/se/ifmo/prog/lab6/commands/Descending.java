package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Descending extends Command {
	public Descending() {
		super("print_field_descending_character", "вывести значения поля character всех элементов в порядке убывания", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		String[] response = new String[collectiondata.getDragons().size()];
		for (int i = collectiondata.getDragons().size() - 1; i >= 0; i--) {
			response[i] = collectiondata.getDragons().get(i).getCharacter().toString();
		}
		return new Response(response);
	}
}
