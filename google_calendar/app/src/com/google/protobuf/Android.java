// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


final class Android
{

    public static final boolean IS_ROBOLECTRIC;
    public static final Class MEMORY_CLASS = getClassForName("libcore.io.Memory");

    private static Class getClassForName(String s)
    {
        try
        {
            s = Class.forName(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return s;
    }

    static 
    {
        boolean flag;
        if (getClassForName("org.robolectric.Robolectric") != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        IS_ROBOLECTRIC = flag;
    }
}
