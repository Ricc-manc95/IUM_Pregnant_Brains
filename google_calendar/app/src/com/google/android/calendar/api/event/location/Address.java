// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import android.os.Parcel;
import android.os.Parcelable;

public class Address
    implements Parcelable
{
    public static final class Builder
    {

        public String country;
        public String formattedAddress;
        public String locality;
        public String postOfficeBoxNumber;
        public String postalCode;
        public String region;
        public String streetAddress;

        public Builder()
        {
            formattedAddress = "";
            country = "";
            locality = "";
            region = "";
            postOfficeBoxNumber = "";
            postalCode = "";
            streetAddress = "";
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String country;
    public final String formattedAddress;
    public final String locality;
    public final String postOfficeBoxNumber;
    public final String postalCode;
    public final String region;
    public final String streetAddress;

    Address(Parcel parcel)
    {
        Builder builder = new Builder();
        String s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.formattedAddress = (String)s;
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.country = (String)s;
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.locality = (String)s;
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.region = (String)s;
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.postOfficeBoxNumber = (String)s;
        s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        builder.postalCode = (String)s;
        parcel = parcel.readString();
        if (parcel == null)
        {
            throw new NullPointerException();
        } else
        {
            builder.streetAddress = (String)parcel;
            this(builder);
            return;
        }
    }

    public Address(Builder builder)
    {
        formattedAddress = builder.formattedAddress;
        country = builder.country;
        locality = builder.locality;
        region = builder.region;
        postOfficeBoxNumber = builder.postOfficeBoxNumber;
        postalCode = builder.postalCode;
        streetAddress = builder.streetAddress;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof Address))
            {
                return false;
            }
            obj = (Address)obj;
            if (!formattedAddress.equals(((Address) (obj)).formattedAddress) || !country.equals(((Address) (obj)).country) || !locality.equals(((Address) (obj)).locality) || !region.equals(((Address) (obj)).region) || !postOfficeBoxNumber.equals(((Address) (obj)).postOfficeBoxNumber) || !postalCode.equals(((Address) (obj)).postalCode) || !streetAddress.equals(((Address) (obj)).streetAddress))
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        return ((((((formattedAddress.hashCode() + 527) * 31 + country.hashCode()) * 31 + locality.hashCode()) * 31 + region.hashCode()) * 31 + postOfficeBoxNumber.hashCode()) * 31 + postalCode.hashCode()) * 31 + streetAddress.hashCode();
    }

    public String toString()
    {
        return String.format("Address{formatted_address=%s, country=%s, locality=%s, region=%s, post_office_box_number=%s, postal_code=%s, street_address=%s}", new Object[] {
            formattedAddress, country, locality, region, postOfficeBoxNumber, postalCode, streetAddress
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(formattedAddress);
        parcel.writeString(country);
        parcel.writeString(locality);
        parcel.writeString(region);
        parcel.writeString(postOfficeBoxNumber);
        parcel.writeString(postalCode);
        parcel.writeString(streetAddress);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Address(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Address[i];
        }

        _cls1()
        {
        }
    }

}
