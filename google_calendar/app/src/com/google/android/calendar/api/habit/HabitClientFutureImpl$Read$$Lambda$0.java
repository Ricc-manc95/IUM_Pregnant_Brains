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

    private final  arg$1;

    public final Object call()
    {
        int j = 0;
        Object obj1 = arg$1;
        Object obj = ((arg._cls1) (obj1))._fld1;
        obj1 = ((arg._cls1) (obj1)).;
        obj = ((HabitClientBase) (obj)).api.read(((HabitDescriptor) (obj1)));
        int i;
        if (obj != null)
        {
            i = 0;
        } else
        {
            i = 13;
        }
        if (obj != null)
        {
            j = 1;
        }
        return new (i, j, ((Habit) (obj)), null);
    }

    ( )
    {
        arg$1 = ;
    }
}
