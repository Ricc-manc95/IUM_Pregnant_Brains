// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissionUtils;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ReadOnlyConferencePermissionsImpl

public final class MutableConferencePermissionsImpl extends ReadOnlyConferencePermissionsImpl
{

    private final Event event;

    public MutableConferencePermissionsImpl(Event event1)
    {
        if (event1 == null)
        {
            throw new NullPointerException();
        } else
        {
            event = (Event)event1;
            return;
        }
    }

    public final boolean isReadOnly()
    {
        return !EventPermissionUtils.isGoogleEvent(event);
    }
}
