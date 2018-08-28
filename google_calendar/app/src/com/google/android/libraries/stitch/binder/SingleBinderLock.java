// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.binder;


// Referenced classes of package com.google.android.libraries.stitch.binder:
//            BinderLocks

final class SingleBinderLock
    implements BinderLocks
{

    private final Object lock = new Object();

    SingleBinderLock()
    {
    }

    public final Object getLock$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0()
    {
        return lock;
    }
}
