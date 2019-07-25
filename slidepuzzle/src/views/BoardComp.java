package views;

import model.Board;
import model.SlidePuzzleObserver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import control.Controller;

public class BoardComp extends JComponent implements SlidePuzzleObserver {

	private Controller _ctrl;
	Board _board;
	private int _cellWidth;
	private int _cellHeight;

	BoardComp(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}

	private void initGUI() {
		
		addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if ( e.getKeyChar() == 'r') {
					try {
						_ctrl.reset(_board.getNumRows(), _board.getNumCols());
					} catch (Exception ex) {
					}
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {				
			}
		});
		
		addMouseListener( new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) { 				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {				
				int row = e.getY()/_cellHeight;
				int col = e.getX()/_cellWidth;
				_ctrl.move(row, col);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
		});
		_board =  null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("repainting");
		if (_board == null)
			return;

		_cellWidth = getWidth() / _board.getNumCols();
		_cellHeight = getHeight() / _board.getNumRows();

		for (int i = 0; i < _board.getNumRows(); i++) {
			for (int j = 0; j < _board.getNumCols(); j++) {
				drawCell(i, j, g);
			}
		}

	}

	private void drawCell(int row, int col, Graphics g) {
		int v = _board.getPosition(row, col);
		if (v == 0)
			return;
		
		int x = col*_cellWidth;
		int y = row*_cellHeight;
		
		g.setColor(Color.GRAY);
		g.fillRect(x+2, y+2, _cellWidth-4, _cellHeight-4);

		g.setColor(Color.BLACK);

		g.drawString(v+"", x+_cellWidth/2, y+_cellHeight/2);
	}

	@Override
	public void onGameStart(Board board) {
		_board = board;
		repaint();
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
		_board = board;
		repaint();
	}

	@Override
	public void onError(String msg) {
	}

	@Override
	public void onGameOver(Board board) {
	}

}
