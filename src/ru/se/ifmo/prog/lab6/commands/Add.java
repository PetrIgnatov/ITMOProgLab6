package ru.se.ifmo.prog.lab6.commands;

import ru.se.ifmo.prog.lab6.cores.*;
import ru.se.ifmo.prog.lab6.classes.*;

public class Add extends Command {
	public Add() {
		super("add {element}", "добавить новый элемент в коллекцию", 1, new String[]{"Имя дракона: ", "Координата X: ", "Координата Y: ", "Возраст: ", "Цвет (доступные варианты - GREEN, YELLOW, ORANGE, WHITE): ", "Тип дракона (доступные варианты - WATER, UNDERGROUND, AIR): ", "Характер дракона (доступные варианты - EVIL, GOOD, CHAOTIC, CHAOTIC_EVIL, FICKLE): ", "Глубина пещеры: ", "Количество сокровищ в пещере: "});
	}
	@Override
	public Response execute(String[] args, Integer stacksize, Dragon dragon, CommandManager commandmanager, CollectionData collectiondata) {
		super.check(args.length);
		if (stacksize > 10000) {
      return new Response(new String[0]);
    }
		collectiondata.add(dragon);
		return new Response(new String[0]);
	}
}

