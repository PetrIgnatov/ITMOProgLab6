package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Ascending extends Command {
	public Ascending() {
		super("print_field_ascending_character", "вывести значения поля character всех элементов в порядке возрастания", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		String[] response = new String[collectiondata.getDragons().size()];
		for (int i = 0; i < collectiondata.getDragons().size(); ++i) {
			response[i] = collectiondata.getDragons().get(i).getCharacter().toString();
		}
		return new Response(response);
	}
}
