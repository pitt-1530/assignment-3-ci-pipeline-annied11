package edu.pitt.se;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistRecommenderTest {

    @Test
    public void testClassifyEnergyCategoriesAndInvalidInput() {
        // HIGH: average BPM ≥ 140
        assertEquals("HIGH",
                PlaylistRecommender.classifyEnergy(Arrays.asList(150, 140, 130)));

        // MEDIUM: 100–139
        assertEquals("MEDIUM",
                PlaylistRecommender.classifyEnergy(Arrays.asList(120, 100, 110)));

        // LOW: < 100
        assertEquals("LOW",
                PlaylistRecommender.classifyEnergy(Arrays.asList(80, 90, 95)));

        // reject null list
        assertThrows(IllegalArgumentException.class,
                () -> PlaylistRecommender.classifyEnergy(null));

        // reject empty list
        assertThrows(IllegalArgumentException.class,
                () -> PlaylistRecommender.classifyEnergy(Collections.emptyList()));
    }

    @Test
    public void testIsValidTrackTitle() {
        // valid titles
        assertTrue(PlaylistRecommender.isValidTrackTitle("Hello World"));
        assertTrue(PlaylistRecommender.isValidTrackTitle("A"));  // length 1

        // exactly 30 characters, letters only
        String thirtyChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD"; // 26 + 4 = 30
        assertTrue(PlaylistRecommender.isValidTrackTitle(thirtyChars));

        // null should be invalid
        assertFalse(PlaylistRecommender.isValidTrackTitle(null));

        // too long 
        assertFalse(PlaylistRecommender.isValidTrackTitle("This title is definitely too long"));

        // has digits or special characters → invalid
        assertFalse(PlaylistRecommender.isValidTrackTitle("Track 1"));
        assertFalse(PlaylistRecommender.isValidTrackTitle("Rock&Roll"));
    }

    @Test
    public void testNormalizeVolumeClamping() {
        // < 0 → clamped to 0
        assertEquals(0, PlaylistRecommender.normalizeVolume(-10));

        // 0–100 → unchanged
        assertEquals(0, PlaylistRecommender.normalizeVolume(0));
        assertEquals(50, PlaylistRecommender.normalizeVolume(50));
        assertEquals(100, PlaylistRecommender.normalizeVolume(100));

        // > 100 → clamped to 100
        assertEquals(100, PlaylistRecommender.normalizeVolume(120));
    }
}
