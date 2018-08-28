// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            UnsafeUtil

static final class it> extends it>
{

    public final void copyMemory(byte abyte0[], long l, long l1, long l2)
    {
        unsafe.copyMemory(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l, null, l1, l2);
    }

    public final boolean getBoolean(Object obj, long l)
    {
        return unsafe.getBoolean(obj, l);
    }

    public final byte getByte(Object obj, long l)
    {
        return unsafe.getByte(obj, l);
    }

    public final double getDouble(Object obj, long l)
    {
        return unsafe.getDouble(obj, l);
    }

    public final float getFloat(Object obj, long l)
    {
        return unsafe.getFloat(obj, l);
    }

    public final void putBoolean(Object obj, long l, boolean flag)
    {
        unsafe.putBoolean(obj, l, flag);
    }

    public final void putByte(long l, byte byte0)
    {
        unsafe.putByte(l, byte0);
    }

    public final void putByte(Object obj, long l, byte byte0)
    {
        unsafe.putByte(obj, l, byte0);
    }

    public final void putDouble(Object obj, long l, double d)
    {
        unsafe.putDouble(obj, l, d);
    }

    public final void putFloat(Object obj, long l, float f)
    {
        unsafe.putFloat(obj, l, f);
    }

    (Unsafe unsafe)
    {
        super(unsafe);
    }
}
