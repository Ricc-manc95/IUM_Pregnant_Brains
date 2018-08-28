// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Entity;
import android.content.PeriodicSync;
import android.content.SyncResult;
import android.content.SyncStats;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseLongArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.api.services.calendar.model.Event;
import com.google.common.base.Supplier;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            SyncHooksSyncMetadata, Utilities, AnalyticsLoggerExtensionFactory, DeviceIdleAndNetworkStatus, 
//            SyncAnalyticsLoggerExtension, CalendarSyncState, SyncLogSanitizer, FeedState, 
//            SyncLogFileUtils, CalendarSyncInfo

public class SyncLog
{

    public static final boolean LOG_TO_FILES;
    private static final Object START_STOP_LOG_LOCK;
    public static final boolean SYNC_LOG_ENABLED;
    public static final String TAG;
    public static final boolean WRITE_SYNC_HISTORY_TO_FILES;
    public static final boolean WRITE_SYNC_LOG_TO_FILES;
    public static int accountIndex;
    public static String accountType;
    private static String cachedDateString;
    public static String cachedStartTimeLog;
    public static String calendarAccess;
    public static int calendarId;
    public static String calendarName;
    public static String calendarType;
    public static String calendarVisibility;
    public static Context context;
    public static Integer currentPageSize;
    private static int deletionsInBatch;
    private static int deletionsInBatchPercent;
    private static int deletionsInEditableCalendars;
    private static String deletionsTopMutator;
    public static int depth;
    private static int entriesInEditableCalendars;
    private static final SimpleDateFormat format;
    public static Boolean isPageSizeDecreased;
    public static StringBuilder lineBuilder;
    public static int localDbQueries;
    public static long localDbQueryStart;
    public static long localDbTimeMs;
    public static int localDbTotalQueries;
    private static String massDeletionResolution;
    private static int processLocalCalendarsAttempts;
    private static int processLocalEventsAttempts;
    public static long remoteApiCallStart;
    public static int remoteApiCalls;
    public static long remoteApiTimeMs;
    public static int remoteApiTotalCalls;
    public static ThreadLocal startTimesMs;
    public static StringBuilder syncHistorySessionLog;
    public static StringBuilder syncLogSessionLog;
    public static long syncStartTimeMs;
    public static String syncType;
    public static String tooManyDeletionsResolution;
    private static int topEditableCalendarDeletions;
    private static int topEditableCalendarEntries;
    private static int totalDeletions;
    private static int totalDeletionsPercent;

    public SyncLog()
    {
    }

    private static boolean canContainSubTimings(int i)
    {
        switch (i)
        {
        default:
            LogUtils.wtf(TAG, "Unexpected timing type %d", new Object[] {
                Integer.valueOf(i)
            });
            // fall through

        case 0: // '\0'
            return true;

        case 1: // '\001'
        case 2: // '\002'
            return false;
        }
    }

    static void clearDeletionValues()
    {
        deletionsInBatch = 0;
        deletionsInBatchPercent = 0;
        totalDeletions = 0;
        totalDeletionsPercent = 0;
        deletionsInEditableCalendars = 0;
        entriesInEditableCalendars = 0;
        topEditableCalendarDeletions = 0;
        topEditableCalendarEntries = 0;
        deletionsTopMutator = null;
        massDeletionResolution = null;
    }

