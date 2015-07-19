package me.stronglift.stronglift.model;

/**
 * Enum sa tipovima lifta.
 *
 * Created by Dusan Eremic.
 */
public enum LiftType {

    BENCH(0, "Bench Press"), SQUAT(1, "Squat"), DEADLIFT(2, "Deadlift");

    /**
     * ID
     */
    private int id;

    /**
     * Opis koji će se koristiti za prikaz u UI
     */
    private String description;

    /**
     *  Private konstruktor
     */
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
