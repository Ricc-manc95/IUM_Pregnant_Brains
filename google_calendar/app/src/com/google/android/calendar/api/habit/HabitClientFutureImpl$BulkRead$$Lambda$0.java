// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitApiStoreImpl

final class arg._cls1
    implements Callable
{

    private final s arg$1;

    public final Object call()
    {
        arg._cls1 _lcls1 = arg$1;
        HabitClientFutureImpl habitclientfutureimpl = _lcls1._fld1;
        Habit ahabit[] = HabitApiStoreImpl.read(_lcls1.s);
        return new s(0, ahabit.length, null, ahabit);
    }

    ( )
    {
        arg$1 = ;
    }
}
