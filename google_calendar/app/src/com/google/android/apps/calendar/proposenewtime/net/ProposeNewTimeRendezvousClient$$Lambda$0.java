// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.net;

import com.google.calendar.suggest.v2.Event;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.net:
//            ProposeNewTimeRendezvousClient

final class arg._cls1
    implements Predicate
{

    private final String arg$1;

    public final boolean apply(Object obj)
    {
        return ProposeNewTimeRendezvousClient.lambda$buildAttendeeMap$0$ProposeNewTimeRendezvousClient(arg$1, (Event)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}