// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

static abstract class 
{

    abstract boolean casListeners(AbstractFuture abstractfuture,  ,  1);

    abstract boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1);

    abstract boolean casWaiters(AbstractFuture abstractfuture,  ,  1);

    abstract void putNext( ,  1);

    abstract void putThread( , Thread thread);

    ()
    {
    }
}
