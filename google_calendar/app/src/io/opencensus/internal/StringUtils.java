// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.internal;


public final class StringUtils
{

    public static boolean isPrintableString(String s)
    {
        boolean flag;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c >= ' ' && c <= '~')
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return false;
            }
        }

        return true;
    }
}
