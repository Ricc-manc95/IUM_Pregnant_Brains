// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attachment;

import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissionUtils;

// Referenced classes of package com.google.android.calendar.api.event.attachment:
//            ReadOnlyAttachmentPermissionsImpl

public final class MutableAttachmentPermissionsImpl extends ReadOnlyAttachmentPermissionsImpl
{

    private final Event event;

    public MutableAttachmentPermissionsImpl(Event event1)
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

    public final boolean canAddAttachment()
    {
        return EventPermissionUtils.isGoogleEvent(event);
    }

    public final boolean canRemoveAttachment()
    {
        return EventPermissionUtils.isGoogleEvent(event);
    }

    public final boolean isReadOnly()
    {
        return !EventPermissionUtils.isGoogleEvent(event);
    }
}
