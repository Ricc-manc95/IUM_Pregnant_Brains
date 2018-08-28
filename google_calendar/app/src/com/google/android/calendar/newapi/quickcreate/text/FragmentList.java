// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FragmentList
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private static final String TAG = com/google/android/calendar/newapi/quickcreate/text/FragmentList.getSimpleName();
    public List fragments;

    FragmentList()
    {
        fragments = new ArrayList();
    }

    FragmentList(Parcel parcel)
    {
        int i;
        int j;
        fragments = new ArrayList();
        j = parcel.readInt();
        i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        List list = fragments;
        byte abyte0[] = parcel.createByteArray();
        list.add((AnnotationFragment)GeneratedMessageLite.parseFrom(AnnotationFragment.DEFAULT_INSTANCE, abyte0));
        i++;
        if (true) goto _L2; else goto _L1
        parcel;
        String s = TAG;
        parcel = String.valueOf(parcel.toString());
        if (parcel.length() != 0)
        {
            parcel = "Failed to parse protos from parcel. ".concat(parcel);
        } else
        {
            parcel = new String("Failed to parse protos from parcel. ");
        }
        Log.wtf(s, parcel);
        fragments.clear();
_L1:
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(fragments.size());
        for (Iterator iterator = fragments.iterator(); iterator.hasNext(); parcel.writeByteArray(((AnnotationFragment)iterator.next()).toByteArray())) { }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FragmentList(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FragmentList[i];
        }

        _cls1()
        {
        }
    }

}
