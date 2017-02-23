package com.team.hashcode.pizza;

public class BestPosition {

	private int bestRow;
	private int bestColumn;

	private int rows;
	private int columns;
	private char[][] matrixCopy;

	public BestPosition(int rows, int columns, char[][] matrixCopy) {
		this.rows = rows;
		this.columns = columns;
		this.matrixCopy = matrixCopy;
		findBestPositions();
	}

	private void findBestPositions(){
		finish : for(int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				if('*' != matrixCopy[r][c]){
					bestRow = r;
					bestColumn = c;
					break finish;
				}
			}
		}
	}

	public int getBestRow() {
		return bestRow;
	}

	public int getBestColumn() {
		return bestColumn;
	}
}
