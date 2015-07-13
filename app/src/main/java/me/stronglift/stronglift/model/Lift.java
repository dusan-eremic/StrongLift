package me.stronglift.stronglift.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Dusan Eremic.
 */
public class Lift {

    private String id;
    private LiftType liftType;
    private Integer repetition;
    private Integer weight;
    private Date time;
    private User owner;

    public Lift() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

    public Lift(LiftType liftType, Integer repetition, Integer weight, Date time, User owner) {
        this();
        this.liftType = liftType;
        this.repetition = repetition;
        this.weight = weight;
        this.time = time;
        this.owner = owner;
    }

    public Lift(String id, LiftType liftType, Integer repetition, Integer weight, Date time, User owner) {
        this.id = id;
        this.liftType = liftType;
        this.repetition = repetition;
        this.weight = weight;
        this.time = time;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LiftType getLiftType() {
        return liftType;
    }

    public void setLiftType(LiftType liftType) {
        this.liftType = liftType;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Lift{" +
                "liftType=" + liftType +
                ", repetition=" + repetition +
                ", weight=" + weight +
                ", time=" + time +
                ", owner=" + owner +
                '}';
    }
}
