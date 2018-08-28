// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.annotation;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestRequest;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.annotation:
//            Annotator, DurationRecorder

public final class arg._cls2
    implements Runnable
{

    private final Annotator arg$1;
    private final AnnotatedSuggestRequest arg$2;

    public final void run()
    {
        Object obj = arg$1;
        Object obj1 = arg$2;
        Object obj2 = Features.instance;
        if (obj2 == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj2).fishfood_debug();
        obj.currentFuture = ((Annotator) (obj)).service.startRequest(new com.google.android.calendar.net.taskassist.ambda._cls0(((AnnotatedSuggestRequest) (obj1))));
        obj2 = ((Annotator) (obj)).durationRecorder;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        obj2.currentStart = l;
        obj2 = ((Annotator) (obj)).currentFuture;
        obj = new ure(((Annotator) (obj)), ((AnnotatedSuggestRequest) (obj1)));
        obj1 = CalendarExecutor.MAIN;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj2)).addListener(new com.google.common.util.concurrent.ener(((java.util.concurrent.Future) (obj2)), ((com.google.common.util.concurrent.FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj1)));
            return;
        }
    }

    public (Annotator annotator, AnnotatedSuggestRequest annotatedsuggestrequest)
    {
        arg$1 = annotator;
        arg$2 = annotatedsuggestrequest;
    }
}
