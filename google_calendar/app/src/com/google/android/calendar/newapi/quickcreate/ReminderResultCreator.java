// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.content.Context;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.android.calendar.task.assist.TaskAssistanceUtils;
import com.google.common.base.Platform;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            ResultCreator, AutoValue_ReminderResult, AutoValue_ReminderResult_TaskAssistanceInfo

public final class ReminderResultCreator
    implements ResultCreator
{

    private final Context context;
    private String lastAnnotationHint;
    public byte lastFetchedAssistance[];
    private String lastFetchedQuery;
    private final TaskAssistService service;

    public ReminderResultCreator(Context context1, TaskAssistService taskassistservice)
    {
        context = context1;
        service = taskassistservice;
    }

    public final Object createResult(String s, List list, boolean flag)
    {
        list = lastFetchedAssistance;
        String s1 = lastFetchedQuery;
        return new AutoValue_ReminderResult(s, new AutoValue_ReminderResult_TaskAssistanceInfo(Platform.nullToEmpty(lastAnnotationHint), list, Platform.nullToEmpty(s1)));
    }

    public final void processSelectedSuggestion(List list, String s, String s1)
    {
        lastFetchedQuery = s;
        lastAnnotationHint = s1;
        class .Lambda._cls0
            implements Consumer
        {

            private final ReminderResultCreator arg$1;

            public final void accept(Object obj)
            {
                arg$1.lastFetchedAssistance = (byte[])obj;
            }

            .Lambda._cls0()
            {
                arg$1 = ReminderResultCreator.this;
            }
        }

        TaskAssistanceUtils.fetchAssistance(context, service, s, s1, new .Lambda._cls0());
    }
}
