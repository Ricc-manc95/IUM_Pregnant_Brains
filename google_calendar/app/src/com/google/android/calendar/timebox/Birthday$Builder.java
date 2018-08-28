// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.timebox:
//            Birthday

public abstract class Key
{

    public abstract Birthday build();

    public abstract Key calendarId(CalendarKey calendarkey);

    public abstract Key email(String s);

    public abstract Key eventId(EventKey eventkey);

    public abstract Key fullName(String s);

    public abstract Key isBirthday(boolean flag);

    public abstract Key isContactAvailable(boolean flag);

    public abstract Key isGPlusUser(boolean flag);

    public abstract Key isSelfBirthday(boolean flag);

    public abstract Key originalTitle(String s);

    public abstract Key photoUrl(String s);

    public abstract Key profileId(String s);

    public abstract Key sourceAccount(String s);

    public Key()
    {
    }
}
