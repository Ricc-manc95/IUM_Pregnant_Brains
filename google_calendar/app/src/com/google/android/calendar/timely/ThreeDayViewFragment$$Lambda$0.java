// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            WeekRecyclerAdapter

final class arg._cls1
    implements Runnable
{

    private final WeekRecyclerAdapter arg$1;

    public final void run()
    {
        ((android.support.v7.widget.Lambda._cls0) (arg$1)).e.notifyChanged();
    }

    (WeekRecyclerAdapter weekrecycleradapter)
    {
        arg$1 = weekrecycleradapter;
    }
}
