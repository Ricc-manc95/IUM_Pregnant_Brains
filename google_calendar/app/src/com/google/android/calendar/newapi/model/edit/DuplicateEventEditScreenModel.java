// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model.edit;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.newapi.model.edit:
//            EventEditScreenModel

public final class DuplicateEventEditScreenModel extends EventEditScreenModel
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final int viewMode;

    public DuplicateEventEditScreenModel(int i)
    {
        viewMode = i;
    }

    DuplicateEventEditScreenModel(Parcel parcel)
    {
        super(parcel);
        viewMode = parcel.readInt();
    }

    public final String getAnalyticsAction()
    {
        switch (viewMode)
        {
        default:
            return super.getAnalyticsAction();

        case 2: // '\002'
            return "duplicate";

        case 1: // '\001'
            return "copy";
        }
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(viewMode);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new DuplicateEventEditScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new DuplicateEventEditScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
