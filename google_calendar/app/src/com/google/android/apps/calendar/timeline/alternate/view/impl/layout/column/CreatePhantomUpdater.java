// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class CreatePhantomUpdater
{

    public static final CalendarExecutor EXECUTOR;
    public static final long TIMEOUT_MS;
    public final CreationMode creationMode;
    public Future currentClearFuture;
    public Optional currentPhantom;
    public final TimelineHostView hostView;
    public final ObservableReference phantomObservable;

    public CreatePhantomUpdater(CreationMode creationmode, TimelineHostView timelinehostview, ObservableReference observablereference)
    {
        currentPhantom = Absent.INSTANCE;
        creationMode = creationmode;
        hostView = timelinehostview;
        phantomObservable = observablereference;
        class .Lambda._cls0
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
                            class .Lambda._cls1
                                implements Runnable
                            {

                                private final CreatePhantomUpdater arg$1;

                                public final void run()
                                {
                                    arg$1.phantomObservable.set(Absent.INSTANCE);
                                }

                        .Lambda._cls1()
                        {
                            arg$1 = CreatePhantomUpdater.this;
                        }
                            }

                            .Lambda._cls1 _lcls1 = createphantomupdater. new .Lambda._cls1();
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

            .Lambda._cls0()
            {
                arg$1 = CreatePhantomUpdater.this;
            }
        }

        observablereference.subscribe(new .Lambda._cls0(), EXECUTOR, true);
    }

    static 
    {
        TIMEOUT_MS = TimeUnit.SECONDS.toMillis(3L);
        EXECUTOR = CalendarExecutor.MAIN;
    }
}
