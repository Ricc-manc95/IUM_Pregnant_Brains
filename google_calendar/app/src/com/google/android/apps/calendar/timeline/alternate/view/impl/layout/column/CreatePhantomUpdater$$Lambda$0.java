// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            CreatePhantomUpdater

final class arg._cls1
    implements Consumer
{

    private final CreatePhantomUpdater arg$1;

    public final void accept(Object obj)
    {
        CreatePhantomUpdater createphantomupdater = arg$1;
        obj = (Optional)obj;
        if (!createphantomupdater.currentPhantom.equals(obj))
        {
            createphantomupdater.currentPhantom = ((Optional) (obj));
            if (createphantomupdater.creationMode.automaticTimeout)
            {
                if (((Optional) (obj)).isPresent())
                {
                    long l = CreatePhantomUpdater.TIMEOUT_MS;
                    if (createphantomupdater.currentClearFuture != null)
                    {
                        createphantomupdater.currentClearFuture.cancel(false);
                    }
                    createphantomupdater.currentClearFuture = null;
                    obj = CreatePhantomUpdater.EXECUTOR;
                    arg._cls1 _lcls1 = new <init>(createphantomupdater);
                    TimeUnit timeunit = TimeUnit.MILLISECONDS;
                    createphantomupdater.currentClearFuture = ((CalendarExecutor) (obj)).getDelegate().schedule(_lcls1, l, timeunit);
                } else
                {
                    if (createphantomupdater.currentClearFuture != null)
                    {
                        createphantomupdater.currentClearFuture.cancel(false);
                    }
                    createphantomupdater.currentClearFuture = null;
                }
            }
            createphantomupdater.hostView.requestLayout();
            createphantomupdater.hostView.invalidate();
        }
    }

    (CreatePhantomUpdater createphantomupdater)
    {
        arg$1 = createphantomupdater;
    }
}
