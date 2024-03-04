package ru.se.ifmo.prog.lab6.commands;

public interface Executable {
	public String getName();
	public String getDescription();
	public void execute(String[] args);
}
