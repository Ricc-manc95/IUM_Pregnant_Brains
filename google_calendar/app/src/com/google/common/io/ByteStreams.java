// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import com.google.common.base.Strings;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public final class ByteStreams
{

    private static byte[] combineBuffers(Deque deque, int i)
    {
        byte abyte0[] = new byte[i];
        int k;
        for (int j = i; j > 0; j -= k)
        {
            byte abyte1[] = (byte[])deque.removeFirst();
            k = Math.min(j, abyte1.length);
            System.arraycopy(abyte1, 0, abyte0, i - j, k);
        }

        return abyte0;
    }

    public static byte[] toByteArray(InputStream inputstream)
        throws IOException
    {
        if (inputstream == null)
        {
            throw new NullPointerException();
        } else
        {
            return toByteArrayInternal(inputstream, new ArrayDeque(20), 0);
        }
    }

    static byte[] toByteArray(InputStream inputstream, long l)
        throws IOException
    {
        byte abyte1[];
        int i;
        boolean flag;
        if (l >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("expectedSize (%s) must be non-negative", new Object[] {
                Long.valueOf(l)
            }));
        }
        if (l > 0x7ffffff7L)
        {
            throw new OutOfMemoryError((new StringBuilder(62)).append(l).append(" bytes is too large to fit in a byte array").toString());
        }
        abyte1 = new byte[(int)l];
        i = (int)l;
_L8:
        if (i <= 0) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        j = (int)l - i;
        k = inputstream.read(abyte1, j, i);
        if (k != -1) goto _L4; else goto _L3
_L3:
        byte abyte0[] = Arrays.copyOf(abyte1, j);
_L6:
        return abyte0;
_L4:
        i -= k;
        continue; /* Loop/switch isn't completed */
_L2:
        i = inputstream.read();
        abyte0 = abyte1;
        if (i == -1) goto _L6; else goto _L5
_L5:
        ArrayDeque arraydeque = new ArrayDeque(22);
        arraydeque.add(abyte1);
        arraydeque.add(new byte[] {
            (byte)i
        });
        return toByteArrayInternal(inputstream, arraydeque, abyte1.length + 1);
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static byte[] toByteArrayInternal(InputStream inputstream, Deque deque, int i)
        throws IOException
    {
        char c = '\u2000';
        int j = i;
        for (i = c; j < 0x7ffffff7; i = IntMath.saturatedMultiply(i, 2))
        {
            byte abyte0[] = new byte[Math.min(i, 0x7ffffff7 - j)];
            deque.add(abyte0);
            for (int k = 0; k < abyte0.length;)
            {
                int l = inputstream.read(abyte0, k, abyte0.length - k);
                if (l == -1)
                {
                    return combineBuffers(deque, j);
                }
                k += l;
                j += l;
            }

        }

        if (inputstream.read() == -1)
        {
            return combineBuffers(deque, 0x7ffffff7);
        } else
        {
            throw new OutOfMemoryError("input is too large to fit in a byte array");
        }
    }

    static 
    {
        new _cls1();
    }

    private class _cls1 extends OutputStream
    {

        public final String toString()
        {
            return "ByteStreams.nullOutputStream()";
        }

        public final void write(int i)
        {
        }

        public final void write(byte abyte0[])
        {
            if (abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                return;
            }
        }

        public final void write(byte abyte0[], int i, int j)
        {
            if (abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                return;
            }
        }

        _cls1()
        {
        }
    }

}
