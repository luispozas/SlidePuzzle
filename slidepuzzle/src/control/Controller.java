package control;

import model.SlidePuzzle;
import model.SlidePuzzleObserver;

public class Controller {
	boolean exit;
	SlidePuzzle game;

	public Controller(SlidePuzzle game) {
		this.game = game;
		exit = false;
	}

	public void move(int row, int col) {
		game.move(row, col);
	}

	public void requestExit() {
		exit = true;
	}

	public void reset(int rows, int cols) {
		game.reset(rows, cols);
	}
	
	public void addObserver(SlidePuzzleObserver o) {
		game.addObserver(o);
	}

	public void removeObserver(SlidePuzzleObserver o) {
		game.removeObserver(o);
	}

}
