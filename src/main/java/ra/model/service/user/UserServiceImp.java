package ra.model.service.user;

import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class UserServiceImp implements IUserService{
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean save(User user) {
        Connection conn = null;
        try {
            conn= ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_REGISTER(?,?)}");
            callSt.setString(1,user.getUsername());
            callSt.setString(2, user.getPassword());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User findById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public UserLogin login(User user) {
        UserLogin userLogin= null;
        Connection conn = null;
        try {
            conn= ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_LOGIN(?,?)}");
            callSt.setString(1,user.getUsername());
            callSt.setString(2, user.getPassword());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                userLogin= new UserLogin();
                userLogin.setId(rs.getInt(1));
                userLogin.setUsername(rs.getString(2));
                userLogin.setPassword(rs.getString(3));
                userLogin.setRole(rs.getInt(4));
                userLogin.setCartId(rs.getInt(5));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userLogin;
    }

    @Override
    public boolean checkExistsUsername(String username) {

        Connection conn = null;
        try {
            conn= ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FINDBYUSERNAME(?)}");
            callSt.setString(1,username);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
               return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
