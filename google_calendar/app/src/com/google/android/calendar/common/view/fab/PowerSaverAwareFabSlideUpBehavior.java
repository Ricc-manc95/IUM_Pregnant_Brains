// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.fab;

import android.content.Context;
import android.os.PowerManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

public class PowerSaverAwareFabSlideUpBehavior extends android.support.design.widget.FloatingActionButton.Behavior
{

    private PowerManager powerManager;

    public PowerSaverAwareFabSlideUpBehavior(Context context, AttributeSet attributeset)
    {
        powerManager = (PowerManager)context.getSystemService("power");
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        view = (FloatingActionButton)view;
        super.onLayoutChild(coordinatorlayout, view, i);
        if (powerManager.isPowerSaveMode())
        {
            List list = coordinatorlayout.getDependencies(view);
            i = 0;
            float f = 0.0F;
            while (i < list.size()) 
            {
                View view1 = (View)list.get(i);
                float f1;
                boolean flag;
                if ((view1 instanceof android.support.design.widget.Snackbar.SnackbarLayout) && view1 != null && view1.isShown())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                f1 = f;
                if (flag)
                {
                    f1 = f;
                    if (coordinatorlayout.doViewsOverlap(view1, view))
                    {
                        f1 = Math.min(f, ViewCompat.getTranslationY(view1) - (float)view1.getHeight());
                    }
                }
                i++;
                f = f1;
            }
            if (f != view.getTranslationY())
            {
                ViewCompat.setTranslationY(view, f);
            }
        }
        return true;
    }
}
