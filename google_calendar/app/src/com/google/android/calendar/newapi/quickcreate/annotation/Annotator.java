// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.annotation;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestResponse;
import java.util.List;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.annotation:
//            DurationRecorder, RequestFactory

public class Annotator
{
    public static interface Listener
    {

        public abstract void onSuggestionsLoaded$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FELQ6IR1F9HKN6T1R55B0____0(List list);
    }


    public static final String TAG = com/google/android/calendar/newapi/quickcreate/annotation/Annotator.getSimpleName();
    public ListenableFuture currentFuture;
    public final DurationRecorder durationRecorder = new DurationRecorder();
    public Listener listener;
    public final RequestFactory requestFactory;
    public final TaskAssistService service;
    public final String sessionId;
    public final Executor throttlingExecutorMain;

    public Annotator(TaskAssistService taskassistservice, RequestFactory requestfactory, Executor executor, String s)
    {
        service = taskassistservice;
        requestFactory = requestfactory;
        throttlingExecutorMain = executor;
        sessionId = s;
    }

    static void logResponse(AnnotatedSuggestResponse annotatedsuggestresponse)
    {
        annotatedsuggestresponse = Features.instance;
        if (annotatedsuggestresponse == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)annotatedsuggestresponse).fishfood_debug();
            return;
        }
    }

}
