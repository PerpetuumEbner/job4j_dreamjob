package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * В классе происходит обработка пользователей в базе данных.
 *
 * @author yustas
 * @version 1.0
 */
@Repository
public class UserDBStore {

    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    /**
     * Поиск всех параметров пользователя в базе данных.
     *
     * @return Список параметров вакансии.
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM users")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    users.add(new User(
                            it.getInt("id"),
                            it.getString("email"),
                            it.getString("password")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Добавление параметров в вакансию.
     *
     * @param user Объект пользователя.
     * @return Объект пользователя с добавленными параметрами.
     */
    public Optional<User> add(User user) {
        Optional<User> optionalUser = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO users(email, password) VALUES (?, ?)")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getPassword());
                    ps.execute();
                }
            }
            optionalUser = Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    /**
     * Обновление параметров в объекте пользователь.
     *
     * @param user Объект пользователя с обновлёнными параметрами.
     */
    public void update(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE users SET email = ?, password = ?")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getPassword());
                    ps.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Поиск объекта пользователя по id.
     *
     * @param id Id пользователя.
     * @return Объект пользователя если найден по id иначе null.
     */
    public User findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM users WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    it.getInt("id");
                    it.getString("email");
                    it.getString("password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM users WHERE email = ? and password = ?")
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return Optional.of(new User(
                            it.getInt("id"),
                            it.getString("email"),
                            it.getString("password")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}