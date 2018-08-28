// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;


// Referenced classes of package com.google.apps.xplat.logging:
//            LoggingApi

final class NoOpLoggingApi
    implements LoggingApi
{

    NoOpLoggingApi()
    {
    }

    public final void log(String s)
    {
    }

    public final void log(String s, Object obj)
    {
    }

    public final void log(String s, Object obj, Object obj1)
    {
    }

    public final volatile LoggingApi withCause(Throwable throwable)
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }
}
