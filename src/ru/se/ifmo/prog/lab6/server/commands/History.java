package ru.se.ifmo.prog.lab6.server.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;

public class History extends Command {
	public History(CommandManager commandmanager, Console console) {
		super("history", "вывести последние 5 команд (без их аргументов)", 1, commandmanager, console);
	}
	@Override
	public void execute(String[] args) {
		super.check(args.length);
		console.printHistory();
	}
}
