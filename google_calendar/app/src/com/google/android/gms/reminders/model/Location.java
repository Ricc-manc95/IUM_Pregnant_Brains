// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Address, FeatureIdProto

public interface Location
    extends Parcelable, Freezable
{

    public abstract Address getAddress();

    public abstract String getDisplayAddress();

    public abstract FeatureIdProto getGeoFeatureId();

    public abstract Double getLat();

    public abstract Double getLng();

    public abstract String getLocationAliasId();

    public abstract Integer getLocationType();

    public abstract String getName();

    public abstract Integer getRadiusMeters();
}
