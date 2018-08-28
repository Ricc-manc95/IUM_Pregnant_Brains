// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

final class val.radius extends ViewOutlineProvider
{

    private final int val$radius;

    public final void getOutline(View view, Outline outline)
    {
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), val$radius);
    }

    ()
    {
        val$radius = i;
        super();
    }
}
