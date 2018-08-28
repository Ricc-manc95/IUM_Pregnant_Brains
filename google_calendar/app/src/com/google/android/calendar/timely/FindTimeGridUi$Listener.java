// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineSuggestion

public interface 
{

    public abstract void onGridCancelled();

    public abstract void onGridSuggestionSwiped(TimelineSuggestion timelinesuggestion, int i);

    public abstract void onGridTimeSlotSelected(TimelineSuggestion timelinesuggestion, boolean flag, boolean flag1);
}
