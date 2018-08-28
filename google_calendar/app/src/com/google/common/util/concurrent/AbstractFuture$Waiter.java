// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

static final class elper.putThread
{

    public static final elper.putThread TOMBSTONE = new <init>();
    public volatile <init> next;
    public volatile Thread thread;


    elper()
    {
    }

    elper(byte byte0)
    {
        AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
    }
}
