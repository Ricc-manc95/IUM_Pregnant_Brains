// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.people.model.AvatarReference;
import java.io.Serializable;
import java.util.Arrays;

public class ContactInfo
    implements Parcelable, Serializable
{
    public static final class Builder
    {

        public byte avatarPhotoBytes[];
        public AvatarReference avatarReference;
        public Long contactId;
        public Boolean hasPhoto;
        public String lookupKey;
        public String name;
        public String primaryEmail;
        public String sourceAccountName;

        Builder()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final byte avatarPhotoBytes[];
    public final AvatarReference avatarReference;
    public final Long contactId;
    private final Boolean hasPhoto;
    public final String lookupKey;
    public final String name;
    public final String primaryEmail;
    public final String sourceAccountName;

    ContactInfo(Parcel parcel)
    {
        Object obj;
        if (parcel.readByte() == 1)
        {
            obj = Long.valueOf(parcel.readLong());
        } else
        {
            obj = null;
        }
        contactId = ((Long) (obj));
        if (parcel.readByte() == 1)
        {
            boolean flag;
            if (parcel.readByte() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj = Boolean.valueOf(flag);
        } else
        {
            obj = null;
        }
        hasPhoto = ((Boolean) (obj));
        if (parcel.readByte() == 1)
        {
            avatarPhotoBytes = new byte[parcel.readInt()];
            parcel.readByteArray(avatarPhotoBytes);
        } else
        {
            avatarPhotoBytes = null;
        }
        name = parcel.readString();
        primaryEmail = parcel.readString();
        sourceAccountName = parcel.readString();
        lookupKey = parcel.readString();
        avatarReference = (AvatarReference)parcel.readParcelable(com/google/android/gms/people/model/AvatarReference.getClassLoader());
    }

    public ContactInfo(Builder builder)
    {
        name = builder.name;
        primaryEmail = builder.primaryEmail;
        contactId = builder.contactId;
        lookupKey = builder.lookupKey;
        hasPhoto = builder.hasPhoto;
        sourceAccountName = builder.sourceAccountName;
        avatarReference = builder.avatarReference;
        avatarPhotoBytes = builder.avatarPhotoBytes;
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }

    public static Builder newBuilder(ContactInfo contactinfo)
    {
        Builder builder = new Builder();
        builder.name = contactinfo.name;
        builder.primaryEmail = contactinfo.primaryEmail;
        builder.contactId = contactinfo.contactId;
        builder.lookupKey = contactinfo.lookupKey;
        builder.hasPhoto = contactinfo.hasPhoto;
        builder.sourceAccountName = contactinfo.sourceAccountName;
        builder.avatarReference = contactinfo.avatarReference;
        return builder;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (ContactInfo)obj;
            if (primaryEmail == null ? ((ContactInfo) (obj)).primaryEmail != null : !primaryEmail.equals(((ContactInfo) (obj)).primaryEmail))
            {
                return false;
            }
            if (sourceAccountName == null ? ((ContactInfo) (obj)).sourceAccountName != null : !sourceAccountName.equals(((ContactInfo) (obj)).sourceAccountName))
            {
                return false;
            }
            if (avatarReference == null ? ((ContactInfo) (obj)).avatarReference != null : !avatarReference.equals(((ContactInfo) (obj)).avatarReference))
            {
                return false;
            }
            if (avatarPhotoBytes == null ? ((ContactInfo) (obj)).avatarPhotoBytes != null : !Arrays.equals(avatarPhotoBytes, ((ContactInfo) (obj)).avatarPhotoBytes))
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int l = 0;
        int i;
        int j;
        int k;
        if (primaryEmail != null)
        {
            i = primaryEmail.hashCode();
        } else
        {
            i = 0;
        }
        if (sourceAccountName != null)
        {
            j = sourceAccountName.hashCode();
        } else
        {
            j = 0;
        }
        if (avatarReference != null)
        {
            k = avatarReference.hashCode();
        } else
        {
            k = 0;
        }
        if (avatarPhotoBytes != null)
        {
            l = Arrays.hashCode(avatarPhotoBytes);
        }
        return (k + (j + i * 31) * 31) * 31 + l;
    }

    public String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName());
        Object obj = name;
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("mName" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"mName";
        obj = primaryEmail;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("mPrimaryEmail" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"mPrimaryEmail";
        obj = sourceAccountName;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("mSourceAccountName" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"mSourceAccountName";
        obj = contactId;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("mContactId" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"mContactId";
        obj = lookupKey;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("mLookupKey" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"mLookupKey";
        obj = hasPhoto;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("mHasPhoto" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"mHasPhoto";
        obj = avatarReference;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("mAvatarReference" == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)"mAvatarReference";
            return tostringhelper.toString();
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if (contactId != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (contactId != null)
        {
            parcel.writeLong(contactId.longValue());
        }
        if (hasPhoto != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (hasPhoto != null)
        {
            if (hasPhoto.booleanValue())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeByte((byte)i);
        }
        if (avatarPhotoBytes != null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (avatarPhotoBytes != null)
        {
            parcel.writeInt(avatarPhotoBytes.length);
            parcel.writeByteArray(avatarPhotoBytes);
        }
        parcel.writeString(name);
        parcel.writeString(primaryEmail);
        parcel.writeString(sourceAccountName);
        parcel.writeString(lookupKey);
        parcel.writeParcelable(avatarReference, 0);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ContactInfo(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ContactInfo[i];
        }

        _cls1()
        {
        }
    }

}
