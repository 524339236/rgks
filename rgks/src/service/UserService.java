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
	 * 判断用户是否存在的业务
	 * @param user_name 用户的名字
	 * @return 是否存在（user/null）
	 */
	public Users isExistsUser(String user_name) 
			throws SQLException{
		try {
			if(userDao.existsUser(user_name)!=null)return userDao.existsUser(user_name);
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnUtil.getConn().rollback();	//事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
	}
	
	/**
	 * 注册用户
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
				ConnUtil.getConn().rollback();	//事务回滚
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
	}
	
	/**
	 * 用户登陆验证
	 * @param userName 输入的用户名称
	 * @param userPass 输入的用户密码
	 * @return 用户对象user
	 * @throws NameException 业务异常，用户名不存在时抛出此异常
	 * @throws PassException 业务异常，当密码不正确是抛出此异常
	 */
	public Users isLogin(String userName, String userPass) throws NameException, PassException{
		try {
			Users user = this.userDao.existsUser(userName);
			if(user == null){
				//用户名不存在抛出异常
				throw new NameException("用户名 不存在！");
			} 
			//验证密码
			if(SHAUtil.getSHA256(userPass+user.getUserCode()).equals(user.getAdminPass())){
				//密码正确
				return user;
			} else{
				throw new PassException("密码输入 错误！");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			ConnUtil.closeConn();
		}
		
	}
}