    private static String createErrorLogMessage(Throwable throwable, String s)
    {
        s = String.valueOf(s);
        String s1;
        if (s.length() != 0)
        {
            s = "ERROR\t".concat(s);
        } else
        {
            s = new String("ERROR\t");
        }
        s1 = s;
        if (throwable != null)
        {
            s = String.valueOf(s);
            throwable = Log.getStackTraceString(throwable);
            s1 = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(throwable).length())).append(s).append("\n").append(throwable).toString();
        }
        return s1;
    }

    private static transient String createLogLine(String as[])
    {
        boolean flag = false;
        lineBuilder.setLength(0);
        SimpleDateFormat simpledateformat = format;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        cachedDateString = simpledateformat.format(new Date(l));
        lineBuilder.append(cachedDateString).append(": ");
        for (int i = 0; i < depth; i++)
        {
            lineBuilder.append("\t");
        }

        int k = as.length;
        for (int j = ((flag) ? 1 : 0); j < k; j++)
        {
            String s = as[j];
            lineBuilder.append(s);
        }

        lineBuilder.append("\n");
        return lineBuilder.toString();
    }

    static void determineSyncType(String s, SyncHooksSyncMetadata synchookssyncmetadata, Bundle bundle, Account account, ContentProviderClient contentproviderclient)
    {
        Object obj1 = null;
        syncType = "DEVICE";
        Object obj;
        if (bundle.containsKey("feed") && bundle.getString("feed").startsWith("http"))
        {
            syncType = "TICKLE";
        } else
        if (bundle.getBoolean("sync_periodic", false))
        {
            syncType = "PERIODIC";
        } else
        if (bundle.getBoolean("sync_default", false))
        {
            syncType = "DEFAULT";
        } else
        if (bundle.getBoolean("moveWindow", false))
        {
            syncType = "MOVE-WINDOW";
        } else
        if (bundle.getBoolean("force", false))
        {
            syncType = "MANUAL";
        } else
        if (bundle.getBoolean("upload", false))
        {
            syncType = "UPLOAD";
        } else
        if (bundle.containsKey("feed_internal"))
        {
            syncType = "IN-APP";
        }
        if (synchookssyncmetadata != null)
        {
            String s1 = synchookssyncmetadata.syncTypePrefix;
            String s2 = syncType;
            syncType = (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s2).length())).append(s1).append("#").append(s2).toString();
            synchookssyncmetadata = synchookssyncmetadata.calendarId;
        } else
        {
            synchookssyncmetadata = null;
        }
        obj = synchookssyncmetadata;
        if (synchookssyncmetadata == null)
        {
            obj = Utilities.parseFeedId(s);
        }
        calendarName = ((String) (obj));
        if (Utilities.isGoogleConsumerAccount(account))
        {
            synchookssyncmetadata = "CONSUMER";
        } else
        {
            synchookssyncmetadata = "DASHER";
        }
        accountType = synchookssyncmetadata;
        synchookssyncmetadata = Arrays.asList(AccountsUtil.getGoogleAccounts(context));
        Collections.sort(synchookssyncmetadata, new _cls2());
        accountIndex = synchookssyncmetadata.indexOf(account);
        tooManyDeletionsResolution = null;
        if (bundle.getBoolean("deletions_override", false))
        {
            tooManyDeletionsResolution = "OVERRIDE";
        } else
        if (bundle.getBoolean("discard_deletions", false))
        {
            tooManyDeletionsResolution = "DISCARD";
        }
        if (obj == null)
        {
            synchookssyncmetadata = "NONE";
        } else
        {
label0:
            {
                if (!account.name.equalsIgnoreCase(((String) (obj))))
                {
                    break label0;
                }
                synchookssyncmetadata = "PRIMARY";
            }
        }
        calendarType = synchookssyncmetadata;
        if (s != null) goto _L2; else goto _L1
_L1:
        calendarVisibility = "NA";
        calendarAccess = "NA";
        calendarId = 0;
_L14:
        return;
