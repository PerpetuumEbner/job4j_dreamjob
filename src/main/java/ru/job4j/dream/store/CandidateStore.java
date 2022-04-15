package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Хранилище в котором находятся кандидаты.
 *
 * @author yustas
 * @version 1.0
 */
public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Junior Java Job"));
        candidates.put(2, new Candidate(2, "Middle Java Job"));
        candidates.put(3, new Candidate(3, "Senior Java Job"));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}