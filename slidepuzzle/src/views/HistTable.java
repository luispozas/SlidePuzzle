package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import control.Controller;
import views.HistTableModel;

public class HistTable extends JPanel {
	
	private Controller _ctrl;

	HistTable(Controller ctrl) {
		_ctrl =ctrl;
		initGUI();
	}

	private void initGUI() {
		setLayout( new BorderLayout() );
		
		JTable t = new JTable( new HistTableModel(_ctrl));
		
		
		this.add( new JScrollPane(t) );
		
	}
}
