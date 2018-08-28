// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.attendees;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.calendar.material.Material;

public final class AttendeeInfoView extends LinearLayout
{

    public final TextView displayNameView = (TextView)findViewById(0x7f10011c);
    public final int imageSize = getResources().getDimensionPixelSize(0x7f0e01d3);
    public final ImageView photoView = (ImageView)findViewById(0x7f10011b);

    public AttendeeInfoView(Context context)
    {
        super(context);
        setOrientation(1);
        inflate(context, 0x7f050023, this);
        TextView textview = displayNameView;
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        textview.setTypeface(context);
        setFocusable(true);
    }
}
