// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.phone;

import android.net.Uri;

public interface PhoneUtil
{

    public abstract void makeCall(Uri uri);

    public abstract void openDialer(Uri uri);
}
