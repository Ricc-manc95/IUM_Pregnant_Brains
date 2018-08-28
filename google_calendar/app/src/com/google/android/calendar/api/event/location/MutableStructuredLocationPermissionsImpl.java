// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import com.google.android.calendar.api.event.Event;

// Referenced classes of package com.google.android.calendar.api.event.location:
//            ReadOnlyStructuredLocationPermissionsImpl

public final class MutableStructuredLocationPermissionsImpl extends ReadOnlyStructuredLocationPermissionsImpl
{

    public MutableStructuredLocationPermissionsImpl(Event event)
    {
        if (event == null)
        {
            throw new NullPointerException();
        } else
        {
            return;
        }
    }

    public final boolean canAddLocations()
    {
        return true;
    }

    public final boolean canRemoveLocation()
    {
        return true;
    }

    public final boolean isReadOnly()
    {
        return false;
    }
}
