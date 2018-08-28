// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event:
//            GooglePrivateData

public interface GooglePrivateDataModification
    extends Parcelable
{

    public abstract GooglePrivateData get();

    public abstract boolean isModified();

    public abstract void setGuestNotification(GooglePrivateData.GuestNotification guestnotification);

    public abstract void setIsEveryoneDeclinedDismissed(boolean flag);
}
