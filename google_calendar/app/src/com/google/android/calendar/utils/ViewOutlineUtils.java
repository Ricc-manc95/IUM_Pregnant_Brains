// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils;

import android.view.View;
import android.view.ViewOutlineProvider;

public final class ViewOutlineUtils
{

    private static final ViewOutlineProvider ROUNDRECT_OUTLINE_PROVIDER = new _cls1();

    public static void setViewRoundRectOutline(View view)
    {
        boolean flag;
        if (view != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            view.setClipToOutline(true);
            view.setOutlineProvider(ROUNDRECT_OUTLINE_PROVIDER);
            return;
        }
    }


    private class _cls1 extends ViewOutlineProvider
    {

        public final void getOutline(View view, Outline outline)
        {
            if (view.getHeight() > 0)
            {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), view.getHeight() / 2);
            }
        }

        _cls1()
        {
        }
    }

}
