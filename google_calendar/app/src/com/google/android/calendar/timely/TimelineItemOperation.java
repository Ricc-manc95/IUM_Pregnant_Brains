// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem, TimelineEvent, TimelineTaskType, TimelineAttendeeEvent, 
//            TimelineBirthday, TimelineGroove, TimelineTaskBundle, TimelineTask

public class TimelineItemOperation
{

    public TimelineItemOperation()
    {
    }

    public transient Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return null;
    }

    public transient Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        return onAny(timelineevent, aobj);
    }

    public transient Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
    {
        return onAny(timelinetasktype, aobj);
    }

    public transient Object onAttendeeEvent(TimelineAttendeeEvent timelineattendeeevent, Object aobj[])
    {
        return onAnyEvent(timelineattendeeevent, aobj);
    }

    public transient Object onBirthdayBundle(TimelineBirthday timelinebirthday, Object aobj[])
    {
        return onAny(timelinebirthday, aobj);
    }

    public transient Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        return onAnyEvent(timelinegroove, aobj);
    }

    public transient Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        return onAnyReminder(timelinetaskbundle, aobj);
    }

    public transient Object onSingleReminder(TimelineTask timelinetask, Object aobj[])
    {
        return onAnyReminder(timelinetask, aobj);
    }
}
