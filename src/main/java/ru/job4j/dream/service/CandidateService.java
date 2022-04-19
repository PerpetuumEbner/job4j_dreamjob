package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateStore;

import java.util.Collection;

/**
 * Верхний слой хранилища CandidateStore в котором находятся кандидаты.
 *
 * @author yustas
 * @version 1.0
 */
@ThreadSafe
@Controller
public class CandidateService {

    private static final CandidateService INST = new CandidateService();

    private final CandidateStore store = CandidateStore.instOf();

    public static CandidateService instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public void add(Candidate candidate) {
        store.add(candidate);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }
}