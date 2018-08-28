// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;


// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

public interface 
{

    public abstract boolean isSwipeEnabled();

    public abstract boolean isSwipePossible();

    public abstract void onSwipeGestureCancel(Chip chip);

    public abstract boolean onSwipeGestureComplete(Chip chip, int i);

    public abstract void onSwipeGestureStart(Chip chip);
}
