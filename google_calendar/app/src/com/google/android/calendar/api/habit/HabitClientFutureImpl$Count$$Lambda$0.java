// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitClientBase, HabitApiStoreImpl

final class arg._cls1
    implements Callable
{

    private final arg._cls1 arg$1;

    public final Object call()
    {
        Object obj = arg$1;
        HabitClientFutureImpl habitclientfutureimpl = ((arg._cls1) (obj))._fld1;
        obj = ((arg._cls1) (obj))._fld1;
        int j = ((HabitClientBase) (habitclientfutureimpl)).api.count(((HabitFilterOptions) (obj)));
        int i;
        if (j >= 0)
        {
            i = 0;
        } else
        {
            i = 13;
        }
        return new arg._cls1(i, j, null, null);
    }

    Q(Q q)
    {
        arg$1 = q;
    }
}
