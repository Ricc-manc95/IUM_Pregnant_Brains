// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;


// Referenced classes of package com.google.apps.xplat.logging:
//            XLogLevel

public interface 
{

    public abstract boolean isLoggable(XLogLevel xloglevel);

    public abstract void log(XLogLevel xloglevel, String s);

    public abstract void log(XLogLevel xloglevel, String s, Throwable throwable);
}
