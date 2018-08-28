// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v7.preference:
//            EditTextPreference

static class t> extends t>
{

    public static final android.os.ference.SavedState._cls1 CREATOR = new _cls1();
    public String text;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.eToParcel(parcel, i);
        parcel.writeString(text);
    }


    public _cls1(Parcel parcel)
    {
        super(parcel);
        text = parcel.readString();
    }

    public text(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EditTextPreference.SavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EditTextPreference.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
