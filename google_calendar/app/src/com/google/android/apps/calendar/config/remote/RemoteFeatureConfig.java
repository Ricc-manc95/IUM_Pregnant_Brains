// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.remote;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.config.remote:
//            SafetyNetFeature, HtcMailIssueResyncFeature, RemoteFeature, OrphanEventsDataCleanupFeature, 
//            UnifiedSyncAndStoreFeature, PrimesApiLoggingFeature, NewNotificationsFeature

public final class RemoteFeatureConfig
{

    private static final RemoteFeature ALL_FEATURES[];
    public static final RemoteFeature ALTERNATE_TIMELINE;
    private static final RemoteFeature BETTER_EVENT_IMAGE_CACHE;
    public static final RemoteFeature BROADCASTING;
    public static final RemoteFeature CONFERENCE_PSTN;
    public static final RemoteFeature DISABLE_QUICK_CREATE;
    public static final RemoteFeature DUMP_BUNDLE_STATS;
    private static final String EMPTY_STRING_ARRAY[] = new String[0];
    public static final RemoteFeature EVERYONE_DECLINED;
    public static final RemoteFeature FLINGING;
    public static final RemoteFeature GROWTH_KIT;
    public static final RemoteFeature HATS_FOR_DASHERS_IN_PROD;
    private static final RemoteFeature LONG_PRESS_TO_CREATE;
    private static final RemoteFeature NEW_ICS_IMPORT;
    public static final NewNotificationsFeature NEW_NOTIFICATIONS;
    private static final RemoteFeature NEW_SETTINGS;
    public static final OrphanEventsDataCleanupFeature ORPHAN_EVENTS_DATA_CLEANUP;
    public static final RemoteFeature OUT_OF_OFFICE;
    public static final PrimesApiLoggingFeature PRIMES_API_LOGGING;
    public static final RemoteFeature PROPOSE_NEW_TIME;
    public static final RemoteFeature REMOVE_ALERT_SERVICE;
    private static final RemoteFeature REMOVE_FIT_OPERATION_SERVICE;
    public static final HtcMailIssueResyncFeature RESYNC;
    public static final SafetyNetFeature SAFETY_NET_V_3;
    public static final RemoteFeature SRB;
    public static final RemoteFeature THIRD_PARTY_CONFERENCES;
    private static final RemoteFeature THIRD_PARTY_CONFERENCES_EDITING;
    public static final RemoteFeature THIRD_PARTY_RESOURCE_SUPPORT;
    private static final RemoteFeature UNICORN_PRIVACY_POLICY;
    public static final UnifiedSyncAndStoreFeature UNIFIED_SYNC_AND_STORE;
    private static String enabledFeaturesString;
    private static String enabledFeaturesStringArray[];

    public static String getEnabledFeatures()
    {
        if (enabledFeaturesString == null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            String as[] = getEnabledFeaturesArray();
            int j = as.length;
            for (int i = 0; i < j; i++)
            {
                String s = as[i];
                stringbuilder.append("[").append(s).append("]");
            }

            enabledFeaturesString = stringbuilder.toString();
        }
        return enabledFeaturesString;
    }

    public static String[] getEnabledFeaturesArray()
    {
        if (enabledFeaturesStringArray == null)
        {
            ArrayList arraylist = new ArrayList(ALL_FEATURES.length);
            RemoteFeature aremotefeature[] = ALL_FEATURES;
            int j = aremotefeature.length;
            for (int i = 0; i < j; i++)
            {
                RemoteFeature remotefeature = aremotefeature[i];
                if (remotefeature.enabled())
                {
                    arraylist.add(remotefeature.getCode());
                }
            }

            enabledFeaturesStringArray = (String[])arraylist.toArray(EMPTY_STRING_ARRAY);
        }
        return enabledFeaturesStringArray;
    }

