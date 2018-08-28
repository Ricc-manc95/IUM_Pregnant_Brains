// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings;

import com.google.common.util.concurrent.ListenableFuture;

public interface MeetingsPstnFinder
{

    public abstract ListenableFuture findLocalPhoneNumber(String s, String s1);

    public abstract ListenableFuture listPhones(String s);
}
