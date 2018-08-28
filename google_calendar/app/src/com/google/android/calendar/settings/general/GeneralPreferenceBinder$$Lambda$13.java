// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import com.google.android.calendar.settings.SettingsShims;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder

final class arg._cls1
    implements android.support.v7.preference.r
{

    private final GeneralPreferenceBinder arg$1;

    public final boolean onPreferenceClick(Preference preference)
    {
        preference = arg$1;
        SettingsShims.instance.manageNotificationChannel$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEDIN8T39DPJN6BQJCLQ78QBECTPL6Q39DLPI8JJFEHKMCQB3C5Q6IRRE8DK62RJECLM3MAAM0(((GeneralPreferenceBinder) (preference)).fragment.getContext(), android.support.v4.content.HMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJCLQ78QBECTPIUKR5EHQ6IRJ7ED9MGQBDECI4SRRKD5J6IOR1EHKMURI3D1GMSRJ5DGTG____0);
        return true;
    }

    (GeneralPreferenceBinder generalpreferencebinder)
    {
        arg$1 = generalpreferencebinder;
    }
}
