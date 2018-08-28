// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.os.Bundle;
import android.os.Parcel;
import java.util.List;

// Referenced classes of package com.google.android.calendar.event.scope:
//            $AutoValue_ScopeSelectionDialog_Config

final class AutoValue_ScopeSelectionDialog_Config extends $AutoValue_ScopeSelectionDialog_Config
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_ScopeSelectionDialog_Config(int i, int j, Bundle bundle, List list)
    {
        super(i, j, bundle, list);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(title());
        parcel.writeInt(positiveButton());
        parcel.writeBundle(additionalArguments());
        parcel.writeList(scopes());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_ScopeSelectionDialog_Config(parcel.readInt(), parcel.readInt(), parcel.readBundle(android/os/Bundle.getClassLoader()), parcel.readArrayList(com/google/android/calendar/event/scope/ScopeSelectionDialog$Scope.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ScopeSelectionDialog_Config[i];
        }

        _cls1()
        {
        }
    }

}
