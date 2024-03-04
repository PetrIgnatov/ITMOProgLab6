package ru.se.ifmo.prog.lab6.server.commands;

import ru.se.ifmo.prog.lab6.server.cores.*;

public class Exit extends Command {
	public Exit(CommandManager commandmanager, Console console) {
		super("exit", "завершить программу (без сохранения в файл)", 1, commandmanager, console);
	}
	@Override
	public void execute(String[] args) {
		super.check(args.length);
		console.stop();	
	}
}
