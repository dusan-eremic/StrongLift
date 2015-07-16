package me.stronglift.stronglift.rest;

import java.util.List;

import me.stronglift.stronglift.model.Lift;

/**
 * Created by dusan on 16/07/15.
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
