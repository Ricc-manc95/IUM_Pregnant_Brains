// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.viewedit.segment.edit;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

public final class EditSegmentDivider extends View
{

    public EditSegmentDivider(Context context)
    {
        super(context);
        context = context.getResources();
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, context.getDimensionPixelSize(0x7f0e0114)));
        setBackgroundColor(context.getColor(0x7f0d009c));
    }
}