_L2:
        calendarVisibility = "WTF";
        calendarAccess = "WTF";
        calendarId = -1;
        break MISSING_BLOCK_LABEL_512;
        switch (CalendarType.calculateType(((String) (obj))))
        {
        default:
            synchookssyncmetadata = "WTF";
            break;

        case 1: // '\001'
            synchookssyncmetadata = "BIRTHDAY";
            break;

        case 2: // '\002'
            synchookssyncmetadata = "HOLIDAY";
            break;

        case 0: // '\0'
            synchookssyncmetadata = "ROOM";
            break;

        case 4: // '\004'
            synchookssyncmetadata = "GROUP";
            break;

        case 5: // '\005'
            synchookssyncmetadata = "UNKNOWN";
            break;

        case 3: // '\003'
            synchookssyncmetadata = "BLACKLISTED";
            break;

        case 6: // '\006'
            synchookssyncmetadata = "INDIVIDUAL";
            break;
        }
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_203;
        }
        s = android.provider.CalendarContract.Calendars.CONTENT_URI;
        synchookssyncmetadata = ColumnConstants.WHERE_ACCOUNT_AND_TYPE;
        synchookssyncmetadata = (new StringBuilder(String.valueOf(synchookssyncmetadata).length() + 19)).append(synchookssyncmetadata).append(" AND ownerAccount").append("=?").toString();
        bundle = account.name;
        account = account.type;
        synchookssyncmetadata = contentproviderclient.query(s, new String[] {
            "_id", "calendar_access_level", "visible"
        }, synchookssyncmetadata, new String[] {
            bundle, account, obj
        }, null);
        if (synchookssyncmetadata == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        bundle = synchookssyncmetadata;
        if (synchookssyncmetadata.getCount() != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        bundle = synchookssyncmetadata;
        synchookssyncmetadata.moveToFirst();
        bundle = synchookssyncmetadata;
        calendarId = synchookssyncmetadata.getInt(0);
        bundle = synchookssyncmetadata;
        synchookssyncmetadata.getInt(1);
        JVM INSTR lookupswitch 9: default 859
    //                   0: 880
    //                   100: 873
    //                   200: 901
    //                   300: 908
    //                   400: 887
    //                   500: 783
    //                   600: 866
    //                   700: 894
    //                   800: 915;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L12:
        break MISSING_BLOCK_LABEL_915;
_L15:
        bundle = synchookssyncmetadata;
        calendarAccess = s;
        bundle = synchookssyncmetadata;
        if (synchookssyncmetadata.getInt(2) != 0)
        {
            s = "VISIBLE";
        } else
        {
            s = "INVISIBLE";
        }
        bundle = synchookssyncmetadata;
        calendarVisibility = s;
        if (synchookssyncmetadata == null) goto _L14; else goto _L13
_L13:
        synchookssyncmetadata.close();
        return;
_L9:
        s = "CONTRIBUTOR";
          goto _L15
        s;
        synchookssyncmetadata = null;
_L19:
        bundle = synchookssyncmetadata;
        LogUtils.e(TAG, s, "Getting feed info", new Object[0]);
        if (synchookssyncmetadata == null) goto _L14; else goto _L16
_L16:
        synchookssyncmetadata.close();
        return;
        s;
        synchookssyncmetadata = obj1;
_L18:
        if (synchookssyncmetadata != null)
        {
            synchookssyncmetadata.close();
        }
        throw s;
        s;
        synchookssyncmetadata = bundle;
        if (true) goto _L18; else goto _L17
_L17:
        s;
          goto _L19
_L3:
        s = "WTF";
          goto _L15
_L10:
        s = "EDITOR";
          goto _L15
_L5:
        s = "FREEBUSY";
          goto _L15
_L4:
        s = "NONE";
          goto _L15
_L8:
        s = "OVERRIDE";
          goto _L15
_L11:
        s = "OWNER";
          goto _L15
_L6:
        s = "READ";
          goto _L15
_L7:
        s = "RESPOND";
          goto _L15
        s = "ROOT";
          goto _L15
    }

    public static void endSync(SyncResult syncresult, boolean flag, DeviceIdleAndNetworkStatus deviceidleandnetworkstatus, Supplier supplier)
    {
        if (SYNC_LOG_ENABLED)
        {
            long l = SystemClock.elapsedRealtime() - syncStartTimeMs;
            String s;
            if (flag)
            {
                s = "CANCEL";
            } else
            if (syncresult.hasError())
            {
                s = "ERROR";
            } else
            if (syncresult.stats.numSkippedEntries > 0L)
            {
                s = "WARNING";
            } else
            {
                s = "SUCCESS";
            }
            if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
            }
            SyncAnalyticsLoggerExtension syncanalyticsloggerextension = AnalyticsLoggerExtensionFactory.analyticsLogger;
            SparseArray sparsearray = new SparseArray();
            sparsearray.put(24, s);
            if (isPageSizeDecreased != null)
            {
                sparsearray.put(28, Boolean.toString(isPageSizeDecreased.booleanValue()));
            }
            if (currentPageSize != null)
            {
                sparsearray.put(29, Integer.toString(currentPageSize.intValue()));
            }
            if (deviceidleandnetworkstatus != null)
            {
                if (deviceidleandnetworkstatus.hasNetwork != null)
                {
                    sparsearray.put(32, Boolean.toString(deviceidleandnetworkstatus.hasNetwork.booleanValue()));
                }
                if (deviceidleandnetworkstatus.isDeviceIdle != null)
                {
                    sparsearray.put(33, Boolean.toString(deviceidleandnetworkstatus.isDeviceIdle.booleanValue()));
                }
                if (deviceidleandnetworkstatus.isDeviceIdleLight != null)
                {
                    sparsearray.put(34, Boolean.toString(deviceidleandnetworkstatus.isDeviceIdleLight.booleanValue()));
                }
            }
            SparseLongArray sparselongarray = new SparseLongArray();
            sparselongarray.put(10, remoteApiTimeMs);
            sparselongarray.put(11, localDbTimeMs);
            sparselongarray.put(14, remoteApiTotalCalls);
            sparselongarray.put(15, localDbTotalQueries);
            if (processLocalCalendarsAttempts > 0)
            {
                sparselongarray.put(14, processLocalCalendarsAttempts);
            }
            if (processLocalEventsAttempts > 0)
            {
                sparselongarray.put(15, processLocalEventsAttempts);
            }
            if (deletionsInEditableCalendars > 0)
            {
                sparselongarray.put(12, entriesInEditableCalendars);
                sparselongarray.put(13, deletionsInEditableCalendars);
                sparselongarray.put(16, topEditableCalendarEntries);
                sparselongarray.put(17, topEditableCalendarDeletions);
                sparsearray.put(31, deletionsTopMutator);
            }
            if (!TextUtils.isEmpty(massDeletionResolution))
            {
                sparsearray.put(30, massDeletionResolution);
            }
            syncanalyticsloggerextension.logSyncLogEvent("End", syncType, l, syncresult, sparsearray, sparselongarray);
            if (WRITE_SYNC_LOG_TO_FILES)
            {
                depth = 0;
                logTimings(syncLogSessionLog, l);
                StringBuilder stringbuilder = syncLogSessionLog;
                String s1 = syncresult.stats.toString();
                Object obj;
                if (deviceidleandnetworkstatus != null)
                {
                    obj = String.valueOf(deviceidleandnetworkstatus);
                    obj = (new StringBuilder(String.valueOf(obj).length() + 3)).append(" - ").append(((String) (obj))).toString();
                } else
                {
                    obj = "";
                }
                obj = createLogLine(new String[] {
                    "Sync result", " - ", s, " - ", s1, obj
                });
                LogUtils.v(TAG, "%s", new Object[] {
                    obj
                });
                stringbuilder.append(((String) (obj)));
                obj = (CalendarSyncState)supplier.get();
                stringbuilder = syncLogSessionLog;
                if (obj == null)
                {
                    obj = "{}";
                } else
                {
                    obj = ((CalendarSyncState) (obj)).toString();
                }
                obj = createLogLine(new String[] {
                    "Calendar sync state", " - ", obj
                });
                LogUtils.v(TAG, "%s", new Object[] {
                    obj
                });
                stringbuilder.append(((String) (obj)));
                if (Utilities.writeToFile(context, "sync_log", syncLogSessionLog.toString(), 32768))
                {
                    syncLogSessionLog.setLength(0);
                } else
                {
                    Log.e(TAG, "Error saving sync log to file");
                }
            }
            if (WRITE_SYNC_HISTORY_TO_FILES)
            {
                supplier = (CalendarSyncState)supplier.get();
                obj = syncHistorySessionLog;
                Object obj2 = new SyncLogSanitizer(calendarId, calendarName);
                if (supplier == null)
                {
                    supplier = "{}";
                } else
                {
                    Object obj1 = Features.instance;
                    if (obj1 == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)obj1).anonymize_sync_history();
                    obj1 = new HashMap();
                    ((Map) (obj1)).put("version", Integer.valueOf(supplier.getVersion()));
                    ((Map) (obj1)).put("jellyBeanOrNewer", Boolean.valueOf(supplier.isJellyBean()));
                    ((Map) (obj1)).put("package", supplier.getSyncingPackage());
                    ((Map) (obj1)).put("firstSeen", Boolean.valueOf(supplier.isFirstSeen()));
                    ((Map) (obj1)).put("b38085245", Integer.valueOf(supplier.getHtcMailIssueRecoveryStage()));
                    String s2 = ((SyncLogSanitizer) (obj2)).calendarName;
                    if (((CalendarSyncState) (supplier)).data.has(s2))
                    {
                        int i = ((SyncLogSanitizer) (obj2)).calendarId;
                        obj2 = supplier.getFeedState(((SyncLogSanitizer) (obj2)).calendarName);
                        HashMap hashmap = new HashMap();
                        hashmap.put("do_incremental_sync", Boolean.valueOf(((FeedState) (obj2)).getBoolean("do_incremental_sync", false)));
                        hashmap.put("window_end", Long.valueOf(((FeedState) (obj2)).getLong("window_end", 0L)));
                        hashmap.put("new_window_end", Long.valueOf(((FeedState) (obj2)).getLong("new_window_end", 0L)));
                        hashmap.put("upgrade_min_start", Long.valueOf(((FeedState) (obj2)).getLong("upgrade_min_start", 0L)));
                        hashmap.put("upgrade_max_start", Long.valueOf(((FeedState) (obj2)).getLong("upgrade_max_start", 0L)));
                        if (((FeedState) (obj2)).getString("feed_updated_time", null) == null)
                        {
                            supplier = "";
                        } else
                        {
                            supplier = ((FeedState) (obj2)).getString("feed_updated_time", null);
                        }
                        hashmap.put("feed_updated_time", supplier);
                        hashmap.put("last_sync_time", Long.valueOf(((FeedState) (obj2)).getLong("last_sync_time", 0L)));
                        hashmap.put("in_progress_params", SyncLogSanitizer.getAnonymizedFeedDataMap(((FeedState) (obj2)).getInProgressParams("in_progress_params")));
                        ((Map) (obj1)).put(String.valueOf(i), hashmap);
                    }
                    supplier = obj1.toString();
                }
                supplier = createLogLine(new String[] {
                    "Calendar sync state", " - ", supplier
                });
                LogUtils.v(TAG, "%s", new Object[] {
                    supplier
                });
                ((StringBuilder) (obj)).append(supplier);
                logTimings(syncHistorySessionLog, l);
                supplier = syncHistorySessionLog;
                obj = syncresult.stats.toString();
                if (deviceidleandnetworkstatus != null)
                {
                    syncresult = String.valueOf(deviceidleandnetworkstatus);
                    syncresult = (new StringBuilder(String.valueOf(syncresult).length() + 3)).append(" - ").append(syncresult).toString();
                } else
                {
                    syncresult = "";
                }
                syncresult = createLogLine(new String[] {
                    "Sync result", " - ", s, " - ", obj, syncresult, "\n"
                });
                LogUtils.v(TAG, "%s", new Object[] {
                    syncresult
                });
                supplier.append(syncresult);
                if (Utilities.writeToFile(context, "sync_history", syncHistorySessionLog.toString(), 32768))
                {
                    syncHistorySessionLog.setLength(0);
                    return;
                } else
                {
                    LogUtils.e(TAG, "Couldn't save to sync history", new Object[0]);
                    return;
                }
            }
        }
    }

    private static void flushCachedStartTimeLog()
    {
        if (cachedStartTimeLog != null)
        {
            LogUtils.v(TAG, "%s", new Object[] {
                cachedStartTimeLog
            });
            syncLogSessionLog.append(cachedStartTimeLog);
            cachedStartTimeLog = null;
        }
    }

    public static byte[] getCombinedSyncHistoryByteArray(Context context1)
    {
        return SyncLogFileUtils.getCombinedLogs(context1, "sync_history", 40).toByteArray();
    }

    public static byte[] getCombinedSyncLogByteArray(Context context1)
    {
        return SyncLogFileUtils.getCombinedLogs(context1, "sync_log", 20).toByteArray();
    }

    static boolean hasDefaultPeriodic(Iterable iterable)
    {
        for (iterable = iterable.iterator(); iterable.hasNext();)
        {
            PeriodicSync periodicsync = (PeriodicSync)iterable.next();
            if (periodicsync.extras == null || periodicsync.extras.isEmpty())
            {
                return true;
            }
        }

        return false;
    }

    public static boolean hasSyncHistory(Context context1)
    {
        return context1.getFileStreamPath("sync_history").exists();
    }

    public static boolean hasSyncLog(Context context1)
    {
        return context1.getFileStreamPath("sync_log").exists();
    }

    static boolean hasTimelyPeriodic(Iterable iterable)
    {
        for (iterable = iterable.iterator(); iterable.hasNext();)
        {
            PeriodicSync periodicsync = (PeriodicSync)iterable.next();
            if (periodicsync.extras != null && periodicsync.extras.getBoolean("sync_periodic"))
            {
                return true;
            }
        }

        return false;
    }

    public static void initialize(Context context1)
    {
        context = context1;
    }

    public static void logDecreasePageSizeParams(boolean flag, int i)
    {
        isPageSizeDecreased = Boolean.valueOf(flag);
        currentPageSize = Integer.valueOf(i);
    }

    public static void logError(Throwable throwable, Entity entity, String s)
    {
        logError(throwable, entity, s, null);
    }

    private static void logError(Throwable throwable, Entity entity, String s, String s1)
    {
        SyncLogSanitizer synclogsanitizer = new SyncLogSanitizer(calendarId, calendarName);
        String s2 = synclogsanitizer.getDebugString(null, null, entity, false);
        s = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s2).length())).append(s).append(" ").append(s2).toString();
        LogUtils.e(TAG, throwable, "%s", new Object[] {
            s
        });
        logToSyncLog(new String[] {
            createErrorLogMessage(throwable, s)
        });
        s = Features.instance;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)s).anonymize_sync_history();
            entity = synclogsanitizer.getDebugString(null, null, entity, true);
            throwable = synclogsanitizer.getSanitizedThrowable(throwable);
            s = String.valueOf(s1);
            logToSyncHistory(new String[] {
                createErrorLogMessage(throwable, (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(entity).length())).append(s).append(" ").append(entity).toString())
            });
            return;
        }
    }

    public static void logError(Throwable throwable, String s)
    {
        LogUtils.e(TAG, throwable, "%s", new Object[] {
            s
        });
        logToSyncLog(new String[] {
            createErrorLogMessage(throwable, s)
        });
        logToSyncHistory(new String[] {
            createErrorLogMessage((new SyncLogSanitizer(calendarId, calendarName)).getSanitizedThrowable(throwable), "Something went wrong")
        });
    }

    public static transient void logError(Throwable throwable, String s, Object aobj[])
    {
        logError(throwable, String.format(null, s, aobj));
    }

    public static void logErrorType(Throwable throwable, Entity entity, String s)
    {
        logError(throwable, entity, s, s);
    }

    public static void logErrorType(Throwable throwable, CalendarSyncInfo calendarsyncinfo, Event event, Entity entity, String s)
    {
        SyncLogSanitizer synclogsanitizer = new SyncLogSanitizer(calendarId, calendarName);
        Object obj = synclogsanitizer.getDebugString(calendarsyncinfo, event, entity, false);
        obj = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(obj).length())).append(s).append(" ").append(((String) (obj))).toString();
        LogUtils.e(TAG, throwable, "%s", new Object[] {
            obj
        });
        logToSyncLog(new String[] {
            createErrorLogMessage(throwable, ((String) (obj)))
        });
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)obj).anonymize_sync_history();
            calendarsyncinfo = synclogsanitizer.getDebugString(calendarsyncinfo, event, entity, true);
            logToSyncHistory(new String[] {
                createErrorLogMessage(synclogsanitizer.getSanitizedThrowable(throwable), (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(calendarsyncinfo).length())).append(s).append(" ").append(calendarsyncinfo).toString())
            });
            return;
        }
    }

    static transient void logInternal(StringBuilder stringbuilder, String as[])
    {
        as = createLogLine(as);
        LogUtils.v(TAG, "%s", new Object[] {
            as
        });
        stringbuilder.append(as);
    }

    private static void logTimings(StringBuilder stringbuilder, long l)
    {
        String s = createLogLine(new String[] {
            "Total remote API time", " - ", Long.toString(remoteApiTimeMs), "ms"
        });
        LogUtils.v(TAG, "%s", new Object[] {
            s
        });
        stringbuilder.append(s);
        s = createLogLine(new String[] {
            "Total remote API calls", " - ", Long.toString(remoteApiTotalCalls)
        });
        LogUtils.v(TAG, "%s", new Object[] {
            s
        });
        stringbuilder.append(s);
        s = createLogLine(new String[] {
            "Total local DB time", " - ", Long.toString(localDbTimeMs), "ms"
        });
        LogUtils.v(TAG, "%s", new Object[] {
            s
        });
        stringbuilder.append(s);
        s = createLogLine(new String[] {
            "Total local DB queries", " - ", Long.toString(localDbTotalQueries)
        });
        LogUtils.v(TAG, "%s", new Object[] {
            s
        });
        stringbuilder.append(s);
        s = createLogLine(new String[] {
            "Total sync time", " - ", Long.toString(l), "ms"
        });
        LogUtils.v(TAG, "%s", new Object[] {
            s
        });
        stringbuilder.append(s);
    }

    public static transient void logToSyncHistory(String as[])
    {
        if (!WRITE_SYNC_HISTORY_TO_FILES)
        {
            return;
        } else
        {
            StringBuilder stringbuilder = syncHistorySessionLog;
            as = createLogLine(as);
            LogUtils.v(TAG, "%s", new Object[] {
                as
            });
            stringbuilder.append(as);
            return;
        }
    }

    public static transient void logToSyncLog(String as[])
    {
        if (!WRITE_SYNC_LOG_TO_FILES)
        {
            return;
        }
        synchronized (START_STOP_LOG_LOCK)
        {
            flushCachedStartTimeLog();
            StringBuilder stringbuilder = syncLogSessionLog;
            as = createLogLine(as);
            LogUtils.v(TAG, "%s", new Object[] {
                as
            });
            stringbuilder.append(as);
        }
        return;
        as;
        obj;
        JVM INSTR monitorexit ;
        throw as;
    }

    public static void saveDeletionValuesForEditableCalendars(int i, int j, int k, int l, String s)
    {
        deletionsInEditableCalendars = i;
        entriesInEditableCalendars = j;
        topEditableCalendarDeletions = k;
        topEditableCalendarEntries = l;
        deletionsTopMutator = s;
    }

    public static void saveMassDeletionResolution(String s)
    {
        massDeletionResolution = s;
    }

    public static void saveProcessLocalChangesAttempts(Integer integer, Integer integer1)
    {
        if (integer != null)
        {
            processLocalCalendarsAttempts = integer.intValue();
        }
        if (integer1 != null)
        {
            processLocalEventsAttempts = integer1.intValue();
        }
    }

    public static void start(String s)
    {
        int i = 0;
        if (SYNC_LOG_ENABLED) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        int j;
        if (s.startsWith("DB: "))
        {
            i = 1;
        } else
        if (s.startsWith("API: "))
        {
            i = 2;
        }
        if (!WRITE_SYNC_LOG_TO_FILES && i != 1 && i != 2) goto _L1; else goto _L3
_L3:
        obj = START_STOP_LOG_LOCK;
        obj;
        JVM INSTR monitorenter ;
        if (i != 2) goto _L5; else goto _L4
_L4:
        remoteApiTotalCalls++;
        j = remoteApiCalls + 1;
        remoteApiCalls = j;
        if (j != 1)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        remoteApiCallStart = SystemClock.elapsedRealtime();
_L7:
        if (WRITE_SYNC_LOG_TO_FILES)
        {
            ((Map)startTimesMs.get()).put(s, Long.valueOf(SystemClock.elapsedRealtime()));
            flushCachedStartTimeLog();
            if (canContainSubTimings(i))
            {
                cachedStartTimeLog = createLogLine(new String[] {
                    s, ": "
                });
                depth++;
            }
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
_L5:
        if (i != 1) goto _L7; else goto _L6
_L6:
        localDbTotalQueries++;
        j = localDbQueries + 1;
        localDbQueries = j;
        if (j != 1) goto _L7; else goto _L8
_L8:
        localDbQueryStart = SystemClock.elapsedRealtime();
          goto _L7
    }

    public static void stop(String s)
    {
        int i = 0;
        if (SYNC_LOG_ENABLED) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        Object obj1;
        int j;
        if (s.startsWith("DB: "))
        {
            i = 1;
        } else
        if (s.startsWith("API: "))
        {
            i = 2;
        }
        if (!WRITE_SYNC_LOG_TO_FILES && i != 2 && i != 1) goto _L1; else goto _L3
_L3:
        obj = START_STOP_LOG_LOCK;
        obj;
        JVM INSTR monitorenter ;
        if (i != 1) goto _L5; else goto _L4
_L4:
        j = localDbQueries - 1;
        localDbQueries = j;
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        localDbTimeMs += SystemClock.elapsedRealtime() - localDbQueryStart;
_L16:
        if (!WRITE_SYNC_LOG_TO_FILES) goto _L7; else goto _L6
_L6:
        obj1 = (Long)((Map)startTimesMs.get()).remove(s);
        if (obj1 != null) goto _L9; else goto _L8
_L8:
        obj1 = TAG;
        s = String.valueOf(s);
        if (s.length() == 0) goto _L11; else goto _L10
_L10:
        s = "No start time tracked for ".concat(s);
_L12:
        Log.e(((String) (obj1)), s);
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
_L5:
        if (i != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = remoteApiCalls - 1;
        remoteApiCalls = j;
        if (j != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        remoteApiTimeMs += SystemClock.elapsedRealtime() - remoteApiCallStart;
        continue; /* Loop/switch isn't completed */
_L11:
        s = new String("No start time tracked for ");
          goto _L12
_L9:
        long l;
        long l1;
        l = SystemClock.elapsedRealtime();
        l1 = ((Long) (obj1)).longValue();
        if (!canContainSubTimings(i))
        {
            break MISSING_BLOCK_LABEL_310;
        }
        depth--;
_L14:
        StringBuilder stringbuilder = syncLogSessionLog;
        s = createLogLine(new String[] {
            s, " - ", String.valueOf(l - l1), "ms"
        });
        LogUtils.v(TAG, "%s", new Object[] {
            s
        });
        stringbuilder.append(s);
        cachedStartTimeLog = null;
_L7:
        obj;
        JVM INSTR monitorexit ;
        return;
        flushCachedStartTimeLog();
        if (true) goto _L14; else goto _L13
_L13:
        if (true) goto _L16; else goto _L15
_L15:
    }

    static 
    {
        Object obj;
        int i;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        boolean flag = ((FeatureConfig)obj).sync_log_to_files();
        WRITE_SYNC_LOG_TO_FILES = flag;
        if (!flag)
        {
            obj = Features.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)obj).anonymize_sync_history();
        }
        WRITE_SYNC_HISTORY_TO_FILES = true;
        Locale alocale[];
        Locale locale;
        int j;
        if (WRITE_SYNC_LOG_TO_FILES || WRITE_SYNC_HISTORY_TO_FILES)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        LOG_TO_FILES = flag;
        SYNC_LOG_ENABLED = flag;
        TAG = com/google/android/syncadapters/calendar/SyncLog.getSimpleName();
        START_STOP_LOG_LOCK = new Object();
        alocale = Locale.getAvailableLocales();
        j = alocale.length;
        i = 0;
_L3:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_268;
        }
        locale = alocale[i];
        if (!Locale.ENGLISH.equals(locale)) goto _L2; else goto _L1
_L1:
        alocale = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        alocale.setTimeZone(TimeZone.getTimeZone("UTC"));
_L4:
        format = alocale;
        syncLogSessionLog = new StringBuilder();
        syncHistorySessionLog = new StringBuilder();
        lineBuilder = new StringBuilder();
        startTimesMs = new _cls1();
        depth = 0;
        remoteApiCalls = 0;
        remoteApiTotalCalls = 0;
        remoteApiCallStart = 0L;
        remoteApiTimeMs = 0L;
        localDbQueries = 0;
        localDbTotalQueries = 0;
        localDbQueryStart = 0L;
        localDbTimeMs = 0L;
        syncStartTimeMs = 0L;
        return;
_L2:
        i++;
          goto _L3
        alocale = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        alocale.setTimeZone(TimeZone.getTimeZone("UTC"));
          goto _L4
    }

    private class _cls2
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (Account)obj;
            obj1 = (Account)obj1;
            return ((Account) (obj)).name.compareTo(((Account) (obj1)).name);
        }

        _cls2()
        {
        }
    }


    private class _cls1 extends ThreadLocal
    {

        protected final Object initialValue()
        {
            return new HashMap();
        }

        _cls1()
        {
        }
    }

}
