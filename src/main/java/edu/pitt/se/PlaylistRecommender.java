package edu.pitt.se;

import java.util.List;

public class PlaylistRecommender {

    public static String classifyEnergy(List<Integer> bpms) {
        if (bpms == null || bpms.isEmpty()) {
            throw new IllegalArgumentException("BPM list can't be null/empty");
        }

        double sum = 0.0;
        int count = 0;

        for (Integer bpm : bpms) {
            if (bpm == null) {
                throw new IllegalArgumentException("BPM list can't contain null elements");
            }
            sum += bpm;
            count++;
        }

        double avg = sum / count;

        if (avg >= 140.0) {
            return "HIGH";
        } else if (avg >= 100.0) { // 100–139
            return "MEDIUM";
        } else {
            return "LOW"; // < 100
        }
    }

    public static boolean isValidTrackTitle(String title) {
        if (title == null) {
            return false;
        }

        int length = title.length();
        if (length < 1 || length > 30) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            char c = title.charAt(i);
            // alphabetic characters and spaces only
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }

        return true;
    }

    public static int normalizeVolume(int volumeDb) {
        if (volumeDb < 0) {
            return 0;
        } else if (volumeDb > 100) {
            return 100;
        } else {
            return volumeDb;
        }
    }
}
