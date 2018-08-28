// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewPropertyAnimator;

public final class HeaderElevator
{

    private boolean elevated;
    private final View headerView;
    private final float raisedElevation;

    HeaderElevator(Resources resources, View view)
    {
        headerView = view;
        raisedElevation = resources.getDimensionPixelSize(0x7f0e0147);
    }

    final void elevate(boolean flag)
    {
        headerView.clearAnimation();
        if (elevated || !flag) goto _L2; else goto _L1
_L1:
        headerView.animate().z(raisedElevation).setDuration(150L).start();
_L4:
        elevated = flag;
        return;
_L2:
        if (elevated && !flag)
        {
            headerView.animate().z(0.0F).setDuration(150L).start();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
