// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.util.Comparator;

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (byte[])obj;
        obj1 = (byte[])obj1;
        return obj.length - obj1.length;
    }

    ()
    {
    }
}
