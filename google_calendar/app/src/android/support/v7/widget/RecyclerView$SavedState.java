// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public static final class mLayoutState extends AbsSavedState
{

    public static final android.os.iew.SavedState._cls1 CREATOR = new _cls1();
    public Parcelable mLayoutState;

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(mLayoutState, 0);
    }


    er(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        if (classloader == null)
        {
            classloader = android/support/v7/widget/RecyclerView$LayoutManager.getClassLoader();
        }
        mLayoutState = parcel.readParcelable(classloader);
    }

    mLayoutState(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new RecyclerView.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new RecyclerView.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new RecyclerView.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
