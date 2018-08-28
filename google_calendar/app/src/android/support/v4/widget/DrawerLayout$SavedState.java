// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayout

public static final class openDrawerGravity extends AbsSavedState
{

    public static final android.os.out.SavedState._cls1 CREATOR = new _cls1();
    public int lockModeEnd;
    public int lockModeLeft;
    public int lockModeRight;
    public int lockModeStart;
    public int openDrawerGravity;

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(openDrawerGravity);
        parcel.writeInt(lockModeLeft);
        parcel.writeInt(lockModeRight);
        parcel.writeInt(lockModeStart);
        parcel.writeInt(lockModeEnd);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        openDrawerGravity = 0;
        openDrawerGravity = parcel.readInt();
        lockModeLeft = parcel.readInt();
        lockModeRight = parcel.readInt();
        lockModeStart = parcel.readInt();
        lockModeEnd = parcel.readInt();
    }

    public lockModeEnd(Parcelable parcelable)
    {
        super(parcelable);
        openDrawerGravity = 0;
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new DrawerLayout.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new DrawerLayout.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new DrawerLayout.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
