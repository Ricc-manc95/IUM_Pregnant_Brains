// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel

final class EventDurationBinder
{

    public final String noDurationString;
    public final PreferenceScreen preferenceScreen;
    public final Resources resources;
    public GeneralPreferenceViewModel viewModel;

    public EventDurationBinder(PreferenceScreen preferencescreen)
    {
        preferenceScreen = preferencescreen;
        resources = ((Preference) (preferencescreen)).mContext.getResources();
        noDurationString = resources.getString(0x7f13034e);
    }
}
