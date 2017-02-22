package com.team.hashcode.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1. Save slice only after checking valid slice, what I mean :
 *           - size <= maxCellsPerSlice                            #
 *           - not cover already defined slices                    #
 *           - slice contain minEachIngredient per slice
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
    final List<Slice> slices = new ArrayList<>();

    private int row;
    private int columns;

    public Pizza(char[][] matrix, int maxCellsPerSlice, int minEachIngredient) {
        this.matrix = matrix;
        this.matrixCopy = matrix;
        this.maxCellsPerSlice = maxCellsPerSlice;
		this.minEachIngredient = minEachIngredient;
        this.row = matrix.length;
        this.columns = matrix[0].length;
    }

    public void cutPizza(){
        int r = 0;
        int c = 0;
		char sliceNumber = '1';
        next: for(; c < columns; c++) {
            for(; r < row; r++){
                if(isCorrectSlice(c, r)) {
					final Slice slice = fillSliceAndMatrixCopy(c, r, sliceNumber);
					slices.add(slice);
					sliceNumber++;
					continue next;
                }else{
                    r = 0;
                }

            }
        }
	}

    public Slice fillSliceAndMatrixCopy(int c, int r, char sliceNumber){
		final Slice slice = new Slice(maxCellsPerSlice);
		for(int c1 = c; c1 < columns; c1++) {
			for(int r1 = r; r1 < row; r1++) {
				if(slice.getArraySize() < maxCellsPerSlice) {
					slice.addIngredient(matrix[r1][c1]);
					matrixCopy[r1][c1] = sliceNumber;
				}else{
					return slice;
				}
			}
		}
		return slice;
	}

    public boolean isCorrectSlice(int c, int r){
		int sliceSize = 0;
		boolean isWasAnotherIngredient = false;
		for(; c < columns; c++) {
			char searchIngredient = matrix[r][c] == 'M' ? 'T' : 'M';
			for(; r < row; r++){
				if(sliceSize < maxCellsPerSlice) {
					if(searchIngredient == matrix[r][c])
						isWasAnotherIngredient = true;
					if(sliceSize >= maxCellsPerSlice && !isWasAnotherIngredient)
						return false;
					if(Character.isDigit(matrixCopy[r][c]))
						return false;
					sliceSize++;
				}else {
					return true;
				}
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
