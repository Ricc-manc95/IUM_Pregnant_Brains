// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class 
{

    private static final Set ALL_VALUES = new HashSet(Arrays.asList(new String[] {
        "none", "unknown", "valid", "missing", "expired", "revoked"
    }));

    public static String toCertificateStatus(String s)
    {
        if (s == null)
        {
            s = "";
        } else
        {
            s = s.toLowerCase();
        }
        if (ALL_VALUES.contains(s))
        {
            return s;
        } else
        {
            return "none";
        }
    }

}
