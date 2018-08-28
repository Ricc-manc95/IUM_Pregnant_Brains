// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.provider;

import android.os.Parcel;

// Referenced classes of package com.android.emailcommon.provider:
//            RecipientAvailability

final class 
    implements android.os.cipientAvailability._cls1
{

    public final Object createFromParcel(Parcel parcel)
    {
        RecipientAvailability recipientavailability = new RecipientAvailability();
        recipientavailability.type = parcel.readInt();
        recipientavailability.emailAddress = parcel.readString();
        recipientavailability.displayName = parcel.readString();
        recipientavailability.mergedFreeBusy = parcel.readString();
        return recipientavailability;
    }

    public final Object[] newArray(int i)
    {
        return new RecipientAvailability[i];
    }

    ()
    {
    }
}
