// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.google.api.client.util.Data;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.common.base.Predicate;

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        boolean flag2 = false;
        obj = (EventAttendee)obj;
        boolean flag;
        boolean flag1;
        if (((EventAttendee) (obj)).self == null || ((EventAttendee) (obj)).self == Data.NULL_BOOLEAN)
        {
            flag1 = false;
        } else
        {
            flag1 = ((EventAttendee) (obj)).self.booleanValue();
        }
        flag = flag2;
        if (!flag1)
        {
            if (((EventAttendee) (obj)).resource == null || ((EventAttendee) (obj)).resource == Data.NULL_BOOLEAN)
            {
                flag1 = false;
            } else
            {
                flag1 = ((EventAttendee) (obj)).resource.booleanValue();
            }
            flag = flag2;
            if (!flag1)
            {
                flag = true;
            }
        }
        return flag;
    }


    private ()
    {
    }
}
