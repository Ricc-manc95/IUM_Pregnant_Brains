// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            UnsafeUtil

static abstract class unsafe
{

    public Unsafe unsafe;

    public abstract void copyMemory(byte abyte0[], long l, long l1, long l2);

    public abstract boolean getBoolean(Object obj, long l);

    public abstract byte getByte(Object obj, long l);

    public abstract double getDouble(Object obj, long l);

    public abstract float getFloat(Object obj, long l);

    public abstract void putBoolean(Object obj, long l, boolean flag);

    public abstract void putByte(long l, byte byte0);

    public abstract void putByte(Object obj, long l, byte byte0);

    public abstract void putDouble(Object obj, long l, double d);

    public abstract void putFloat(Object obj, long l, float f);

    (Unsafe unsafe1)
    {
        unsafe = unsafe1;
    }
}
