// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


public final class Ascii
{

    public static String toLowerCase(String s)
    {
        int j = s.length();
        int i = 0;
        do
        {
label0:
            {
                String s1 = s;
                if (i < j)
                {
                    char c = s.charAt(i);
                    boolean flag;
                    if (c >= 'A' && c <= 'Z')
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        break label0;
                    }
                    s = s.toCharArray();
                    while (i < j) 
                    {
                        char c1 = s[i];
                        if (c1 >= 'A' && c1 <= 'Z')
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            s[i] = (char)(c1 ^ 0x20);
                        }
                        i++;
                    }
                    s1 = String.valueOf(s);
                }
                return s1;
            }
            i++;
        } while (true);
    }
}
