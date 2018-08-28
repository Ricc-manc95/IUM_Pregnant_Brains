// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.ImmutableListAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_Room, Room

final class AutoValue_Room extends $AutoValue_Room
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final ImmutableListAdapter IMMUTABLE_LIST_ADAPTER = new ImmutableListAdapter();

    AutoValue_Room(String s, String s1, String s2, String s3, int i, Integer integer, String s4, 
            String s5, String s6, String s7, ImmutableList immutablelist, int j)
    {
        super(s, s1, s2, s3, i, integer, s4, s5, s6, s7, immutablelist, j);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getEmail());
        parcel.writeString(getName());
        if (getShortName() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getShortName());
        }
        if (getDescription() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getDescription());
        }
        parcel.writeInt(getAvailability());
        if (getCapacity() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeInt(getCapacity().intValue());
        }
        if (getBuildingName() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getBuildingName());
        }
        if (getHierarchyNodeId() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getHierarchyNodeId());
        }
        if (getFloorName() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getFloorName());
        }
        if (getFloorSectionName() == null)
        {
            parcel.writeInt(1);
        } else
        {
            parcel.writeInt(0);
            parcel.writeString(getFloorSectionName());
        }
        parcel.writeList(getFeatures());
        parcel.writeInt(getCategory());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s5 = null;
            String s6 = parcel.readString();
            String s7 = parcel.readString();
            String s;
            String s1;
            Integer integer;
            String s2;
            String s3;
            String s4;
            int i;
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
            } else
            {
                s1 = null;
            }
            i = parcel.readInt();
            if (parcel.readInt() == 0)
            {
                integer = Integer.valueOf(parcel.readInt());
            } else
            {
                integer = null;
            }
            if (parcel.readInt() == 0)
            {
                s2 = parcel.readString();
            } else
            {
                s2 = null;
            }
            if (parcel.readInt() == 0)
            {
                s3 = parcel.readString();
            } else
            {
                s3 = null;
            }
            if (parcel.readInt() == 0)
            {
                s4 = parcel.readString();
            } else
            {
                s4 = null;
            }
            if (parcel.readInt() == 0)
            {
                s5 = parcel.readString();
            }
            return new AutoValue_Room(s6, s7, s, s1, i, integer, s2, s3, s4, s5, ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader())), parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_Room[i];
        }

        _cls1()
        {
        }
    }

}
