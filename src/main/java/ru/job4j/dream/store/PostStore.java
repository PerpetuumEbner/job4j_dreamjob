package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище в котором находятся вакансии.
 *
 * @author yustas
 * @version 1.0
 */
@ThreadSafe
@Repository
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger ID = new AtomicInteger();

    private PostStore() {
        posts.put(1, new Post(
                1, "Junior Java Job",
                "Collections",
                LocalDate.of(2022, 04, 1)));
        posts.put(2, new Post(
                2, "Middle Java Job",
                "IO, SQl, GC",
                LocalDate.of(2022, 04, 5)));
        posts.put(3, new Post(
                3, "Senior Java Job",
                "Spring, Hibernate",
                LocalDate.of(2022, 04, 11)));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(ID.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}