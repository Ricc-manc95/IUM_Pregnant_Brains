// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.AbsSavedState;

public final class ExtendableSavedState extends AbsSavedState
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final SimpleArrayMap extendableStates;

    ExtendableSavedState(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        int j = parcel.readInt();
        classloader = new String[j];
        parcel.readStringArray(classloader);
        Bundle abundle[] = new Bundle[j];
        parcel.readTypedArray(abundle, Bundle.CREATOR);
        extendableStates = new SimpleArrayMap(j);
        for (int i = 0; i < j; i++)
        {
            extendableStates.put(classloader[i], abundle[i]);
        }

    }

    public ExtendableSavedState(Parcelable parcelable)
    {
        super(parcelable);
        extendableStates = new SimpleArrayMap();
    }

    public final String toString()
    {
        String s = Integer.toHexString(System.identityHashCode(this));
        String s1 = String.valueOf(extendableStates);
        return (new StringBuilder(String.valueOf(s).length() + 30 + String.valueOf(s1).length())).append("ExtendableSavedState{").append(s).append(" states=").append(s1).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        int j = extendableStates.size();
        parcel.writeInt(j);
        String as[] = new String[j];
        Bundle abundle[] = new Bundle[j];
        for (i = 0; i < j; i++)
        {
            as[i] = (String)extendableStates.mArray[i << 1];
            abundle[i] = (Bundle)extendableStates.mArray[(i << 1) + 1];
        }

        parcel.writeStringArray(as);
        parcel.writeTypedArray(abundle, 0);
    }


    private class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
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

        _cls1()
        {
        }
    }

}
