package org.comstudy21.controller;

import static org.comstudy21.resource.R.emailField;
import static org.comstudy21.resource.R.nameField;
import static org.comstudy21.resource.R.phoneField;

import org.comstudy21.model.Member;
import org.comstudy21.view.ButtonView;

public class InputController implements Controller {

	public InputController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service() {
		Member member = new Member();
		String name = nameField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		if (dao.checking(member).equals("정상적으로 입력했습니다")) {
			listView.messageDialog(dao.checking(member));
			nameField.setText("");
			emailField.setText("");
			phoneField.setText("");
		} else {
			listView.messageDialog(dao.checking(member));
		}

	}

}
