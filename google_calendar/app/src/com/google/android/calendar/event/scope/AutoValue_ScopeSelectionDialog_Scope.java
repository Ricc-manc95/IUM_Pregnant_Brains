// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.event.scope:
//            $AutoValue_ScopeSelectionDialog_Scope

public final class AutoValue_ScopeSelectionDialog_Scope extends $AutoValue_ScopeSelectionDialog_Scope
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_ScopeSelectionDialog_Scope(int i, int j)
    {
        super(i, j);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(description());
        parcel.writeInt(value());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_ScopeSelectionDialog_Scope(parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ScopeSelectionDialog_Scope[i];
        }

        _cls1()
        {
        }
    }

}
