// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devtools.build.android.desugar.runtime;

import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class 
{

    public static final Throwable EMPTY_THROWABLE_ARRAY[] = new Throwable[0];

    public abstract void addSuppressed(Throwable throwable, Throwable throwable1);

    public abstract Throwable[] getSuppressed(Throwable throwable);

    public abstract void printStackTrace(Throwable throwable);

    public abstract void printStackTrace(Throwable throwable, PrintStream printstream);

    public abstract void printStackTrace(Throwable throwable, PrintWriter printwriter);


    ()
    {
    }
}
