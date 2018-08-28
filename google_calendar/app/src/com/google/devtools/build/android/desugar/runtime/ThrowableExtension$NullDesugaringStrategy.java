// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devtools.build.android.desugar.runtime;

import java.io.PrintStream;
import java.io.PrintWriter;

// Referenced classes of package com.google.devtools.build.android.desugar.runtime:
//            ThrowableExtension

static final class egy extends egy
{

    public final void addSuppressed(Throwable throwable, Throwable throwable1)
    {
    }

    public final Throwable[] getSuppressed(Throwable throwable)
    {
        return EMPTY_THROWABLE_ARRAY;
    }

    public final void printStackTrace(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    public final void printStackTrace(Throwable throwable, PrintStream printstream)
    {
        throwable.printStackTrace(printstream);
    }

    public final void printStackTrace(Throwable throwable, PrintWriter printwriter)
    {
        throwable.printStackTrace(printwriter);
    }

    egy()
    {
    }
}
