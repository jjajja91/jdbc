package org.comstudy21.controller;

import java.util.Vector;

import org.comstudy21.model.Member;

public class ListController implements Controller {

	public ListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service() {
		Vector<Member> list = dao.selectAll();
		intList.clear();
		if (list != null) {
			while (dm.getRowCount() > 0) {
				dm.removeRow(0);
			}
			for (Member m : list) {
				Vector v = new Vector<>();
				intList.add(m.getNo());
				v.add(m.getNo());
				v.add(m.getName());
				v.add(m.getEmail());
				v.add(m.getPhone());
				dm.addRow(v);
			}
		}
	}

}
