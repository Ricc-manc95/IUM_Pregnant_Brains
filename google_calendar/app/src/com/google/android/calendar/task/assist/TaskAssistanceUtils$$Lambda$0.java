// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import com.google.common.base.Function;
import com.google.personalization.assist.annotate.api.TaskAssistanceResponse;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistanceUtils

final class arg._cls1
    implements Function
{

    private final String arg$1;

    public final Object apply(Object obj)
    {
        return TaskAssistanceUtils.lambda$fetchAssistance$0$TaskAssistanceUtils(arg$1, (TaskAssistanceResponse)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}
