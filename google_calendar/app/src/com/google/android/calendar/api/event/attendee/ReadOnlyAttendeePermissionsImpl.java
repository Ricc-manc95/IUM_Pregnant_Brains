// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;


// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            AttendeePermissions

public class ReadOnlyAttendeePermissionsImpl
    implements AttendeePermissions
{

    public ReadOnlyAttendeePermissionsImpl()
    {
    }

    public boolean canAddAttendees()
    {
        return false;
    }

    public boolean canModifyResponseComment()
    {
        return false;
    }

    public boolean canModifyResponseStatus()
    {
        return false;
    }

    public boolean canProposeNewTime()
    {
        return false;
    }

    public boolean canRemoveAttendees()
    {
        return false;
    }

    public boolean isReadOnly()
    {
        return true;
    }
}
