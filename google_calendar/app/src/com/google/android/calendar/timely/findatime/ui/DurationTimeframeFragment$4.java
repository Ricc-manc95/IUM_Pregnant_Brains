// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.widget.CompoundButton;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            DurationTimeframeFragment, DurationTimeframe

final class this._cls0
    implements android.widget.geListener
{

    private final DurationTimeframeFragment this$0;

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        durationTimeframe.considerExistingRooms = flag;
    }

    Q()
    {
        this$0 = DurationTimeframeFragment.this;
        super();
    }
}
