package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;
import model.Board;
import model.SlidePuzzleObserver;

public class MainWindow extends JFrame implements SlidePuzzleObserver {

	private Controller _ctrl;

	public MainWindow(Controller ctrl) {
		super("Slide Puzzle");
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		BoardComp b = new BoardComp(_ctrl);
		b.setMaximumSize( new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		b.setMinimumSize( new Dimension(200,200));
		b.setPreferredSize( new Dimension(200,200));

		HistTable t = new HistTable(_ctrl);
		t.setMaximumSize( new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		t.setMinimumSize( new Dimension(200,200));
		t.setPreferredSize( new Dimension(200,200));

		JPanel content = new JPanel();
		content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS));

		mainPanel.add(content, BorderLayout.CENTER);
		content.add(b);
		content.add(t);
		
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void onGameStart(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void onGameOver(Board board) {		
	}

}
