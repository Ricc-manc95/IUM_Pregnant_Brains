// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.widget.RemoteViews;
import com.google.android.calendar.utils.rtl.RtlUtils;

abstract class etViewContext
{

    public boolean isRtl;

    static void updateIcon(RemoteViews remoteviews, int i, int j, boolean flag, int k)
    {
label0:
        {
            boolean flag2 = false;
            boolean flag1;
            int l;
            if (j != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 && !flag)
            {
                l = 0;
            } else
            {
                l = 8;
            }
            remoteviews.setViewVisibility(0x7f10039e, l);
            if (flag1 && flag)
            {
                l = ((flag2) ? 1 : 0);
            } else
            {
                l = 8;
            }
            remoteviews.setViewVisibility(0x7f10039b, l);
            if (flag1)
            {
                if (!flag)
                {
                    break label0;
                }
                remoteviews.setImageViewResource(0x7f10039d, j);
                remoteviews.setInt(0x7f10039d, "setColorFilter", -1);
                remoteviews.setInt(0x7f10039c, "setColorFilter", k);
            }
            return;
        }
        remoteviews.setImageViewResource(0x7f10039e, j);
        remoteviews.setInt(0x7f10039e, "setColorFilter", i);
    }

    public abstract int getLayoutId(etViewContext etviewcontext);

    public void updateStatus(etViewContext etviewcontext, RemoteViews remoteviews)
    {
        isRtl = RtlUtils.isLayoutDirectionRtl(etviewcontext.context);
    }

    final void updateTextView(RemoteViews remoteviews, int i, int j, CharSequence charsequence, int k)
    {
        remoteviews.setViewVisibility(i, 0);
        remoteviews.setTextViewText(i, RtlUtils.forceTextAlignment(charsequence, isRtl));
        remoteviews.setTextColor(i, k);
    }

    etViewContext()
    {
    }
}
