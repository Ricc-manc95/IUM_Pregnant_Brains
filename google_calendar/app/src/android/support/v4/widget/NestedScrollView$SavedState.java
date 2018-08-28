// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v4.widget:
//            NestedScrollView

static final class scrollPosition extends android.view.w.SavedState
{

    public static final android.os.iew.SavedState._cls1 CREATOR = new _cls1();
    public int scrollPosition;

    public final String toString()
    {
        return (new StringBuilder("HorizontalScrollView.SavedState{")).append(Integer.toHexString(System.identityHashCode(this))).append(" scrollPosition=").append(scrollPosition).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.arcel(parcel, i);
        parcel.writeInt(scrollPosition);
    }


    _cls1(Parcel parcel)
    {
        super(parcel);
        scrollPosition = parcel.readInt();
    }

    scrollPosition(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new NestedScrollView.SavedState(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new NestedScrollView.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
