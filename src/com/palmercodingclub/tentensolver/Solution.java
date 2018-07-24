package com.palmercodingclub.tentensolver;

import java.util.ArrayList;

public abstract class Solution {

	public final String name;

	protected Board b;
	
	protected Solution(String name) {
		b = null;
		this.name=name;
	}

	public void setBoard(Board board) {
		b = board;
	}
	
	public Board getBoard() {
		return b;
	}

	/**
	* Desired Behaviour: With the `choices` you are given, pick a piece to insert into the board at some valid position.
	* doMove(...) is the only function that must be implemented.
	* Note: Valid positions are returned by Board.getAvailableSpots(Piece).
	* @param choices the various pieces to choose from when playing
	* @return the score value of the piece
	*/
	public abstract int doMove(ArrayList<Piece> choices);

};
