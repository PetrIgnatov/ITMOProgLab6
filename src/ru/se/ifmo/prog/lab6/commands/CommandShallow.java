package ru.se.ifmo.prog.lab6.commands;

import java.io.Serializable;
import java.net.*;

public class CommandShallow implements Serializable {
	private Command command;
	private String[] args;
	private String[] params;
	
	public CommandShallow(Command command, String[] args) {
		this.command = command;
		this.args = args;
		if (command.getParameterAdvices() != null) {	
			this.params = new String[this.command.getParameterAdvices().length];
		}
		else {
			this.params = new String[0];
		}
		this.command.check(args.length);
	}

	public void setParameters(String[] params) {
		this.params = params;
	}

	public Command getCommand() {
		return command;
	}

	public String[] getArguments() {
		return args;
	}

	public String[] getParameters() {
		return params;
	}
}
