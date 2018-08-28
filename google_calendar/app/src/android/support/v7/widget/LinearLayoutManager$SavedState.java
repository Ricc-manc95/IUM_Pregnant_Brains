// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;

public final class mAnchorLayoutFromEnd
    implements Parcelable
{

    public static final android.os.ger.SavedState._cls1 CREATOR = new _cls1();
    public boolean mAnchorLayoutFromEnd;
    public int mAnchorOffset;
    public int mAnchorPosition;

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mAnchorPosition);
        parcel.writeInt(mAnchorOffset);
        if (mAnchorLayoutFromEnd)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    public _cls1()
    {
    }

    _cls1(Parcel parcel)
    {
        boolean flag = true;
        super();
        mAnchorPosition = parcel.readInt();
        mAnchorOffset = parcel.readInt();
        if (parcel.readInt() != 1)
        {
            flag = false;
        }
        mAnchorLayoutFromEnd = flag;
    }

    public mAnchorLayoutFromEnd(mAnchorLayoutFromEnd manchorlayoutfromend)
    {
        mAnchorPosition = manchorlayoutfromend.mAnchorPosition;
        mAnchorOffset = manchorlayoutfromend.mAnchorOffset;
        mAnchorLayoutFromEnd = manchorlayoutfromend.mAnchorLayoutFromEnd;
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new LinearLayoutManager.SavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new LinearLayoutManager.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
