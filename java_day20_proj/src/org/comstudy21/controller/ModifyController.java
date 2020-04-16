package org.comstudy21.controller;

import java.util.ArrayList;

public class ModifyController implements Controller {

	@Override
	public void service() {

		ArrayList<String> stringList = new ArrayList<>();
		for (int i = 0; i < listView.table.getRowCount(); i++) {
			stringList.add(listView.table.getValueAt(i, 1).toString());
			stringList.add(listView.table.getValueAt(i, 2).toString());
			stringList.add(listView.table.getValueAt(i, 3).toString());
		}
		listView.messageDialog(dao.checkModify(dao.makeModify(stringList)));

	}

}
