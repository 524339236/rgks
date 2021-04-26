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
	 * 判断用户名是否占用
	 * @param userName 用户名
	 * @return Users 是否存在（users/null）
	 * @throws SQLException
	 */
	public Users existsUser(String adminName) throws SQLException{
		String sql = "select *from tab_user where adminname = ?";	//防sql注入攻击
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
			return user;	//如果用户名 已存在，则返回user
		} else{
			return null;	//用户名未占用，返回null
		}
	}
	
	/**
	 * 注册函数，注册用户的个人信息
	 * @param userName	用户名，对应数据库adminname
	 * @param userPass	输入的密码，要和盐组合放在password属性中
	 * @param personName	用户的名字，对应数据库name
	 * @param userPhone	手机号，对应数据库phone
	 * @throws SQLException
	 */
	public void register(String adminName, String adminPass, String personName, String userPhone) throws SQLException{
		String userSalt = UUID.randomUUID().toString();
		
		String sql = "insert into tab_user(adminname,password,name,phone,code) "
				+ " values(?,?,?,?,?)";
		Connection conn = ConnUtil.getConn();
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, adminName);
		//将密码和盐的组合加密储存在user_pass中
		pstat.setString(2, getSHA256(adminPass, userSalt));	
		pstat.setString(3, personName);
		pstat.setString(4, userPhone);
		pstat.setString(5, userSalt);	//盐，存在code中
		pstat.executeUpdate();
	}
	
	/**
	 * 密码SHA256处理
	 * 
	 * @param pass
	 * @param salt
	 * @return 加密字符串
	 */
	private String getSHA256(String pass, String salt) {
		return SHAUtil.getSHA256(pass + salt);
	}
	
}
