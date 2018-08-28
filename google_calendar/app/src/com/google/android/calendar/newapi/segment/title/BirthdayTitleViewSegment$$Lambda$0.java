// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.content.res.Resources;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleViewSegment, BirthdayTitleViewSegment

final class arg._cls2
    implements Runnable
{

    private final BirthdayTitleViewSegment arg$1;
    private final String arg$2;

    public final void run()
    {
        BirthdayTitleViewSegment birthdaytitleviewsegment = arg$1;
        String s = arg$2;
        if (((TextView)birthdaytitleviewsegment.findViewById(0x7f100047)).getLineCount() > 1)
        {
            ((TextView)birthdaytitleviewsegment.findViewById(0x7f100047)).setText(birthdaytitleviewsegment.getResources().getString(0x7f1300e4, new Object[] {
                s
            }));
        }
    }

    Y(BirthdayTitleViewSegment birthdaytitleviewsegment, String s)
    {
        arg$1 = birthdaytitleviewsegment;
        arg$2 = s;
    }
}
