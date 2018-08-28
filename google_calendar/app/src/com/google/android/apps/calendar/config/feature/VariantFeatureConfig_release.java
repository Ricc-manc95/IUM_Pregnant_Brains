// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.feature;

import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;

// Referenced classes of package com.google.android.apps.calendar.config.feature:
//            FeatureConfig

public final class VariantFeatureConfig_release
    implements FeatureConfig
{

    public VariantFeatureConfig_release()
    {
    }

    public final boolean alternate_timeline()
    {
        return true;
    }

    public final boolean analytics_api_logging()
    {
        return false;
    }

    public final boolean android_retail_demo_calendar()
    {
        return true;
    }

    public final boolean anonymize_sync_history()
    {
        return true;
    }

    public final boolean broadcasting()
    {
        return true;
    }

    public final boolean bugfood_build()
    {
        return false;
    }

    public final boolean conference_pstn_assets_only()
    {
        return false;
    }

    public final boolean dogfood_features()
    {
        return false;
    }

    public final boolean enable_memory_monitoring_unsampled()
    {
        return false;
    }

    public final boolean enable_primes_crash_monitoring_unsampled()
    {
        return false;
    }

    public final boolean enable_primes_packagestats_monitoring_unsampled()
    {
        return false;
    }

    public final boolean experimental_options()
    {
        return false;
    }

    public final boolean extended_memory_monitoring()
    {
        return false;
    }

    public final boolean fishfood_build()
    {
        return false;
    }

    public final boolean fishfood_debug()
    {
        return false;
    }

    public final boolean flinging()
    {
        return false;
    }

    public final boolean google_material()
    {
        return false;
    }

    public final boolean new_notifications()
    {
        return true;
    }

    public final boolean notify_guests()
    {
        return true;
    }

    public final boolean ooo()
    {
        return RemoteFeatureConfig.OUT_OF_OFFICE.enabled();
    }

    public final boolean ooo_sync()
    {
        return true;
    }

    public final boolean primes_ui_latency_instrumentation_unsampled()
    {
        return false;
    }

    public final boolean propose_new_time()
    {
        return true;
    }

    public final boolean public_release()
    {
        return true;
    }

    public final boolean ratuypex()
    {
        return false;
    }

    public final boolean response_comments()
    {
        return false;
    }

    public final boolean resync()
    {
        return false;
    }

    public final boolean sync_cancel_logging()
    {
        return false;
    }

    public final boolean sync_log_to_files()
    {
        return false;
    }

    public final boolean third_party_conferences_editing()
    {
        return false;
    }

    public final boolean threads_strictmode()
    {
        return false;
    }

    public final boolean uss()
    {
        return false;
    }

    public final boolean uss_provider_sync()
    {
        return false;
    }

    public final boolean wupdkwpl()
    {
        return false;
    }
}
