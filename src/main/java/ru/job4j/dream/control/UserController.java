package ru.job4j.dream.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.User;
import ru.job4j.dream.service.UserService;

import java.util.Optional;

@ThreadSafe
@Controller
public class UserController {

    private final UserService store;

    public UserController(UserService store) {
        this.store = store;
    }

    @GetMapping("/users")
    public String posts(Model model) {
        model.addAttribute("users", store.findAll());
        return "users";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = store.add(user);
        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
            return "redirect:/fail";
        }
        return "redirect:/success";
    }
}