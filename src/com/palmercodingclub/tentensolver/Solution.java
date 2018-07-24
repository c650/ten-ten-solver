package com.palmercodingclub.tentensolver;
<<<<<<< HEAD

=======
>>>>>>> f415da6935e5207ad64e7bd9c6ea1d41a816bc4f
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
<<<<<<< HEAD
	
	public Board getBoard() {
		return b;
	}
=======
>>>>>>> f415da6935e5207ad64e7bd9c6ea1d41a816bc4f

	/**
	* Desired Behaviour: With the `choices` you are given, pick a piece to insert into the board at some valid position.
	* doMove(...) is the only function that must be implemented.
	* Note: Valid positions are returned by Board.getAvailableSpots(Piece).
	* @param choices the various pieces to choose from when playing
<<<<<<< HEAD
	* @return the score value of the piece
	*/
	public abstract int doMove(ArrayList<Piece> choices);
=======
	*/
	public abstract void doMove(ArrayList<Piece> choices);
>>>>>>> f415da6935e5207ad64e7bd9c6ea1d41a816bc4f

};
