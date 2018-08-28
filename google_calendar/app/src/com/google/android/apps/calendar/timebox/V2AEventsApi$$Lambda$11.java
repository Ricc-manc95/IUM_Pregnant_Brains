// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.Map;

public final class arg._cls2
    implements Function
{

    private final boolean arg$1;
    private final r arg$2;

    public final Object apply(Object obj)
    {
        boolean flag = arg$1;
        r r = arg$2;
        obj = new init>((Map)obj, flag, null);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj = (Predicate)obj;
        if (r == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.base.<init>(Arrays.asList(new Predicate[] {
                obj, (Predicate)r
            }));
        }
    }

    public r(boolean flag, r r)
    {
        arg$1 = flag;
        arg$2 = r;
    }
}
