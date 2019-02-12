package com.team.hashcode.pizza;

public class MinEachIngredient {

	private final int[] array;
	private final int minEachIngredient;

	public MinEachIngredient(int minEachIngredient) {
		this.array = new int[2];
		this.minEachIngredient = minEachIngredient;
	}

	public boolean isEnough(char ingredient) {
		final int tomato = array[0];
		final int mushrooms = array[1];
		if ('T' == ingredient) {
			array[0] = tomato + 1;
		} else if ('M' == ingredient) {
			array[1] = mushrooms + 1;
		}
		return array[0] >= minEachIngredient && array[1] >= minEachIngredient;
	}
}
