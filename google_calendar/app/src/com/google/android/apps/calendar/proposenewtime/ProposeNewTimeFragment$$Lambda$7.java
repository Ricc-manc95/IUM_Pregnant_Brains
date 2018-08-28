// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.support.v4.app.Fragment;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.calendar.Utils;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls1
    implements com.google.android.apps.calendar.proposenewtime.grid.views.ener
{

    private final ProposeNewTimeFragment arg$1;

    public final void onTap(int i, int j)
    {
        boolean flag1 = false;
        ProposeNewTimeFragment proposenewtimefragment = arg$1;
        boolean flag;
        if (proposenewtimefragment.state.getMode() == com.google.android.apps.calendar.proposenewtime.state.pListener)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (j < 30)
            {
                j = ((flag1) ? 1 : 0);
            } else
            {
                j = 30;
            }
            proposenewtimefragment.state = proposenewtimefragment.state.toBuilder().meProposal(proposenewtimefragment.state.getTimeProposal().withNewStartTime(i, j, Utils.getTimeZone(proposenewtimefragment.getContext()))).();
            proposenewtimefragment.refreshViews();
        }
    }

    (ProposeNewTimeFragment proposenewtimefragment)
    {
        arg$1 = proposenewtimefragment;
    }
}
