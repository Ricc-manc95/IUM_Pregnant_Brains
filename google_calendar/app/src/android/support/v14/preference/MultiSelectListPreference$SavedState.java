// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v14.preference;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package android.support.v14.preference:
//            MultiSelectListPreference

static class mValues extends android.support.v7.preference.
{

    public static final android.os.eference.SavedState._cls1 CREATOR = new _cls1();
    public Set mValues;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.el(parcel, i);
        parcel.writeInt(mValues.size());
        parcel.writeStringArray((String[])mValues.toArray(new String[mValues.size()]));
    }


    _cls1(Parcel parcel)
    {
        super(parcel);
        int i = parcel.readInt();
        mValues = new HashSet();
        String as[] = new String[i];
        parcel.readStringArray(as);
        Collections.addAll(mValues, as);
    }

    mValues(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new MultiSelectListPreference.SavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new MultiSelectListPreference.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
