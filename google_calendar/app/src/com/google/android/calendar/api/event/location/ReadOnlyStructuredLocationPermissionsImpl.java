// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;


// Referenced classes of package com.google.android.calendar.api.event.location:
//            StructuredLocationPermissions

public class ReadOnlyStructuredLocationPermissionsImpl
    implements StructuredLocationPermissions
{

    public ReadOnlyStructuredLocationPermissionsImpl()
    {
    }

    public boolean canAddLocations()
    {
        return false;
    }

    public boolean canRemoveLocation()
    {
        return false;
    }

    public boolean isReadOnly()
    {
        return true;
    }
}
