// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public interface CategoryInfo
    extends Parcelable, Freezable
{

    public abstract String getCategoryId();

    public abstract String getDisplayName();

    public abstract List getPlaceTypes();
}
