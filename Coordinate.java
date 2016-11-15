public class Coordinate {
	public final int row,col;
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public String toString() {
		return String.format("( %d , %d )", row , col );
	}
}
