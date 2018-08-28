// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.stateful;

import android.os.Parcel;

// Referenced classes of package android.support.design.stateful:
//            ExtendableSavedState

final class 
    implements android.os.reator
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new ExtendableSavedState(parcel, null);
    }

    public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
    {
        return new ExtendableSavedState(parcel, classloader);
    }

    public final Object[] newArray(int i)
    {
        return new ExtendableSavedState[i];
    }

    ()
    {
    }
}
