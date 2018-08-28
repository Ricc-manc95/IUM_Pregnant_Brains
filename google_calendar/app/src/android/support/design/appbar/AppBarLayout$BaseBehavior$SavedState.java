// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.design.appbar:
//            AppBarLayout

public static final class firstVisibleChildAtMinimumHeight extends AbsSavedState
{

    public static final android.os.ehavior.SavedState._cls1 CREATOR = new _cls1();
    public boolean firstVisibleChildAtMinimumHeight;
    public int firstVisibleChildIndex;
    public float firstVisibleChildPercentageShown;

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(firstVisibleChildIndex);
        parcel.writeFloat(firstVisibleChildPercentageShown);
        if (firstVisibleChildAtMinimumHeight)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        firstVisibleChildIndex = parcel.readInt();
        firstVisibleChildPercentageShown = parcel.readFloat();
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        firstVisibleChildAtMinimumHeight = flag;
    }

    public firstVisibleChildAtMinimumHeight(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AppBarLayout.BaseBehavior.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new AppBarLayout.BaseBehavior.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new AppBarLayout.BaseBehavior.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
