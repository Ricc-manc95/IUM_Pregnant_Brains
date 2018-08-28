// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar:
//            Utils

final class val.textView
    implements android.view.stener
{

    private final View val$customActionBar;
    private final TextView val$textView;
    private final int val$titleStartPadding;

    public final boolean onPreDraw()
    {
        boolean flag = false;
        ViewTreeObserver viewtreeobserver = val$customActionBar.getViewTreeObserver();
        if (!viewtreeobserver.isAlive())
        {
            flag = true;
        } else
        {
            viewtreeobserver.removeOnPreDrawListener(this);
            int i = Utils.getStartCoordinate(val$customActionBar);
            if (i < val$titleStartPadding)
            {
                val$textView.setPaddingRelative(val$titleStartPadding - i, 0, 0, 0);
                return false;
            }
        }
        return flag;
    }

    ()
    {
        val$customActionBar = view;
        val$titleStartPadding = i;
        val$textView = textview;
        super();
    }
}
