package model;

public class Board {
	private int[][] board;
	private int rows;
	private int cols;

	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		board = new int[rows][cols];
	}

	public boolean setPosition(int row, int col, int v) {
		if ( row >= 0 && row < rows && col >= 0 && col < cols ) {
			board[row][col] = v;
			return true;
		} else
			return false;
	}

	public int getPosition(int row, int col) {
		if ( row >= 0 && row < rows && col >= 0 && col < cols ) {
			return board[row][col];
		} else {
			return -1;
		}
	}
	
	public int getNumCols() {
		return cols;
	}

	public int getNumRows() {
		return rows;
	}

	@Override
	public String toString() {
		StringBuilder render = new StringBuilder();

		for (int i = 0; i < rows; i++) {
			render.append("|");
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == 0 )
					render.append("       |");
				else						
					render.append(String.format(" %5d |",board[i][j]));
			}
			render.append("\n");
		}
		return render.toString();
	}


}
