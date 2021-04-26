package service;

import java.sql.SQLException;
import java.util.Date;

import dao.UsersDAO;
import domain.Users;
import exception.NameException;
import exception.PassException;
import util.ConnUtil;
import util.SHAUtil;

public class UserService {
	private UsersDAO userDao = new UsersDAO();
	
	/**
	 * �ж��û��Ƿ���ڵ�ҵ��
	 * @param user_name �û�������
	 * @return �Ƿ���ڣ�user/null��
	 */
	public Users isExistsUser(String user_name) 
			throws SQLException{
		try {
			if(userDao.existsUser(user_name)!=null)return userDao.existsUser(user_name);
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback();	//����ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
	}
	
	/**
	 * ע���û�
	 * @param user_name
	 * @param user_pass
	 * @param user_address
	 * @param user_phone
	 * @throws SQLException
	 */
	public void register(String userName, String userPass, String personName, String userPhone) throws SQLException{
		try {
			userDao.register(userName, userPass, personName, userPhone);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback();	//����ع�
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
	}
	
	/**
	 * �û���½��֤
	 * @param userName ������û�����
	 * @param userPass ������û�����
	 * @return �û�����user
	 * @throws NameException ҵ���쳣���û���������ʱ�׳����쳣
	 * @throws PassException ҵ���쳣�������벻��ȷ���׳����쳣
	 */
	public Users isLogin(String userName, String userPass) throws NameException, PassException{
		try {
			Users user = this.userDao.existsUser(userName);
			if(user == null){
				//�û����������׳��쳣
				throw new NameException("�û��� �����ڣ�");
			} 
			//��֤����
			if(SHAUtil.getSHA256(userPass+user.getUserCode()).equals(user.getAdminPass())){
				//������ȷ
				return user;
			} else{
				throw new PassException("�������� ����");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
		
	}
}
