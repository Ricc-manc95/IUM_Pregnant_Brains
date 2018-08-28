// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.v7.widget:
//            Toolbar

public static final class isOverflowOpen extends AbsSavedState
{

    public static final android.os.bar.SavedState._cls1 CREATOR = new _cls1();
    public int expandedMenuItemId;
    public boolean isOverflowOpen;

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(expandedMenuItemId);
        if (isOverflowOpen)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        expandedMenuItemId = parcel.readInt();
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isOverflowOpen = flag;
    }

    public isOverflowOpen(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Toolbar.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new Toolbar.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new Toolbar.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
