// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.hash;


public abstract class HashCode
{

    private static final char hexDigits[] = "0123456789abcdef".toCharArray();

    HashCode()
    {
    }

    public static HashCode fromInt(int i)
    {
        return new IntHashCode(i);
    }

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract int bits();

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof HashCode)
        {
            obj = (HashCode)obj;
            bits();
            ((HashCode) (obj)).bits();
            flag = flag1;
            if (equalsSameBits(((HashCode) (obj))))
            {
                flag = true;
            }
        }
        return flag;
    }

    abstract boolean equalsSameBits(HashCode hashcode);

    public final int hashCode()
    {
        bits();
        return asInt();
    }

    public final String toString()
    {
        byte abyte0[] = asBytes();
        StringBuilder stringbuilder = new StringBuilder(abyte0.length * 2);
        int j = abyte0.length;
        for (int i = 0; i < j; i++)
        {
            byte byte0 = abyte0[i];
            stringbuilder.append(hexDigits[byte0 >> 4 & 0xf]).append(hexDigits[byte0 & 0xf]);
        }

        return stringbuilder.toString();
    }


    private class IntHashCode extends HashCode
        implements Serializable
    {

        public static final long serialVersionUID = 0L;
        private final int hash;

        public final byte[] asBytes()
        {
            return (new byte[] {
                (byte)hash, (byte)(hash >> 8), (byte)(hash >> 16), hash >> 24
            });
        }

        public final int asInt()
        {
            return hash;
        }

        public final int bits()
        {
            return 32;
        }

        final boolean equalsSameBits(HashCode hashcode)
        {
            return hash == hashcode.asInt();
        }

        IntHashCode(int i)
        {
            hash = i;
        }
    }

}
