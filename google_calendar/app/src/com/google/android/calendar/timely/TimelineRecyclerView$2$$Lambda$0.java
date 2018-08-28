// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            TimelineRecyclerView

final class arg._cls2
    implements Runnable
{

    private final .notifyUpdateFinished arg$1;
    private final  arg$2;

    public final void run()
    {
        arg._cls2 _lcls2 = arg$1;
          = arg$2;
        _lcls2._fld2.onDataReady();
        .notifyUpdateFinished();
    }

    ( ,  1)
    {
        arg$1 = ;
        arg$2 = 1;
    }
}
