// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.api.event.location:
//            StructuredLocationModifications, StructuredLocation, EventLocation

public final class StructuredLocationModificationsImpl
    implements StructuredLocationModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final ArrayList locations;
    private final StructuredLocation original;

    StructuredLocationModificationsImpl(Parcel parcel)
    {
        original = (StructuredLocation)parcel.readParcelable(com/google/android/calendar/api/event/location/StructuredLocation.getClassLoader());
        locations = parcel.createTypedArrayList(EventLocation.CREATOR);
    }

    public StructuredLocationModificationsImpl(StructuredLocation structuredlocation)
    {
        if (structuredlocation == null)
        {
            throw new NullPointerException();
        } else
        {
            original = structuredlocation;
            locations = new ArrayList(structuredlocation.getEventLocations());
            return;
        }
    }

    public final void addEventLocation(EventLocation eventlocation)
    {
        if (eventlocation == null)
        {
            throw new NullPointerException();
        }
        if (!locations.contains(eventlocation))
        {
            locations.add(eventlocation);
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof StructuredLocationModificationsImpl)
        {
            obj = (StructuredLocationModificationsImpl)obj;
            StructuredLocation structuredlocation = original;
            StructuredLocation structuredlocation1 = ((StructuredLocationModificationsImpl) (obj)).original;
            boolean flag;
            if (structuredlocation == structuredlocation1 || structuredlocation != null && structuredlocation.equals(structuredlocation1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ArrayList arraylist = locations;
                obj = ((StructuredLocationModificationsImpl) (obj)).locations;
                if (arraylist == obj || arraylist != null && arraylist.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final Collection getEventLocations()
    {
        if (isModified())
        {
            return Collections.unmodifiableList(locations);
        } else
        {
            return original.getEventLocations();
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, locations
        });
    }

    public final boolean isModified()
    {
        return locations.size() != original.getEventLocations().size() || !locations.containsAll(original.getEventLocations());
    }

    public final void removeEventLocation(EventLocation eventlocation)
    {
        if (eventlocation == null)
        {
            throw new NullPointerException();
        } else
        {
            locations.remove(eventlocation);
            return;
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("StructuredLocationModificationsImpl{");
        String s = String.valueOf(original);
        stringbuilder = stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 9)).append("original=").append(s).toString());
        s = String.valueOf(locations);
        return stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 12)).append(", locations=").append(s).toString()).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(original, i);
        parcel.writeTypedList(locations);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new StructuredLocationModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new StructuredLocationModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
