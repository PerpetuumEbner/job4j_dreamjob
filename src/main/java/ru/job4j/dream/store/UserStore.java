package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище в котором находятся пользователи.
 *
 * @author yustas
 * @version 1.0
 */
@ThreadSafe
@Repository
public class UserStore {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final AtomicInteger ID = new AtomicInteger();

    public Collection<User> findAll() {
        return users.values();
    }

    public void add(User user) {
        user.setId(ID.incrementAndGet());
        users.put(user.getId(), user);
    }

    public void update(User user) {
        users.replace(user.getId(), user);
    }

    public void remove(User user) {
        users.remove(user.getId());
    }

    public void findById(User user) {
        users.get(user.getId());
    }
}