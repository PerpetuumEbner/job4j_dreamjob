package ru.job4j.dream.store;

import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.Candidate;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDBStoreTest {
    @Test
    public void whenCreateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(
                2,
                "Middle Java Job",
                "IO, SQl, GC",
                LocalDate.of(2022, 04, 5)
                );
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }
}