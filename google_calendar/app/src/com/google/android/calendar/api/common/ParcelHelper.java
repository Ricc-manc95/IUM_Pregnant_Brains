// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ParcelHelper
{

    public static Enum readFromParcel(Parcel parcel, Class class1)
    {
        int j = parcel.readInt();
        if (j == -1)
        {
            return null;
        }
        parcel = (Enum[])class1.getEnumConstants();
        int k = parcel.length;
        for (int i = 0; i < k; i++)
        {
            Enum enum = parcel[i];
            if (enum.ordinal() == j)
            {
                return enum;
            }
        }

        parcel = class1.getSimpleName();
        throw new IllegalStateException((new StringBuilder(String.valueOf(parcel).length() + 20)).append("Invalid ").append(parcel).append(" enum value.").toString());
    }

    public static List unparcelUnmodifiableIntegerList(Parcel parcel)
    {
        ArrayList arraylist = new ArrayList();
        parcel.readList(arraylist, java/lang/Integer.getClassLoader());
        if (arraylist.isEmpty())
        {
            return Collections.emptyList();
        } else
        {
            return Collections.unmodifiableList(arraylist);
        }
    }

    public static List unparcelUnmodifiableLongList(Parcel parcel)
    {
        ArrayList arraylist = new ArrayList();
        parcel.readList(arraylist, java/lang/Long.getClassLoader());
        if (arraylist.isEmpty())
        {
            return Collections.emptyList();
        } else
        {
            return Collections.unmodifiableList(arraylist);
        }
    }
}
