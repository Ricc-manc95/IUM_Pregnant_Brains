// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import com.google.android.calendar.timely.rooms.data.Room;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.util.Comparator;

final class addedRoomEmails
    implements Comparator
{

    private ImmutableSet addedRoomEmails;

    public final int compare(Object obj, Object obj1)
    {
        obj = (Room)obj;
        obj1 = (Room)obj1;
        return ComparisonChain.ACTIVE.compareTrueFirst(addedRoomEmails.contains(((Room) (obj)).getEmail()), addedRoomEmails.contains(((Room) (obj1)).getEmail())).compare(((Room) (obj)).getAvailability(), ((Room) (obj1)).getAvailability()).compare(((Room) (obj)).getShortName(), ((Room) (obj1)).getShortName()).result();
    }

    (ImmutableSet immutableset)
    {
        addedRoomEmails = immutableset;
    }
}
