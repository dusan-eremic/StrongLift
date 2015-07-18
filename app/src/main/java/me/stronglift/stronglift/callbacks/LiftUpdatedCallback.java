package me.stronglift.stronglift.callbacks;

import me.stronglift.stronglift.model.Lift;

/**
 *
 *
 * Created by Dusan Eremic.
 */
public interface LiftUpdatedCallback {

    void update(Lift lift);

    void remove(Lift lift);
}
