// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Set;

public final class QuickCreateAvailability
{

    private static final ImmutableSet SUPPORTED_EVENT_LANGUAGES = ImmutableSet.of("en", "pl", "es", "th", "ar", "zh", new String[] {
        "fr", "de", "it", "ja", "ko", "ru", "tr", "nl", "pt", "fa", 
        "fil", "vi", "bg", "ca", "hr", "cs", "da", "fi", "el", "iw", 
        "hu", "id", "in", "lv", "lt", "nb", "no", "ro", "sr", "sk", 
        "sl", "uk", "pt", "sv", "hi"
    });
    private static final ImmutableSet SUPPORTED_REMINDER_LANGUAGES = ImmutableSet.of("en", "es", "de", "fr", "it", "nl", new String[] {
        "pl", "pt", "ru", "th", "tr"
    });

    public static boolean checkForEvents(Context context, Account account, Event event, Locale locale)
    {
        boolean flag;
        if (event != null && event.getParticipantStatus() != null && event.getParticipantStatus().getOutOfOffice() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return !flag && generalCheck(context, account, SUPPORTED_EVENT_LANGUAGES, locale);
    }

    public static boolean checkForReminders(Context context, Account account, Locale locale)
    {
        return generalCheck(context, account, SUPPORTED_REMINDER_LANGUAGES, locale);
    }

    private static boolean generalCheck(Context context, Account account, Set set, Locale locale)
    {
        return !RemoteFeatureConfig.DISABLE_QUICK_CREATE.enabled() && AccountUtil.isGoogleAccount(account) && locale != null && set.contains(locale.getLanguage()) && !AccessibilityUtils.isAccessibilityEnabled(context);
    }

}
