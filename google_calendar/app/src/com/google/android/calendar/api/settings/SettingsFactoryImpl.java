// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;


// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsFactory, GoogleSettingsModificationsImpl, GoogleSettings, SettingsModificationsImpl, 
//            GoogleSettingsModifications, Settings, SettingsModifications

public final class SettingsFactoryImpl
    implements SettingsFactory
{

    public SettingsFactoryImpl()
    {
    }

    public final GoogleSettingsModifications modifyGoogleSettings(GoogleSettings googlesettings)
    {
        return new GoogleSettingsModificationsImpl(googlesettings);
    }

    public final SettingsModifications modifySettings(Settings settings)
    {
        if (settings instanceof GoogleSettings)
        {
            return new GoogleSettingsModificationsImpl((GoogleSettings)settings);
        } else
        {
            return new SettingsModificationsImpl(settings);
        }
    }
}
