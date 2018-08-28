// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import com.google.android.calendar.avatar.ContactInfo;
import com.google.common.base.Predicate;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AttendeeContact

final class arg._cls1
    implements Predicate
{

    private final Set arg$1;

    public final boolean apply(Object obj)
    {
        return !arg$1.contains(((AttendeeContact)obj).getContact().primaryEmail);
    }

    (Set set)
    {
        arg$1 = set;
    }
}
