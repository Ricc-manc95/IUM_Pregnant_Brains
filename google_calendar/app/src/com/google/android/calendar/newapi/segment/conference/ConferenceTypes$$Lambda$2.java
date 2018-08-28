// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import com.google.android.calendar.api.event.conference.Conference;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference:
//            ConferenceTypes

final class arg._cls1
    implements Predicate
{

    private final int arg$1;

    public final boolean apply(Object obj)
    {
        return ConferenceTypes.lambda$firstConferenceWithType$1$ConferenceTypes(arg$1, (Conference)obj);
    }

    (int i)
    {
        arg$1 = i;
    }
}
