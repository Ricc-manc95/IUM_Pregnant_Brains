// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskItem

public final class arg._cls1
    implements Function
{

    private final boolean arg$1;

    public final Object apply(Object obj)
    {
        boolean flag = arg$1;
        return ((TaskItem)obj).setDone(flag);
    }

    public (boolean flag)
    {
        arg$1 = flag;
    }
}
