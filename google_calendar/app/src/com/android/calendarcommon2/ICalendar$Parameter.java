// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;


public final class value
{

    public String name;
    public String value;

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(name);
        stringbuilder.append("=");
        stringbuilder.append(value);
        return stringbuilder.toString();
    }

    public ()
    {
    }

    public (String s, String s1)
    {
        name = s;
        value = s1;
    }
}
