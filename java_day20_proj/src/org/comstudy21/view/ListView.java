package org.comstudy21.view;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListView extends View {
	
	DefaultTableModel dm;
	public JTable table;

	public void messageDialog(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public int choiceDialog(String msg) {
		int result = JOptionPane.showConfirmDialog(null, msg, "선택창", JOptionPane.YES_NO_OPTION);
		return result;
	}
	
	
	public void setTableModel(DefaultTableModel dm) {
		this.dm = dm;
	}

	public ListView(DefaultTableModel dm, Object[] colNames) {
		this.dm = dm;
		dm.setColumnIdentifiers(colNames);
		init();
	}

	@Override
	public void init() {
		
		table = new JTable(dm);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
