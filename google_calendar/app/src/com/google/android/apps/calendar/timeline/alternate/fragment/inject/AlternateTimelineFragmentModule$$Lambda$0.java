// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.inject;

import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineFragment;
import com.google.android.apps.calendar.util.function.BiFunction;

final class 
    implements BiFunction
{

    public static final BiFunction $instance = new <init>();

    public final Object apply(Object obj, Object obj1)
    {
        return new AlternateTimelineFragment((com.google.android.apps.calendar.timeline.alternate.fragment.api.._cls0)obj, ((Integer)obj1).intValue());
    }


    private ()
    {
    }
}
