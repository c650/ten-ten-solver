import java.util.ArrayList;

public class MySolution extends Solution {

	public final String name = "MySolution -- Just Random";

	MySolution() {
		super();
	}

	public void doMove(ArrayList<Piece> choices) {

		int pieceIdx = pickPiece(choices);

		ArrayList<Coordinate> spots = b.getAvailableSpots(  choices.get(  pieceIdx  )  );
		b.placePiece( choices.remove( pieceIdx ) , spots.get((int)(Math.random() * spots.size())) );

	}

	private int pickPiece(ArrayList<Piece> choices) {

		int rand = (int)(Math.random() * choices.size());
		while(b.getAvailableSpots(choices.get(rand)).isEmpty())
			rand = (int)(Math.random() * choices.size());

		return rand;

	}

};
