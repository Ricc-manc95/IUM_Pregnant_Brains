// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.content.Context;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference:
//            ConferenceTypes

final class arg._cls1
    implements Predicate
{

    private final Context arg$1;

    public final boolean apply(Object obj)
    {
        return ConferenceTypes.lambda$getVideoConference$0$ConferenceTypes(arg$1, (Conference)obj);
    }

    (Context context)
    {
        arg$1 = context;
    }
}
