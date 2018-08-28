// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import android.content.Context;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Optional;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            SwipeTaskUtils

final class arg._cls2
    implements Consumer
{

    private final Context arg$1;
    private final Set arg$2;

    public final void accept(Object obj)
    {
        SwipeTaskUtils.lambda$updateTaskDoneAsync$3$SwipeTaskUtils(arg$1, arg$2, (Optional)obj);
    }

    (Context context, Set set)
    {
        arg$1 = context;
        arg$2 = set;
    }
}
