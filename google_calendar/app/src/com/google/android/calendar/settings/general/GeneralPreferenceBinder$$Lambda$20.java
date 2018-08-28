// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.Preference;

// Referenced classes of package com.google.android.calendar.settings.general:
//            QuickResponseFragment, GeneralPreferenceBinder

final class arg._cls1
    implements android.support.v7.preference.r
{

    private final GeneralPreferenceBinder arg$1;

    public final boolean onPreferenceClick(Preference preference)
    {
        preference = arg$1;
        QuickResponseFragment quickresponsefragment = new QuickResponseFragment();
        ((GeneralPreferenceBinder) (preference)).fragment.mFragmentManager.beginTransaction().addToBackStack(null).replace(0x1020002, quickresponsefragment).setTransition(4099).commit();
        return true;
    }

    (GeneralPreferenceBinder generalpreferencebinder)
    {
        arg$1 = generalpreferencebinder;
    }
}
