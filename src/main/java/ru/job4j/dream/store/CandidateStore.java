package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.dream.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище в котором находятся кандидаты.
 *
 * @author yustas
 * @version 1.0
 */
@ThreadSafe
public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private final AtomicInteger ID = new AtomicInteger(3);

    private CandidateStore() {
        candidates.put(1, new Candidate(
                1, "Junior Java Job",
                "Collections",
                LocalDate.of(2022, 04, 1)));
        candidates.put(2, new Candidate(
                2, "Middle Java Job",
                "IO, SQl, GC",
                LocalDate.of(2022, 04, 5)));
        candidates.put(3, new Candidate(
                3, "Senior Java Job",
                "Spring, Hibernate",
                LocalDate.of(2022, 04, 11)));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(ID.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidates.replace(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }
}