// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove.category;

import android.content.res.Resources;

public final class backgroundDrawableResId
{

    public final int backgroundColor;
    public final int backgroundDrawableResId;
    public final String description;
    public final int fabColor;
    public final String question;
    public final String title;

    public (Resources resources, int i, int j, int k, int l, int i1, int j1)
    {
        title = resources.getString(i);
        description = resources.getString(j);
        question = resources.getString(k);
        backgroundColor = resources.getColor(l);
        fabColor = resources.getColor(i1);
        backgroundDrawableResId = j1;
    }
}
