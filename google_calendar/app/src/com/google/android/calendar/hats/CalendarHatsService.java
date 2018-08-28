// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.hats;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.experiments.Experiment;
import com.google.android.apps.calendar.config.experiments.ExperimentConfiguration;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.libraries.hats20.HatsController;
import com.google.android.libraries.hats20.HatsDownloadRequest;
import com.google.android.libraries.hats20.inject.HatsModule;
import java.io.IOException;
import java.util.Locale;

public class CalendarHatsService
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/hats/CalendarHatsService);
    public boolean wasSurveyShown;

    public CalendarHatsService()
    {
        wasSurveyShown = false;
    }

    public static String getSiteId(String s, boolean flag)
    {
        if (Locale.ENGLISH.getLanguage().equals(s))
        {
            s = Features.instance;
            if (s == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)s).fishfood_debug();
            s = Features.instance;
            if (s == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)s).dogfood_features();
            if (flag)
            {
                return "vcfqdkoxwesifh3pwnpphp5tla";
            } else
            {
                return "54bdgbnphehetikms6jqw4l6eu";
            }
        } else
        {
            LogUtils.d(TAG, "No site id available, language %s not supported.", new Object[] {
                s
            });
            return null;
        }
    }

    static final void lambda$downloadSurveyIfSupported$1$CalendarHatsService(Context context, String s)
    {
        Object obj = AdvertisingIdClient.getAdvertisingIdInfo(context);
        if (obj == null)
        {
            return;
        }
        context = new com.google.android.libraries.hats20.HatsDownloadRequest.Builder(context);
        if (((com.google.android.libraries.hats20.HatsDownloadRequest.Builder) (context)).siteId != null)
        {
            throw new UnsupportedOperationException("Currently don't support multiple site IDs.");
        }
          goto _L1
        context;
_L3:
        LogUtils.e(TAG, context, "Failed to download HaTS for id: %s", new Object[] {
            s
        });
        return;
_L1:
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        throw new NullPointerException("Site ID cannot be set to null.");
        context.siteId = s;
        obj = ((com.google.android.gms.ads.identifier.AdvertisingIdClient.Info) (obj)).zzsA;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        throw new NullPointerException("Advertising ID was missing.");
        context.advertisingId = ((String) (obj));
        if (((com.google.android.libraries.hats20.HatsDownloadRequest.Builder) (context)).alreadyBuilt)
        {
            throw new IllegalStateException("Cannot reuse Builder instance once instantiated");
        }
        context.alreadyBuilt = true;
        if (((com.google.android.libraries.hats20.HatsDownloadRequest.Builder) (context)).siteId == null)
        {
            context.siteId = "-1";
        }
        if (((com.google.android.libraries.hats20.HatsDownloadRequest.Builder) (context)).advertisingId == null)
        {
            throw new NullPointerException("Advertising ID was missing.");
        } else
        {
            context = new HatsDownloadRequest(context);
            HatsModule.get().getHatsController().downloadSurvey(context);
            return;
        }
        context;
        continue; /* Loop/switch isn't completed */
        context;
        if (true) goto _L3; else goto _L2
_L2:
    }

    public static void logEvent(Context context, String s)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "hats", s, null, null);
            return;
        }
    }

    public static void logSurveyTaken(Context context)
    {
        logEvent(context, "answer");
    }

    public final void downloadSurveyIfSupported(Context context, boolean flag)
    {
        boolean flag1;
        if (flag)
        {
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).dogfood_features();
            flag1 = RemoteFeatureConfig.HATS_FOR_DASHERS_IN_PROD.enabled();
        } else
        {
            flag1 = ExperimentConfiguration.HATS_PERCENTAGE.isActive(context);
        }
        String s;
        if (flag1)
        {
            if ((s = getSiteId(context.getResources().getConfiguration().locale.getLanguage(), flag)) != null)
            {
                class .Lambda._cls1
                    implements Runnable
                {

                    private final Context arg$1;
                    private final String arg$2;

                    public final void run()
                    {
                        CalendarHatsService.lambda$downloadSurveyIfSupported$1$CalendarHatsService(arg$1, arg$2);
                    }

            .Lambda._cls1(Context context, String s)
            {
                arg$1 = context;
                arg$2 = s;
            }
                }

                CalendarExecutor.NET.execute(new .Lambda._cls1(context, s));
                return;
            }
        }
    }

}
