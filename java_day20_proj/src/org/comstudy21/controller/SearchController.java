package org.comstudy21.controller;

import java.util.Vector;

import org.comstudy21.model.Member;

public class SearchController implements Controller {

	@Override
	public void service() {
		Member member = new Member();
		String name = nameField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);

		Vector<Member> list = dao.selectSome(member);
		intList.clear();
		if (list == null) {
			listView.messageDialog("검색할 대상이 없습니다");
		} else if (list.size() == 0) {
			listView.messageDialog("해당하는 정보가 없습니다");
		} else {
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
				listView.messageDialog("검색 완료했습니다");
			}
		}

	}

}
