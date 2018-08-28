// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis;


public final class GoogleUtils
{

    private static final Integer BUGFIX_VERSION;
    public static final Integer MAJOR_VERSION;
    public static final Integer MINOR_VERSION;
    public static final String VERSION;

    static 
    {
        MAJOR_VERSION = Integer.valueOf(1);
        MINOR_VERSION = Integer.valueOf(24);
        BUGFIX_VERSION = Integer.valueOf(0);
        String s = String.valueOf(MAJOR_VERSION);
        String s1 = String.valueOf(MINOR_VERSION);
        String s2 = String.valueOf(BUGFIX_VERSION);
        VERSION = (new StringBuilder(String.valueOf(s).length() + 11 + String.valueOf(s1).length() + String.valueOf(s2).length())).append(s).append(".").append(s1).append(".").append(s2).append("-SNAPSHOT").toString().toString();
    }
}
