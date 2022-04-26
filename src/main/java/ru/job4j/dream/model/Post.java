package ru.job4j.dream.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Модель описывающая вакансию.
 *
 * @author yustas
 * @version 1.0
 */
public class Post implements Serializable {
    private int id;
    private String name;
    private String description;
    private LocalDate created;
    private boolean visible;
    private City city;

    public Post(int id, String name, String description, LocalDate created, boolean visible, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.visible = visible;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                visible == post.visible &&
                Objects.equals(name, post.name) &&
                Objects.equals(description, post.description) &&
                Objects.equals(created, post.created) &&
                Objects.equals(city, post.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created, visible, city);
    }
}