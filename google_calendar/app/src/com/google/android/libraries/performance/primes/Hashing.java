// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hashing
{

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static Long hash(String s)
    {
        if (s == null || s.trim().isEmpty())
        {
            return null;
        }
        long l;
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes(UTF_8));
            l = ByteBuffer.wrap(messagedigest.digest()).getLong();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new RuntimeException(s);
        }
        return Long.valueOf(l);
    }

}
