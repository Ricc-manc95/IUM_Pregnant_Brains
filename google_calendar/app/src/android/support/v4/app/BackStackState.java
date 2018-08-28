// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            BackStackRecord, Fragment

final class BackStackState
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final int mBreadCrumbShortTitleRes;
    public final CharSequence mBreadCrumbShortTitleText;
    public final int mBreadCrumbTitleRes;
    public final CharSequence mBreadCrumbTitleText;
    public final int mIndex;
    public final String mName;
    public final int mOps[];
    public final boolean mReorderingAllowed;
    public final ArrayList mSharedElementSourceNames;
    public final ArrayList mSharedElementTargetNames;
    public final int mTransition;
    public final int mTransitionStyle;

    public BackStackState(Parcel parcel)
    {
        mOps = parcel.createIntArray();
        mTransition = parcel.readInt();
        mTransitionStyle = parcel.readInt();
        mName = parcel.readString();
        mIndex = parcel.readInt();
        mBreadCrumbTitleRes = parcel.readInt();
        mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mBreadCrumbShortTitleRes = parcel.readInt();
        mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mSharedElementSourceNames = parcel.createStringArrayList();
        mSharedElementTargetNames = parcel.createStringArrayList();
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mReorderingAllowed = flag;
    }

    public BackStackState(BackStackRecord backstackrecord)
    {
        int k = backstackrecord.mOps.size();
        mOps = new int[k * 6];
        if (!backstackrecord.mAddToBackStack)
        {
            throw new IllegalStateException("Not on back stack");
        }
        int i = 0;
        int j = 0;
        while (i < k) 
        {
            BackStackRecord.Op op = (BackStackRecord.Op)backstackrecord.mOps.get(i);
            int ai[] = mOps;
            int l = j + 1;
            ai[j] = op.cmd;
            ai = mOps;
            int i1 = l + 1;
            if (op.fragment != null)
            {
                j = op.fragment.mIndex;
            } else
            {
                j = -1;
            }
            ai[l] = j;
            ai = mOps;
            j = i1 + 1;
            ai[i1] = op.enterAnim;
            ai = mOps;
            l = j + 1;
            ai[j] = op.exitAnim;
            ai = mOps;
            i1 = l + 1;
            ai[l] = op.popEnterAnim;
            ai = mOps;
            j = i1 + 1;
            ai[i1] = op.popExitAnim;
            i++;
        }
        mTransition = backstackrecord.mTransition;
        mTransitionStyle = backstackrecord.mTransitionStyle;
        mName = backstackrecord.mName;
        mIndex = backstackrecord.mIndex;
        mBreadCrumbTitleRes = backstackrecord.mBreadCrumbTitleRes;
        mBreadCrumbTitleText = backstackrecord.mBreadCrumbTitleText;
        mBreadCrumbShortTitleRes = backstackrecord.mBreadCrumbShortTitleRes;
        mBreadCrumbShortTitleText = backstackrecord.mBreadCrumbShortTitleText;
        mSharedElementSourceNames = backstackrecord.mSharedElementSourceNames;
        mSharedElementTargetNames = backstackrecord.mSharedElementTargetNames;
        mReorderingAllowed = backstackrecord.mReorderingAllowed;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        i = 0;
        parcel.writeIntArray(mOps);
        parcel.writeInt(mTransition);
        parcel.writeInt(mTransitionStyle);
        parcel.writeString(mName);
        parcel.writeInt(mIndex);
        parcel.writeInt(mBreadCrumbTitleRes);
        TextUtils.writeToParcel(mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(mSharedElementSourceNames);
        parcel.writeStringList(mSharedElementTargetNames);
        if (mReorderingAllowed)
        {
            i = 1;
        }
        parcel.writeInt(i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new BackStackState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new BackStackState[i];
        }

        _cls1()
        {
        }
    }

}
