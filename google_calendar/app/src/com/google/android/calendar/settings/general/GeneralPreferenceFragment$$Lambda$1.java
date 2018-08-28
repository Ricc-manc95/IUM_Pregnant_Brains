// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import com.android.calendarcommon2.LogUtils;
import com.android.timezonepicker.fullscreen.TimeZonePickerHelper;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.timely.settings.PreferencesUtils;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceFragment, GeneralPreferenceBinder, GeneralPreferenceViewModel

final class arg._cls4
    implements Consumer
{

    private final GeneralPreferenceFragment arg$1;
    private final int arg$2;
    private final Intent arg$3;
    private final int arg$4;

    public final void accept(Object obj)
    {
        Object obj1;
        int i;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        int j;
        flag2 = false;
        flag3 = false;
        flag1 = false;
        obj = arg$1;
        i = arg$2;
        obj1 = arg$3;
        j = arg$4;
        if (i != 1) goto _L2; else goto _L1
_L1:
        obj = ((GeneralPreferenceFragment) (obj)).binder;
        if (obj1 == null) goto _L4; else goto _L3
_L3:
        Object obj2 = ((Intent) (obj1)).getExtras().get("android.intent.extra.ringtone.PICKED_URI");
        if (obj2 != null) goto _L6; else goto _L5
_L5:
label0:
        {
            obj1 = ((GeneralPreferenceBinder) (obj)).viewModel;
            obj2 = ((GeneralPreferenceViewModel) (obj1)).ringtoneUri;
            if (obj2 != "")
            {
                i = ((flag1) ? 1 : 0);
                if (obj2 == null)
                {
                    break label0;
                }
                i = ((flag1) ? 1 : 0);
                if (!obj2.equals(""))
                {
                    break label0;
                }
            }
            i = 1;
        }
        if (i == 0)
        {
            obj1.ringtoneUri = "";
            PreferencesUtils.setRingtonePreference(((GeneralPreferenceViewModel) (obj1)).context, "");
        }
_L9:
        ((GeneralPreferenceBinder) (obj)).bindNotifications(((GeneralPreferenceBinder) (obj)).viewModel);
_L4:
        return;
_L6:
label1:
        {
            if (!(obj2 instanceof Uri))
            {
                continue; /* Loop/switch isn't completed */
            }
            obj1 = ((GeneralPreferenceBinder) (obj)).viewModel;
            obj2 = obj2.toString();
            String s = ((GeneralPreferenceViewModel) (obj1)).ringtoneUri;
            if (s != obj2)
            {
                i = ((flag2) ? 1 : 0);
                if (s == null)
                {
                    break label1;
                }
                i = ((flag2) ? 1 : 0);
                if (!s.equals(obj2))
                {
                    break label1;
                }
            }
            i = 1;
        }
        if (i == 0)
        {
            obj1.ringtoneUri = ((String) (obj2));
            PreferencesUtils.setRingtonePreference(((GeneralPreferenceViewModel) (obj1)).context, ((String) (obj2)));
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (i != 2)
        {
            break MISSING_BLOCK_LABEL_345;
        }
        obj1 = TimeZonePickerHelper.processResultsIntent(((Fragment) (obj)).getContext(), j, ((Intent) (obj1)));
        if (!((com.android.timezonepicker.fullscreen.ntent) (obj1)).WasSelected()) goto _L4; else goto _L7
_L7:
        Object obj3;
        boolean flag;
label2:
        {
            obj = ((GeneralPreferenceFragment) (obj)).binder;
            obj1 = ((com.android.timezonepicker.fullscreen.der) (obj1)).der();
            obj3 = ((GeneralPreferenceBinder) (obj)).viewModel;
            String s1 = ((GeneralPreferenceViewModel) (obj3)).timezone;
            if (obj1 != s1)
            {
                flag = flag3;
                if (obj1 == null)
                {
                    break label2;
                }
                flag = flag3;
                if (!obj1.equals(s1))
                {
                    break label2;
                }
            }
            flag = true;
        }
        if (!flag)
        {
            obj3.timezone = ((String) (obj1));
            ((GeneralPreferenceViewModel) (obj3)).updateTimezone();
        }
        obj1 = ((GeneralPreferenceBinder) (obj)).timezonePreference;
        obj = ((GeneralPreferenceBinder) (obj)).viewModel;
        obj3 = SettingsShims.instance;
        android.content.Context context = ((GeneralPreferenceViewModel) (obj)).context;
        if (((GeneralPreferenceViewModel) (obj)).useDeviceTimezone)
        {
            obj = ((GeneralPreferenceViewModel) (obj)).deviceTimezone;
        } else
        {
            obj = ((GeneralPreferenceViewModel) (obj)).timezone;
        }
        ((Preference) (obj1)).setSummary(((SettingsShims) (obj3)).getTimezoneName(context, ((String) (obj))));
        return;
        LogUtils.w(GeneralPreferenceFragment.TAG, "Received onActivityResult for result code other than ringtone and time zone picker", new Object[0]);
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    (GeneralPreferenceFragment generalpreferencefragment, int i, Intent intent, int j)
    {
        arg$1 = generalpreferencefragment;
        arg$2 = i;
        arg$3 = intent;
        arg$4 = j;
    }
}
