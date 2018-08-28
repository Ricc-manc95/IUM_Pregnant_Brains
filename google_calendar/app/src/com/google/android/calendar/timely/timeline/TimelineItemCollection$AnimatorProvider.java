// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.timeline;

import android.animation.Animator;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.TimelineItem;

// Referenced classes of package com.google.android.calendar.timely.timeline:
//            TimelineItemCollection

public static interface Y
{

    public abstract Animator createAfterExcludeItemAnimator(Chip chip, TimelineItem timelineitem);

    public abstract Animator createBeforeIncludeItemAnimator(TimelineItem timelineitem);

    public abstract Animator createBeforeUpdateItemAnimator(Chip chip, TimelineItem timelineitem, TimelineItem timelineitem1);
}
