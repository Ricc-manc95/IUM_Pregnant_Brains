// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.grid;

import com.google.android.apps.calendar.proposenewtime.state.Attendee;
import com.google.common.base.Predicate;

final class arg._cls1
    implements Predicate
{

    private final String arg$1;

    public final boolean apply(Object obj)
    {
        String s = arg$1;
        obj = ((Attendee)obj).getEmail();
        return obj != null && ((String) (obj)).equalsIgnoreCase(s);
    }

    (String s)
    {
        arg$1 = s;
    }
}
