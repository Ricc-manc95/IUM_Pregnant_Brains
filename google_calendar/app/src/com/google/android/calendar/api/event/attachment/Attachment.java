// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attachment;

import android.os.Parcel;
import android.os.Parcelable;

public class Attachment
    implements Parcelable
{
    public static final class Builder
    {

        public String fileId;
        public String fileUrl;
        public String iconLink;
        public String mimeType;
        public String title;

        public final Attachment build()
        {
            return new Attachment(fileId, fileUrl, iconLink, mimeType, title);
        }

        public Builder()
        {
            fileId = "";
            fileUrl = "";
            iconLink = "";
            mimeType = null;
            title = "";
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String fileId;
    public final String fileUrl;
    public final String iconLink;
    public final String mimeType;
    public final String title;

    Attachment(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
    }

    public Attachment(String s, String s1, String s2, String s3, String s4)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        fileId = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        fileUrl = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        }
        iconLink = (String)s2;
        mimeType = s3;
        if (s4 == null)
        {
            throw new NullPointerException();
        } else
        {
            title = (String)s4;
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
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (obj == null) goto _L4; else goto _L3
_L3:
        flag = flag1;
        if (getClass() != obj.getClass()) goto _L4; else goto _L5
_L5:
        obj = (Attachment)obj;
        flag = flag1;
        if (!fileId.equals(((Attachment) (obj)).fileId)) goto _L4; else goto _L6
_L6:
        flag = flag1;
        if (!fileUrl.equals(((Attachment) (obj)).fileUrl)) goto _L4; else goto _L7
_L7:
        flag = flag1;
        if (!iconLink.equals(((Attachment) (obj)).iconLink)) goto _L4; else goto _L8
_L8:
        if (mimeType == null) goto _L10; else goto _L9
_L9:
        flag = flag1;
        if (!mimeType.equals(((Attachment) (obj)).mimeType)) goto _L4; else goto _L11
_L11:
        return title.equals(((Attachment) (obj)).title);
_L10:
        if (((Attachment) (obj)).mimeType != null)
        {
            return false;
        }
        if (true) goto _L11; else goto _L12
_L12:
    }

    public int hashCode()
    {
        int j = fileId.hashCode();
        int k = fileUrl.hashCode();
        int l = iconLink.hashCode();
        int i;
        if (mimeType != null)
        {
            i = mimeType.hashCode();
        } else
        {
            i = 0;
        }
        return (i + ((j * 31 + k) * 31 + l) * 31) * 31 + title.hashCode();
    }

    public String toString()
    {
        String s = fileId;
        String s1 = fileUrl;
        String s2 = iconLink;
        String s3 = mimeType;
        String s4 = title;
        return (new StringBuilder(String.valueOf(s).length() + 69 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("Attachment{fileId='").append(s).append('\'').append(", fileUrl='").append(s1).append('\'').append(", iconLink='").append(s2).append('\'').append(", mimeType='").append(s3).append('\'').append(", title='").append(s4).append('\'').append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(fileId);
        parcel.writeString(fileUrl);
        parcel.writeString(iconLink);
        parcel.writeString(mimeType);
        parcel.writeString(title);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Attachment(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Attachment[i];
        }

        _cls1()
        {
        }
    }

}
