// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.os.Parcel;
import android.os.Parcelable;

public final class FixPermissionDialogState
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final int checkedRadioButtonId;
    public final int firstSpinnerPosition;
    public final int secondSpinnerPosition;

    public FixPermissionDialogState(int i, int j, int k)
    {
        checkedRadioButtonId = i;
        firstSpinnerPosition = j;
        secondSpinnerPosition = k;
    }

    public FixPermissionDialogState(Parcel parcel)
    {
        checkedRadioButtonId = parcel.readInt();
        firstSpinnerPosition = parcel.readInt();
        secondSpinnerPosition = parcel.readInt();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(checkedRadioButtonId);
        parcel.writeInt(firstSpinnerPosition);
        parcel.writeInt(secondSpinnerPosition);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FixPermissionDialogState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FixPermissionDialogState[i];
        }

        _cls1()
        {
        }
    }

}
