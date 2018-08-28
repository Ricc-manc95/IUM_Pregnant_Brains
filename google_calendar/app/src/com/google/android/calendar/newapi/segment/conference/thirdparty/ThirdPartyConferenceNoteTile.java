// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.calendar.newapi.segment.note.ConferenceTileView;

public class ThirdPartyConferenceNoteTile extends ConferenceTileView
{

    public ThirdPartyConferenceNoteTile(Context context)
    {
        super(context);
    }

    public ThirdPartyConferenceNoteTile(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final String getAnalyticsSegmentDescription()
    {
        return "in_segment_conference_notes";
    }
}
