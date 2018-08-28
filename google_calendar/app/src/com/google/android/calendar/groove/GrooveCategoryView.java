// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class GrooveCategoryView extends LinearLayout
{

    public TextView description;
    public TextView title;

    public GrooveCategoryView(Context context)
    {
        super(context);
        inflate(getContext(), 0x7f050080, this);
        title = (TextView)findViewById(0x7f100047);
        description = (TextView)findViewById(0x7f10010b);
    }
}
