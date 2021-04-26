package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import domain.Users;
import util.ConnUtil;
import util.SHAUtil;

public class UsersDAO {
	
	/**
	 * �ж��û����Ƿ�ռ��
	 * @param userName �û���
	 * @return Users �Ƿ���ڣ�users/null��
	 * @throws SQLException
	 */
	public Users existsUser(String adminName) throws SQLException{
		String sql = "select *from tab_user where adminname = ?";	//��sqlע�빥��
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, adminName);
		ResultSet rs = pstat.executeQuery();
		if(rs.next()){
			Users user = new Users();
			user.setUserId(rs.getInt("uid"));
			user.setAdminName(rs.getString("adminname"));
			user.setAdminPass(rs.getString("password"));
			user.setPersonName(rs.getString("name"));
			user.setUserPhone(rs.getString("phone"));
			user.setUserCode(rs.getString("code"));
			return user;	//����û��� �Ѵ��ڣ��򷵻�user
		} else{
			return null;	//�û���δռ�ã�����null
		}
	}
	
	/**
	 * ע�ắ����ע���û��ĸ�����Ϣ
	 * @param userName	�û�������Ӧ���ݿ�adminname
	 * @param userPass	��������룬Ҫ������Ϸ���password������
	 * @param personName	�û������֣���Ӧ���ݿ�name
	 * @param userPhone	�ֻ��ţ���Ӧ���ݿ�phone
	 * @throws SQLException
	 */
	public void register(String adminName, String adminPass, String personName, String userPhone) throws SQLException{
		String userSalt = UUID.randomUUID().toString();
		
		String sql = "insert into tab_user(adminname,password,name,phone,code) "
				+ " values(?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, adminName);
		//��������ε���ϼ��ܴ�����user_pass��
		pstat.setString(2, getSHA256(adminPass, userSalt));	
		pstat.setString(3, personName);
		pstat.setString(4, userPhone);
		pstat.setString(5, userSalt);	//�Σ�����code��
		pstat.executeUpdate();
	}
	
	/**
	 * ����SHA256����
	 * 
	 * @param pass
	 * @param salt
	 * @return �����ַ���
	 */
	private String getSHA256(String pass, String salt) {
		return SHAUtil.getSHA256(pass + salt);
	}
	
}
