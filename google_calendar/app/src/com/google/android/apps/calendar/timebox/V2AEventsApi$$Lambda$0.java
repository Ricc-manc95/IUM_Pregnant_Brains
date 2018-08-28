// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.common.base.Function;
import java.util.Map;

final class arg._cls2
    implements Function
{

    private final boolean arg$1;
    private final String arg$2;

    public final Object apply(Object obj)
    {
        boolean flag = arg$1;
        String s = arg$2;
        return new <init>((Map)obj, flag, s);
    }

    (boolean flag, String s)
    {
        arg$1 = flag;
        arg$2 = s;
    }
}
