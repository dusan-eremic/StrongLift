package me.stronglift.stronglift.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;
import me.stronglift.stronglift.model.User;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Lift> ITEMS = new ArrayList<Lift>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Lift> ITEM_MAP = new HashMap<String, Lift>();

    static {
        // Add 3 sample items.
//        addItem(new Lift(LiftType.BENCH_PRESS, 10, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.DEADLIFT, 8, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.DEADLIFT, 6, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.SQUAT, 7, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.BENCH_PRESS, 5, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.BENCH_PRESS, 10, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.DEADLIFT, 10, 9, new Date(), new User()));
//        addItem(new Lift(LiftType.BENCH_PRESS, 5, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.SQUAT, 10, 20, new Date(), new User()));
//        addItem(new Lift(LiftType.SQUAT, 10, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.BENCH_PRESS, 10, 80, new Date(), new User()));
//        addItem(new Lift(LiftType.BENCH_PRESS, 10, 80, new Date(), new User()));
    }

    private static void addItem(Lift item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

}
