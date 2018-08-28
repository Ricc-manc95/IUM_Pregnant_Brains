// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomCriteria

public abstract class AttendeeGroup
{

    public AttendeeGroup()
    {
    }

    public abstract RoomCriteria getCriteria();

    public abstract String getDisplayName();

    public abstract String getHierarchyNodeId();

    public abstract ImmutableList getRoomSuggestions();
}
