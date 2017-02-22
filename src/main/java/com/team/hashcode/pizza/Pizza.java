package com.team.hashcode.pizza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

    private char[][] matrix;
    private char[][] matrixCopy;
    private int maxCellsPerSlice;
    final List<Slice> slices = new ArrayList<>();

    private int row;
    private int columns;

    public Pizza(char[][] matrix, int maxCellsPerSlice) {
        this.matrix = matrix;
        this.matrixCopy = matrix;
        this.maxCellsPerSlice = maxCellsPerSlice;
        this.row = matrix.length;
        this.columns = matrix[0].length;
    }

    public void cutPizza(){
        int sliceSize = 0;
        int r = 0;
        int c = 0;
        boolean isWasAnotherIngredient = false;
        for(; c < columns; c++) {

            final Slice slice = new Slice(maxCellsPerSlice);
            char searchIngredient = matrix[r][c] == 'M' ? 'T' : 'M';

            for(; r < row; r++){

                if(sliceSize < maxCellsPerSlice) {
                    if(searchIngredient == matrix[r][c]){
                        isWasAnotherIngredient = true;
                    }
                    slice.addIngredient(matrix[r][c]);
                    if(sliceSize >= maxCellsPerSlice && !isWasAnotherIngredient){
                        slice.clear();
                    }
                    sliceSize++;
                }else{
                    r = 0;
                    sliceSize = 0;
                    slices.add(slice);
                    break;
                }

            }
        }
    }

    public List<Slice> getSlices(){
        return slices;
    }
}
