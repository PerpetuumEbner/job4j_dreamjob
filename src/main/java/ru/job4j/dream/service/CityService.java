package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.CityStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Верхний слой хранилища CityStore в котором находятся города.
 *
 * @author yustas
 * @version 1.0
 */
@ThreadSafe
@Service
public class CityService {
    private final CityStore store;

    public CityService(CityStore store) {
        this.store = store;
    }

    public List<City> getAllCities() {
        return new ArrayList<>(store.getAllCities());
    }

    public City findById(int id) {
        return store.findById(id);
    }
}
