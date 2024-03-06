package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class SumOfAge extends Command {
	public SumOfAge() {
		super("sum_of_age", "вывести сумму значений поля age для всех элементов коллекции", 1);
	}
	@Override
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		String[] response = {Integer.toString(collectiondata.sumAge())};
		return new Response(response);
	}
}
