// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v4.app:
//            FragmentState, BackStackState

final class FragmentManagerState
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public FragmentState mActive[];
    public int mAdded[];
    public BackStackState mBackStack[];
    public int mNextFragmentIndex;
    public int mPrimaryNavActiveIndex;

    public FragmentManagerState()
    {
        mPrimaryNavActiveIndex = -1;
    }

    public FragmentManagerState(Parcel parcel)
    {
        mPrimaryNavActiveIndex = -1;
        mActive = (FragmentState[])parcel.createTypedArray(FragmentState.CREATOR);
        mAdded = parcel.createIntArray();
        mBackStack = (BackStackState[])parcel.createTypedArray(BackStackState.CREATOR);
        mPrimaryNavActiveIndex = parcel.readInt();
        mNextFragmentIndex = parcel.readInt();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedArray(mActive, i);
        parcel.writeIntArray(mAdded);
        parcel.writeTypedArray(mBackStack, i);
        parcel.writeInt(mPrimaryNavActiveIndex);
        parcel.writeInt(mNextFragmentIndex);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FragmentManagerState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FragmentManagerState[i];
        }

        _cls1()
        {
        }
    }

}
