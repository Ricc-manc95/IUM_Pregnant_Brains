// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;


// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            OutOfOffice, AutoValue_OutOfOffice, AutoReply

public final class autoReply extends autoReply
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

    public final autoReply setAutoDeclineEnabled(boolean flag)
    {
        autoDeclineEnabled = Boolean.valueOf(flag);
        return this;
    }

    public final autoDeclineEnabled setAutoReply(AutoReply autoreply)
    {
        autoReply = autoreply;
        return this;
    }

    public final autoReply setCalendarDeclineMessage(String s)
    {
        calendarDeclineMessage = s;
        return this;
    }

    public ()
    {
    }

    (OutOfOffice outofoffice)
    {
        autoDeclineEnabled = Boolean.valueOf(outofoffice.isAutoDeclineEnabled());
        calendarDeclineMessage = outofoffice.getCalendarDeclineMessage();
        autoReply = outofoffice.getAutoReply();
    }
}
