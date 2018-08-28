// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.widget.CompoundButton;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference:
//            ConferenceEditSegment

final class arg._cls1
    implements android.widget.stener
{

    private final ConferenceEditSegment arg$1;

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        ((arg._cls1)((EditSegment) (arg$1)).mListener).nConferenceToggled(flag);
    }

    (ConferenceEditSegment conferenceeditsegment)
    {
        arg$1 = conferenceeditsegment;
    }
}
