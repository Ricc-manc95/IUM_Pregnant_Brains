// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import android.os.Parcel;
import com.google.common.base.Function;
import java.lang.reflect.Array;

public final class val.type
    implements android.os.Creator
{

    private final Class val$type;
    private final Function val$unparceler;

    public final Object createFromParcel(Parcel parcel)
    {
        return val$unparceler.apply(parcel);
    }

    public final Object[] newArray(int i)
    {
        return (Object[])Array.newInstance(val$type, i);
    }

    public ()
    {
        val$unparceler = function;
        val$type = class1;
        super();
    }
}
