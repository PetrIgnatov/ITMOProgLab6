package ru.se.ifmo.prog.lab6.commands;

import java.io.*;
import java.net.*;
import ru.se.ifmo.prog.lab6.classes.*;
import ru.se.ifmo.prog.lab6.exceptions.*;

public class CommandShallow implements Serializable {
	private Command command;
	private String[] args;
	private Dragon dragon;
	private CommandShallow[] commands;
	
	public CommandShallow() {
		this.command = null;
		this.args = null;
		this.dragon = null;
	}

	public CommandShallow(Command command, String[] args) {
		this.command = command;
		this.args = args;
		this.dragon = null;
		this.command.check(args.length);
	}

	public Command getCommand() {
		return command;
	}

	public String[] getArguments() {
		return args;
	}
	
	public void setCommands(CommandShallow[] commands) {
		this.commands = commands;
	}

	public CommandShallow[] getCommands() {
		return this.commands;
	}

	public void setDragon(String[] splitted) throws ConvertationException {
		Color col = null;
		switch(splitted[4]) {
			case "GREEN":
				col = Color.GREEN;
				break;
			case "YELLOW":
				col = Color.YELLOW;
				break;
			case "ORANGE":
				col = Color.ORANGE;
				break;
			case "WHITE":
				col = Color.WHITE;
				break;
			case "":
				col = null;
				break;
			default:
				throw new ConvertationException("Error! Unknown color \"" + splitted[6] + "\"");
		}
		DragonType type = null;
		switch(splitted[5]) {
			case "WATER":
				type = DragonType.WATER;
				break;
			case "UNDERGROUND":
				type = DragonType.UNDERGROUND;
				break;
			case "AIR":
				type = DragonType.AIR;
				break;
			case "":
				type = null;
				break;
			default:
				throw new ConvertationException("Error! Unknown type \"" + splitted[7] + "\"");
		}
		DragonCharacter character = null;
		switch(splitted[6]) {
			case "EVIL":
				character = DragonCharacter.EVIL;
				break;
			case "GOOD":
				character = DragonCharacter.GOOD;
				break;
			case "CHAOTIC":
				character = DragonCharacter.CHAOTIC;
				break;
			case "FICKLE":
				character = DragonCharacter.FICKLE;
				break;
			case "CHAOTIC_EVIL":
				character = DragonCharacter.CHAOTIC_EVIL;
				break;
			case "":
				character = null;
				break;
			default:
				throw new ConvertationException("Error! Unknown character \"" + splitted[8] + "\"");
		}
		this.dragon = new Dragon(
						splitted[0] == "" ? null : splitted[0],
						splitted[1] == "" ? null : Integer.parseInt(splitted[1]),
						splitted[2] == "" ? null : Float.parseFloat(splitted[2]),
						Integer.parseInt(splitted[3]),
						col,type,character,
						splitted[7] == "" ? null : Double.parseDouble(splitted[7]),
						splitted[8] == "" ? null : Float.parseFloat(splitted[8]));
	}
	
	public Dragon getDragon() {
	  return dragon;
	}
}
