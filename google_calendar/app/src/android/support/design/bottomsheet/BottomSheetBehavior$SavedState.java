// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetBehavior

public static final class state extends AbsSavedState
{

    public static final android.os.heetBehavior.SavedState._cls1 CREATOR = new _cls1();
    public final int state;

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(state);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        state = parcel.readInt();
    }

    public state(Parcelable parcelable, int i)
    {
        super(parcelable);
        state = i;
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new BottomSheetBehavior.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new BottomSheetBehavior.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new BottomSheetBehavior.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
