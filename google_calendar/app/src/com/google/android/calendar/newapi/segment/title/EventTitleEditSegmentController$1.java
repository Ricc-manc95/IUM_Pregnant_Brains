// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newapi.logging.EventEditLogMetrics;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditLogMetricsHolder;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            EventTitleEditSegmentController

final class this._cls0
    implements FutureCallback
{

    private final EventTitleEditSegmentController this$0;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e(EventTitleEditSegmentController.TAG, throwable, "Quick create unreachable.", new Object[0]);
        throwable = ((EventEditLogMetricsHolder)(EditScreenModel)model).getLogMetrics();
        throwable.quickCreateStatus = Math.max(((EventEditLogMetrics) (throwable)).quickCreateStatus, 0);
    }

    public final void onSuccess(Object obj)
    {
        obj = ((EventEditLogMetricsHolder)(EditScreenModel)model).getLogMetrics();
        obj.quickCreateStatus = Math.max(((EventEditLogMetrics) (obj)).quickCreateStatus, 2);
    }

    ()
    {
        this$0 = EventTitleEditSegmentController.this;
        super();
    }
}
