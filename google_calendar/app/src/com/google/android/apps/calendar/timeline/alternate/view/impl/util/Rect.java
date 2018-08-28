// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.util;


public final class Rect
{

    public int bottom;
    public int left;
    public int right;
    public int top;

    public Rect()
    {
    }

    public Rect(int i, int j, int k, int l)
    {
        left = 0;
        top = 0;
        right = 0;
        bottom = 0;
    }

    public final void set(Rect rect)
    {
        left = rect.left;
        top = rect.top;
        right = rect.right;
        bottom = rect.bottom;
    }
}
