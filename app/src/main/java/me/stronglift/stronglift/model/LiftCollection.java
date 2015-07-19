package me.stronglift.stronglift.model;

import java.util.List;

/**
 * Pomoćna klasa koja služi da se lista liftova iz JSON
 * poruke prepakuje u Java listu.
 * <p>
 * Created by Dusan Eremic.
 */
public class LiftCollection {

    /**
     * Lista liftova
     */
    private List<Lift> items;

    public List<Lift> getItems() {
        return items;
    }

    public void setItems(List<Lift> items) {
        this.items = items;
    }
}
