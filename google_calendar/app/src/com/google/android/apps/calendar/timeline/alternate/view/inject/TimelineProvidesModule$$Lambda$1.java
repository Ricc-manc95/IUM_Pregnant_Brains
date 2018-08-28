// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.CreationItemToEventAdapter;
import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.common.base.Optional;

final class arg._cls1
    implements BiFunction
{

    private final CreationItemToEventAdapter arg$1;

    public final Object apply(Object obj, Object obj1)
    {
        obj = arg$1;
        return ((Optional)obj1).transform(((com.google.common.base.Function) (obj)));
    }

    apter(CreationItemToEventAdapter creationitemtoeventadapter)
    {
        arg$1 = creationitemtoeventadapter;
    }
}
