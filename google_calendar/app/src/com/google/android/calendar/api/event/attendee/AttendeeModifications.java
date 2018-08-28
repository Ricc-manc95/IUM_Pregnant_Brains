// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcelable;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            Attendee, Response

public interface AttendeeModifications
    extends Parcelable
{

    public abstract void addAttendee(Attendee attendee);

    public abstract List getOriginal();

    public abstract boolean isModified();

    public abstract void removeAttendee(Attendee attendee);

    public abstract void setAttendeeResponse(Attendee attendee, Response response);
}
