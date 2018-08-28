// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.groove:
//            AnimatorHelper

public class GrooveScheduleView extends LinearLayout
{

    public TextView mTitleView;

    public GrooveScheduleView(Context context)
    {
        super(context);
    }

    public final void changeTheme(int i)
    {
        int j = mTitleView.getCurrentTextColor();
        if (i == 0)
        {
            i = getResources().getColor(0x7f0d011e);
        } else
        {
            i = getResources().getColor(0x7f0d011d);
        }
        AnimatorHelper.runColorChangeAnimation(this, this, j, i);
    }

    public boolean shouldChangeColor(View view)
    {
        return true;
    }
}
