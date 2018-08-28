// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.support.v4.app.Fragment;
import android.view.MenuItem;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            DurationTimeframeFragment

final class this._cls0
    implements android.support.v7.widget.ner
{

    private final DurationTimeframeFragment this$0;

    public final boolean onMenuItemClick(MenuItem menuitem)
    {
        ((stener)mTarget).onFilterApply(durationTimeframe);
        return false;
    }

    stener()
    {
        this$0 = DurationTimeframeFragment.this;
        super();
    }
}
