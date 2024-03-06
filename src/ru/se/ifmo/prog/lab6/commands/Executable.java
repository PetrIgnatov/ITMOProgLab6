package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public interface Executable {
	public String getName();
	public String getDescription();
	public Response execute(String[] args, String[] parameters, CommandManager commandmanager, CollectionData collectiondata);
}
