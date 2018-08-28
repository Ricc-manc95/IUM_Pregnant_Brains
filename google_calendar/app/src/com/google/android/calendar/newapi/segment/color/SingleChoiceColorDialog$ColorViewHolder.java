// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.color;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.calendar.common.drawable.ColorCircle;

final class calendarColorsOnly
{

    public final boolean calendarColorsOnly;
    public final ImageView check;
    public final ColorCircle circle;
    public final ImageView circleImage;
    public final TextView label;

    (ViewGroup viewgroup, boolean flag)
    {
        label = (TextView)viewgroup.findViewById(0x7f100188);
        circleImage = (ImageView)viewgroup.findViewById(0x7f100187);
        check = (ImageView)viewgroup.findViewById(0x7f100148);
        circle = new ColorCircle(viewgroup.getResources(), 0x7f0e0137);
        circleImage.setImageDrawable(circle);
        calendarColorsOnly = flag;
    }
}
