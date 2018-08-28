// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import libcore.io.Memory;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            UnsafeUtil

static final class  extends 
{

    public final void copyMemory(byte abyte0[], long l, long l1, long l2)
    {
        Memory.pokeByteArray((int)(-1L & l1), abyte0, (int)l, (int)l2);
    }

    public final boolean getBoolean(Object obj, long l)
    {
        if (UnsafeUtil.IS_BIG_ENDIAN)
        {
            return UnsafeUtil.getBooleanBigEndian(obj, l);
        } else
        {
            return UnsafeUtil.getBooleanLittleEndian(obj, l);
        }
    }

    public final byte getByte(Object obj, long l)
    {
        if (UnsafeUtil.IS_BIG_ENDIAN)
        {
            return UnsafeUtil.getByteBigEndian(obj, l);
        } else
        {
            return UnsafeUtil.getByteLittleEndian(obj, l);
        }
    }

    public final double getDouble(Object obj, long l)
    {
        return Double.longBitsToDouble(super..getLong(obj, l));
    }

    public final float getFloat(Object obj, long l)
    {
        return Float.intBitsToFloat(super..getInt(obj, l));
    }

    public final void putBoolean(Object obj, long l, boolean flag)
    {
        if (UnsafeUtil.IS_BIG_ENDIAN)
        {
            UnsafeUtil.putBooleanBigEndian(obj, l, flag);
            return;
        } else
        {
            UnsafeUtil.putBooleanLittleEndian(obj, l, flag);
            return;
        }
    }

    public final void putByte(long l, byte byte0)
    {
        Memory.pokeByte((int)(-1L & l), byte0);
    }

    public final void putByte(Object obj, long l, byte byte0)
    {
        if (UnsafeUtil.IS_BIG_ENDIAN)
        {
            UnsafeUtil.putByteBigEndian(obj, l, byte0);
            return;
        } else
        {
            UnsafeUtil.putByteLittleEndian(obj, l, byte0);
            return;
        }
    }

    public final void putDouble(Object obj, long l, double d)
    {
        long l1 = Double.doubleToLongBits(d);
        super..putLong(obj, l, l1);
    }

    public final void putFloat(Object obj, long l, float f)
    {
        int i = Float.floatToIntBits(f);
        super..putInt(obj, l, i);
    }

    (Unsafe unsafe)
    {
        super(unsafe);
    }
}
