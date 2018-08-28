// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.util.SparseArray;

// Referenced classes of package android.support.design.widget:
//            CoordinatorLayout

public static final class behaviorStates extends AbsSavedState
{

    public static final android.os.rLayout.SavedState._cls1 CREATOR = new _cls1();
    public SparseArray behaviorStates;

    public final void writeToParcel(Parcel parcel, int i)
    {
        int k = 0;
        super.writeToParcel(parcel, i);
        int ai[];
        Parcelable aparcelable[];
        int j;
        if (behaviorStates != null)
        {
            j = behaviorStates.size();
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        ai = new int[j];
        aparcelable = new Parcelable[j];
        for (; k < j; k++)
        {
            ai[k] = behaviorStates.keyAt(k);
            aparcelable[k] = (Parcelable)behaviorStates.valueAt(k);
        }

        parcel.writeIntArray(ai);
        parcel.writeParcelableArray(aparcelable, i);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        int j = parcel.readInt();
        int ai[] = new int[j];
        parcel.readIntArray(ai);
        parcel = parcel.readParcelableArray(classloader);
        behaviorStates = new SparseArray(j);
        for (int i = 0; i < j; i++)
        {
            behaviorStates.append(ai[i], parcel[i]);
        }

    }

    public behaviorStates(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new CoordinatorLayout.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new CoordinatorLayout.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new CoordinatorLayout.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
