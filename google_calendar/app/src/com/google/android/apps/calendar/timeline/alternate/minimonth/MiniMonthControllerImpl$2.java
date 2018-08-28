// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.view.View;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import java.util.Arrays;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthControllerImpl

final class this._cls0
    implements android.view.stener
{

    private Subscription attachSubscriptions;
    public final MiniMonthControllerImpl this$0;

    public final void onViewAttachedToWindow(View view)
    {
        boolean flag;
        if (attachSubscriptions == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            class .Lambda._cls0
                implements Consumer
            {

                private final MiniMonthControllerImpl._cls2 arg$1;

                public final void accept(Object obj)
                {
                    arg$1.this$0.updateLayoutParams();
                }

            .Lambda._cls0()
            {
                arg$1 = MiniMonthControllerImpl._cls2.this;
            }
            }

            view = new .Lambda._cls0();
            attachSubscriptions = new com.google.android.apps.calendar.util.concurrent.it>(Arrays.asList(new Subscription[] {
                screenType.subscribe(view, new com.google.android.apps.calendar.util.concurrent.(CalendarExecutor.MAIN), false), isPortrait.subscribe(view, new com.google.android.apps.calendar.util.concurrent.(CalendarExecutor.MAIN), false), shouldShowWeekNumbers.subscribe(view, new com.google.android.apps.calendar.util.concurrent.(CalendarExecutor.MAIN), false)
            }));
            return;
        }
    }

    public final void onViewDetachedFromWindow(View view)
    {
        attachSubscriptions.cancel(false);
        attachSubscriptions = null;
    }

    .Lambda._cls0()
    {
        this$0 = MiniMonthControllerImpl.this;
        super();
    }
}
