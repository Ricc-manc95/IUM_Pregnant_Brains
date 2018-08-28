// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Address
    extends Parcelable, Freezable
{

    public abstract String getCountry();

    public abstract String getLocality();

    public abstract String getName();

    public abstract String getPostalCode();

    public abstract String getRegion();

    public abstract String getStreetAddress();

    public abstract String getStreetName();

    public abstract String getStreetNumber();
}
