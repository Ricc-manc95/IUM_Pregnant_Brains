// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.analytics;

import android.accounts.Account;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.account.CalendarAccountsUtil;
import com.google.android.apps.calendar.commonsync.analytics.api.AnalyticsLogger;
import com.google.android.apps.calendar.commonsync.analytics.factory.AnalyticsLoggerFactory;
import com.google.android.apps.calendar.commonsync.analytics.noop.NoopAnalyticsLogger;
import com.google.android.apps.calendar.commonsync.analytics.v2.V2AnalyticsLogger;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.loggers.CalendarClearcutLogger;
import com.google.android.apps.calendar.loggers.ClearcutManager;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.AnalyticsUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.app.ApplicationUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gsf.Gservices;
import com.google.android.libraries.internal.growth.growthkit.events.GrowthKitEventManager;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.syncadapters.calendar.AnalyticsLoggerBase;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.protobuf.AbstractMessageLite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.analytics:
//            AnalyticsLogger

public final class CalendarAnalyticsLoggerExtension extends AnalyticsLoggerBase
    implements com.google.android.calendar.analytics.AnalyticsLogger
{

    private static final String ANALYTICS_DEFAULT_SAMPLING_RATE;
    private static final String ANALYTICS_PROPERTY_ID = "UA-39295668-5";
    private static final ImmutableSet CONSUMER_ACCOUNT_DOMAINS;
    private final ClearcutManager clearcutManager;

    public CalendarAnalyticsLoggerExtension(Context context)
    {
        String s = ANALYTICS_PROPERTY_ID;
        double d = Double.parseDouble(Gservices.getString(context.getContentResolver(), "calendar_app_analytics_sampling_rate", ANALYTICS_DEFAULT_SAMPLING_RATE));
        Object obj;
        if (AnalyticsLoggerFactory.BUGFOOD_OR_EMULATOR_OR_TEST)
        {
            obj = new NoopAnalyticsLogger();
        } else
        {
            obj = new V2AnalyticsLogger();
        }
        super(context, s, d, ((AnalyticsLogger) (obj)));
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0)
        {
            clearcutManager = ClearcutManager.getInstance(context, ApplicationUtils.isSystemApp(context));
            return;
        } else
        {
            clearcutManager = null;
            return;
        }
    }

    private static boolean isConsumerAccount(Account account)
    {
label0:
        {
            if (account.name == null)
            {
                break label0;
            }
            UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)CONSUMER_ACCOUNT_DOMAINS.iterator();
            String s;
            do
            {
                if (!unmodifiableiterator.hasNext())
                {
                    break label0;
                }
                s = (String)unmodifiableiterator.next();
            } while (!account.name.endsWith(s));
            return true;
        }
        return false;
    }

    private final void setCalendarStatisticsDimensions(Context context, Iterable iterable, boolean flag)
    {
        Iterator iterator = iterable.iterator();
        int i = 0;
        int k = 0;
        while (iterator.hasNext()) 
        {
            CalendarListEntry calendarlistentry1 = (CalendarListEntry)iterator.next();
            int i1;
            if (calendarlistentry1.isSyncEnabled())
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            i1 = k + i1;
            if (calendarlistentry1.isVisible())
            {
                k = 1;
            } else
            {
                k = 0;
            }
            i = k + i;
            k = i1;
        }
        setCustomDimension(context, 27, AnalyticsUtils.convertNumToDimensionValue(k, 25));
        setCustomDimension(context, 30, AnalyticsUtils.convertNumToDimensionValue(i, 25));
        setCustomMetric(context, 1, k);
        setCustomMetric(context, 4, i);
        if (flag)
        {
            iterable = iterable.iterator();
            int l = 0;
            int j = 0;
            int j1 = 0;
            int k1 = 0;
            while (iterable.hasNext()) 
            {
                CalendarListEntry calendarlistentry = (CalendarListEntry)iterable.next();
                if ("com.google".equals(calendarlistentry.getDescriptor().account.type))
                {
                    int l1;
                    if (calendarlistentry.isSyncEnabled())
                    {
                        l1 = 1;
                    } else
                    {
                        l1 = 0;
                    }
                    k1 = l1 + k1;
                    if (calendarlistentry.isVisible())
                    {
                        l1 = 1;
                    } else
                    {
                        l1 = 0;
                    }
                    j1 = l1 + j1;
                    if (isConsumerAccount(calendarlistentry.getDescriptor().account))
                    {
                        int k2;
                        if (calendarlistentry.isSyncEnabled())
                        {
                            l1 = 1;
                        } else
                        {
                            l1 = 0;
                        }
                        if (calendarlistentry.isVisible())
                        {
                            k2 = 1;
                        } else
                        {
                            k2 = 0;
                        }
                        k2 = l + k2;
                        l = j + l1;
                        j = k2;
                    } else
                    {
                        int i2 = j;
                        j = l;
                        l = i2;
                    }
                } else
                {
                    int j2 = j;
                    j = l;
                    l = j2;
                }
                l1 = l;
                l = j;
                j = l1;
            }
            setCustomDimension(context, 28, AnalyticsUtils.convertNumToDimensionValue(k1, 25));
            setCustomDimension(context, 29, AnalyticsUtils.convertNumToDimensionValue(j, 25));
            setCustomDimension(context, 31, AnalyticsUtils.convertNumToDimensionValue(j1, 25));
            setCustomDimension(context, 32, AnalyticsUtils.convertNumToDimensionValue(l, 25));
            setCustomMetric(context, 2, k1);
            setCustomMetric(context, 3, j);
            setCustomMetric(context, 5, j1);
            setCustomMetric(context, 6, l);
        }
    }

    public final void addAccountTypeCustomDimensions(Context context, Account account)
    {
        String s;
        Object obj1;
        boolean flag;
        obj1 = null;
        if (!"com.google".equals(account.type))
        {
            break MISSING_BLOCK_LABEL_284;
        }
        s = "Google";
        flag = false;
        if (account.name == null) goto _L2; else goto _L1
_L1:
        Object obj;
        flag = isConsumerAccount(account);
        ListenableFutureCache listenablefuturecache = SettingsCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = obj1;
        if (((ListenableFutureCache)listenablefuturecache).result != null)
        {
            obj = SettingsCache.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            account = (Settings)((ImmutableMap)((ListenableFutureCache)obj).result).get(account);
            obj = obj1;
            if (account instanceof GoogleSettings)
            {
                obj = (GoogleSettings)account;
            }
        }
        if (obj == null) goto _L2; else goto _L3
_L3:
        if (flag)
        {
            account = ((GoogleSettings) (obj)).getSmartMailMode();
            if (account != null)
            {
                if (account != com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode.IGNORE)
                {
                    account = "yes";
                } else
                {
                    account = "no";
                }
                setCustomDimension(context, 18, account);
            }
        }
        if (((GoogleSettings) (obj)).getBirthdayMode() == null) goto _L2; else goto _L4
_L4:
        ((GoogleSettings) (obj)).getBirthdayMode().ordinal();
        JVM INSTR tableswitch 0 2: default 208
    //                   0 267
    //                   1 260
    //                   2 253;
           goto _L5 _L6 _L7 _L8
_L5:
        account = "Invalid";
_L9:
        setCustomDimension(context, 19, account);
_L2:
        if (flag)
        {
            account = "ConsumerGoogle";
            obj = s;
        } else
        {
            account = "NonConsumerGoogle";
            obj = s;
        }
_L10:
        setCustomDimension(context, 20, ((String) (obj)));
        setCustomDimension(context, 21, account);
        return;
_L8:
        account = "None";
          goto _L9
_L7:
        account = "Contacts";
          goto _L9
_L6:
        account = "GooglePlus";
          goto _L9
        account = "NotGoogle";
        obj = "NotGoogle";
          goto _L10
    }

    public final void logClearcutEvent(com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype)
    {
        if (clearcutManager != null)
        {
            LogUtils.v("AnalyticsLogCalendarExt", "logClearcutEvent: %s", new Object[] {
                actiontype
            });
            ClearcutManager clearcutmanager = clearcutManager;
            byte abyte0[] = ClearcutManager.createExtensionProto(clearcutmanager.rlzConfig, actiontype).toByteArray();
            Account aaccount[] = AccountsUtil.getGoogleAccounts(clearcutmanager.context);
            if (aaccount.length == 0)
            {
                Object obj = clearcutmanager.calendarClearcutLogger;
                abyte0 = new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutmanager.clearcutLogger, abyte0);
                int i = actiontype.value;
                ((com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder) (abyte0)).zzaHz.eventCode = i;
                ((CalendarClearcutLogger) (obj)).logEvent(abyte0);
                obj = clearcutmanager.context;
                i = actiontype.value;
                try
                {
                    actiontype = GrowthKit.get(((Context) (obj)));
                }
                // Misplaced declaration of an exception variable
                catch (com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype)
                {
                    actiontype = null;
                }
                if (actiontype != null)
                {
                    actiontype.getGrowthKitEventManager().reportClearCutEventAsync(111, i, null);
                }
            } else
            {
                int k = aaccount.length;
                int j = 0;
                while (j < k) 
                {
                    Object obj1 = aaccount[j];
                    Object obj2 = new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutmanager.clearcutLogger, abyte0);
                    int l = actiontype.value;
                    ((com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder) (obj2)).zzaHz.eventCode = l;
                    clearcutmanager.calendarClearcutLogger.logEvent(((com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder) (obj2)), ((Account) (obj1)));
                    Context context = clearcutmanager.context;
                    l = actiontype.value;
                    obj2 = ((Account) (obj1)).name;
                    try
                    {
                        obj1 = GrowthKit.get(context);
                    }
                    catch (NullPointerException nullpointerexception)
                    {
                        nullpointerexception = null;
                    }
                    if (obj1 != null)
                    {
                        ((GrowthKitComponent) (obj1)).getGrowthKitEventManager().reportClearCutEventAsync(111, l, ((String) (obj2)));
                    }
                    j++;
                }
            }
        }
    }

    public final void logClearcutEvent(com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype, String s)
    {
        if (clearcutManager != null)
        {
            LogUtils.v("AnalyticsLogCalendarExt", "logClearcutEvent: %s %s", new Object[] {
                actiontype, s
            });
            clearcutManager.logAction(actiontype, s);
        }
    }

    public final void sendAdditionalEventsOnApplicationOpen(Context context, Iterable iterable)
    {
        Account aaccount[] = CalendarAccountsUtil.getSyncableAccounts(context);
        SimpleArrayMap simplearraymap = new SimpleArrayMap(aaccount.length);
        int k = aaccount.length;
        for (int i = 0; i < k; i++)
        {
            simplearraymap.put(aaccount[i], new ArrayList());
        }

        for (iterable = iterable.iterator(); iterable.hasNext();)
        {
            CalendarListEntry calendarlistentry = (CalendarListEntry)iterable.next();
            List list = (List)simplearraymap.get(calendarlistentry.getDescriptor().account);
            if (list != null)
            {
                list.add(calendarlistentry);
            } else
            {
                LogUtils.w("AnalyticsLogCalendarExt", "Calendar belonging to not syncable account", new Object[0]);
            }
        }

        for (int j = 0; j < simplearraymap.size(); j++)
        {
            addAccountTypeCustomDimensions(context, (Account)simplearraymap.mArray[j << 1]);
            setCalendarStatisticsDimensions(context, (Iterable)simplearraymap.mArray[(j << 1) + 1], false);
            trackEvent(context, "account_daily", "account_daily", "", null);
        }

    }

    public final void setAdditionalDimensionsForApplicationOpenEvent(Context context, Iterable iterable)
    {
        int j = 0;
        Account aaccount[] = CalendarAccountsUtil.getSyncableAccounts(context);
        int i1 = aaccount.length;
        int i = 0;
        int k;
        int l;
        for (k = 0; i < i1; k = l)
        {
            Account account = aaccount[i];
            l = k + 1;
            k = j;
            if ("com.google".equals(account.type))
            {
                k = j + 1;
            }
            i++;
            j = k;
        }

        setCustomDimension(context, 6, AnalyticsUtils.convertNumToDimensionValue(k, 10));
        setCustomDimension(context, 7, AnalyticsUtils.convertNumToDimensionValue(j, 10));
        String s = Gservices.getString(context.getContentResolver(), "device_country", null);
        if (s != null)
        {
            s = s.toLowerCase();
        } else
        {
            s = null;
        }
        if (s == null)
        {
            s = "null";
        }
        setCustomDimension(context, 12, s);
        if (((ActivityManager)context.getSystemService("activity")).isLowRamDevice())
        {
            s = "yes";
        } else
        {
            s = "no";
        }
        setCustomDimension(context, 13, s);
        if (ApplicationUtils.isSystemApp(context))
        {
            s = "preload";
        } else
        {
            s = "manual";
        }
        setCustomDimension(context, 33, s);
        setCalendarStatisticsDimensions(context, iterable, true);
    }

    public final void setCustomDimension(Context context, int i, String s)
    {
        LogUtils.v("AnalyticsLogCalendarExt", "Set Custom Dimensions: %d %s", new Object[] {
            Integer.valueOf(i), s
        });
        super.setCustomDimension(context, ANALYTICS_PROPERTY_ID, i, s);
    }

    public final void setCustomMetric(Context context, int i, long l)
    {
        LogUtils.v("AnalyticsLogCalendarExt", "Set Custom metric: %d %d", new Object[] {
            Integer.valueOf(i), Long.valueOf(l)
        });
        super.setCustomMetric(context, ANALYTICS_PROPERTY_ID, i, l);
    }

    public final void trackEvent(Context context, String s, String s1)
    {
        trackEvent(context, s, s1, "", null);
    }

    public final void trackEvent(Context context, String s, String s1, String s2, Long long1)
    {
        LogUtils.v("AnalyticsLogCalendarExt", "track event: %s %s %s %d", new Object[] {
            s, s1, s2, long1
        });
        String s3 = ANALYTICS_PROPERTY_ID;
        super.analytics.trackEvent(context, s3, s, s1, s2, long1);
    }

    public final void trackTiming(Context context, String s, long l, String s1, String s2)
    {
        LogUtils.v("AnalyticsLogCalendarExt", "track timing: %s %d %s %s", new Object[] {
            s, Long.valueOf(l), s1, s2
        });
        String s3 = ANALYTICS_PROPERTY_ID;
        super.analytics.trackTiming(context, s3, s, l, s1, s2);
    }

    public final void trackView(Context context, String s)
    {
        LogUtils.v("AnalyticsLogCalendarExt", "track view: %s", new Object[] {
            s
        });
        String s1 = ANALYTICS_PROPERTY_ID;
        super.analytics.trackScreenView(context, s1, s);
        logClearcutEvent(com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.APPLICATION_VIEW);
    }

    static 
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).public_release();
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).public_release();
            ANALYTICS_DEFAULT_SAMPLING_RATE = "10.0";
            CONSUMER_ACCOUNT_DOMAINS = ImmutableSet.construct(2, new Object[] {
                "@gmail.com", "@googlemail.com"
            });
        }
    }
}
