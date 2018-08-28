// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.newapi.model.edit.AssistInformationEditHolder;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            ReminderTitleEditSegmentController

final class arg._cls1
    implements Consumer
{

    private final ReminderTitleEditSegmentController arg$1;

    public final void accept(Object obj)
    {
        ReminderTitleEditSegmentController remindertitleeditsegmentcontroller = arg$1;
        obj = (byte[])obj;
        ((AssistInformationEditHolder)(EditScreenModel)((SegmentController) (remindertitleeditsegmentcontroller)).model).setAssistInformation(((byte []) (obj)));
        remindertitleeditsegmentcontroller.notifyTaskAssistChanged();
    }

    (ReminderTitleEditSegmentController remindertitleeditsegmentcontroller)
    {
        arg$1 = remindertitleeditsegmentcontroller;
    }
}
