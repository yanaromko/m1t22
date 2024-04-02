package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.SqlString;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection1();

    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
//        PreparedStatement preparedStatement = null;
        String sql = """
                CREATE TABLE IF NOT EXISTS Users
                (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name varchar(40) NOT NULL,
                    last_name varchar(45) NOT NULL,
                    age INT NOT NULL
                )
                """;
//                ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3
        try {
            var statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void dropUsersTable() {
//        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS users";
        try {
            var statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
//        PreparedStatement preparedStatement = null;
        String sql = """
                        INSERT INTO users
                        (
                            name, last_name, age
                        ) VALUES (?, ?, ?)
                        """;
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with name " + name + " added to the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM users WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, last_name, Age FROM users";
//        Statement statement
//                = null;
        try (var preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                User users = new User();
                users.setId(resultSet.getLong("id"));
                users.setName(resultSet.getString("name"));
                users.setLastName(resultSet.getString("last_name"));
                users.setAge(resultSet.getByte("age"));
                userList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
