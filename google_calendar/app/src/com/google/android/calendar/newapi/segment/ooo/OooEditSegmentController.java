// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.ooo;

import android.support.design.textfield.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.userstatus.AutoValue_UserStatus;
import com.google.android.calendar.api.event.userstatus.OutOfOffice;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.ooo:
//            OooEditSegment

public class OooEditSegmentController extends EditSegmentController
    implements OooEditSegment.Listener
{

    public OooEditSegmentController()
    {
    }

    private final OutOfOffice getOutOfOffice()
    {
        if (((EventModificationsHolder)super.model).getEventModifications().getParticipantStatus() == null)
        {
            return null;
        } else
        {
            return ((EventModificationsHolder)super.model).getEventModifications().getParticipantStatus().getOutOfOffice();
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (OooEditSegment)layoutinflater.inflate(0x7f0500dd, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onAutoDeclineMessageTextChanged(String s)
    {
        if (s.equals(getOutOfOffice().getCalendarDeclineMessage()))
        {
            return;
        } else
        {
            s = getOutOfOffice().toBuilder().setCalendarDeclineMessage(s).build();
            ((EventModificationsHolder)super.model).getEventModifications().setParticipantStatus(new AutoValue_UserStatus(s));
            return;
        }
    }

    protected final void onInitialize()
    {
        Object obj = super.view;
        boolean flag;
        if (getOutOfOffice() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (obj != null)
        {
            EditText edittext;
            Object obj1;
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((View) (obj)).setVisibility(i);
        }
        if (flag)
        {
            obj = (OooEditSegment)super.view;
            obj1 = getOutOfOffice();
            ((OooEditSegment) (obj)).autoDeclineTextInputLayout.hintAnimationEnabled = false;
            edittext = ((OooEditSegment) (obj)).autoDeclineMessageText;
            obj1 = ((OutOfOffice) (obj1)).getCalendarDeclineMessage();
            if (!edittext.getText().toString().equals(obj1))
            {
                edittext.setText(((CharSequence) (obj1)));
            }
            ((OooEditSegment) (obj)).autoDeclineTextInputLayout.hintAnimationEnabled = true;
        }
    }
}
