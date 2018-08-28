// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.timely:
//            EventRangedQueryHandler, MonthData, RangedData

final class ryHandler extends EventRangedQueryHandler
{

    protected final ntResults createStorage(int i)
    {
        return new hEventResults(i);
    }

    protected final void processResults(RangedData rangeddata, ntResults ntresults)
    {
        ntResults ntresults1 = ntresults;
        if (ntresults == null)
        {
            ntresults1 = createStorage(0);
        }
        if (rangeddata instanceof MonthData)
        {
            ((MonthData)rangeddata).addEvents(ntresults1);
        }
    }

    ntResults(Context context, boolean flag)
    {
        super(context, true);
    }
}
