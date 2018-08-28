// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.timebox:
//            Birthday, AutoValue_Birthday

public final class sourceAccount extends sourceAccount
{

    private CalendarKey calendarId;
    private String email;
    private EventKey eventId;
    private String fullName;
    private Boolean isBirthday;
    private Boolean isContactAvailable;
    private Boolean isGPlusUser;
    private Boolean isSelfBirthday;
    private String originalTitle;
    private String photoUrl;
    private String profileId;
    private String sourceAccount;

    public final Birthday build()
    {
        String s1 = "";
        if (eventId == null)
        {
            s1 = String.valueOf("").concat(" eventId");
        }
        String s = s1;
        if (calendarId == null)
        {
            s = String.valueOf(s1).concat(" calendarId");
        }
        s1 = s;
        if (originalTitle == null)
        {
            s1 = String.valueOf(s).concat(" originalTitle");
        }
        s = s1;
        if (fullName == null)
        {
            s = String.valueOf(s1).concat(" fullName");
        }
        s1 = s;
        if (isContactAvailable == null)
        {
            s1 = String.valueOf(s).concat(" isContactAvailable");
        }
        s = s1;
        if (isSelfBirthday == null)
        {
            s = String.valueOf(s1).concat(" isSelfBirthday");
        }
        s1 = s;
        if (isBirthday == null)
        {
            s1 = String.valueOf(s).concat(" isBirthday");
        }
        s = s1;
        if (isGPlusUser == null)
        {
            s = String.valueOf(s1).concat(" isGPlusUser");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_Birthday(eventId, calendarId, originalTitle, fullName, email, isContactAvailable.booleanValue(), isSelfBirthday.booleanValue(), isBirthday.booleanValue(), isGPlusUser.booleanValue(), profileId, photoUrl, sourceAccount);
        }
    }

    public final sourceAccount calendarId(CalendarKey calendarkey)
    {
        if (calendarkey == null)
        {
            throw new NullPointerException("Null calendarId");
        } else
        {
            calendarId = calendarkey;
            return this;
        }
    }

    public final calendarId email(String s)
    {
        email = s;
        return this;
    }

    public final email eventId(EventKey eventkey)
    {
        if (eventkey == null)
        {
            throw new NullPointerException("Null eventId");
        } else
        {
            eventId = eventkey;
            return this;
        }
    }

    public final eventId fullName(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null fullName");
        } else
        {
            fullName = s;
            return this;
        }
    }

    public final fullName isBirthday(boolean flag)
    {
        isBirthday = Boolean.valueOf(flag);
        return this;
    }

    public final isBirthday isContactAvailable(boolean flag)
    {
        isContactAvailable = Boolean.valueOf(flag);
        return this;
    }

    public final isContactAvailable isGPlusUser(boolean flag)
    {
        isGPlusUser = Boolean.valueOf(flag);
        return this;
    }

    public final isGPlusUser isSelfBirthday(boolean flag)
    {
        isSelfBirthday = Boolean.valueOf(flag);
        return this;
    }

    public final isSelfBirthday originalTitle(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null originalTitle");
        } else
        {
            originalTitle = s;
            return this;
        }
    }

    public final originalTitle photoUrl(String s)
    {
        photoUrl = s;
        return this;
    }

    public final photoUrl profileId(String s)
    {
        profileId = s;
        return this;
    }

    public final profileId sourceAccount(String s)
    {
        sourceAccount = s;
        return this;
    }

    public ()
    {
    }

    (Birthday birthday)
    {
        eventId = birthday.eventId();
        calendarId = birthday.calendarId();
        originalTitle = birthday.originalTitle();
        fullName = birthday.fullName();
        email = birthday.email();
        isContactAvailable = Boolean.valueOf(birthday.isContactAvailable());
        isSelfBirthday = Boolean.valueOf(birthday.isSelfBirthday());
        isBirthday = Boolean.valueOf(birthday.isBirthday());
        isGPlusUser = Boolean.valueOf(birthday.isGPlusUser());
        profileId = birthday.profileId();
        photoUrl = birthday.photoUrl();
        sourceAccount = birthday.sourceAccount();
    }
}
