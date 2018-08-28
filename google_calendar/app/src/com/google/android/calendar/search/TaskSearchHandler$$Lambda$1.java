// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import com.google.common.base.Function;
import java.util.Collection;

// Referenced classes of package com.google.android.calendar.search:
//            TaskSearchHandler

final class arg._cls1
    implements Function
{

    private final TaskSearchHandler arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (Collection)obj;
        obj1 = ((TaskSearchHandler) (obj1)).adapter;
        obj1.getClass();
        return new com.google.common.collect.ection(((Collection) (obj)), new <init>(((com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter) (obj1))));
    }

    (TaskSearchHandler tasksearchhandler)
    {
        arg$1 = tasksearchhandler;
    }
}
