// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.calendar.event.conference.AccessCode;
import com.google.android.calendar.utils.phone.PhoneUtil;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.event:
//            AccessCodePickerDialog, ConferenceCallUtils

final class this._cls0
    implements android.content.stener
{

    private final AccessCodePickerDialog this$0;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        String s = null;
        dialoginterface = AccessCodePickerDialog.this;
        PhoneUtil phoneutil;
        String s1;
        AccessCodePickerDialog accesscodepickerdialog;
        if (((Fragment) (dialoginterface)).mHost == null)
        {
            dialoginterface = null;
        } else
        {
            dialoginterface = (FragmentActivity)((Fragment) (dialoginterface)).mHost.mActivity;
        }
        phoneutil = (PhoneUtil)dialoginterface;
        s1 = conferenceNumber;
        accesscodepickerdialog = AccessCodePickerDialog.this;
        if (accesscodepickerdialog.adapter.selectedItemIndex < accesscodepickerdialog.accessCodes.size())
        {
            s = ((AccessCode)accesscodepickerdialog.accessCodes.get(accesscodepickerdialog.adapter.selectedItemIndex)).number;
        }
        phoneutil.makeCall(ConferenceCallUtils.buildUri(s1, s));
        if (adapter.selectedItemIndex == adapter.getCount() - 1)
        {
            ConferenceCallUtils.logAction(dialoginterface, "join_conference", "without_passcode");
            return;
        } else
        {
            ConferenceCallUtils.logAction(dialoginterface, "join_conference", "with_passcode");
            return;
        }
    }

    cessCodeAdapter()
    {
        this$0 = AccessCodePickerDialog.this;
        super();
    }
}
