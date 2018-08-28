// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzq;

public final class zzabl
{

    public final String mTag;
    public final String zzaQF;

    private zzabl(String s, String s1)
    {
        zzaQF = s1;
        mTag = s;
        new zzq(s);
    }

    public transient zzabl(String s, String as[])
    {
        if (as.length == 0)
        {
            as = "";
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append('[');
            int j = as.length;
            for (int i = 0; i < j; i++)
            {
                String s1 = as[i];
                if (stringbuilder.length() > 1)
                {
                    stringbuilder.append(",");
                }
                stringbuilder.append(s1);
            }

            stringbuilder.append(']').append(' ');
            as = stringbuilder.toString();
        }
        this(s, ((String) (as)));
    }
}
