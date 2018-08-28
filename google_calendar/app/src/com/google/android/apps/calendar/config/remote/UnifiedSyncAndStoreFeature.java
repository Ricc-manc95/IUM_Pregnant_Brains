// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.remote;

import com.google.android.gms.phenotype.PhenotypeFlag;

// Referenced classes of package com.google.android.apps.calendar.config.remote:
//            RemoteFeature

public final class UnifiedSyncAndStoreFeature extends RemoteFeature
{

    private Boolean enableSideSync;
    private final PhenotypeFlag enableSideSyncFlag = buildFlag("enable_side_sync", false);
    private String overriddenCode;
    private Boolean supportsCalendars;
    private final PhenotypeFlag supportsCalendarsFlag = buildFlag("supports_calendars", false);
    private Boolean supportsEvents;
    private final PhenotypeFlag supportsEventsFlag = buildFlag("supports_events", false);
    private Boolean supportsHabits;
    private final PhenotypeFlag supportsHabitsFlag = buildFlag("supports_habits", false);
    private Boolean supportsSettings;
    private final PhenotypeFlag supportsSettingsFlag = buildFlag("supports_settings", false);
    private Boolean syncCalendars;
    private final PhenotypeFlag syncCalendarsFlag = buildFlag("sync_calendars", false);

    UnifiedSyncAndStoreFeature()
    {
        super("UnifiedSyncAndStore", "USS", false);
    }

    public final String getCode()
    {
        if (overriddenCode == null)
        {
            StringBuilder stringbuilder = new StringBuilder(super.getCode());
            if (getSupportsSettings())
            {
                stringbuilder.append("S");
            }
            if (getSupportsHabits())
            {
                stringbuilder.append("H");
            }
            if (getSupportsEvents())
            {
                stringbuilder.append("E");
            }
            if (getSupportsCalendars())
            {
                stringbuilder.append("L");
            }
            if (getSyncCalendars())
            {
                stringbuilder.append("C");
            }
            if (getEnableSideSync())
            {
                stringbuilder.append("P");
            }
            overriddenCode = stringbuilder.toString();
        }
        return overriddenCode;
    }

    public final boolean getEnableSideSync()
    {
        if (enableSideSync == null)
        {
            enableSideSync = getFlagOverride("enable_side_sync");
        }
        if (enableSideSync == null)
        {
            enableSideSync = (Boolean)enableSideSyncFlag.get();
        }
        return enableSideSync.booleanValue();
    }

    public final boolean getSupportsCalendars()
    {
        if (supportsCalendars == null)
        {
            supportsCalendars = getFlagOverride("supports_calendars");
        }
        if (supportsCalendars == null)
        {
            supportsCalendars = (Boolean)supportsCalendarsFlag.get();
        }
        return supportsCalendars.booleanValue();
    }

    public final boolean getSupportsEvents()
    {
        if (supportsEvents == null)
        {
            supportsEvents = getFlagOverride("supports_events");
        }
        if (supportsEvents == null)
        {
            supportsEvents = (Boolean)supportsEventsFlag.get();
        }
        return supportsEvents.booleanValue();
    }

    public final boolean getSupportsHabits()
    {
        if (supportsHabits == null)
        {
            supportsHabits = getFlagOverride("supports_habits");
        }
        if (supportsHabits == null)
        {
            supportsHabits = (Boolean)supportsHabitsFlag.get();
        }
        return supportsHabits.booleanValue();
    }

    public final boolean getSupportsSettings()
    {
        if (supportsSettings == null)
        {
            supportsSettings = getFlagOverride("supports_settings");
        }
        if (supportsSettings == null)
        {
            supportsSettings = (Boolean)supportsSettingsFlag.get();
        }
        return supportsSettings.booleanValue();
    }

    public final boolean getSyncCalendars()
    {
        if (syncCalendars == null)
        {
            syncCalendars = getFlagOverride("sync_calendars");
        }
        if (syncCalendars == null)
        {
            syncCalendars = (Boolean)syncCalendarsFlag.get();
        }
        return syncCalendars.booleanValue();
    }
}
