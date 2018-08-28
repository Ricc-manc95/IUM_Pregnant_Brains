// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.gms;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.apps.calendar.util.gms:
//            ResultException

public final class arg._cls1
    implements Function
{

    private final Function arg$1;

    public final Object apply(Object obj)
    {
        Function function = arg$1;
        obj = (Result)obj;
        boolean flag;
        if (((Result) (obj)).getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return function.apply(obj);
        } else
        {
            throw new ResultException(((Result) (obj)).getStatus());
        }
    }

    public (Function function)
    {
        arg$1 = function;
    }
}
