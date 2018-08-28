// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            ImmediateFuture

public static final class value extends ImmediateFuture
{

    public static final value NULL = new <init>(null);
    private final Object value;

    public final Object get()
    {
        return value;
    }

    public final String toString()
    {
        String s = super.toString();
        String s1 = String.valueOf(value);
        return (new StringBuilder(String.valueOf(s).length() + 27 + String.valueOf(s1).length())).append(s).append("[status=SUCCESS, result=[").append(s1).append("]]").toString();
    }


    public Q(Object obj)
    {
        value = obj;
    }
}
