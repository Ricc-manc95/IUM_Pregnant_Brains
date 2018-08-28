// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v7.preference:
//            SeekBarPreference

static class it> extends it>
{

    public static final android.os.ference.SavedState._cls1 CREATOR = new _cls1();
    public int max;
    public int min;
    public int seekBarValue;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.teToParcel(parcel, i);
        parcel.writeInt(seekBarValue);
        parcel.writeInt(min);
        parcel.writeInt(max);
    }


    public _cls1(Parcel parcel)
    {
        super(parcel);
        seekBarValue = parcel.readInt();
        min = parcel.readInt();
        max = parcel.readInt();
    }

    public max(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SeekBarPreference.SavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SeekBarPreference.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
