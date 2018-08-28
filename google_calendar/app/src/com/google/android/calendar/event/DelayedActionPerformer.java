// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;


// Referenced classes of package com.google.android.calendar.event:
//            DelayedActionDescription

public interface DelayedActionPerformer
{

    public abstract void performDelayedAction(DelayedActionDescription delayedactiondescription);

    public abstract boolean shouldDelayAction(DelayedActionDescription delayedactiondescription);
}
