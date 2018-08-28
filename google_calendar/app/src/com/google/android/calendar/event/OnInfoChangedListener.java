// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.support.v4.app.DialogFragment;

public interface OnInfoChangedListener
{

    public abstract void onInfoBack(DialogFragment dialogfragment, boolean flag);

    public abstract void onInfoCancel(DialogFragment dialogfragment, boolean flag);
}
