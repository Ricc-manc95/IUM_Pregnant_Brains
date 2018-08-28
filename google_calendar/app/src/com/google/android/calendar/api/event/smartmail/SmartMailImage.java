// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

public class SmartMailImage
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String imageMetadataUrl;
    private final String imageUrl;

    SmartMailImage(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString());
    }

    public SmartMailImage(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        imageUrl = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        } else
        {
            imageMetadataUrl = (String)s1;
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
                    obj = (SmartMailImage)obj;
                    flag = flag1;
                    if (imageUrl.equals(((SmartMailImage) (obj)).imageUrl))
                    {
                        return imageMetadataUrl.equals(((SmartMailImage) (obj)).imageMetadataUrl);
                    }
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return imageUrl.hashCode() * 31 + imageMetadataUrl.hashCode();
    }

    public String toString()
    {
        return String.format("SmartMailImage{imageUrl='%s', imageMetadataUrl='%s'}", new Object[] {
            imageUrl, imageMetadataUrl
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(imageUrl);
        parcel.writeString(imageMetadataUrl);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailImage(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailImage[i];
        }

        _cls1()
        {
        }
    }

}
