package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.UserDBStore;

import java.util.Collection;
import java.util.Optional;

/**
 * Верхний слой хранилища UserStore в котором находятся пользователи.
 *
 * @author yustas
 * @version 1.0
 */
@ThreadSafe
@Service
public class UserService {

    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public Collection<User> findAll() {
        return store.findAll();
    }

    public Optional<User> add(User user) {
        return store.add(user);
    }

    public void update(User user) {
        store.update(user);
    }

    public User findById(int id) {
        return store.findById(id);
    }
}