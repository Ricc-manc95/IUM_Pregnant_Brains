// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.belong;

import android.util.SparseArray;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;

public final class FitIntegrationConstants
{

    public static final SparseArray HABIT_TYPE_TO_FIT_ACTIVITY_MAP;

    static 
    {
        SparseArray sparsearray = new SparseArray();
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP = sparsearray;
        sparsearray.put(257, ImmutableSet.of("weightlifting", "strength_training", "circuit_training", "kettlebell_training", "crossfit", "interval_training.high_intensity", new String[] {
            "interval_training"
        }));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(258, ImmutableSet.construct(4, new Object[] {
            "running", "running.jogging", "running.sand", "running.treadmill"
        }));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(262, ImmutableSet.construct(3, new Object[] {
            "biking", "biking.mountain", "biking.road"
        }));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(263, ImmutableSet.construct(3, new Object[] {
            "swimming", "swimming.open_water", "swimming.pool"
        }));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(260, Collections.singleton("yoga"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(261, Collections.singleton("hiking"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(264, Collections.singleton("rock_climbing"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(265, Collections.singleton("tennis"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(266, Collections.singleton("badminton"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(267, Collections.singleton("baseball"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(268, Collections.singleton("basketball"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(269, Collections.singleton("football.soccer"));
        HABIT_TYPE_TO_FIT_ACTIVITY_MAP.put(1026, Collections.singleton("meditation"));
    }
}
