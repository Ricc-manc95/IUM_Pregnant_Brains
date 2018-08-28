// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitModifications, HabitClientBase, HabitApiStoreImpl

final class arg._cls1
    implements Callable
{

    private final arg._cls1 arg$1;

    public final Object call()
    {
        boolean flag = true;
        Object obj = arg$1;
        HabitClientFutureImpl habitclientfutureimpl = ((arg._cls1) (obj))._fld1;
        obj = ((arg._cls1) (obj))._fld1;
        if (((HabitModifications) (obj)).getOriginal() == null)
        {
            return new arg._cls1(13, 0, null, null);
        }
        int i;
        if (((HabitClientBase) (habitclientfutureimpl)).api.update(((HabitModifications) (obj)), null) != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        return new arg._cls1(0, i, null, null);
    }

    ( )
    {
        arg$1 = ;
    }
}
