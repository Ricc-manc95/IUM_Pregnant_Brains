// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Context

public static final class defaultValue
{

    public final Object defaultValue;
    private final String name;

    public final String toString()
    {
        return name;
    }

    (String s)
    {
        this(s, null);
    }

    private <init>(String s, Object obj)
    {
        name = (String)Context.checkNotNull(s, "name");
        defaultValue = null;
    }
}
