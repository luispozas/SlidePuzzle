package views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import control.Controller;
import model.Board;
import model.SlidePuzzleObserver;

public class HistTableModel extends AbstractTableModel implements SlidePuzzleObserver {

	class HistItem {
		int srcRow;
		int srcCol;
		int trgtRow;
		int trgtCol;
	}
	
	String[] headers = { "sRow", "sCol", "tRow", "tCol" };
	List<HistItem> _hist;
	
	HistTableModel(Controller ctrl) {
		_hist = new ArrayList<>();
		ctrl.addObserver(this);
	}
	
	@Override
	public int getRowCount() {
		return _hist.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return headers[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		HistItem h = _hist.get(rowIndex);
		switch ( columnIndex ) {
		case 0: 
			return h.srcRow;
		case 1:
			return h.srcCol;
		case 2:
			return h.trgtRow;
		case 3:
			return h.trgtCol;
		}
		
		return null;
	}

	@Override
	public void onGameStart(Board board) {
		_hist.clear();
		fireTableStructureChanged();
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
		HistItem h = new HistItem();
		h.srcRow = srcRow;
		h.srcCol = srcCol;
		h.trgtRow = trgtRow;
		h.trgtCol = trgtCol;
		_hist.add(h);
		fireTableStructureChanged();

		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameOver(Board board) {
		// TODO Auto-generated method stub
		
	}

}
