// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.ValueAnimator;
import com.google.android.apps.calendar.util.concurrent.AsyncSettable;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

final class arg._cls1
    implements android.animation.eListener
{

    private final ObservableReference arg$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        arg$1.set(valueanimator);
    }

    (ObservableReference observablereference)
    {
        arg$1 = observablereference;
    }
}
