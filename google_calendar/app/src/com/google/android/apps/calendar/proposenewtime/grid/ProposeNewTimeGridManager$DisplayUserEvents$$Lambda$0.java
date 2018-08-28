// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.grid;

import android.accounts.Account;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.StateHolder;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.grid:
//            ProposeNewTimeGridManager

final class arg._cls1
    implements Predicate
{

    private final arg._cls1 arg$1;

    public final boolean apply(Object obj)
    {
        arg._cls1 _lcls1 = arg$1;
        for (obj = (TimelineItem)obj; !((TimelineItem) (obj)).getSourceAccountName().equals(_lcls1._fld1.stateHolder.getState().getAccount().name) || (obj instanceof TimelineEvent) && !((TimelineEvent)obj).ownerAccount.equals(_lcls1._fld1.stateHolder.getState().getCalendarId());)
        {
            return false;
        }

        return true;
    }

    ( )
    {
        arg$1 = ;
    }
}
