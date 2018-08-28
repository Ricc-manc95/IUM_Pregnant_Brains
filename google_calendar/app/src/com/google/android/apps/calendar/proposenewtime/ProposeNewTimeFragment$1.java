// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class this._cls0
    implements android.support.v4.view.tener
{

    private final ProposeNewTimeFragment this$0;

    public final void onPageScrollStateChanged(int i)
    {
    }

    public final void onPageScrolled(int i, float f, int j)
    {
    }

    public final void onPageSelected(int i)
    {
        ProposeNewTimeState proposenewtimestate = state.toBuilder().setSelectedProposalIndex(i).build();
        ProposeNewTimeFragment proposenewtimefragment = ProposeNewTimeFragment.this;
        proposenewtimefragment.state = proposenewtimestate;
        proposenewtimefragment.refreshViews();
        autoscroll(true);
    }

    .Builder()
    {
        this$0 = ProposeNewTimeFragment.this;
        super();
    }
}
