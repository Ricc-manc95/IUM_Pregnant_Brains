// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.widget.CompoundButton;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.time:
//            EventTimeEditSegment

final class arg._cls1
    implements android.widget.istener
{

    private final EventTimeEditSegment arg$1;

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        ((arg._cls1)((EditSegment) (arg$1)).mListener).nAllDayToggled(flag);
    }

    (EventTimeEditSegment eventtimeeditsegment)
    {
        arg$1 = eventtimeeditsegment;
    }
}
