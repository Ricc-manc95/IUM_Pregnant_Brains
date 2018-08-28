// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineSuggestion

public final class FindTimeGridSlabItem extends FrameLayout
{

    public TextView dateTimeView;
    public TextView dateView;
    public TextView descriptionView;
    public OnSlabItemUpdatedListener listener;
    public TimelineSuggestion suggestion;
    public String suggestionDescription;
    public TextView timeView;
    public TimeZone timezone;

    public FindTimeGridSlabItem(Context context, TimeZone timezone1)
    {
        super(context);
        LayoutInflater.from(context).inflate(0x7f05006b, this);
        dateView = (TextView)findViewById(0x7f10019a);
        timeView = (TextView)findViewById(0x7f100151);
        dateTimeView = (TextView)findViewById(0x7f1001a2);
        descriptionView = (TextView)findViewById(0x7f10010b);
        timezone = timezone1;
    }
}
