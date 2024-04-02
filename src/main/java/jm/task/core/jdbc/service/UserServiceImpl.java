package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserServiceImpl implements UserService {
    private static final UserDao userdao = new UserDaoHibernateImpl();
    @Override
    public void createUsersTable() {
        userdao.createUsersTable();
    }
    @Override
    public void dropUsersTable() throws SQLException {
        userdao.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userdao.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) throws SQLException {
        userdao.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        return userdao.getAllUsers();
    }
    @Override
    public void cleanUsersTable() throws SQLException {
        userdao.cleanUsersTable();
    }
}
