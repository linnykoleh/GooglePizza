package com.team.hashcode.pizza;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TeamPizza {

    private static final int NUMBER_PARAM_LINE = 0;

    private static int rows;
    private static int columns;
    private static int minEachIngredient;
    private static int maxCellsPerSlice;

    public static void main(String[] args) throws Exception {
        List<String> strings = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("small.in").toURI()));
        parseParams(strings.get(NUMBER_PARAM_LINE));

        strings = strings.subList(1, strings.size());
        char[][] matrix = createMatrix(strings);

        Pizza pizza = new Pizza(matrix, maxCellsPerSlice);
        pizza.cutPizza();
        List<Slice> slices = pizza.getSlices();

        slices.forEach(System.out::println);

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

    private static char[][] createMatrix(List<String> strings){
        String[] values = new String[strings.size()];
        values = strings.toArray(values);
        final char[][] matrix = new char[rows][columns];
        for(int i = 0; i < matrix.length; i++){
            final char[] chars = values[i].toCharArray();
            for(int j = 0; j < chars.length; j++){
                matrix[i][j] = chars[j];
            }
        }
        return matrix;
    }
}
