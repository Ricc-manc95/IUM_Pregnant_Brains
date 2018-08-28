// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.os.Parcelable;
import java.util.List;

public interface Settings
    extends Parcelable
{

    public abstract Account getDescriptor();

    public abstract List getPreferredNotifications(int i);
}
