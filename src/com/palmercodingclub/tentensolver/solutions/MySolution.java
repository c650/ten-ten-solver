package com.palmercodingclub.tentensolver.solutions;
import java.util.ArrayList;
import com.palmercodingclub.tentensolver.*;

public class MySolution extends Solution {


	public MySolution() {
		super("MySolution -- Just Random");
	}

	public int doMove(ArrayList<Piece> choices) {

		int pieceIdx = pickPiece(choices);
		Piece p=choices.remove(pieceIdx);
		ArrayList<Coordinate> spots = b.getAvailableSpots(  p  );
		b.placePiece( p , spots.get((int)(Math.random() * spots.size())) );
		return p.getScore();
	}

	private int pickPiece(ArrayList<Piece> choices) {

		int rand = (int)(Math.random() * choices.size());
		while(b.getAvailableSpots(choices.get(rand)).isEmpty())
			rand = (int)(Math.random() * choices.size());

		return rand;

	}
};
