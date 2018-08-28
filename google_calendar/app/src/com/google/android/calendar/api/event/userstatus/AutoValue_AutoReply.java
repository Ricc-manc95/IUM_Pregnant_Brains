// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.userstatus;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.userstatus:
//            $AutoValue_AutoReply, AutoReply

final class AutoValue_AutoReply extends $AutoValue_AutoReply
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_AutoReply(boolean flag, String s, String s1, boolean flag1, boolean flag2)
    {
        super(flag, s, s1, flag1, flag2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if (isEnabled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (getSubject() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getSubject());
        }
        if (getBody() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getBody());
        }
        if (isRestrictToContacts())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (isRestrictToDomain())
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s1 = null;
            boolean flag2 = true;
            String s;
            boolean flag;
            boolean flag1;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() == 0)
            {
                s = parcel.readString();
            } else
            {
                s = null;
            }
            if (parcel.readInt() == 0)
            {
                s1 = parcel.readString();
            }
            if (parcel.readInt() == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (parcel.readInt() != 1)
            {
                flag2 = false;
            }
            return new AutoValue_AutoReply(flag, s, s1, flag1, flag2);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_AutoReply[i];
        }

        _cls1()
        {
        }
    }

}
