package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Хранилище в котором находятся вакансии.
 *
 * @author yustas
 * @version 1.0
 */
public class PostStore {
    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

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

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        posts.put(post.getId(), post);
    }
}