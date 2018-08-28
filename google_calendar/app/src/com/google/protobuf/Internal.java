// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

// Referenced classes of package com.google.protobuf:
//            CodedInputStream, Utf8, MessageLite

public final class Internal
{

    public static final byte EMPTY_BYTE_ARRAY[];
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static Object checkNotNull(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return obj;
        }
    }

    static Object checkNotNull(Object obj, String s)
    {
        if (obj == null)
        {
            throw new NullPointerException(s);
        } else
        {
            return obj;
        }
    }

    public static int hashBoolean(boolean flag)
    {
        return !flag ? 1237 : 1231;
    }

    public static int hashCode(byte abyte0[])
    {
        int k = abyte0.length;
        int j = 0;
        int i;
        byte byte0;
        for (i = k; j < 0 + k; i = byte0 + i * 31)
        {
            byte0 = abyte0[j];
            j++;
        }

        j = i;
        if (i == 0)
        {
            j = 1;
        }
        return j;
    }

    public static int hashLong(long l)
    {
        return (int)(l >>> 32 ^ l);
    }

    static boolean isProto1Group(MessageLite messagelite)
    {
        return false;
    }

    public static boolean isValidUtf8(byte abyte0[])
    {
        boolean flag = false;
        if (Utf8.processor.partialIsValidUtf8(0, abyte0, 0, abyte0.length) == 0)
        {
            flag = true;
        }
        return flag;
    }

    static Object mergeMessage(Object obj, Object obj1)
    {
        return ((MessageLite)obj).toBuilder().mergeFrom((MessageLite)obj1).buildPartial();
    }

    static int partialHash(int i, byte abyte0[], int j, int k)
    {
        for (int l = j; l < j + k; l++)
        {
            i = i * 31 + abyte0[l];
        }

        return i;
    }

    public static String toStringUtf8(byte abyte0[])
    {
        return new String(abyte0, UTF_8);
    }

    static 
    {
        Charset.forName("ISO-8859-1");
        byte abyte0[] = new byte[0];
        EMPTY_BYTE_ARRAY = abyte0;
        ByteBuffer.wrap(abyte0);
        abyte0 = EMPTY_BYTE_ARRAY;
        CodedInputStream.newInstance(abyte0, 0, abyte0.length, false);
    }
}
