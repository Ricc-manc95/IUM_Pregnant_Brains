// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import com.google.android.calendar.timely.rooms.data.Room;
import com.google.common.base.Predicate;
import java.util.List;

final class arg._cls1
    implements Predicate
{

    private final List arg$1;

    public final boolean apply(Object obj)
    {
        return !arg$1.contains((Room)obj);
    }

    (List list)
    {
        arg$1 = list;
    }
}
