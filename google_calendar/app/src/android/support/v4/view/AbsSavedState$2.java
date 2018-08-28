// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Parcel;

// Referenced classes of package android.support.v4.view:
//            AbsSavedState

final class 
    implements android.os.LoaderCreator
{

    public final Object createFromParcel(Parcel parcel)
    {
        if (parcel.readParcelable(null) != null)
        {
            throw new IllegalStateException("superState must be null");
        } else
        {
            return AbsSavedState.EMPTY_STATE;
        }
    }

    public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
    {
        if (parcel.readParcelable(classloader) != null)
        {
            throw new IllegalStateException("superState must be null");
        } else
        {
            return AbsSavedState.EMPTY_STATE;
        }
    }

    public final Object[] newArray(int i)
    {
        return new AbsSavedState[i];
    }

    ()
    {
    }
}
