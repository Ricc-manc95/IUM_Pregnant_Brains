// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.support.v4.app.Fragment;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timely.net.BaseClientFragment;
import com.google.android.calendar.timely.net.Client;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls1
    implements Function
{

    private final ProposeNewTimeFragment arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        int i = ((Integer)obj).intValue();
        if (((ProposeNewTimeFragment) (obj1)).rendezvousClient == null)
        {
            obj = new RuntimeException("Rendezvous client not initialized.");
            if (obj == null)
            {
                throw new NullPointerException();
            } else
            {
                return new com.google.common.util.concurrent.ture(((Throwable) (obj)));
            }
        } else
        {
            obj = ((ProposeNewTimeFragment) (obj1)).rendezvousClient;
            com.google.android.apps.calendar.proposenewtime.net._cls1 _lcls1 = new com.google.android.apps.calendar.proposenewtime.net.nt();
            _lcls1.nt((ImmutableList)ImmutableList.copyOf(((ProposeNewTimeFragment) (obj1)).state.getAttendees()).subList(1, ((ProposeNewTimeFragment) (obj1)).state.getAttendees().size()));
            _lcls1.s(Utils.getMillisFromJulianDay(i));
            _lcls1.s(Utils.getMillisFromJulianDay(i + 1));
            _lcls1.s(Utils.getTimeZone(((Fragment) (obj1)).getContext()));
            _lcls1.s(((ProposeNewTimeFragment) (obj1)).state.getEventId());
            _lcls1.s(((ProposeNewTimeFragment) (obj1)).state.getCalendarId());
            obj1 = _lcls1.Id();
            return ((BaseClientFragment) (obj)).client.sendRequest(obj1);
        }
    }

    (ProposeNewTimeFragment proposenewtimefragment)
    {
        arg$1 = proposenewtimefragment;
    }
}
