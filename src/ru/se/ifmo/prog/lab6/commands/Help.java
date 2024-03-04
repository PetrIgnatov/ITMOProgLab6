package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;
import ru.se.ifmo.prog.lab6.cores.*;

public class Help extends Command {
	public Help(CommandManager commandmanager, Console console) {
		super("help", "вывести справку по доступным командам", 1, commandmanager, console);
	}
	@Override
	public void execute(String[] args) {
		super.check(args.length);
		console.print(commandmanager.getCommands());
	}
}
