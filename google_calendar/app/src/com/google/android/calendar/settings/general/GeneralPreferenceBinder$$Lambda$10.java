// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel, GeneralPreferenceBinder

final class arg._cls2
    implements android.support.v7.preference.r
{

    private final GeneralPreferenceBinder arg$1;
    private final GeneralPreferenceViewModel arg$2;

    public final boolean onPreferenceClick(Preference preference)
    {
        GeneralPreferenceBinder generalpreferencebinder = arg$1;
        preference = arg$2.ringtoneUri;
        Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
        intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
        if (Platform.stringIsNullOrEmpty(preference))
        {
            preference = null;
        } else
        {
            preference = Uri.parse(preference);
        }
        intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", preference);
        intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(2));
        generalpreferencebinder.fragment.startActivityForResult(intent, 1);
        return true;
    }

    (GeneralPreferenceBinder generalpreferencebinder, GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        arg$1 = generalpreferencebinder;
        arg$2 = generalpreferenceviewmodel;
    }
}
