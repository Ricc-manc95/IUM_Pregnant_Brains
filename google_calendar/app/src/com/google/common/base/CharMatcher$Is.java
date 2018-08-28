// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


final class match extends tMatcher
{

    private final char match;

    public final boolean matches(char c)
    {
        return c == match;
    }

    public final String toString()
    {
        char c = match;
        char ac[] = new char[6];
        char[] _tmp = ac;
        ac[0] = '\\';
        ac[1] = 'u';
        ac[2] = '\0';
        ac[3] = '\0';
        ac[4] = '\0';
        ac[5] = '\0';
        for (int i = 0; i < 4; i++)
        {
            ac[5 - i] = "0123456789ABCDEF".charAt(c & 0xf);
            c >>= '\004';
        }

        String s = String.copyValueOf(ac);
        return (new StringBuilder(String.valueOf(s).length() + 18)).append("CharMatcher.is('").append(s).append("')").toString();
    }

    tMatcher(char c)
    {
        match = c;
    }
}
