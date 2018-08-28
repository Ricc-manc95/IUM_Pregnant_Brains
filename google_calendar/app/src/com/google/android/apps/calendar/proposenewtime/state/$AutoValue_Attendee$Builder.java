// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;


// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            Attendee, AutoValue_Attendee, TimeProposal

public final class proposal extends proposal
{

    private Boolean disabled;
    private String displayName;
    private String email;
    private TimeProposal proposal;
    private String sourceAccount;

    public final Attendee build()
    {
        String s1 = "";
        if (sourceAccount == null)
        {
            s1 = String.valueOf("").concat(" sourceAccount");
        }
        String s = s1;
        if (email == null)
        {
            s = String.valueOf(s1).concat(" email");
        }
        s1 = s;
        if (displayName == null)
        {
            s1 = String.valueOf(s).concat(" displayName");
        }
        s = s1;
        if (disabled == null)
        {
            s = String.valueOf(s1).concat(" disabled");
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
            return new AutoValue_Attendee(sourceAccount, email, displayName, disabled.booleanValue(), proposal);
        }
    }

    public final proposal setDisabled(boolean flag)
    {
        disabled = Boolean.valueOf(flag);
        return this;
    }

    public final disabled setDisplayName(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null displayName");
        } else
        {
            displayName = s;
            return this;
        }
    }

    public final displayName setEmail(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null email");
        } else
        {
            email = s;
            return this;
        }
    }

    public final email setProposal(TimeProposal timeproposal)
    {
        proposal = timeproposal;
        return this;
    }

    public final proposal setSourceAccount(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null sourceAccount");
        } else
        {
            sourceAccount = s;
            return this;
        }
    }

    public ()
    {
    }

    (Attendee attendee)
    {
        sourceAccount = attendee.getSourceAccount();
        email = attendee.getEmail();
        displayName = attendee.getDisplayName();
        disabled = Boolean.valueOf(attendee.isDisabled());
        proposal = attendee.getProposal();
    }
}
