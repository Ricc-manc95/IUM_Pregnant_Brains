// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.common.io:
//            Closeables

final class 
    implements 
{

    public static final  INSTANCE = new <init>();

    public final void suppress(Closeable closeable, Throwable throwable, Throwable throwable1)
    {
        throwable = Closeables.logger;
        Level level = Level.WARNING;
        closeable = String.valueOf(closeable);
        throwable.logp(level, "com.google.common.io.Closer$LoggingSuppressor", "suppress", (new StringBuilder(String.valueOf(closeable).length() + 42)).append("Suppressing exception thrown when closing ").append(closeable).toString(), throwable1);
    }


    ()
    {
    }
}
