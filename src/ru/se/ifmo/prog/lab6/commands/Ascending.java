package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class Ascending extends Command {
	public Ascending() {
		super("print_field_ascending_character", "вывести значения поля character всех элементов в порядке возрастания", 1);
	}
	@Override
	public void execute(String[] args, Console console, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		for (int i = 0; i < collectiondata.getDragons().size(); ++i) {
			console.println(collectiondata.getDragons().get(i).getCharacter().toString());
		}
	}
}
