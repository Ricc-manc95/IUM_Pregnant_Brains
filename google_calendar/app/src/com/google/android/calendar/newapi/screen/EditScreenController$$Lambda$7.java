// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EditScreenController

public final class arg._cls1
    implements Consumer
{

    private final boolean arg$1;

    public final void accept(Object obj)
    {
        EditScreenController.lambda$notifyLocationChanged$2$EditScreenController(arg$1, (EditSegmentController)obj);
    }

    public (boolean flag)
    {
        arg$1 = flag;
    }
}
