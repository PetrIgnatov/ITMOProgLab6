package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class SumOfAge extends Command {
	public SumOfAge(CommandManager commandmanager, Console console, CollectionData collectiondata) {
		super("sum_of_age", "вывести сумму значений поля age для всех элементов коллекции", 1, commandmanager, console, collectiondata);
	}
	@Override
	public void execute(String[] args) {
		super.check(args.length);
		console.println(Integer.toString(collectiondata.sumAge()));
	}
}
