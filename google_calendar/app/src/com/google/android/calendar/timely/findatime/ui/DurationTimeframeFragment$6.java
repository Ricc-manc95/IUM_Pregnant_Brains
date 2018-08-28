// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.widget.RadioGroup;
import android.widget.ScrollView;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            DurationTimeframeFragment

final class this._cls0
    implements Runnable
{

    private final DurationTimeframeFragment this$0;

    public final void run()
    {
        scrollView.scrollTo(0, timeframeRadioGroup.getBottom());
    }

    Q()
    {
        this$0 = DurationTimeframeFragment.this;
        super();
    }
}
