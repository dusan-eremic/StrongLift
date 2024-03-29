package me.stronglift.stronglift.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Lift model
 * <p>
 * Created by Dusan Eremic.
 */
public class Lift {

    /**
     * ID
     */
    private String id;

    /**
     * Tip lifta
     */
    private LiftType liftType;

    /**
     * Broj ponavljanja
     */
    private Integer repetition;

    /**
     * Težina
     */
    private BigDecimal weight;

    /**
     * Datum unosa
     */
    private Date time;

    /**
     * Konstruktor
     */
    public Lift() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

    // ##### GET i SET metode #####

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LiftType getLiftType() {
        return liftType;
    }

    public void setLiftType(int liftTypeId) {

        for (LiftType liftType : LiftType.values()) {
            if (liftType.getId() == liftTypeId) {
                this.liftType = liftType;
                return;
            }
        }

        throw new IllegalArgumentException("Unknown liftType id: " + liftTypeId);
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    // ##### GET i SET metode #####

    /**
     * Metoda računa 1RM na osnovu broja ponavljanja i težine.
     *
     * @return
     */
    public BigDecimal calcOneRepMax() {
        if (weight == null || !(weight.doubleValue() > 0) || repetition == null || !(repetition > 0)) {
            return new BigDecimal(0).setScale(2);
        } else {
            return new BigDecimal(weight.doubleValue() / (1.0278 - (0.0278 * repetition))).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Lift lift = (Lift) o;

        return id.equals(lift.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Lift{" +
                "liftType=" + liftType +
                ", repetition=" + repetition +
                ", weight=" + weight +
                ", time=" + time + '}';
    }
}
