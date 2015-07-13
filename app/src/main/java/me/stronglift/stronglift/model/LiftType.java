package me.stronglift.stronglift.model;

import me.stronglift.stronglift.R;

/**
 * Created by Dusan Eremic.
 */
public enum LiftType {

    BENCH_PRESS(0, "Bench Press"), SQUAT(1, "Squat"), DEADLIFT(2, "Deadlift");

    private int id;
    private String description;

    private LiftType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return description;
    }
}
