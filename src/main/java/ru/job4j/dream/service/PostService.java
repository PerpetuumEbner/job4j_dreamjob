package ru.job4j.dream.service;

import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;

import java.util.Collection;

/**
 * Верхний слой хранилища PostStore в котором находятся вакансии.
 *
 * @author yustas
 * @version 1.0
 */
@Service
public class PostService {

    private final PostStore store;

    public PostService(PostStore store) {
        this.store = store;
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public void add(Post post) {
        store.add(post);
    }

    public void update(Post post) {
        store.update(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }
}