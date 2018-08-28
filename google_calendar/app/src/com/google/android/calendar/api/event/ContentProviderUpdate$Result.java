// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            EventDescriptor

final class changed
{

    public final boolean changed;
    public final EventDescriptor updatedDescriptor;

    (EventDescriptor eventdescriptor, boolean flag)
    {
        updatedDescriptor = eventdescriptor;
        changed = flag;
    }
}
