// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab;

import com.google.android.apps.calendar.proposenewtime.state.AutoValue_TimeProposal;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.StateHolder;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import java.util.Calendar;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.slab:
//            ProposalChangeHandler

final class arg._cls1
    implements com.android.datetimepicker.time.er
{

    private final ProposalChangeHandler arg$1;

    public final void onTimeSet(int i, int j)
    {
        ProposalChangeHandler proposalchangehandler = arg$1;
        Object obj = proposalchangehandler.stateHolder.getState();
        StateHolder stateholder = proposalchangehandler.stateHolder;
        com.google.android.apps.calendar.proposenewtime.state.rg._cls1 _lcls1 = ((ProposeNewTimeState) (obj)).toBuilder();
        obj = ((ProposeNewTimeState) (obj)).getTimeProposal();
        Object obj1 = proposalchangehandler.timeZone;
        long l = ((TimeProposal) (obj)).getEndTimeMillis();
        obj1 = Calendar.getInstance(((java.util.TimeZone) (obj1)));
        ((Calendar) (obj1)).setTimeInMillis(l);
        ((Calendar) (obj1)).set(11, i);
        ((Calendar) (obj1)).set(12, j);
        stateholder.setState(_lcls1.imeProposal(new AutoValue_TimeProposal(((TimeProposal) (obj)).getStartTimeMillis(), ((Calendar) (obj1)).getTimeInMillis(), ((TimeProposal) (obj)).getComment())).d());
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