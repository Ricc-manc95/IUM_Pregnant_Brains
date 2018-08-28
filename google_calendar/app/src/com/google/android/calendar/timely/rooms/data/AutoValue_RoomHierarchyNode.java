// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_RoomHierarchyNode, RoomHierarchyNode

public final class AutoValue_RoomHierarchyNode extends $AutoValue_RoomHierarchyNode
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_RoomHierarchyNode(String s, int i, String s1)
    {
        super(s, i, s1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getId());
        parcel.writeInt(getType());
        if (getName() == null)
        {
            parcel.writeInt(1);
            return;
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getName());
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s = parcel.readString();
            int i = parcel.readInt();
            if (parcel.readInt() == 0)
            {
                parcel = parcel.readString();
            } else
            {
                parcel = null;
            }
            return new AutoValue_RoomHierarchyNode(s, i, parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RoomHierarchyNode[i];
        }

        _cls1()
        {
        }
    }

}
