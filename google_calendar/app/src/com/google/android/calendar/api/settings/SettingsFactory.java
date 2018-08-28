// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;


// Referenced classes of package com.google.android.calendar.api.settings:
//            GoogleSettings, GoogleSettingsModifications, Settings, SettingsModifications

public interface SettingsFactory
{

    public abstract GoogleSettingsModifications modifyGoogleSettings(GoogleSettings googlesettings);

    public abstract SettingsModifications modifySettings(Settings settings);
}
