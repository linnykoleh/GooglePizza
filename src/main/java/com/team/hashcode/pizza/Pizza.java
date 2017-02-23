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
					createSlices(c, r);
                }else{
					final BestPosition bestPosition = new BestPosition(rows, columns, matrixCopy);
					createSlicesByBestPositions(bestPosition.getBestRow(), bestPosition.getBestColumn());
				}
            }
        }
	}

	private void createSlices(int c, int r){
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

	private void createSlicesByBestPositions(int bestRow, int bestColumn){
		int rows = bestRow + 1;
		if(rows == this.rows){             // then it bottom
			for(; bestColumn < columns; bestColumn++) {
				if(isCorrectSliceByColumns(bestColumn, bestRow)){
					fillSliceAndMatrixCopyByColumns(bestColumn, bestRow);
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

	private boolean isCorrectSlice(int c, int r){
		int sliceSize = 0;
		boolean isEnoughIngredient = false;
		final MinEachIngredient eachIngredient = new MinEachIngredient(minEachIngredient);
		for(; c < columns; c++) {
			if(r < matrix.length - 1 && c < matrix[0].length - 1) {
				for (; r < rows; r++) {
					char ingredient = matrix[r][c];
					if (sliceSize < maxCellsPerSlice) {
						if(eachIngredient.isEnough(ingredient)){
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

	private boolean isCorrectSliceByColumns(int c, int r){
		int sliceSize = 1;
		boolean isEnoughIngredient = false;
		final MinEachIngredient eachIngredient = new MinEachIngredient(minEachIngredient);
		for(; c < columns; c++) {
			char ingredient = matrix[r][c];
			if(sliceSize <= maxCellsPerSlice) {
				if(eachIngredient.isEnough(ingredient)){
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

    public List<Slice> getSlices(){
        return slices;
    }

	public char[][] getMatrixCopy() {
		return matrixCopy;
	}
}
