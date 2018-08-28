// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

public abstract class SettingsShims
{

    public static SettingsShims instance;

    public SettingsShims()
    {
    }

    public abstract void broadcastWidgetUpdate(Context context);

    public abstract DialogFragment createCustomNotificationDialog(Context context, CustomNotificationListener customnotificationlistener, boolean flag, boolean flag1);

    public abstract void disableFitIntegration(Context context);

    public abstract String formatNotification(Context context, Notification notification, boolean flag);

    public abstract List getNotificationOptions(Context context, CalendarDescriptor calendardescriptor, boolean flag);

    public abstract String getTimezone(Context context, boolean flag);

    public abstract CharSequence getTimezoneName(Context context, String s);

    public abstract ListenableFuture hasHabitsWithEnabledIntegration$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T66ISRKCLN62OJCCL37AT3LE9IJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0(ListenableFuture listenablefuture);

    public abstract boolean isSmartMailAccount(Context context, Account account);

    public abstract void launchHelpAndFeedback(Activity activity, String s);

    public abstract com.google.calendar.v2.libs.proto.DirectoryProto.Directory loadDirectory(Context context);

    public abstract void manageNotificationChannel$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEDIN8T39DPJN6BQJCLQ78QBECTPL6Q39DLPI8JJFEHKMCQB3C5Q6IRRE8DK62RJECLM3MAAM0(Context context, int i);

    public abstract void setAlternateCalendar(Context context, int i);

    public abstract void setBirthdayColor(Context context, CalendarColor calendarcolor);

    public abstract void setTimezone(Context context, String s);

    public abstract boolean shouldShowFlingingSettings(Context context);

    public abstract void showColorPicker(ImmutableList immutablelist, int i, Fragment fragment);
}
