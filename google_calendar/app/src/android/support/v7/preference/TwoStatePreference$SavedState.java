// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v7.preference:
//            TwoStatePreference

static class t> extends t>
{

    public static final android.os.ference.SavedState._cls1 CREATOR = new _cls1();
    public boolean checked;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.eToParcel(parcel, i);
        if (checked)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    public _cls1(Parcel parcel)
    {
        boolean flag = true;
        super(parcel);
        if (parcel.readInt() != 1)
        {
            flag = false;
        }
        checked = flag;
    }

    public checked(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TwoStatePreference.SavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TwoStatePreference.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
