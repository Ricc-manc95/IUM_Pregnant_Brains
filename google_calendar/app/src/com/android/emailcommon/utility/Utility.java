// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.utility;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Utility
{

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static String getSmallHash(String s)
    {
        Object obj = null;
        MessageDigest messagedigest;
        Charset charset;
        int i;
        byte byte0;
        byte byte1;
        byte byte2;
        try
        {
            messagedigest = MessageDigest.getInstance("SHA-1");
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        charset = UTF_8;
        if (s == null)
        {
            s = obj;
        } else
        {
            ByteBuffer bytebuffer = charset.encode(CharBuffer.wrap(s));
            s = new byte[bytebuffer.limit()];
            bytebuffer.get(s);
        }
        messagedigest.update(s);
        s = messagedigest.digest();
        i = s[19] & 0xf;
        byte0 = s[i];
        byte1 = s[i + 1];
        byte2 = s[i + 2];
        return Integer.toString(s[i + 3] & 0xff | ((byte0 & 0x7f) << 24 | (byte1 & 0xff) << 16 | (byte2 & 0xff) << 8));
    }

    static 
    {
        Charset.forName("US-ASCII");
        new ThreadLocalDateFormat("yyyyMMdd'T'HHmmss'Z'");
        new ThreadLocalDateFormat("yyyyMMdd");
        new ThreadLocalDateFormat("yyyy-MM-dd");
        new ThreadLocalDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        new ThreadLocalDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    private class ThreadLocalDateFormat extends ThreadLocal
    {

        private final String formatStr;

        protected final Object initialValue()
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(formatStr);
            simpledateformat.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT")));
            return simpledateformat;
        }

        public ThreadLocalDateFormat(String s)
        {
            formatStr = s;
        }
    }

}
