// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;


// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            Attendee, TimeProposal

abstract class $AutoValue_Attendee extends Attendee
{

    private final boolean disabled;
    private final String displayName;
    private final String email;
    private final TimeProposal proposal;
    private final String sourceAccount;

    $AutoValue_Attendee(String s, String s1, String s2, boolean flag, TimeProposal timeproposal)
    {
        if (s == null)
        {
            throw new NullPointerException("Null sourceAccount");
        }
        sourceAccount = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null email");
        }
        email = s1;
        if (s2 == null)
        {
            throw new NullPointerException("Null displayName");
        } else
        {
            displayName = s2;
            disabled = flag;
            proposal = timeproposal;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof Attendee))
        {
            break MISSING_BLOCK_LABEL_104;
        }
        obj = (Attendee)obj;
        if (!sourceAccount.equals(((Attendee) (obj)).getSourceAccount()) || !email.equals(((Attendee) (obj)).getEmail()) || !displayName.equals(((Attendee) (obj)).getDisplayName()) || disabled != ((Attendee) (obj)).isDisabled())
        {
            break; /* Loop/switch isn't completed */
        }
        if (proposal != null) goto _L4; else goto _L3
_L3:
        if (((Attendee) (obj)).getProposal() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!proposal.equals(((Attendee) (obj)).getProposal())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final String getDisplayName()
    {
        return displayName;
    }

    public final String getEmail()
    {
        return email;
    }

    public final TimeProposal getProposal()
    {
        return proposal;
    }

    public final String getSourceAccount()
    {
        return sourceAccount;
    }

    public int hashCode()
    {
        int j = sourceAccount.hashCode();
        int k = email.hashCode();
        int l = displayName.hashCode();
        char c;
        int i;
        if (disabled)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (proposal == null)
        {
            i = 0;
        } else
        {
            i = proposal.hashCode();
        }
        return i ^ (c ^ (((j ^ 0xf4243) * 0xf4243 ^ k) * 0xf4243 ^ l) * 0xf4243) * 0xf4243;
    }

    public final boolean isDisabled()
    {
        return disabled;
    }

    public final Attendee.Builder toBuilder()
    {
        class Builder extends Attendee.Builder
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

            public final Attendee.Builder setDisabled(boolean flag)
            {
                disabled = Boolean.valueOf(flag);
                return this;
            }

            public final Attendee.Builder setDisplayName(String s)
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

            public final Attendee.Builder setEmail(String s)
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

            public final Attendee.Builder setProposal(TimeProposal timeproposal)
            {
                proposal = timeproposal;
                return this;
            }

            public final Attendee.Builder setSourceAccount(String s)
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

            public Builder()
            {
            }

            Builder(Attendee attendee)
            {
                sourceAccount = attendee.getSourceAccount();
                email = attendee.getEmail();
                displayName = attendee.getDisplayName();
                disabled = Boolean.valueOf(attendee.isDisabled());
                proposal = attendee.getProposal();
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        String s = sourceAccount;
        String s1 = email;
        String s2 = displayName;
        boolean flag = disabled;
        String s3 = String.valueOf(proposal);
        return (new StringBuilder(String.valueOf(s).length() + 73 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("Attendee{sourceAccount=").append(s).append(", email=").append(s1).append(", displayName=").append(s2).append(", disabled=").append(flag).append(", proposal=").append(s3).append("}").toString();
    }
}
