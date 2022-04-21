package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostDBStore;

import java.util.List;

/**
 * Верхний слой хранилища PostStore в котором находятся вакансии.
 *
 * @author yustas
 * @version 1.0
 */
@ThreadSafe
@Service
public class PostService {

    private final CityService cityService;

    private final PostDBStore store;

    public PostService(CityService cityService, PostDBStore store) {
        this.cityService = cityService;
        this.store = store;
    }

    public List<Post> findAll() {
        List<Post> posts = store.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
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