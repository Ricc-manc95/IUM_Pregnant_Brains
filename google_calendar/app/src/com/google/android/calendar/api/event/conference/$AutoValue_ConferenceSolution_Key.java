// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


abstract class $AutoValue_ConferenceSolution_Key extends ConferenceSolution.Key
{

    private final ConferenceSolution.Key.AddOnId addOnId;
    private final String type;

    $AutoValue_ConferenceSolution_Key(String s, ConferenceSolution.Key.AddOnId addonid)
    {
        if (s == null)
        {
            throw new NullPointerException("Null type");
        } else
        {
            type = s;
            addOnId = addonid;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof ConferenceSolution.Key))
        {
            break MISSING_BLOCK_LABEL_65;
        }
        obj = (ConferenceSolution.Key)obj;
        if (!type.equals(((ConferenceSolution.Key) (obj)).getType()))
        {
            break; /* Loop/switch isn't completed */
        }
        if (addOnId != null) goto _L4; else goto _L3
_L3:
        if (((ConferenceSolution.Key) (obj)).getAddOnId() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!addOnId.equals(((ConferenceSolution.Key) (obj)).getAddOnId())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final ConferenceSolution.Key.AddOnId getAddOnId()
    {
        return addOnId;
    }

    public final String getType()
    {
        return type;
    }

    public int hashCode()
    {
        int j = type.hashCode();
        int i;
        if (addOnId == null)
        {
            i = 0;
        } else
        {
            i = addOnId.hashCode();
        }
        return i ^ 0xf4243 * (j ^ 0xf4243);
    }

    public String toString()
    {
        String s = type;
        String s1 = String.valueOf(addOnId);
        return (new StringBuilder(String.valueOf(s).length() + 20 + String.valueOf(s1).length())).append("Key{type=").append(s).append(", addOnId=").append(s1).append("}").toString();
    }
}
