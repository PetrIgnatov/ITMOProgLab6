package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class SumOfAge extends Command {
	public SumOfAge() {
		super("sum_of_age", "вывести сумму значений поля age для всех элементов коллекции", 1);
	}
	@Override
	public void execute(String[] args, Console console, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		console.println(Integer.toString(collectiondata.sumAge()));
	}
}
