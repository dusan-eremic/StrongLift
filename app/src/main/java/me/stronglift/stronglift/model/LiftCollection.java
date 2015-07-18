package me.stronglift.stronglift.model;

import java.util.List;

/**
 *
 *
 * Created by Dusan Eremic.
 */
public class LiftCollection {

    private List<Lift> items;

    public void setItems(List<Lift> items) {
        this.items = items;
    }

    public List<Lift> getItems() {
        return items;
    }
}
