// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;


public class 
{

    public static String getFullName( )
    {
        String s = .ategory();
         = .ction();
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf().length())).append(s).append(".").append().toString();
    }
}
