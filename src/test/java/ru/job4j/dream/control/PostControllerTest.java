package ru.job4j.dream.control;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.service.CityService;
import ru.job4j.dream.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostControllerTest {

    @Test
    void whenPosts() {
        List<Post> posts = List.of(
                new Post(1, "New post", "description", LocalDate.now(), true, new City(1, "Москва")),
                new Post(2, "New post", "description", LocalDate.now(), true, new City(2, "Санкт-Петербург")),
                new Post(3, "New post", "description", LocalDate.now(), true, new City(3, "Екатеринбург")),
                new Post(3, "New post", "description", LocalDate.now(), true, new City(3, "Челябинск")));
        Model model = mock(Model.class);
        HttpSession httpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.posts(model, httpSession);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    void whenFromAddPost() {
        Post post = new Post(
                0, "Заполните поле", "Заполните поле", LocalDate.now(), false, new City(0, "Заполните поле"));
        Model model = mock(Model.class);
        HttpSession httpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findById(0)).thenReturn(post);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.addPost(model, httpSession);
        verify(model).addAttribute("post", post);
        assertThat(page, is("addPost"));
    }

    @Test
    void createPost() {
        Post post = new Post(
                1, "New post", "description", LocalDate.now(), true, new City(3, "Екатеринбург"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.createPost(post, 1);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    void updatePost() {
        Post post = new Post(
                1, "New post", "description", LocalDate.now(), true, new City(3, "Екатеринбург"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.updatePost(post);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    void formUpdatePost() {
        Model model = mock(Model.class);
        HttpSession httpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.formUpdatePost(model, 1, httpSession);
        assertThat(page, is("updatePost"));
    }
}