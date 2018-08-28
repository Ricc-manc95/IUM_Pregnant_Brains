// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;

import com.google.type.DayOfWeek;

final class 
    implements com.google.protobuf.tCondition._cls1
{

    public final Object convert(Object obj)
    {
        DayOfWeek dayofweek = DayOfWeek.forNumber(((Integer)obj).intValue());
        obj = dayofweek;
        if (dayofweek == null)
        {
            obj = DayOfWeek.DAY_OF_WEEK_UNSPECIFIED;
        }
        return obj;
    }

    ()
    {
    }
}
