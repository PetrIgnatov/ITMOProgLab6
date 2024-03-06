package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;

public class Add extends Command {
	public Add() {
		super("add {element}", "добавить новый элемент в коллекцию", 1, new String[]{"Имя дракона: ", "Координата X: ", "Координата Y: ", "Возраст: ", "Цвет (доступные варианты - GREEN, YELLOW, ORANGE, WHITE): ", "Тип дракона (доступные варианты - WATER, UNDERGROUND, AIR): ", "Характер дракона (доступные варианты - EVIL, GOOD, CHAOTIC, CHAOTIC_EVIL, FICKLE): ", "Глубина пещеры: ", "Количество сокровищ в пещере: "});
	}
	@Override
	public Response execute(String[] args, String[] params, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		collectiondata.add(params);
		return new Response(new String[0]);
	}
}

