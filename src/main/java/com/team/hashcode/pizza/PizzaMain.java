package com.team.hashcode.pizza;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PizzaMain {

	private static final int NUMBER_PARAM_LINE = 0;

	private static int rows;
	private static int columns;
	private static int minEachIngredient;
	private static int maxCellsPerSlice;

	private static int numberOfSlices;

	public static void main(String[] args) throws Exception {
		final List<String> strings = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("small.in").toURI()));
		parseParams(strings.get(NUMBER_PARAM_LINE));
		defineNumberOfSlices(strings);

	}

	private static void parseParams(String firstLine){
		final String[] params = firstLine.split(" ");
		if(params.length == 4) {
			rows = Integer.parseInt(params[0]);
			columns = Integer.parseInt(params[1]);
			minEachIngredient = Integer.parseInt(params[2]);
			maxCellsPerSlice = Integer.parseInt(params[3]);
		}else {
			throw new IllegalArgumentException();
		}
	}

	private static void defineNumberOfSlices(final List<String> strings){
		int tomato = 0;
		int mushroom = 0;
		for(String line : strings){
			final char[] chars = line.toCharArray();
			for(char value : chars){
				if('T' == value){
					tomato++;
				}else if('M' == value){
					mushroom++;
				}
			}
		}

		numberOfSlices = Math.min(tomato, mushroom);
	}

}
