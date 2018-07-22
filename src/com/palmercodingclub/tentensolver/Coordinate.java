package com.palmercodingclub.tentensolver;
public class Coordinate {
	public final int row,col;
	
	/**
	* Constructs a `Coordinate` object with a specified x and y value.
	* @param row the y value of the location
	* @param col the x value of the location
	*/
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	* Returns a `String` representation of *this* `Coordinate`
	* Overriden from parent *class* `Object`
	* @return a `String` representation of *this* `Coordinate`
	*/
	public String toString() {
		return String.format("( %d , %d )", row , col );
	}
}
