// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty.adapter;

import android.content.Context;
import android.view.View;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.utils.phone.PhoneUtil;
import com.google.android.calendar.utils.snackbar.SnackbarShower;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference.thirdparty.adapter:
//            ConferenceAdapter

public final class SipConferenceAdapter extends ConferenceAdapter
{

    private final PhoneUtil caller;

    public SipConferenceAdapter(Context context, SnackbarShower snackbarshower, PhoneUtil phoneutil, Conference conference)
    {
        super(context, snackbarshower, conference);
        caller = phoneutil;
    }

    protected final int getOnLongClickSuccessString()
    {
        return 0x7f13013b;
    }

    public final void onClick(View view)
    {
        startCall(caller);
    }
}
