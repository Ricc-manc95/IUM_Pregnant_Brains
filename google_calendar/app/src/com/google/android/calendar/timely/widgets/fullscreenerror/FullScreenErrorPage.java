// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.widgets.fullscreenerror;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.calendar.material.Material;

public class FullScreenErrorPage extends LinearLayout
{

    private final TextView bodyView;
    private final TextView titleView;
    public final Button tryAgainButton;

    public FullScreenErrorPage(Context context)
    {
        this(context, null);
    }

    public FullScreenErrorPage(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setGravity(17);
        setOrientation(1);
        LayoutInflater.from(context).inflate(0x7f05007d, this);
        titleView = (TextView)findViewById(0x7f1001bf);
        bodyView = (TextView)findViewById(0x7f1001c0);
        tryAgainButton = (Button)findViewById(0x7f1001c1);
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        titleView.setTypeface(context);
        tryAgainButton.setTypeface(context);
    }

    public void setSubtitle(int i)
    {
        bodyView.setText(i);
    }

    public void setTitle(int i)
    {
        titleView.setText(i);
    }
}
