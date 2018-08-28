// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.util;


public final class Preconditions
{

    public static transient String format(String s, Object aobj[])
    {
        int i = 0;
        s = String.valueOf(s);
        StringBuilder stringbuilder = new StringBuilder(s.length() + aobj.length * 16);
        int j = 0;
        do
        {
            if (i >= aobj.length)
            {
                break;
            }
            int k = s.indexOf("%s", j);
            if (k == -1)
            {
                break;
            }
            stringbuilder.append(s.substring(j, k));
            stringbuilder.append(aobj[i]);
            j = k + 2;
            i++;
        } while (true);
        stringbuilder.append(s.substring(j));
        if (i < aobj.length)
        {
            stringbuilder.append(" [");
            stringbuilder.append(aobj[i]);
            for (i++; i < aobj.length; i++)
            {
                stringbuilder.append(", ");
                stringbuilder.append(aobj[i]);
            }

            stringbuilder.append(']');
        }
        return stringbuilder.toString();
    }
}
