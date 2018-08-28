// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.nio.ByteBuffer;

abstract class 
{

    static void encodeUtf8Default(CharSequence charsequence, ByteBuffer bytebuffer)
    {
        int i;
        int l;
        int k1;
        k1 = charsequence.length();
        l = bytebuffer.position();
        i = 0;
_L2:
        int j;
        int k;
        if (i >= k1)
        {
            break; /* Loop/switch isn't completed */
        }
        j = i;
        k = l;
        int i1 = charsequence.charAt(i);
        if (i1 >= '\200')
        {
            break; /* Loop/switch isn't completed */
        }
        j = i;
        k = l;
        bytebuffer.put(l + i, (byte)i1);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        if (i != k1) goto _L4; else goto _L3
_L3:
        char c;
        j = i;
        k = l;
        byte byte0;
        try
        {
            bytebuffer.position(l + i);
            return;
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            i = j;
        }
          goto _L5
_L10:
        if (i >= k1) goto _L7; else goto _L6
_L6:
        j = i;
        k = l;
        c = charsequence.charAt(i);
        if (c >= '\200') goto _L9; else goto _L8
_L8:
        j = i;
        k = l;
        bytebuffer.put(l, (byte)c);
_L13:
        i++;
        l++;
          goto _L10
_L9:
        if (c >= '\u0800') goto _L12; else goto _L11
_L11:
        i1 = l + 1;
        byte0 = (byte)(c >>> 6 | 0xc0);
        k = i;
        j = i1;
        bytebuffer.put(l, byte0);
        k = i;
        j = i1;
        bytebuffer.put(i1, (byte)(c & 0x3f | 0x80));
        l = i1;
          goto _L13
_L14:
        i1 = l + 1;
        k = i;
        j = i1;
        bytebuffer.put(l, (byte)(c >>> 12 | 0xe0));
        l = i1 + 1;
        byte0 = (byte)(c >>> 6 & 0x3f | 0x80);
        j = i;
        k = l;
        bytebuffer.put(i1, byte0);
        j = i;
        k = l;
        bytebuffer.put(l, (byte)(c & 0x3f | 0x80));
          goto _L13
_L5:
        j = bytebuffer.position();
        k = Math.max(i, (k - bytebuffer.position()) + 1);
        c = charsequence.charAt(i);
        throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(c).append(" at index ").append(k + j).toString());
_L15:
        i1 = i;
        if (i + 1 == k1)
        {
            break MISSING_BLOCK_LABEL_468;
        }
        i++;
        j = i;
        k = l;
        char c1 = charsequence.charAt(i);
        j = i;
        k = l;
        if (Character.isSurrogatePair(c, c1))
        {
            break MISSING_BLOCK_LABEL_488;
        }
        i1 = i;
        j = i1;
        k = l;
        throw new rrogateException(i1, k1);
        j = i;
        k = l;
        int l1 = Character.toCodePoint(c, c1);
        byte byte1;
        int j1;
        j1 = l + 1;
        byte1 = (byte)(l1 >>> 18 | 0xf0);
        bytebuffer.put(l, byte1);
        i1 = j1 + 1;
        byte1 = (byte)(l1 >>> 12 & 0x3f | 0x80);
        k = i;
        j = i1;
        bytebuffer.put(j1, byte1);
        l = i1 + 1;
        byte1 = (byte)(l1 >>> 6 & 0x3f | 0x80);
        j = i;
        k = l;
        bytebuffer.put(i1, byte1);
        j = i;
        k = l;
        bytebuffer.put(l, (byte)(l1 & 0x3f | 0x80));
          goto _L13
_L7:
        j = i;
        k = l;
        bytebuffer.position(l);
        return;
        IndexOutOfBoundsException indexoutofboundsexception1;
        indexoutofboundsexception1;
        i = k;
        k = j;
          goto _L5
        indexoutofboundsexception1;
        k = j1;
          goto _L5
_L4:
        l += i;
          goto _L10
_L12:
        if (c >= '\uD800' && '\uDFFF' >= c) goto _L15; else goto _L14
    }

    abstract int encodeUtf8(CharSequence charsequence, byte abyte0[], int i, int j);

    abstract void encodeUtf8Direct(CharSequence charsequence, ByteBuffer bytebuffer);

    abstract int partialIsValidUtf8(int i, byte abyte0[], int j, int k);

    ()
    {
    }
}
