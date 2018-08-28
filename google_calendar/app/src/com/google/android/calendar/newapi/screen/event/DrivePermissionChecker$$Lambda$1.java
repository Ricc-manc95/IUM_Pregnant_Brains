// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            DrivePermissionChecker

final class arg._cls1
    implements Consumer
{

    private final Event arg$1;

    public final void accept(Object obj)
    {
        DrivePermissionChecker.lambda$start$0$DrivePermissionChecker(arg$1, (DrivePermissionChecker)obj);
    }

    (Event event)
    {
        arg$1 = event;
    }
}
