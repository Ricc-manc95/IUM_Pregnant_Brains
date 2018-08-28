// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;


public interface LoggingApi
{

    public abstract void log(String s);

    public abstract void log(String s, Object obj);

    public abstract void log(String s, Object obj, Object obj1);

    public abstract LoggingApi withCause(Throwable throwable);
}
