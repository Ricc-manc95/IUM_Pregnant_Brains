// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.birthday;

import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.timely.TimelineBirthday;

// Referenced classes of package com.google.android.calendar.newapi.screen.birthday:
//            BirthdayViewScreenModel

public final class BirthdayViewScreenLoader extends CompositeLoader
{

    private final TimelineBirthday timelineItem;

    public BirthdayViewScreenLoader(TimelineBirthday timelinebirthday)
    {
        timelineItem = timelinebirthday;
    }

    public final Object getResult()
    {
        return new BirthdayViewScreenModel(timelineItem);
    }

    public final void load()
    {
        onLoadingSuccess(this);
    }
}
