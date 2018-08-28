// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            FragmentList

public final class AnnotatedText
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final FragmentList fragmentList;
    public final String text;

    AnnotatedText(Parcel parcel)
    {
        text = parcel.readString();
        fragmentList = (FragmentList)parcel.readParcelable(com/google/android/calendar/newapi/quickcreate/text/FragmentList.getClassLoader());
    }

    public AnnotatedText(String s, FragmentList fragmentlist)
    {
        text = s;
        fragmentList = fragmentlist;
    }

    public static AnnotatedText create(String s, List list)
    {
        FragmentList fragmentlist = new FragmentList();
        fragmentlist.fragments.addAll(list);
        return new AnnotatedText(s, fragmentlist);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(text);
        parcel.writeParcelable(fragmentList, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AnnotatedText(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AnnotatedText[i];
        }

        _cls1()
        {
        }
    }

}
