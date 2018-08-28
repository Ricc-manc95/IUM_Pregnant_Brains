// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.calendar:
//            Utils

final class AlternateTimelineHolderManager
{

    static void adjustHolder(ViewGroup viewgroup, com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype)
    {
        boolean flag = false;
        Context context = viewgroup.getContext();
        Resources resources = context.getResources();
        ViewGroup viewgroup1 = (ViewGroup)viewgroup.findViewById(0x7f10011a);
        float f;
        int i;
        int j;
        if (context.getResources().getBoolean(0x7f0c0016) && !context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams;
            boolean flag1;
            if (Utils.isPortrait(context))
            {
                j = 0x7f0e043e;
            } else
            {
                j = 0x7f0e03db;
            }
            j = resources.getDimensionPixelOffset(j);
        } else
        {
            j = 0;
        }
        marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)viewgroup.getLayoutParams();
        marginlayoutparams.topMargin = j;
        viewgroup.setLayoutParams(marginlayoutparams);
        if (i != 0)
        {
            i = resources.getDimensionPixelOffset(0x7f0e0445);
        } else
        {
            i = 0;
        }
        viewgroup = (android.view.ViewGroup.MarginLayoutParams)viewgroup1.getLayoutParams();
        viewgroup.topMargin = i;
        viewgroup1.setLayoutParams(viewgroup);
        flag1 = shouldShowInCard(context, viewtype);
        viewgroup = viewgroup1.getResources();
        if (flag1)
        {
            i = viewgroup.getDimensionPixelOffset(0x7f0e03a1);
        } else
        {
            i = 0;
        }
        viewtype = (android.widget.FrameLayout.LayoutParams)viewgroup1.getLayoutParams();
        if (flag1)
        {
            j = viewgroup.getDisplayMetrics().widthPixels / 2;
        } else
        {
            j = -1;
        }
        viewtype.width = j;
        viewtype.setMarginEnd(i);
        i = ((flag) ? 1 : 0);
        if (flag1)
        {
            i = 0x800035;
        }
        viewtype.gravity = i;
        viewgroup1.setLayoutParams(viewtype);
        if (flag1)
        {
            viewgroup1.setBackgroundResource(0x7f0200d5);
        } else
        {
            viewgroup1.setBackgroundColor(-1);
        }
        if (flag1)
        {
            f = viewgroup.getDimension(0x7f0e007f);
        } else
        {
            f = 0.0F;
        }
        ViewCompat.setElevation(viewgroup1, f);
    }

    static boolean shouldShowInCard(Context context, com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype)
    {
        return context.getResources().getBoolean(0x7f0c0016) && !Utils.isPortrait(context) && (com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.AGENDA.equals(viewtype) || com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.DAY.equals(viewtype));
    }
}
