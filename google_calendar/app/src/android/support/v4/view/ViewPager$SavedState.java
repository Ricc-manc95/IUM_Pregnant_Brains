// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.support.v4.view:
//            AbsSavedState, ViewPager

public static final class loader extends AbsSavedState
{

    public static final android.os.r.SavedState._cls1 CREATOR = new _cls1();
    public Parcelable adapterState;
    public ClassLoader loader;
    public int position;

    public final String toString()
    {
        return (new StringBuilder("FragmentPager.SavedState{")).append(Integer.toHexString(System.identityHashCode(this))).append(" position=").append(position).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(position);
        parcel.writeParcelable(adapterState, i);
    }


    _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        ClassLoader classloader1 = classloader;
        if (classloader == null)
        {
            classloader1 = getClass().getClassLoader();
        }
        position = parcel.readInt();
        adapterState = parcel.readParcelable(classloader1);
        loader = classloader1;
    }

    public loader(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ViewPager.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new ViewPager.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new ViewPager.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
