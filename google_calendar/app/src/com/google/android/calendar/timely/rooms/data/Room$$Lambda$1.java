// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomFeature

public final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        obj = (RoomFeature)obj;
        return ((RoomFeature) (obj)).getEquipmentType() == 2 || ((RoomFeature) (obj)).getEquipmentType() == 1;
    }


    private ()
    {
    }
}
