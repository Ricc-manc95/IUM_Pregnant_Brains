// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v7.preference:
//            ListPreference

static class <init> extends <init>
{

    public static final android.os.ference.SavedState._cls1 CREATOR = new _cls1();
    public String value;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeString(value);
    }


    public _cls1(Parcel parcel)
    {
        super(parcel);
        value = parcel.readString();
    }

    public value(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ListPreference.SavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ListPreference.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
