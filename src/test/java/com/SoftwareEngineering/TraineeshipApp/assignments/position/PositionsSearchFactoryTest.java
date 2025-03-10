package com.SoftwareEngineering.TraineeshipApp.assignments.position;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PositionsSearchFactoryTest {

    @Mock
    private SearchBasedOnLocation searchBasedOnLocation;

    @Mock
    private SearchBasedOnInterests searchBasedOnInterests;

    @Mock
    private CompositeSearch compositeSearch;

    @InjectMocks
    private PositionsSearchFactory positionsSearchFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_LocationStrategy() {
        PositionsSearchStrategy strategy = positionsSearchFactory.create("location");

        assertNotNull(strategy);
        assertEquals(searchBasedOnLocation, strategy); 
    }

    @Test
    void testCreate_InterestsStrategy() {
        PositionsSearchStrategy strategy = positionsSearchFactory.create("interests");

        assertNotNull(strategy);
        assertEquals(searchBasedOnInterests, strategy); 
    }

    @Test
    void testCreate_BothStrategy() {
        PositionsSearchStrategy strategy = positionsSearchFactory.create("both");

        assertNotNull(strategy);
        assertEquals(compositeSearch, strategy); 

    }

    @Test
    void testCreate_InvalidStrategy() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            positionsSearchFactory.create("invalid");
        });

        assertEquals("Invalid search strategy: invalid", exception.getMessage());
    }
}
