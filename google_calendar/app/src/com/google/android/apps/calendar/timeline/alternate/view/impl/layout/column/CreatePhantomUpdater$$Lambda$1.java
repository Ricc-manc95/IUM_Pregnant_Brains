// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Absent;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            CreatePhantomUpdater

final class arg._cls1
    implements Runnable
{

    private final CreatePhantomUpdater arg$1;

    public final void run()
    {
        arg$1.phantomObservable.set(Absent.INSTANCE);
    }

    (CreatePhantomUpdater createphantomupdater)
    {
        arg$1 = createphantomupdater;
    }
}
