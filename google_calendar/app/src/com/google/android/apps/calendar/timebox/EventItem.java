// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.EventDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            Item

public interface EventItem
    extends Item
{

    public abstract Event getEvent();

    public abstract EventDescriptor getEventDescriptor();
}
