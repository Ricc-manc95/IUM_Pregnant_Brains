// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

public class AbsSavedState
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls2();
    public static final AbsSavedState EMPTY_STATE = new _cls1();
    public final Parcelable mSuperState;

    private AbsSavedState()
    {
        mSuperState = null;
    }

    AbsSavedState(byte byte0)
    {
        this();
    }

    public AbsSavedState(Parcel parcel, ClassLoader classloader)
    {
        parcel = parcel.readParcelable(classloader);
        if (parcel == null)
        {
            parcel = EMPTY_STATE;
        }
        mSuperState = parcel;
    }

    public AbsSavedState(Parcelable parcelable)
    {
        if (parcelable == null)
        {
            throw new IllegalArgumentException("superState must not be null");
        }
        if (parcelable == EMPTY_STATE)
        {
            parcelable = null;
        }
        mSuperState = parcelable;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mSuperState, i);
    }


    private class _cls1 extends AbsSavedState
    {

        _cls1()
        {
            super((byte)0);
        }
    }


    private class _cls2
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            if (parcel.readParcelable(null) != null)
            {
                throw new IllegalStateException("superState must be null");
            } else
            {
                return AbsSavedState.EMPTY_STATE;
            }
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            if (parcel.readParcelable(classloader) != null)
            {
                throw new IllegalStateException("superState must be null");
            } else
            {
                return AbsSavedState.EMPTY_STATE;
            }
        }

        public final Object[] newArray(int i)
        {
            return new AbsSavedState[i];
        }

        _cls2()
        {
        }
    }

}
