// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.net.Uri;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            AbstractEventViewScreenController

final class arg._cls2
    implements Runnable
{

    private final AbstractEventViewScreenController arg$1;
    private final Uri arg$2;

    public final void run()
    {
        AbstractEventViewScreenController.lambda$notifyContentProviderUpdateIfAvailable$0$AbstractEventViewScreenController(arg$1, arg$2);
    }

    (AbstractEventViewScreenController abstracteventviewscreencontroller, Uri uri)
    {
        arg$1 = abstracteventviewscreencontroller;
        arg$2 = uri;
    }
}
