package com.palmercodingclub.tentensolver.solutions;

import java.util.ArrayList;
import com.palmercodingclub.tentensolver.Solution;
import com.palmercodingclub.tentensolver.Piece;
import com.palmercodingclub.tentensolver.Coordinate;

public class MySolution extends Solution {


	public MySolution() {
		super("MySolution -- Just Random");
	}

	public int doMove(ArrayList<Piece> choices) {

		int pieceIdx = pickPiece(choices);
		Piece pieceToPlay=choices.remove(pieceIdx);
		ArrayList<Coordinate> spots = b.getAvailableSpots(  pieceToPlay  );
		
		b.placePiece( pieceToPlay , spots.get((int)(Math.random() * spots.size())) );
		return pieceToPlay.getScore();

	}

	private int pickPiece(ArrayList<Piece> choices) {

		int rand = (int)(Math.random() * choices.size());
		while(b.getAvailableSpots(choices.get(rand)).isEmpty())
			rand = (int)(Math.random() * choices.size());

		return rand;

	}
};
