// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.nio.charset.Charset;

public final class Util
{

    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static boolean arrayRangeEquals(byte abyte0[], int i, byte abyte1[], int j, int k)
    {
        for (int l = 0; l < k; l++)
        {
            if (abyte0[l + i] != abyte1[l + j])
            {
                return false;
            }
        }

        return true;
    }

    public static void checkOffsetAndCount(long l, long l1, long l2)
    {
        if ((l1 | l2) < 0L || l1 > l || l - l1 < l2)
        {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[] {
                Long.valueOf(l), Long.valueOf(l1), Long.valueOf(l2)
            }));
        } else
        {
            return;
        }
    }

    public static void sneakyRethrow(Throwable throwable)
    {
        throw throwable;
    }

}
