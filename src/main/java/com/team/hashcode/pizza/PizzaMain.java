package com.team.hashcode.pizza;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PizzaMain {

	private static final int NUMBER_PARAM_LINE = 0;

	private static int rows;
	private static int columns;
	private static int minEachIngredient;
	private static int maxCellsPerSlice;

	private static int tomato;
	private static int mushroom;
	private static int numberOfSlices;
	private static char[][] matrix;

	public static void main(String[] args) throws Exception {
		List<String> strings = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("small.in").toURI()));
		parseParams(strings.get(NUMBER_PARAM_LINE));
		defineNumberOfTomatoAndMushrooms(strings);
		defineNumberOfSlices();
		strings = strings.subList(1, strings.size());
		createMatrix(strings);
		cutPizza();
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

	private static void defineNumberOfTomatoAndMushrooms(final List<String> strings){
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
	}

	private static void defineNumberOfSlices(){
		numberOfSlices = rows * columns / maxCellsPerSlice;
		if(numberOfSlices % 10 > 0){
			++numberOfSlices;
		}
	}

	private static void createMatrix(List<String> strings){
		String[] values = new String[strings.size()];
		values = strings.toArray(values);
		matrix = new char[rows][columns];
		for(int i = 0; i < matrix.length; i++){
			final char[] chars = values[i].toCharArray();
			for(int j = 0; j < chars.length; j++){
				matrix[i][j] = chars[j];
			}
		}
	}

	private static void cutPizza(){
		List<char[][]> result = new ArrayList<>();
		char[][] slice = new char[1][maxCellsPerSlice];
		int sliceSize = 0;
		char[][] copyMatrix = matrix;

		int i = 0;
		int j = 0;
		boolean isWasAnotherIngredient = false;
		for(; i < matrix.length;){
			char searchIngredient = matrix[i][j] == 'M' ? 'T' : 'M';
			for(; j < matrix[i].length; ) {
				if(sliceSize < maxCellsPerSlice) {
					if(searchIngredient == matrix[i][j]){
						isWasAnotherIngredient = true;
					}
					slice[0][sliceSize] = matrix[i][j];
					if(sliceSize >= maxCellsPerSlice && !isWasAnotherIngredient){
						slice = new char[1][maxCellsPerSlice];
					}
					sliceSize++;
					i++;
				}else{
					i = 0;
					j++;
					sliceSize = 0;
					result.add(slice);
					slice = new char[1][maxCellsPerSlice];
				}
			}

		}
	}

	public boolean isRightSlice(int i, int j){
		char[][] tempSlice = new char[1][maxCellsPerSlice];
		for(;i < matrix.length; i++){
			for(;j < matrix[i].length; j++) {

			}

		}
		return false;
	}



}
