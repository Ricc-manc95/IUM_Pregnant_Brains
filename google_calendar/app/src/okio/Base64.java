// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.UnsupportedEncodingException;

final class Base64
{

    private static final byte MAP[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };

    public static String encode(byte abyte0[])
    {
        return encode(abyte0, MAP);
    }

    private static String encode(byte abyte0[], byte abyte1[])
    {
        byte abyte2[];
        int k;
        int l;
        int i = 0;
        abyte2 = new byte[(abyte0.length + 2) / 3 << 2];
        l = abyte0.length - abyte0.length % 3;
        k = 0;
        for (; i < l; i += 3)
        {
            int i1 = k + 1;
            abyte2[k] = abyte1[(abyte0[i] & 0xff) >> 2];
            k = i1 + 1;
            abyte2[i1] = abyte1[(abyte0[i] & 3) << 4 | (abyte0[i + 1] & 0xff) >> 4];
            i1 = k + 1;
            abyte2[k] = abyte1[(abyte0[i + 1] & 0xf) << 2 | (abyte0[i + 2] & 0xff) >> 6];
            k = i1 + 1;
            abyte2[i1] = abyte1[abyte0[i + 2] & 0x3f];
        }

        abyte0.length % 3;
        JVM INSTR tableswitch 1 2: default 172
    //                   1 185
    //                   2 241;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_241;
_L4:
        int j;
        try
        {
            abyte0 = new String(abyte2, "US-ASCII");
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return abyte0;
_L2:
        j = k + 1;
        abyte2[k] = abyte1[(abyte0[l] & 0xff) >> 2];
        k = j + 1;
        abyte2[j] = abyte1[(abyte0[l] & 3) << 4];
        abyte2[k] = 61;
        abyte2[k + 1] = 61;
          goto _L4
        j = k + 1;
        abyte2[k] = abyte1[(abyte0[l] & 0xff) >> 2];
        k = j + 1;
        abyte2[j] = abyte1[(abyte0[l] & 3) << 4 | (abyte0[l + 1] & 0xff) >> 4];
        abyte2[k] = abyte1[(abyte0[l + 1] & 0xf) << 2];
        abyte2[k + 1] = 61;
          goto _L4
    }

}
