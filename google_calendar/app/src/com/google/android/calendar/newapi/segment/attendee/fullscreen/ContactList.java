// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.newapi.segment.attendee.AttendeesUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AutoValue_AttendeeContact, AttendeeContact

final class ContactList
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final Map contacts;
    private final Account source;

    ContactList(Account account)
    {
        contacts = new LinkedHashMap();
        source = account;
    }

    ContactList(Parcel parcel)
    {
        contacts = new LinkedHashMap();
        source = (Account)parcel.readParcelable(android/accounts/Account.getClassLoader());
        parcel = (ArrayList)parcel.createTypedArrayList(AutoValue_AttendeeContact.CREATOR);
        int j = parcel.size();
        for (int i = 0; i < j;)
        {
            Object obj = parcel.get(i);
            i++;
            obj = (AttendeeContact)obj;
            contacts.put(((AttendeeContact) (obj)).getContact().primaryEmail, obj);
        }

    }

    static ContactInfo attendeeToContact(Attendee attendee, Account account)
    {
        com.google.android.calendar.avatar.ContactInfo.Builder builder = ContactInfo.newBuilder();
        builder.name = attendee.displayName;
        builder.primaryEmail = attendee.attendeeDescriptor.email;
        builder.sourceAccountName = account.name;
        return new ContactInfo(builder);
    }

    static final Attendee lambda$getAttendees$0$ContactList(AttendeeContact attendeecontact)
    {
        return AttendeesUtils.createPerson(attendeecontact.getContact().name, attendeecontact.getContact().primaryEmail);
    }

    final void addEmail(CharSequence charsequence)
    {
        charsequence = charsequence.toString().toLowerCase();
        if (!contacts.containsKey(charsequence))
        {
            Map map = contacts;
            Account account = source;
            AttendeeContact.Type type = AttendeeContact.Type.ADDED_REMOVABLE;
            com.google.android.calendar.avatar.ContactInfo.Builder builder = ContactInfo.newBuilder();
            builder.sourceAccountName = account.name;
            builder.primaryEmail = charsequence;
            map.put(charsequence, new AutoValue_AttendeeContact(new ContactInfo(builder), type));
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(source, i);
        parcel.writeTypedList(new ArrayList(contacts.values()));
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ContactList(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ContactList[i];
        }

        _cls1()
        {
        }
    }

}
