// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;

import java.util.regex.Pattern;

public final class zzv
{

    public static boolean zzdg(String s)
    {
        return s == null || s.trim().isEmpty();
    }

    static 
    {
        Pattern.compile("\\$\\{(.*?)\\}");
    }
}
