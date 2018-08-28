// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EveryoneDeclinedManager, EventViewScreenController

final class val.editModel
    implements FutureCallback
{

    private final EveryoneDeclinedManager this$0;
    private final EventEditScreenModel val$editModel;

    public final void onFailure(Throwable throwable)
    {
        Toast.makeText(eventViewScreenController.getContext(), 0x7f1301ab, 0).show();
        LoggingUtils.logSaveFailure(eventViewScreenController.getContext(), val$editModel, 0);
    }

    public final void onSuccess(Object obj)
    {
        obj = (Event)obj;
        eventViewScreenController.updateSegmentsWithUpdatedEvent(((Event) (obj)));
    }

    I()
    {
        this$0 = final_everyonedeclinedmanager;
        val$editModel = EventEditScreenModel.this;
        super();
    }
}
