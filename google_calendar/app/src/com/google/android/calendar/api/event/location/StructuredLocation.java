// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import android.os.Parcelable;
import java.util.Collection;

public interface StructuredLocation
    extends Parcelable
{

    public abstract Collection getEventLocations();
}
