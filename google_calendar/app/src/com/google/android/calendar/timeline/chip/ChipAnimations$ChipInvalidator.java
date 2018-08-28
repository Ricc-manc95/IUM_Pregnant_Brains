// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

public final class 
    implements android.animation.tener
{

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        ((Chip)((ObjectAnimator)valueanimator).getTarget()).invalidateIncludingFootprint();
    }

    ()
    {
    }
}
