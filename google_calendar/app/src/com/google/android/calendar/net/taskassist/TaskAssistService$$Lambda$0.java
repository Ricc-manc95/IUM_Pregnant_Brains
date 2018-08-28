// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.net.taskassist;

import com.google.common.base.Function;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestRequest;

// Referenced classes of package com.google.android.calendar.net.taskassist:
//            TaskAssistService

public final class arg._cls1
    implements Function
{

    private final AnnotatedSuggestRequest arg$1;

    public final Object apply(Object obj)
    {
        return TaskAssistService.lambda$suggest$0$TaskAssistService(arg$1, (com.google.personalization.assist.annotate.api.lockingStub)obj);
    }

    public iceBlockingStub(AnnotatedSuggestRequest annotatedsuggestrequest)
    {
        arg$1 = annotatedsuggestrequest;
    }
}
