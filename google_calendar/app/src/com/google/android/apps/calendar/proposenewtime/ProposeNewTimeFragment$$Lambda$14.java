// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.calendar.api.event.EventKey;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls1
    implements Predicate
{

    private final EventKey arg$1;

    public final boolean apply(Object obj)
    {
        return ProposeNewTimeFragment.lambda$filterOutProposalTarget$11$ProposeNewTimeFragment(arg$1, (TimeRangeEntry)obj);
    }

    (EventKey eventkey)
    {
        arg$1 = eventkey;
    }
}
