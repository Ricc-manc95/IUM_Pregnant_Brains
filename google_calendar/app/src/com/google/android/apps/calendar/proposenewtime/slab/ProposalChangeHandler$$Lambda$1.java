// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab;

import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.StateHolder;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.slab:
//            ProposalChangeHandler

final class arg._cls1
    implements com.android.datetimepicker.time.er
{

    private final ProposalChangeHandler arg$1;

    public final void onTimeSet(int i, int j)
    {
        ProposalChangeHandler proposalchangehandler = arg$1;
        ProposeNewTimeState proposenewtimestate = proposalchangehandler.stateHolder.getState();
        proposalchangehandler.stateHolder.setState(proposenewtimestate.toBuilder().imeProposal(proposenewtimestate.getTimeProposal().withNewStartTime(i, j, proposalchangehandler.timeZone)).d());
        if (proposalchangehandler.postUpdateAction != null)
        {
            proposalchangehandler.postUpdateAction.run();
        }
    }

    (ProposalChangeHandler proposalchangehandler)
    {
        arg$1 = proposalchangehandler;
    }
}
