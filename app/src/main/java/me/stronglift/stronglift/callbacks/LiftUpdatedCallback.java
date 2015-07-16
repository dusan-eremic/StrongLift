package me.stronglift.stronglift.callbacks;

import me.stronglift.stronglift.model.Lift;

/**
 * Created by dusan on 16/07/15.
 */
public interface LiftUpdatedCallback {

    void update(Lift lift);

    void remove(Lift lift);
}
