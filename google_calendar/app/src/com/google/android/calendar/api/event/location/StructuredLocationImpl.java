// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.api.event.location:
//            StructuredLocation, EventLocation

public final class StructuredLocationImpl
    implements StructuredLocation
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final ArrayList locations;

    StructuredLocationImpl(Parcel parcel)
    {
        locations = parcel.createTypedArrayList(EventLocation.CREATOR);
    }

    public StructuredLocationImpl(Collection collection)
    {
        if (collection == null)
        {
            throw new NullPointerException();
        } else
        {
            locations = new ArrayList(collection);
            return;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (!(obj instanceof StructuredLocationImpl))
        {
            return false;
        } else
        {
            obj = (StructuredLocationImpl)obj;
            return locations.equals(Collections.unmodifiableList(((StructuredLocationImpl) (obj)).locations));
        }
    }

    public final Collection getEventLocations()
    {
        return Collections.unmodifiableList(locations);
    }

    public final int hashCode()
    {
        return locations.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(locations);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new StructuredLocationImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new StructuredLocationImpl[i];
        }

        _cls1()
        {
        }
    }

}
