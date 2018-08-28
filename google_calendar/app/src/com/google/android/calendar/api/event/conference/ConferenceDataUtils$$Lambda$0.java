// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableCollection;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            Conference, ConferenceDataUtils

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        obj = (Conference)obj;
        return ConferenceDataUtils.PRIMARY_CONFERENCE_TYPES.contains(Integer.valueOf(((Conference) (obj)).getType()));
    }


    private ()
    {
    }
}
