// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.smartprofile;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.timebox.Birthday;
import com.google.android.calendar.utils.activity.ActivityUtils;

public class SmartProfileHelper
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/smartprofile/SmartProfileHelper);

    public SmartProfileHelper()
    {
    }

    public static void showSmartProfile(Activity activity, String s, ContactInfo contactinfo)
    {
        String s1;
        com.google.android.gms.smart_profile.SmartProfile.IntentBuilder intentbuilder;
        if (contactinfo.contactId != null)
        {
            s1 = String.valueOf(contactinfo.contactId);
            s1 = (new StringBuilder(String.valueOf(s1).length() + 2)).append("c:").append(s1).toString();
        } else
        if (!TextUtils.isEmpty(contactinfo.primaryEmail))
        {
            s1 = String.valueOf(contactinfo.primaryEmail);
            if (s1.length() != 0)
            {
                s1 = "e:".concat(s1);
            } else
            {
                s1 = new String("e:");
            }
        } else
        {
            LogUtils.w(TAG, "Can't show SmartProfile for contact info without primary email, contact id and focus id. ContactInfo: %s", new Object[] {
                contactinfo
            });
            return;
        }
        intentbuilder = new com.google.android.gms.smart_profile.SmartProfile.IntentBuilder();
        intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.APPLICATION_ID", 139);
        intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.QUALIFIED_ID", s1);
        contactinfo = contactinfo.name;
        intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.DISPLAY_NAME", contactinfo);
        intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.VIEWER_ACCOUNT_NAME", s);
        ActivityUtils.startActivityForResult(activity, intentbuilder.mIntent);
    }

    public static void showSmartProfile(Activity activity, String s, Birthday birthday)
    {
        com.google.android.gms.smart_profile.SmartProfile.IntentBuilder intentbuilder = new com.google.android.gms.smart_profile.SmartProfile.IntentBuilder();
        intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.APPLICATION_ID", 139);
        String s1 = birthday.fullName();
        intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.DISPLAY_NAME", s1);
        intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.VIEWER_ACCOUNT_NAME", s);
        if (!TextUtils.isEmpty(birthday.email()))
        {
            s = String.valueOf(birthday.email());
            if (s.length() != 0)
            {
                s = "e:".concat(s);
            } else
            {
                s = new String("e:");
            }
            intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.QUALIFIED_ID", s);
        } else
        if (birthday.isGPlusUser() && !TextUtils.isEmpty(birthday.profileId()))
        {
            s = String.valueOf(birthday.profileId());
            if (s.length() != 0)
            {
                s = "g:".concat(s);
            } else
            {
                s = new String("g:");
            }
            intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.QUALIFIED_ID", s);
        } else
        {
            LogUtils.w(TAG, "Can't show SmartProfile for birthday info without email and gaia id.BirthdayInfo: %s", new Object[] {
                birthday
            });
            return;
        }
        if (!TextUtils.isEmpty(birthday.photoUrl()))
        {
            s = birthday.photoUrl();
            intentbuilder.mIntent.putExtra("com.google.android.gms.people.smart_profile.AVATAR_URL", s);
        }
        ActivityUtils.startActivityForResult(activity, intentbuilder.mIntent);
    }

}
