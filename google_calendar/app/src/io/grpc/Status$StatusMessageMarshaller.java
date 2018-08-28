// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import com.google.common.base.Charsets;
import java.nio.ByteBuffer;

final class 
    implements 
{

    private static final byte HEX[] = {
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        65, 66, 67, 68, 69, 70
    };

    private static String parseAsciiStringSlow(byte abyte0[])
    {
        ByteBuffer bytebuffer;
        int i;
        bytebuffer = ByteBuffer.allocate(abyte0.length);
        i = 0;
_L2:
        if (i >= abyte0.length)
        {
            break; /* Loop/switch isn't completed */
        }
        if (abyte0[i] != 37 || i + 2 >= abyte0.length)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        bytebuffer.put((byte)Integer.parseInt(new String(abyte0, i + 1, 2, Charsets.US_ASCII), 16));
        i += 3;
        continue; /* Loop/switch isn't completed */
        NumberFormatException numberformatexception;
        numberformatexception;
        bytebuffer.put(abyte0[i]);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        return new String(bytebuffer.array(), 0, bytebuffer.position(), Charsets.UTF_8);
    }

    public final Object parseAsciiString(byte abyte0[])
    {
        for (int i = 0; i < abyte0.length; i++)
        {
            byte byte0 = abyte0[i];
            if (byte0 < 32 || byte0 >= 126 || byte0 == 37 && i + 2 < abyte0.length)
            {
                return parseAsciiStringSlow(abyte0);
            }
        }

        return new String(abyte0, 0);
    }

    public final byte[] toAsciiString(Object obj)
    {
        byte abyte0[] = ((String)obj).getBytes(Charsets.UTF_8);
        for (int i = 0; i < abyte0.length; i++)
        {
            int j = abyte0[i];
            if (j < 32 || j >= 126 || j == 37)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                obj = new byte[(abyte0.length - i) * 3 + i];
                if (i != 0)
                {
                    System.arraycopy(abyte0, 0, obj, 0, i);
                }
                j = i;
                while (j < abyte0.length) 
                {
                    byte byte0 = abyte0[j];
                    boolean flag;
                    if (byte0 < 32 || byte0 >= 126 || byte0 == 37)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        obj[i] = 37;
                        obj[i + 1] = HEX[byte0 >> 4 & 0xf];
                        obj[i + 2] = HEX[byte0 & 0xf];
                        i += 3;
                    } else
                    {
                        obj[i] = byte0;
                        i++;
                    }
                    j++;
                }
                abyte0 = new byte[i];
                System.arraycopy(obj, 0, abyte0, 0, i);
                return abyte0;
            }
        }

        return abyte0;
    }


    ()
    {
    }
}
