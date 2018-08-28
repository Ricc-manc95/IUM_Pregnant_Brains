// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.floatingactionbutton.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls2
    implements android.view..ProposeNewTimeFragment..Lambda._cls10
{

    private final ProposeNewTimeFragment arg$1;
    private final FloatingActionButton arg$2;

    public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        view = arg$1;
        arg$2.setTranslationY(view.requireContext().getResources().getDimensionPixelSize(0x7f0e031f) / 2 - ((ProposeNewTimeFragment) (view)).viewPager.getHeight());
    }

    (ProposeNewTimeFragment proposenewtimefragment, FloatingActionButton floatingactionbutton)
    {
        arg$1 = proposenewtimefragment;
        arg$2 = floatingactionbutton;
    }
}
