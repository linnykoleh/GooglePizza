package com.team.hashcode.pizza;

import java.util.Arrays;

public class Slice {

    private char[] slicePositions;
    private int arraySize;
    private int[] rows;
    private int[] columns;

    public Slice(int sizeSlice) {
        this.slicePositions = new char[sizeSlice];
        this.rows = new int[sizeSlice];
        this.columns = new int[sizeSlice];
    }

    public void addIngredient(char value, int r, int c){
        slicePositions[arraySize] = value;
        rows[arraySize] = r;
        columns[arraySize] = c;
        arraySize++;
    }

    public int getRowFirst(){
        return rows[0];
    }

    public int getColumnsFirst(){
        return columns[0];
    }

    public int getRowLast(){
        return rows[rows.length - 1];
    }
    public int getColumnsLast(){
        return columns[columns.length - 1];
    }

    public int getArraySize(){
        return arraySize;
    }

    @Override
    public String toString() {
        return Arrays.toString(slicePositions);
    }
}
