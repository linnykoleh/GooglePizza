package com.team.hashcode.pizza;

import java.util.Arrays;

public class Slice {

    private char[] slicePositions;
    private int arraySize;
    private int sizeSlice;

    public Slice(int sizeSlice) {
        this.slicePositions = new char[sizeSlice];
        this.sizeSlice = sizeSlice;
    }

    public void addIngredient(char value){
        slicePositions[arraySize++] = value;
    }

    public int getArraySize(){
        return arraySize;
    }

    @Override
    public String toString() {
        return Arrays.toString(slicePositions);
    }
}
