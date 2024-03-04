package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class Descending extends Command {
	public Descending() {
		super("print_field_descending_character", "вывести значения поля character всех элементов в порядке убывания", 1);
	}
	@Override
	public void execute(String[] args, Console console, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		for (int i = collectiondata.getDragons().size() - 1; i >= 0; i--) {
			console.println(collectiondata.getDragons().get(i).getCharacter().toString());
		}
	}
}
