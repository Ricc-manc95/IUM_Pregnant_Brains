// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import java.util.Collections;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            ChipAnimationChoreographer

final class arg._cls1
    implements Runnable
{

    private final ChipAnimationChoreographer arg$1;

    public final void run()
    {
        ChipAnimationChoreographer chipanimationchoreographer = arg$1;
        ChipAnimationChoreographer.setPositions(chipanimationchoreographer.animatedChips, .instance);
        chipanimationchoreographer.animateAllChipsThenRun(Collections.EMPTY_LIST, null);
    }

    (ChipAnimationChoreographer chipanimationchoreographer)
    {
        arg$1 = chipanimationchoreographer;
    }
}