    static 
    {
        SAFETY_NET_V_3 = new SafetyNetFeature();
        RESYNC = new HtcMailIssueResyncFeature();
        CONFERENCE_PSTN = new RemoteFeature("ConferencePstn", "CPRN", false);
        ORPHAN_EVENTS_DATA_CLEANUP = new OrphanEventsDataCleanupFeature();
        UNICORN_PRIVACY_POLICY = new RemoteFeature("UnicornPrivacyPolicy", "UPPM", false);
        SRB = new RemoteFeature("SRB", "SRBO", false);
        NEW_ICS_IMPORT = new RemoteFeature("NewIcsImport", "NIIM", false);
        NEW_SETTINGS = new RemoteFeature("NewSettings", "NSKD", false);
        BETTER_EVENT_IMAGE_CACHE = new RemoteFeature("BetterEventImageCache", "BEIC", false);
        UNIFIED_SYNC_AND_STORE = new UnifiedSyncAndStoreFeature();
        DISABLE_QUICK_CREATE = new RemoteFeature("DisableQuickCreate", "DQCT", false);
        DUMP_BUNDLE_STATS = new RemoteFeature("DumpBundleStats", "DBSC", false);
        REMOVE_ALERT_SERVICE = new RemoteFeature("RemoveAlertService", "RASV", false);
        LONG_PRESS_TO_CREATE = new RemoteFeature("LongPressToCreate", "LPTC", false);
        HATS_FOR_DASHERS_IN_PROD = new RemoteFeature("HatsForDashersInProd", "HFDIP", false);
        PRIMES_API_LOGGING = new PrimesApiLoggingFeature();
        GROWTH_KIT = new RemoteFeature("GrowthKit", "GKGC", true);
        THIRD_PARTY_RESOURCE_SUPPORT = new RemoteFeature("ThirdPartyResourceSupport", "TPRS", false);
        THIRD_PARTY_CONFERENCES = new RemoteFeature("third_party_conferences", "YCZG", false);
        NEW_NOTIFICATIONS = new NewNotificationsFeature();
        ALTERNATE_TIMELINE = new RemoteFeature("AlternateTimeline", "ATBC", false);
        REMOVE_FIT_OPERATION_SERVICE = new RemoteFeature("RemoveFitOperationService", "RFOS", false);
        EVERYONE_DECLINED = new RemoteFeature("EveryoneDeclined", "EDQW", false);
        THIRD_PARTY_CONFERENCES_EDITING = new RemoteFeature("ThirdPartyConferencesEditing", "TPCE", false);
        FLINGING = new RemoteFeature("Flinging", "FHVM", false);
        PROPOSE_NEW_TIME = new RemoteFeature("ProposeNewTime", "PNTM", false);
        OUT_OF_OFFICE = new RemoteFeature("OutOfOffice", "OOOO", false);
        BROADCASTING = new RemoteFeature("Broadcasting", "BROM", false);
        ALL_FEATURES = (new RemoteFeature[] {
            SAFETY_NET_V_3, RESYNC, CONFERENCE_PSTN, ORPHAN_EVENTS_DATA_CLEANUP, UNICORN_PRIVACY_POLICY, SRB, NEW_ICS_IMPORT, NEW_SETTINGS, BETTER_EVENT_IMAGE_CACHE, UNIFIED_SYNC_AND_STORE, 
            DISABLE_QUICK_CREATE, DUMP_BUNDLE_STATS, REMOVE_ALERT_SERVICE, LONG_PRESS_TO_CREATE, HATS_FOR_DASHERS_IN_PROD, PRIMES_API_LOGGING, GROWTH_KIT, THIRD_PARTY_RESOURCE_SUPPORT, THIRD_PARTY_CONFERENCES, NEW_NOTIFICATIONS, 
            ALTERNATE_TIMELINE, REMOVE_FIT_OPERATION_SERVICE, EVERYONE_DECLINED, THIRD_PARTY_CONFERENCES_EDITING, FLINGING, PROPOSE_NEW_TIME, OUT_OF_OFFICE, BROADCASTING
        });
    }
}
