// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import android.accounts.Account;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.calendar.event.conference.PhoneNumberDetails;

public interface VisualElementAttacher
{

    public abstract void attachCreateEventButton(View view);

    public abstract void attachDayView(View view);

    public abstract void attachEditElements$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78GB3EHKNCQBKF4TKOOBECHP6UQB45TR6IPBN5TB6IPBN7D662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQMD5INEEQQ9HL62TJ15TM62RJ75T9N8SJ9DPJJMJ3AC5R62BRCC5N6EBQJEHP6IRJ77D66KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(View view, View view1, View view2, boolean flag, String s, String s1, String s2);

    public abstract void attachEditEventButton(View view, String s, String s1);

    public abstract void attachEventChip(View view);

    public abstract void attachEventDetailsPage$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78GB3EHKNCQBKF4TKOOBECHP6UQB45TR6IPBN5TB6IPBN7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0(View view, String s, String s1);

    public abstract void attachJoinConference(View view, View view1, View view2, View view3, View view4, PhoneNumberDetails phonenumberdetails);

    public abstract void attachMainCalendarPage(FragmentActivity fragmentactivity);

    public abstract void attachMonthView(View view);

    public abstract void attachPhoneNumberItem(View view);

    public abstract void attachPhoneNumbersActivity(View view);

    public abstract void attachScheduleView(View view);

    public abstract void attachThreeDayView(View view);

    public abstract void attachWeekView(View view);

    public abstract void detachChip(View view);

    public abstract void recordAppVisibilityEvent(Context context, boolean flag);

    public abstract void recordChipTap(Context context, View view);

    public abstract void recordImpression(Context context, View view);

    public abstract void recordImpression(Context context, View view, Account account);

    public abstract void recordTap(Context context, View view);

    public abstract void recordTap(Context context, View view, Account account);
}
