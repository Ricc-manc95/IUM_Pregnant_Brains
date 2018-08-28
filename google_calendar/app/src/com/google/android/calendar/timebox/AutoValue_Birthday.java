// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.timebox:
//            Birthday

final class AutoValue_Birthday extends Birthday
{

    private final CalendarKey calendarId;
    private final String email;
    private final EventKey eventId;
    private final String fullName;
    private final boolean isBirthday;
    private final boolean isContactAvailable;
    private final boolean isGPlusUser;
    private final boolean isSelfBirthday;
    private final String originalTitle;
    private final String photoUrl;
    private final String profileId;
    private final String sourceAccount;

    AutoValue_Birthday(EventKey eventkey, CalendarKey calendarkey, String s, String s1, String s2, boolean flag, boolean flag1, 
            boolean flag2, boolean flag3, String s3, String s4, String s5)
    {
        eventId = eventkey;
        calendarId = calendarkey;
        originalTitle = s;
        fullName = s1;
        email = s2;
        isContactAvailable = flag;
        isSelfBirthday = flag1;
        isBirthday = flag2;
        isGPlusUser = flag3;
        profileId = s3;
        photoUrl = s4;
        sourceAccount = s5;
    }

    public final CalendarKey calendarId()
    {
        return calendarId;
    }

    public final String email()
    {
        return email;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof Birthday))
            {
                break MISSING_BLOCK_LABEL_244;
            }
            obj = (Birthday)obj;
            if (eventId.equals(((Birthday) (obj)).eventId()) && calendarId.equals(((Birthday) (obj)).calendarId()) && originalTitle.equals(((Birthday) (obj)).originalTitle()) && fullName.equals(((Birthday) (obj)).fullName()) && (email != null ? email.equals(((Birthday) (obj)).email()) : ((Birthday) (obj)).email() == null) && (isContactAvailable == ((Birthday) (obj)).isContactAvailable() && isSelfBirthday == ((Birthday) (obj)).isSelfBirthday() && isBirthday == ((Birthday) (obj)).isBirthday() && isGPlusUser == ((Birthday) (obj)).isGPlusUser()) && (profileId != null ? profileId.equals(((Birthday) (obj)).profileId()) : ((Birthday) (obj)).profileId() == null) && (photoUrl != null ? photoUrl.equals(((Birthday) (obj)).photoUrl()) : ((Birthday) (obj)).photoUrl() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (sourceAccount != null) goto _L4; else goto _L3
_L3:
        if (((Birthday) (obj)).sourceAccount() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!sourceAccount.equals(((Birthday) (obj)).sourceAccount())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final EventKey eventId()
    {
        return eventId;
    }

    public final String fullName()
    {
        return fullName;
    }

    public final int hashCode()
    {
        char c3 = '\u04CF';
        int l = 0;
        int i1 = eventId.hashCode();
        int j1 = calendarId.hashCode();
        int k1 = originalTitle.hashCode();
        int l1 = fullName.hashCode();
        int i;
        char c;
        char c1;
        char c2;
        int j;
        int k;
        if (email == null)
        {
            i = 0;
        } else
        {
            i = email.hashCode();
        }
        if (isContactAvailable)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (isSelfBirthday)
        {
            c1 = '\u04CF';
        } else
        {
            c1 = '\u04D5';
        }
        if (isBirthday)
        {
            c2 = '\u04CF';
        } else
        {
            c2 = '\u04D5';
        }
        if (!isGPlusUser)
        {
            c3 = '\u04D5';
        }
        if (profileId == null)
        {
            j = 0;
        } else
        {
            j = profileId.hashCode();
        }
        if (photoUrl == null)
        {
            k = 0;
        } else
        {
            k = photoUrl.hashCode();
        }
        if (sourceAccount != null)
        {
            l = sourceAccount.hashCode();
        }
        return (k ^ (j ^ ((c2 ^ (c1 ^ (c ^ (i ^ ((((i1 ^ 0xf4243) * 0xf4243 ^ j1) * 0xf4243 ^ k1) * 0xf4243 ^ l1) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ c3) * 0xf4243) * 0xf4243) * 0xf4243 ^ l;
    }

    public final boolean isBirthday()
    {
        return isBirthday;
    }

    public final boolean isContactAvailable()
    {
        return isContactAvailable;
    }

    public final boolean isGPlusUser()
    {
        return isGPlusUser;
    }

    public final boolean isSelfBirthday()
    {
        return isSelfBirthday;
    }

    public final String originalTitle()
    {
        return originalTitle;
    }

    public final String photoUrl()
    {
        return photoUrl;
    }

    public final String profileId()
    {
        return profileId;
    }

    public final String sourceAccount()
    {
        return sourceAccount;
    }

    public final Birthday.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        String s = String.valueOf(eventId);
        String s1 = String.valueOf(calendarId);
        String s2 = originalTitle;
        String s3 = fullName;
        String s4 = email;
        boolean flag = isContactAvailable;
        boolean flag1 = isSelfBirthday;
        boolean flag2 = isBirthday;
        boolean flag3 = isGPlusUser;
        String s5 = profileId;
        String s6 = photoUrl;
        String s7 = sourceAccount;
        return (new StringBuilder(String.valueOf(s).length() + 190 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length())).append("Birthday{eventId=").append(s).append(", calendarId=").append(s1).append(", originalTitle=").append(s2).append(", fullName=").append(s3).append(", email=").append(s4).append(", isContactAvailable=").append(flag).append(", isSelfBirthday=").append(flag1).append(", isBirthday=").append(flag2).append(", isGPlusUser=").append(flag3).append(", profileId=").append(s5).append(", photoUrl=").append(s6).append(", sourceAccount=").append(s7).append("}").toString();
    }

    private class Builder extends Birthday.Builder
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

        public final Birthday.Builder calendarId(CalendarKey calendarkey)
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

        public final Birthday.Builder email(String s)
        {
            email = s;
            return this;
        }

        public final Birthday.Builder eventId(EventKey eventkey)
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

        public final Birthday.Builder fullName(String s)
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

        public final Birthday.Builder isBirthday(boolean flag)
        {
            isBirthday = Boolean.valueOf(flag);
            return this;
        }

        public final Birthday.Builder isContactAvailable(boolean flag)
        {
            isContactAvailable = Boolean.valueOf(flag);
            return this;
        }

        public final Birthday.Builder isGPlusUser(boolean flag)
        {
            isGPlusUser = Boolean.valueOf(flag);
            return this;
        }

        public final Birthday.Builder isSelfBirthday(boolean flag)
        {
            isSelfBirthday = Boolean.valueOf(flag);
            return this;
        }

        public final Birthday.Builder originalTitle(String s)
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

        public final Birthday.Builder photoUrl(String s)
        {
            photoUrl = s;
            return this;
        }

        public final Birthday.Builder profileId(String s)
        {
            profileId = s;
            return this;
        }

        public final Birthday.Builder sourceAccount(String s)
        {
            sourceAccount = s;
            return this;
        }

        public Builder()
        {
        }

        Builder(Birthday birthday)
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

}
