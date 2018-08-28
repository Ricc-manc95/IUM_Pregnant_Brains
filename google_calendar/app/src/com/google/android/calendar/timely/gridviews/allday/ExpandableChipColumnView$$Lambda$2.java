// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.support.v4.util.Pair;
import com.google.common.base.Function;

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = ()obj;
        if ((() (obj)).sPair == null)
        {
            obj.sPair = new Pair(((sPair) (obj)).artitionInfo, ((artitionInfo) (obj)).hip);
        }
        return ((hip) (obj)).sPair;
    }


    private ()
    {
    }
}
