// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import com.google.android.apps.calendar.config.remote.PrimesApiLoggingFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.gms.phenotype.PhenotypeFlag;

public final class ApiOperation extends Enum
    implements com.google.calendar.v2a.android.util.metric.MetricUtils.Operation
{

    private static final ApiOperation $VALUES[];
    public static final ApiOperation CALENDAR_COUNT;
    public static final ApiOperation CALENDAR_LIST;
    public static final ApiOperation CALENDAR_READ;
    public static final ApiOperation CALENDAR_SUBSCRIBE;
    public static final ApiOperation CALENDAR_UNSUBSCRIBE;
    public static final ApiOperation CALENDAR_UPDATE;
    public static final ApiOperation EVENT_CREATE;
    public static final ApiOperation EVENT_CREATE_DESCRIPTOR;
    public static final ApiOperation EVENT_DELETE;
    public static final ApiOperation EVENT_ICS_LIST;
    public static final ApiOperation EVENT_INSTANCES_GET;
    public static final ApiOperation EVENT_INSTANCES_LIST;
    public static final ApiOperation EVENT_INSTANCES_SEARCH;
    public static final ApiOperation EVENT_INSTANCES_SEARCH_HABITS;
    private static final ApiOperation EVENT_LIST;
    public static final ApiOperation EVENT_READ;
    public static final ApiOperation EVENT_UPDATE;
    public static final ApiOperation HABIT_BULK_READ;
    public static final ApiOperation HABIT_COUNT;
    public static final ApiOperation HABIT_CREATE;
    private static final ApiOperation HABIT_DEFER;
    public static final ApiOperation HABIT_LIST;
    public static final ApiOperation HABIT_READ;
    public static final ApiOperation HABIT_UPDATE;
    private static final ApiOperation HABIT_UPDATE_COMPLETION;
    public static final ApiOperation SETTINGS_LIST;
    public static final ApiOperation SETTINGS_READ;
    public static final ApiOperation SETTINGS_UPDATE;
    private final String action;

    private ApiOperation(String s, int i, String s1)
    {
        super(s, i);
        action = s1;
    }

    public static ApiOperation[] values()
    {
        return (ApiOperation[])$VALUES.clone();
    }

    public final String getAction()
    {
        return action;
    }

    public final String getCategory()
    {
        return "API";
    }

    public final String getFullName()
    {
        return com.google.calendar.v2a.android.util.metric.MetricUtils.Operation..CC.getFullName(this);
    }

    public final double getSampling()
    {
        switch (ordinal())
        {
        default:
            return ((Double)RemoteFeatureConfig.PRIMES_API_LOGGING.mSamplingApiFlag.get()).doubleValue();

        case 24: // '\030'
            return ((Double)RemoteFeatureConfig.PRIMES_API_LOGGING.mSamplingApiEventListFlag.get()).doubleValue();
        }
    }

    static 
    {
        SETTINGS_LIST = new ApiOperation("SETTINGS_LIST", 0, "Settings.List");
        SETTINGS_READ = new ApiOperation("SETTINGS_READ", 1, "Settings.Read");
        SETTINGS_UPDATE = new ApiOperation("SETTINGS_UPDATE", 2, "Settings.Update");
        CALENDAR_COUNT = new ApiOperation("CALENDAR_COUNT", 3, "Calendar.Count");
        CALENDAR_LIST = new ApiOperation("CALENDAR_LIST", 4, "Calendar.List");
        CALENDAR_READ = new ApiOperation("CALENDAR_READ", 5, "Calendar.Read");
        CALENDAR_SUBSCRIBE = new ApiOperation("CALENDAR_SUBSCRIBE", 6, "Calendar.Subscribe");
        CALENDAR_UNSUBSCRIBE = new ApiOperation("CALENDAR_UNSUBSCRIBE", 7, "Calendar.Unsubscribe");
        CALENDAR_UPDATE = new ApiOperation("CALENDAR_UPDATE", 8, "Calendar.Update");
        EVENT_CREATE = new ApiOperation("EVENT_CREATE", 9, "Event.Create");
        EVENT_CREATE_DESCRIPTOR = new ApiOperation("EVENT_CREATE_DESCRIPTOR", 10, "Event.CreateDescriptor");
        EVENT_DELETE = new ApiOperation("EVENT_DELETE", 11, "Event.Delete");
        EVENT_ICS_LIST = new ApiOperation("EVENT_ICS_LIST", 12, "Event.IcsList");
        EVENT_LIST = new ApiOperation("EVENT_LIST", 13, "Event.List");
        EVENT_READ = new ApiOperation("EVENT_READ", 14, "Event.Read");
        EVENT_UPDATE = new ApiOperation("EVENT_UPDATE", 15, "Event.Update");
        HABIT_COUNT = new ApiOperation("HABIT_COUNT", 16, "Habit.Count");
        HABIT_CREATE = new ApiOperation("HABIT_CREATE", 17, "Habit.Create");
        HABIT_DEFER = new ApiOperation("HABIT_DEFER", 18, "Habit.Defer");
        HABIT_LIST = new ApiOperation("HABIT_LIST", 19, "Habit.List");
        HABIT_READ = new ApiOperation("HABIT_READ", 20, "Habit.Read");
        HABIT_BULK_READ = new ApiOperation("HABIT_BULK_READ", 21, "Habit.BulkRead");
        HABIT_UPDATE = new ApiOperation("HABIT_UPDATE", 22, "Habit.Update");
        HABIT_UPDATE_COMPLETION = new ApiOperation("HABIT_UPDATE_COMPLETION", 23, "Habit.UpdateCompletion");
        EVENT_INSTANCES_LIST = new ApiOperation("EVENT_INSTANCES_LIST", 24, "EventInstance.List");
        EVENT_INSTANCES_GET = new ApiOperation("EVENT_INSTANCES_GET", 25, "EventInstance.Get");
        EVENT_INSTANCES_SEARCH = new ApiOperation("EVENT_INSTANCES_SEARCH", 26, "EventInstance.Search");
        EVENT_INSTANCES_SEARCH_HABITS = new ApiOperation("EVENT_INSTANCES_SEARCH_HABITS", 27, "EventInstance.SearchHabits");
        $VALUES = (new ApiOperation[] {
            SETTINGS_LIST, SETTINGS_READ, SETTINGS_UPDATE, CALENDAR_COUNT, CALENDAR_LIST, CALENDAR_READ, CALENDAR_SUBSCRIBE, CALENDAR_UNSUBSCRIBE, CALENDAR_UPDATE, EVENT_CREATE, 
            EVENT_CREATE_DESCRIPTOR, EVENT_DELETE, EVENT_ICS_LIST, EVENT_LIST, EVENT_READ, EVENT_UPDATE, HABIT_COUNT, HABIT_CREATE, HABIT_DEFER, HABIT_LIST, 
            HABIT_READ, HABIT_BULK_READ, HABIT_UPDATE, HABIT_UPDATE_COMPLETION, EVENT_INSTANCES_LIST, EVENT_INSTANCES_GET, EVENT_INSTANCES_SEARCH, EVENT_INSTANCES_SEARCH_HABITS
        });
    }
}
