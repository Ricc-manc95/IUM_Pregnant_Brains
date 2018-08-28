// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.annotation;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.util.concurrent.FutureCallback;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestRequest;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestResponse;
import java.util.List;
import java.util.concurrent.CancellationException;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.annotation:
//            Annotator, DurationRecorder

final class 
    implements FutureCallback
{

    private final Annotator this$0;
    private final AnnotatedSuggestRequest val$request;

    public final void onFailure(Throwable throwable)
    {
        if (throwable instanceof CancellationException)
        {
            LogUtils.d(Annotator.TAG, "QuickCreate annotation canceled.", new Object[0]);
            return;
        } else
        {
            LogUtils.e(Annotator.TAG, throwable, "Error annotating text in QuickCreate.", new Object[0]);
            return;
        }
    }

    public final void onSuccess(Object obj)
    {
        obj = (AnnotatedSuggestResponse)obj;
        if (obj != null && listener != null)
        {
            Object obj1 = Annotator.this;
            Annotator.logResponse(((AnnotatedSuggestResponse) (obj)));
            obj1 = durationRecorder;
            Object obj2 = ((DurationRecorder) (obj1)).durations;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            ((List) (obj2)).add(Long.valueOf(l - ((DurationRecorder) (obj1)).currentStart));
            obj1 = listener;
            obj2 = val$request.query_;
            ((stener) (obj1))._mth5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FELQ6IR1F9HKN6T1R55B0____0(((AnnotatedSuggestResponse) (obj)).annotatedSuggestion_);
        }
    }

    ()
    {
        this$0 = final_annotator;
        val$request = AnnotatedSuggestRequest.this;
        super();
    }
}
