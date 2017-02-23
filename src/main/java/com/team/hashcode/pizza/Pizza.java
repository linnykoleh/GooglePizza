package com.team.hashcode.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1. Save slice only after checking valid slice, what I mean :    #
 *           - size <= maxCellsPerSlice                            #
 *           - not cover already defined slices                    #
 *           - slice contain minEachIngredient per slice           #
 * 2. Mark slice's coordinates in matrixCopy as number of slice in order to avoid covering #
 * 3. Keep in mind even and odd numbers in slice
 *
 *            (# it's already done)
 *
 * */
public class Pizza {

    private char[][] matrix;
    private char[][] matrixCopy;
    private int maxCellsPerSlice;
	private int minEachIngredient;
	private final List<Slice> slices = new ArrayList<>();

    private int rows;
    private int columns;

    public Pizza(char[][] matrix, int maxCellsPerSlice, int minEachIngredient) {
        this.matrix = matrix;
        this.matrixCopy = matrix;
        this.maxCellsPerSlice = maxCellsPerSlice;
		this.minEachIngredient = minEachIngredient;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }

	public void cutPizza(){
		cutPizza(0, 0);
	}

    private void cutPizza(int r, int c){
        for(; c < columns; c++) {
            for(; r < rows; r++){
                if(isCorrectSlice(c, r)) {
					fillSliceAndMatrixCopy(c, r);
                }else{
					final int[] bestPositions = findBestPositions();
					fillSliceAndMatrixCopyByBestPositions(bestPositions);
				}
            }
        }
	}

	private void fillSliceAndMatrixCopyByBestPositions(int[] bestPositions){
		int r = bestPositions[0];
		int c = bestPositions[1];
		int rows = r + 1;
		if(rows == this.rows){             // then it bottom
			for(; c < columns; c++) {
				if(isCorrectSliceByColumns(c, r)){
					fillSliceAndMatrixCopyByColumns(c, r);
				}
			}
		}
	}

	private void fillSliceAndMatrixCopyByColumns(int c, int r){
		final Slice slice = new Slice(maxCellsPerSlice);
		for(int c1 = c; c1 < columns; c1++) {
			if(slice.getArraySize() < maxCellsPerSlice) {
				slice.addIngredient(matrix[r][c1], r, c1);
				matrixCopy[r][c1] = '*';
			}else{
				break;
			}
		}
		slices.add(slice);
	}

	private boolean isCorrectSliceByColumns(int c, int r){
		int sliceSize = 1;
		boolean isEnoughIngredient = false;
		int[] array = new int[2];
		for(; c < columns; c++) {
			char ingredient = matrix[r][c];
			if(sliceSize <= maxCellsPerSlice) {
				if(!isEnoughIngredient && isEnoughMinEachIngredient(array, ingredient)){
					isEnoughIngredient = true;
				}
				if(sliceSize >= maxCellsPerSlice && !isEnoughIngredient)
					return false;
				if('*' == matrixCopy[r][c])
					return false;
				sliceSize++;
			}else {
				return true;
			}
		}
		return true;
	}

	private int[] findBestPositions(){
    	int [] position = new int[2];
		for(int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				if('*' != matrixCopy[r][c]){
					position[0] = r;
					position[1] = c;
					return position;
				}
			}
		}
		return position;
	}

	private void fillSliceAndMatrixCopy(int c, int r){
		for(int c1 = c; c1 < columns; ) {
			final Slice slice = new Slice(maxCellsPerSlice);
			for(int r1 = r; r1 < rows; r1++) {
				if(slice.getArraySize() < maxCellsPerSlice) {
					slice.addIngredient(matrix[r1][c1], r1, c1);
					matrixCopy[r1][c1] = '*';
				}else{
					c1++;
					slices.add(slice);
					break;
				}
			}
		}
	}

	private boolean isCorrectSlice(int c, int r){
		int sliceSize = 0;
		boolean isEnoughIngredient = false;
		for(; c < columns; c++) {
			if(r < matrix.length - 1 && c < matrix[0].length - 1) {
				int[] array = new int[2];
				for (; r < rows; r++) {
					char ingredient = matrix[r][c];
					if (sliceSize < maxCellsPerSlice) {
						if(!isEnoughIngredient && isEnoughMinEachIngredient(array, ingredient)){
							isEnoughIngredient = true;
						}
						if (sliceSize >= maxCellsPerSlice && !isEnoughIngredient)
							return false;
						if ('*' == matrixCopy[r][c])
							return false;
						sliceSize++;
					} else {
						return true;
					}
				}
			}else{
				break;
			}
		}
		return false;
	}

	private boolean isEnoughMinEachIngredient(int[] array, char ingredient){
		final int tomats = array[0];
		final int mushrooms = array[1];
		if('T' == ingredient){
			array[0] = tomats + 1;
		}else if('M' == ingredient){
			array[1] = mushrooms + 1;
		}
		return array[0] >= minEachIngredient && array[1] >= minEachIngredient;
	}

    public List<Slice> getSlices(){
        return slices;
    }

	public char[][] getMatrixCopy() {
		return matrixCopy;
	}
}
