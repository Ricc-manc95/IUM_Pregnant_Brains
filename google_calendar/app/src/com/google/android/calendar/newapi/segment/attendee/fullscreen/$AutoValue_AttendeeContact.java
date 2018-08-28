// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import com.google.android.calendar.avatar.ContactInfo;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AttendeeContact

abstract class $AutoValue_AttendeeContact extends AttendeeContact
{

    private final ContactInfo contact;
    private final AttendeeContact.Type type;

    $AutoValue_AttendeeContact(ContactInfo contactinfo, AttendeeContact.Type type1)
    {
        if (contactinfo == null)
        {
            throw new NullPointerException("Null contact");
        }
        contact = contactinfo;
        if (type1 == null)
        {
            throw new NullPointerException("Null type");
        } else
        {
            type = type1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AttendeeContact)
            {
                if (!contact.equals(((AttendeeContact) (obj = (AttendeeContact)obj)).getContact()) || !type.equals(((AttendeeContact) (obj)).getType()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final ContactInfo getContact()
    {
        return contact;
    }

    public final AttendeeContact.Type getType()
    {
        return type;
    }

    public int hashCode()
    {
        return (contact.hashCode() ^ 0xf4243) * 0xf4243 ^ type.hashCode();
    }

    public String toString()
    {
        String s = String.valueOf(contact);
        String s1 = String.valueOf(type);
        return (new StringBuilder(String.valueOf(s).length() + 32 + String.valueOf(s1).length())).append("AttendeeContact{contact=").append(s).append(", type=").append(s1).append("}").toString();
    }
}
