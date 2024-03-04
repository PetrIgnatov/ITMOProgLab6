package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class Info extends Command {
	public Info(CommandManager commandmanager, Console console) {
		super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", 1, commandmanager, console);
	}
	@Override
	public void execute(String[] args) {
		super.check(args.length);
//		console.printInfo();
	}
}
