// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

public class Person
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final String firstName;
    private final String lastName;
    private final String name;

    Person(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString(), parcel.readString());
    }

    public Person(String s, String s1, String s2)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        name = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        firstName = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        } else
        {
            lastName = (String)s2;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj != null)
            {
                flag = flag1;
                if (getClass() == obj.getClass())
                {
                    obj = (Person)obj;
                    flag = flag1;
                    if (name.equals(((Person) (obj)).name))
                    {
                        flag = flag1;
                        if (firstName.equals(((Person) (obj)).firstName))
                        {
                            return lastName.equals(((Person) (obj)).lastName);
                        }
                    }
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return (name.hashCode() * 31 + firstName.hashCode()) * 31 + lastName.hashCode();
    }

    public String toString()
    {
        return String.format("Person{name='%s', firstName='%s', lastName='%s'}", new Object[] {
            name, firstName, lastName
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Person(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Person[i];
        }

        _cls1()
        {
        }
    }

}
