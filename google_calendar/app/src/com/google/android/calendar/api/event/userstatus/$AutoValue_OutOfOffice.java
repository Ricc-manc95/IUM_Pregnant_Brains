// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;


// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            OutOfOffice, AutoReply

abstract class $AutoValue_OutOfOffice extends OutOfOffice
{

    private final boolean autoDeclineEnabled;
    private final AutoReply autoReply;
    private final String calendarDeclineMessage;

    $AutoValue_OutOfOffice(boolean flag, String s, AutoReply autoreply)
    {
        autoDeclineEnabled = flag;
        calendarDeclineMessage = s;
        autoReply = autoreply;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof OutOfOffice))
            {
                break MISSING_BLOCK_LABEL_93;
            }
            obj = (OutOfOffice)obj;
            if (autoDeclineEnabled == ((OutOfOffice) (obj)).isAutoDeclineEnabled() && (calendarDeclineMessage != null ? calendarDeclineMessage.equals(((OutOfOffice) (obj)).getCalendarDeclineMessage()) : ((OutOfOffice) (obj)).getCalendarDeclineMessage() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (autoReply != null) goto _L4; else goto _L3
_L3:
        if (((OutOfOffice) (obj)).getAutoReply() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!autoReply.equals(((OutOfOffice) (obj)).getAutoReply())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final AutoReply getAutoReply()
    {
        return autoReply;
    }

    public final String getCalendarDeclineMessage()
    {
        return calendarDeclineMessage;
    }

    public int hashCode()
    {
        int j = 0;
        char c;
        int i;
        if (autoDeclineEnabled)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (calendarDeclineMessage == null)
        {
            i = 0;
        } else
        {
            i = calendarDeclineMessage.hashCode();
        }
        if (autoReply != null)
        {
            j = autoReply.hashCode();
        }
        return (i ^ (c ^ 0xf4243) * 0xf4243) * 0xf4243 ^ j;
    }

    public final boolean isAutoDeclineEnabled()
    {
        return autoDeclineEnabled;
    }

    public final OutOfOffice.Builder toBuilder()
    {
        class Builder extends OutOfOffice.Builder
        {

            private Boolean autoDeclineEnabled;
            private AutoReply autoReply;
            private String calendarDeclineMessage;

            public final OutOfOffice build()
            {
                String s = "";
                if (autoDeclineEnabled == null)
                {
                    s = String.valueOf("").concat(" autoDeclineEnabled");
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
                    return new AutoValue_OutOfOffice(autoDeclineEnabled.booleanValue(), calendarDeclineMessage, autoReply);
                }
            }

            public final OutOfOffice.Builder setAutoDeclineEnabled(boolean flag)
            {
                autoDeclineEnabled = Boolean.valueOf(flag);
                return this;
            }

            public final OutOfOffice.Builder setAutoReply(AutoReply autoreply)
            {
                autoReply = autoreply;
                return this;
            }

            public final OutOfOffice.Builder setCalendarDeclineMessage(String s)
            {
                calendarDeclineMessage = s;
                return this;
            }

            public Builder()
            {
            }

            Builder(OutOfOffice outofoffice)
            {
                autoDeclineEnabled = Boolean.valueOf(outofoffice.isAutoDeclineEnabled());
                calendarDeclineMessage = outofoffice.getCalendarDeclineMessage();
                autoReply = outofoffice.getAutoReply();
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        boolean flag = autoDeclineEnabled;
        String s = calendarDeclineMessage;
        String s1 = String.valueOf(autoReply);
        return (new StringBuilder(String.valueOf(s).length() + 74 + String.valueOf(s1).length())).append("OutOfOffice{autoDeclineEnabled=").append(flag).append(", calendarDeclineMessage=").append(s).append(", autoReply=").append(s1).append("}").toString();
    }
}
