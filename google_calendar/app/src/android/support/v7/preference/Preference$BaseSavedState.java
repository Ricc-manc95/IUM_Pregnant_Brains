// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;

// Referenced classes of package android.support.v7.preference:
//            Preference

public static class _cls1 extends AbsSavedState
{

    public static final android.os.nce.BaseSavedState._cls1 CREATOR = new _cls1();


    public _cls1(Parcel parcel)
    {
        super(parcel);
    }

    public _cls1(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Preference.BaseSavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Preference.BaseSavedState[i];
        }

            _cls1()
            {
            }
    }

}
