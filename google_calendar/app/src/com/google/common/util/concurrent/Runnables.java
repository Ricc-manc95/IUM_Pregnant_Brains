// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


public final class Runnables
{

    public static final Runnable EMPTY_RUNNABLE = new _cls1();


    private class _cls1
        implements Runnable
    {

        public final void run()
        {
        }

        _cls1()
        {
        }
    }

}
