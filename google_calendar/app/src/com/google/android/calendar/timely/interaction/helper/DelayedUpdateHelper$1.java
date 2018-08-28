// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction.helper;

import android.view.View;
import com.google.android.calendar.timely.interaction.InteractionTracker;

// Referenced classes of package com.google.android.calendar.timely.interaction.helper:
//            DelayedUpdateHelper

final class this._cls0
    implements android.view.geListener
{

    private final DelayedUpdateHelper this$0;

    public final void onViewAttachedToWindow(View view)
    {
        boolean flag;
        if (!listening)
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
            listening = true;
            InteractionTracker.getInstance().addListener(DelayedUpdateHelper.this);
            return;
        }
    }

    public final void onViewDetachedFromWindow(View view)
    {
        if (!listening)
        {
            throw new IllegalStateException();
        } else
        {
            listening = false;
            InteractionTracker.getInstance().removeListener(DelayedUpdateHelper.this);
            return;
        }
    }

    Q()
    {
        this$0 = DelayedUpdateHelper.this;
        super();
    }
}
