package org.comstudy21.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import static org.comstudy21.resource.R.*;

import org.comstudy21.util.JdbcUtil;

public class Dao {

	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static String sql = null;

	private static Dao instance;

	public static Dao getInstance() {
		if (instance == null) {
			instance = new Dao();
		}
		return instance;
	}

	public boolean update(ArrayList<Member> modifyList) {

		boolean flag = false;
		sql = "UPDATE MEMBERS SET NAME=?, EMAIL=?, PHONE=? WHERE NO=?";
		setConn(JdbcUtil.getConnection());

		if (intList == null) {
			return flag;
		} else if (intList.size() == 0) {
			return flag;
		}

		if (intList.size() != modifyList.size()) {
			return flag;
		}

		try {
			for (int i = 0; i < intList.size(); i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, modifyList.get(i).getName());
				pstmt.setString(2, modifyList.get(i).getEmail());
				pstmt.setString(3, modifyList.get(i).getPhone());
				pstmt.setInt(4, intList.get(i));
				if (pstmt.executeUpdate() > 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public String checkModify(ArrayList<Member> modifyList) {
		for (int i = 0; i < modifyList.size(); i++) {
			String name = modifyList.get(i).getName();
			String email = modifyList.get(i).getEmail();
			String phone = modifyList.get(i).getPhone();
			String[] check = phone.split("-");
			if (name.equals("") || name.equals(null)) {
				return "[경고] 이름이 비어있습니다";
			} else if (email.equals("") || email.equals(null)) {
				return "[경고] 이메일이 비어있습니다";
			} else if (phone.equals("") || phone.equals(null)) {
				return "[경고] 전화번호가 비어있습니다";
			} else if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
				return "[경고] 이메일은 올바른 형식으로 입력해주세요 예)XX@XX.XX";
			} else if (check.length != 3 || check[0].length() != 3 || check[0].indexOf("0") != 0
					|| check[1].length() != 4 || check[2].length() != 4) {
				return "[경고] 전화번호는 올바른 형식으로 입력해주세요 예)000-0000-0000";
			} else {
				try {
					Integer.parseInt(check[0]);
					Integer.parseInt(check[1]);
					Integer.parseInt(check[2]);
				} catch (NumberFormatException e) {
					return "[경고] 전화번호는 숫자만 입력해주세요";
				}
			}
		}
		if (dao.update(modifyList)) {
			return "수정되었습니다";
		} else {
			return "수정에 실패하였습니다";
		}
	}

	public String checking(Member member) {

		String name = member.getName();
		String email = member.getEmail();
		String phone = member.getPhone();
		String[] check = phone.split("-");
		if (name.equals("") || name.equals(null)) {
			return "[경고] 이름이 비어있습니다";
		} else if (email.equals("") || email.equals(null)) {
			return "[경고] 이메일이 비어있습니다";
		} else if (phone.equals("") || phone.equals(null)) {
			return "[경고] 전화번호가 비어있습니다";
		} else if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
			return "[경고] 이메일은 올바른 형식으로 입력해주세요 예)XX@XX.XX";
		} else if (check.length != 3 || check[0].length() != 3 || check[0].indexOf("0") != 0 || check[1].length() != 4
				|| check[2].length() != 4) {
			return "[경고] 전화번호는 올바른 형식으로 입력해주세요 예)000-0000-0000";
		} else {
			try {
				Integer.parseInt(check[0]);
				Integer.parseInt(check[1]);
				Integer.parseInt(check[2]);
			} catch (NumberFormatException e) {
				return "[경고] 전화번호는 숫자만 입력해주세요";
			}
		}

		if (dao.insert(member)) {
			return "정상적으로 입력했습니다";
		} else {
			return "입력에 실패했습니다";
		}
	}

	public ArrayList<Member> makeModify(ArrayList<String> stringList) {
		ArrayList<Member> modifyList = new ArrayList<>();
		for (int i = 0; i < stringList.size(); i += 3) {
			Member member = new Member();
			member.setName(stringList.get(i + 0));
			member.setEmail(stringList.get(i + 1));
			member.setPhone(stringList.get(i + 2));
			modifyList.add(member);
		}
		return modifyList;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public boolean delete(Vector<Integer> index) {
		sql = "DELETE FROM MEMBERS WHERE NO=?";
		setConn(JdbcUtil.getConnection());
		boolean flag = false;

		try {
			for (int i = 0; i < index.size(); i++) {
				int no = index.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				if (pstmt.executeUpdate() > 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean insert(Member member) {
		sql = "INSERT INTO MEMBERS(NAME, EMAIL, PHONE) VALUES(?,?,?)";
		setConn(JdbcUtil.getConnection());

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public Vector<Member> selectSome(Member member) {
		Vector<Member> allList = null;
		Vector<Member> searchlist = new Vector<Member>();
		setConn(JdbcUtil.getConnection());
		sql = "SELECT * FROM MEMBERS";

		if (member.getName().trim().length() == 0 && member.getEmail().trim().length() == 0
				&& member.getPhone().trim().length() == 0) {
			return null;
		}

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			allList = new Vector<Member>();
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Member newMem = new Member(no, name, email, phone);
				allList.add(newMem);
			}
			for (int i = 0; i < allList.size(); i++) {
				if (allList.get(i).getName().toUpperCase().trim()
						.indexOf(member.getName().toUpperCase().trim()) != -1) {
					if (allList.get(i).getEmail().toUpperCase().trim()
							.indexOf(member.getEmail().toUpperCase().trim()) != -1) {
						if (allList.get(i).getPhone().toUpperCase().trim()
								.indexOf(member.getPhone().toUpperCase().trim()) != -1) {
							Member searchMem = new Member();
							searchMem.setNo(allList.get(i).getNo());
							searchMem.setName(allList.get(i).getName());
							searchMem.setEmail(allList.get(i).getEmail());
							searchMem.setPhone(allList.get(i).getPhone());
							searchlist.add(searchMem);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return searchlist;
	}

	public Vector<Member> selectAll() {
		Vector<Member> list = null;
		setConn(JdbcUtil.getConnection());
		sql = "SELECT * FROM MEMBERS";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = new Vector<Member>();
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Member newMem = new Member(no, name, email, phone);
				list.add(newMem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

}