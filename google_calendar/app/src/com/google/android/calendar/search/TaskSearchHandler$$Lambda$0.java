// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import com.google.common.base.Function;
import java.util.List;

// Referenced classes of package com.google.android.calendar.search:
//            TaskSearchHandler

final class arg._cls1
    implements Function
{

    private final String arg$1;

    public final Object apply(Object obj)
    {
        return TaskSearchHandler.lambda$search$1$TaskSearchHandler(arg$1, (List)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}
