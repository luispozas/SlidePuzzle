package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlidePuzzle implements Observable<SlidePuzzleObserver>{


	private Board board;
	private int rows;
	private int cols;

	private boolean finished;
	private int emptyRow;
	private int emptyCol;

	List<SlidePuzzleObserver> obs;
	
	public SlidePuzzle() {
		this(4, 4);
	}

	public SlidePuzzle(int rows, int cols) {
		obs = new ArrayList<>();
		reset(rows, cols);
	}

	public void move(int row, int col) {

		// if the game is finished, we cannot make a move
		if (finished) {
			notifyError("Game is finshed already!");
			throw new GameError("Game is finshed already!");
		}

		// if it is not adjacent to an empty cell, we cannot make a move
		if ( !adjacentToEmptyCell(row,col)) {
			notifyError("Invalid move " + "(" + row + "," + col + ")");
			throw new GameError("Invalid move " + "(" + row + "," + col + ")");
		}

		// swap (row,col) with the empty cell (i,j)
		int v = board.getPosition(row, col);
		board.setPosition(row, col, board.getPosition(emptyRow, emptyCol));
		board.setPosition(emptyRow, emptyCol, v);
		
		notifyMove(row, col, emptyRow, emptyCol);
		// new empty cell
		
		emptyRow = row;
		emptyCol = col;
		
		// check if the game is over, and notify if so
		if (checkIfFinished()) {
			finished = true;
			notifyGameOver();
		}
	}

	private boolean adjacentToEmptyCell(int row, int col) {
		return (row == emptyRow && (Math.abs(col-emptyCol)==1)) ||
				(col == emptyCol && (Math.abs(row-emptyRow)==1));
	}

	private boolean checkIfFinished() {
		int maxNum = rows * cols;
		int currNum = 1;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board.getPosition(i, j) != currNum) {
					return false;
				}
				currNum = (currNum + 1) % maxNum;
			}
		}
		return true;
	}

	public boolean isFinished() {
		return finished;
	}

	public int getRows() {
		return rows;
	}

	public int getColss() {
		return cols;
	}

	public void reset(int rows, int cols) {
		rows = Math.max(rows, 2);
		cols = Math.max(cols, 2);
		this.rows = rows;
		this.cols = cols;

		board = new Board(rows, cols);
		finished = false;

		// fill in the board
		Random r = new Random();
		int maxNum = rows * cols;
		boolean[] usedNums = new boolean[maxNum];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				int k = r.nextInt(maxNum);
				while (usedNums[k])
					k = (k + 1) % maxNum;
				usedNums[k] = true;
				if ( k == 0 ) {
					emptyRow = i;
					emptyCol = j;
				}
				board.setPosition(i, j, k);
			}
		
		notifyReset();
	}

	@Override
	public String toString() {
		return board.toString();
	}

	
	@Override
	public void addObserver(SlidePuzzleObserver o) {
		obs.add(o);
		o.onGameStart(board);
	}

	@Override
	public void removeObserver(SlidePuzzleObserver o) {
		obs.remove(o);
	}

	
	
	private void notifyReset() {
		for (SlidePuzzleObserver o : obs) {
			o.onGameStart(board);
		}
	}

	private void notifyGameOver() {
		for (SlidePuzzleObserver o : obs) {
			o.onGameOver(board);
		}
	}

	private void notifyMove(int row, int col, int i, int j) {
		for (SlidePuzzleObserver o : obs) {
			o.onMove(board, row, col, i, j);
		}
	}

	private void notifyError(String msg) {
		for (SlidePuzzleObserver o : obs) {
			o.onError(msg);
		}
		throw new GameError(msg);
	}


}
