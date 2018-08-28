// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.app.Activity;
import com.google.android.calendar.timeline.chip.ChipFactory;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.recycler.Recycler;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            ChipRecyclerManager

final class  extends ActivitySingletonCache
{

    protected final Object createInstance(Activity activity)
    {
        return new Recycler(new ChipRecyclerManager(ChipFactory.getInstance(activity)), 100);
    }

    ()
    {
    }
}
