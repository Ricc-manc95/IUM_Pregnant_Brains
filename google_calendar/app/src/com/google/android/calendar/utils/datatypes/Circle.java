// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.datatypes;


public final class Circle
{

    public final int rr;
    public final int x;
    public final int y;

    public Circle(int i, int j, int k)
    {
        x = i;
        y = j;
        rr = k * k;
    }
}
