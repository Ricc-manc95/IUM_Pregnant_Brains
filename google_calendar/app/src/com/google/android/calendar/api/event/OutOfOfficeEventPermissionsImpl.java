// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            ReadOnlyEventPermissionsImpl, Event, EventPermissionUtils

final class OutOfOfficeEventPermissionsImpl extends ReadOnlyEventPermissionsImpl
{

    private final Event event;

    OutOfOfficeEventPermissionsImpl(Event event1)
    {
        if (event1 == null)
        {
            throw new NullPointerException();
        }
        event = (Event)event1;
        if (!EventPermissionUtils.isGoogleEvent(event1))
        {
            throw new IllegalStateException();
        } else
        {
            return;
        }
    }

    public final boolean canChangeOrganizer()
    {
        return EventPermissionUtils.canChangeOrganizer(event);
    }

    public final boolean canDelete()
    {
        return true;
    }

    public final boolean canModifyAllDayProperty()
    {
        return true;
    }

    public final boolean canModifyColorOverride()
    {
        return true;
    }

    public final boolean canModifyEndTime()
    {
        return true;
    }

    public final boolean canModifyStartTime()
    {
        return true;
    }

    public final boolean canModifySummary()
    {
        return true;
    }

    public final List getAllowedModificationScopes()
    {
        return EventPermissionUtils.getAllowedModificationScopesForEvent(event);
    }

    public final List getAllowedVisibilityValues()
    {
        return EventPermissionUtils.DEFAULT_VSIBILITITY_LIST;
    }

    public final boolean isReadOnly()
    {
        return false;
    }
}
