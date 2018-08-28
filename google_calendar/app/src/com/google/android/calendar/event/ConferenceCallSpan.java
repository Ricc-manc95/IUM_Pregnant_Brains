// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.text.style.ClickableSpan;
import android.view.View;

public class ConferenceCallSpan extends ClickableSpan
{
    public static interface OnConferenceNumberClickListener
    {

        public abstract void onClick(String s);
    }


    public final String conferenceNumber;
    private final OnConferenceNumberClickListener listener;

    public ConferenceCallSpan(String s, OnConferenceNumberClickListener onconferencenumberclicklistener)
    {
        conferenceNumber = s;
        listener = onconferencenumberclicklistener;
    }

    public void onClick(View view)
    {
        listener.onClick(conferenceNumber);
    }
}
