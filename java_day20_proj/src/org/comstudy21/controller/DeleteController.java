package org.comstudy21.controller;

import java.util.Vector;

public class DeleteController implements Controller {

	@Override
	public void service() {

		int[] rowArray = listView.table.getSelectedRows();
		Vector<Integer> idxVector = new Vector<>();
		for (int i = 0; i < rowArray.length; i++) {
			int index = Integer.parseInt("" + dm.getValueAt(rowArray[i], 0));
			idxVector.add(index);
		}

		if (dao.delete(idxVector)) {
			listView.messageDialog("삭제 되었습니다");
		} else {
			listView.messageDialog("삭제에 실패했습니다");
		}

	}

}
