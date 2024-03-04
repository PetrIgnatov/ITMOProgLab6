package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.server.cores.*;
import ru.se.ifmo.prog.lab6.client.cores.*;

public interface Executable {
	public String getName();
	public String getDescription();
	public void execute(String[] args, Console console, CommandManager commandmanager, CollectionData collectiondata);
}
