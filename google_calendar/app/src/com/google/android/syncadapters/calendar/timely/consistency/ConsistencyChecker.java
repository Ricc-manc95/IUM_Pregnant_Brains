// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import com.google.android.calendar.utils.version.MncUtil;
import com.google.android.syncadapters.calendar.AnalyticsLoggerExtensionFactory;
import com.google.android.syncadapters.calendar.CalendarSyncState;
import com.google.android.syncadapters.calendar.FeedState;
import com.google.android.syncadapters.calendar.ProviderHelper;
import com.google.android.syncadapters.calendar.SyncAnalyticsLoggerExtension;
import com.google.android.syncadapters.calendar.Utilities;
import com.google.android.syncadapters.calendar.timely.DebugReportingConstants;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.common.base.Joiner;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.consistency:
//            EventComparator

public class ConsistencyChecker
{
    public static final class EventMapBuilder
        implements Callable
    {

        private final Callable fetcher;

        public final Object call()
            throws Exception
        {
            List list = (List)fetcher.call();
            if (list == null)
            {
                return null;
            }
            HashMap hashmap = new HashMap();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) 
            {
                Event event = (Event)iterator.next();
                String s;
                if (Platform.stringIsNullOrEmpty(event.recurringEventId))
                {
                    s = String.valueOf("E");
                    String s1 = String.valueOf(event.id);
                    if (s1.length() != 0)
                    {
                        s = s.concat(s1);
                    } else
                    {
                        s = new String(s);
                    }
                } else
                {
                    long l = ConsistencyChecker.getUtcMillis(event.originalStartTime);
                    s = event.recurringEventId;
                    s = (new StringBuilder(String.valueOf(s).length() + 22)).append("I").append(l).append("E").append(s).toString();
                }
                hashmap.put(s, event);
            }
            return hashmap;
        }

        public EventMapBuilder(Callable callable)
        {
            fetcher = callable;
        }
    }

    static final class Report extends GenericJson
    {

        public String account;
        public String appVersion;
        public Long deviceDate;
        public Long feedUpdatedTime;
        public final List inconsistencies = new ArrayList();
        public final Requests requests = new Requests();
        public final Statistics statistics = new Statistics();

        Report()
        {
            class Statistics extends GenericJson
            {

                public Long checkedEvents;
                public Long eventsToReport;
                public Long failedEvents;
                public Long inconsistentEvents;
                public Long overfetchedBackendEvents;
                public Long overfetchedClientEvents;
                public Long skippedEvents;

                Statistics()
                {
                    checkedEvents = Long.valueOf(0L);
                    skippedEvents = Long.valueOf(0L);
                    inconsistentEvents = Long.valueOf(0L);
                    eventsToReport = Long.valueOf(0L);
                    overfetchedClientEvents = Long.valueOf(0L);
                    overfetchedBackendEvents = Long.valueOf(0L);
                    failedEvents = Long.valueOf(0L);
                }
            }

            deviceDate = Long.valueOf(-1L);
            feedUpdatedTime = Long.valueOf(-1L);
            class Requests
            {

                public List backendRequests;
                public List providerRequests;

                Requests()
                {
                }
            }

        }
    }


    private static final boolean IS_REPORTING_ENABLED;
    private static final JsonFactory JSON_FACTORY;
    private static final Map NO_CUSTOM_DIMENSIONS;
    private static final String NO_LABEL;
    public static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/timely/consistency/ConsistencyChecker);
    private final Account account;
    public final SyncAnalyticsLoggerExtension analyticsLogger;
    public final String calendarId;
    public final Context context;
    private long feedUpdatedTime;
    public final boolean manualCheck;
    private final CalendarSyncState syncState;
    public long timeMaxMs;
    public long timeMinMs;

    public ConsistencyChecker(Context context1, Account account1, String s, CalendarSyncState calendarsyncstate, boolean flag)
    {
        if (!account1.name.equals(s))
        {
            throw new IllegalArgumentException("NotImplementedYet: ConsistencyChecker only supports calendars with ids equal to the account name");
        }
        if (AnalyticsLoggerExtensionFactory.consistencyAnalyticsLogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        } else
        {
            analyticsLogger = AnalyticsLoggerExtensionFactory.consistencyAnalyticsLogger;
            context = context1;
            account = account1;
            calendarId = s;
            syncState = calendarsyncstate;
            manualCheck = flag;
            return;
        }
    }

    private final Report.Bucket getBucket(Event event, boolean flag, EventComparator.Result result)
    {
        class Report.Bucket extends GenericJson
        {

            public String category;
            public String classification;
            public List differentFields;
            public String eventRecurrence;
            public String eventTimeType;

            Report.Bucket()
            {
            }
        }

        String s;
        Report.Bucket bucket;
        boolean flag1;
        if (event.start.date != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag)
        {
            if (event.originalStartTime != null)
            {
                if (event.phantom.booleanValue())
                {
                    event = "RECURRING";
                } else
                {
                    event = "EXCEPTION";
                }
            } else
            {
                event = "SINGLE";
            }
        } else
        if (event.recurrence != null)
        {
            event = "RECURRING";
        } else
        if (event.originalStartTime != null)
        {
            event = "EXCEPTION";
        } else
        {
            event = "SINGLE";
        }
        bucket = new Report.Bucket();
        if (flag1)
        {
            s = "ALLDAY";
        } else
        {
            s = "TIMED";
        }
        bucket.eventTimeType = s;
        bucket.differentFields = result.differentFields;
        bucket.classification = result.inconsistencyClass;
        bucket.eventRecurrence = event;
        return bucket;
    }

    public static long getNextMidnightTimeMillis(long l)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        calendar.add(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    static long getUtcMillis(EventDateTime eventdatetime)
    {
        DateTime datetime = eventdatetime.dateTime;
        eventdatetime = eventdatetime.date;
        if (datetime != null)
        {
            eventdatetime = datetime;
        } else
        if (eventdatetime == null)
        {
            throw new NullPointerException("Both parameters are null");
        }
        return ((DateTime)eventdatetime).value;
    }

    private static void insertInconsistency(Report.Bucket bucket, Map map)
    {
        if (map.containsKey(bucket))
        {
            map.put(bucket, Long.valueOf(((Long)map.get(bucket)).longValue() + 1L));
            return;
        } else
        {
            map.put(bucket, Long.valueOf(1L));
            return;
        }
    }

    public static boolean isBatteryLevelSatisfactory(Intent intent)
    {
        int i = intent.getIntExtra("scale", -1);
        if (i <= 0)
        {
            LogUtils.i(TAG, "Unable to retrieve battery scale or wrong state. Still trying to check consistency.", new Object[0]);
        } else
        {
            int j = intent.getIntExtra("level", -1);
            if (j < 0)
            {
                LogUtils.i(TAG, "Unable to retrieve battery level or wrong state. Still trying to check consistency.", new Object[0]);
                return true;
            }
            if ((double)j / (double)i < 0.20000000000000001D)
            {
                LogUtils.i(TAG, "Battery level too low to perform consistency check.", new Object[0]);
                return false;
            }
        }
        return true;
    }

    private final boolean isOverfetched(Event event)
    {
        long l = getUtcMillis(event.start);
        long l1 = getUtcMillis(event.end);
        return l1 < timeMinMs || l != l1 && l1 == timeMinMs || l >= timeMaxMs;
    }

    private final void markEventsAsReported(Set set)
    {
        ArrayList arraylist = new ArrayList();
        Object obj = new ContentValues();
        ((ContentValues) (obj)).put("name", "cc:mark");
        ((ContentValues) (obj)).put("value", "1");
        for (set = set.iterator(); set.hasNext(); arraylist.add(ProviderHelper.asSyncAdapter(account).newInsert(android.provider.CalendarContract.ExtendedProperties.CONTENT_URI).withValues(((ContentValues) (obj))).build()))
        {
            ((ContentValues) (obj)).put("event_id", (String)set.next());
        }

        set = context;
        boolean flag;
        if (!MncUtil.isMnc())
        {
            flag = true;
        } else
        if (PermissionsUtil.checkSelfPermission(set, "android.permission.READ_CALENDAR") == 0 && PermissionsUtil.checkSelfPermission(set, "android.permission.WRITE_CALENDAR") == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.wtf(TAG, "Insufficient permissions", new Object[0]);
        }
        obj = context.getContentResolver().acquireContentProviderClient("com.android.calendar");
        ((ContentProviderClient) (obj)).applyBatch(arraylist);
_L2:
        ((ContentProviderClient) (obj)).release();
        return;
        set;
_L3:
        LogUtils.e(TAG, set, "Marking events as reported", new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
        set;
          goto _L3
    }

    private static void reportMissingEvent(Report report, Event event, Report.Bucket bucket, String s)
    {
        if (report == null)
        {
            return;
        } else
        {
            class Report.Inconsistency extends GenericJson
            {

                public Event backendEvent;
                public Report.Bucket bucket;
                public Event clientEvent;
                public Event event;

            Report.Inconsistency()
            {
            }
            }

            Report.Inconsistency inconsistency = new Report.Inconsistency();
            bucket.category = s;
            inconsistency.bucket = bucket;
            inconsistency.event = strippedEventForReport(event);
            report.inconsistencies.add(inconsistency);
            return;
        }
    }

    private final void requestOutputOfShortStatus(int i)
    {
        Intent intent = (new Intent(DebugReportingConstants.ACTION_SHORT_STATUS, null)).setClassName(context, "com.google.android.calendar.timely.report.DebugReportingReceiver").putExtra(DebugReportingConstants.EXTRA_STATUS_CODE, i).putExtra(DebugReportingConstants.EXTRA_CALENDAR_ID, calendarId);
        context.sendBroadcast(intent);
    }

    private static Event strippedEventForReport(Event event)
    {
        Event event1 = new Event();
        event1.id = event.id;
        event1.recurringEventId = event.recurringEventId;
        event1.etag = event.etag;
        event1.updated = event.updated;
        event1.status = event.status;
        event1.start = event.start;
        event1.originalStartTime = event.originalStartTime;
        event1.end = event.end;
        event1.recurrence = event.recurrence;
        event1.organizer = event.organizer;
        event1.location = event.location;
        return event1;
    }

    public static boolean wasLastCheckLongEnoughAgo(long l, SharedPreferences sharedpreferences)
    {
        long l1 = sharedpreferences.getLong("LAST_CONSISTENCY_CHECK_TIME", -1L);
        if (l1 != -1L && l1 + 0x240c8400L > l)
        {
            LogUtils.d(TAG, "Skipping store consistency checking. Last sync less than a week ago.", new Object[0]);
            return false;
        } else
        {
            return true;
        }
    }

    final void compareEventsIfBothFetched(Map map, Map map1, Report report)
    {
        if (map1 == null || map == null) goto _L2; else goto _L1
_L1:
        byte byte0;
        boolean flag;
        long l;
        if (AnalyticsLoggerExtensionFactory.appVersion == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        }
        report.appVersion = AnalyticsLoggerExtensionFactory.appVersion;
        report.account = calendarId;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        report.deviceDate = Long.valueOf(l);
        report.feedUpdatedTime = Long.valueOf(feedUpdatedTime);
        if (map == null)
        {
            LogUtils.w(TAG, "Client events map was null", new Object[0]);
        } else
        {
label0:
            {
                if (map1 != null)
                {
                    break label0;
                }
                LogUtils.w(TAG, "Backend events map was null", new Object[0]);
            }
        }
_L18:
        byte0 = 0;
_L19:
        if (byte0 == 0) goto _L4; else goto _L3
_L3:
        map1 = JSON_FACTORY;
        map = new ByteArrayOutputStream();
        map1 = map1.createJsonGenerator(map, Charsets.UTF_8);
        map1.enablePrettyPrint();
        map1.serialize(false, report);
        map1.flush();
        map = map.toString("UTF-8");
        if (!IS_REPORTING_ENABLED) goto _L6; else goto _L5
_L5:
        map1 = calendarId;
        if (map1 == null) goto _L8; else goto _L7
_L7:
        map1 = map1.toLowerCase();
        if (map1.endsWith("@google.com") || map1.endsWith("@altostrat.com") || map1.endsWith("@nestlabs.com"))
        {
            break MISSING_BLOCK_LABEL_2343;
        }
          goto _L8
_L20:
        if (flag) goto _L10; else goto _L9
_L9:
        if (!manualCheck) goto _L6; else goto _L10
_L10:
        if (!manualCheck) goto _L12; else goto _L11
_L11:
        LogUtils.d(TAG, "Trying to send report - %s - manual check", new Object[] {
            calendarId
        });
_L21:
        map1 = calendarId;
        if (Clock.mockedTimestamp <= 0L) goto _L14; else goto _L13
_L13:
        l = Clock.mockedTimestamp;
_L22:
        map1 = String.format(null, "%s_%s_%d.log", new Object[] {
            "consistency_report", map1, Long.valueOf(l)
        });
        Utilities.writeToFile(context, map1, map, 0);
        map = (new Intent(DebugReportingConstants.ACTION_NOTIFY_REPORT, Uri.parse(map1))).setClassName(context, "com.google.android.calendar.timely.report.DebugReportingReceiver").putExtra(DebugReportingConstants.EXTRA_CALENDAR_ID, calendarId);
        if (!context.getPackageManager().queryBroadcastReceivers(map, 0).isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L16; else goto _L15
_L15:
        try
        {
            context.sendBroadcast(map);
        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            LogUtils.e(TAG, map, "Converting report to String", new Object[0]);
        }
_L4:
        if (manualCheck)
        {
            Report.Statistics statistics;
            HashSet hashset;
            HashMap hashmap;
            Object obj;
            Object obj1;
            Object obj2;
            Object obj3;
            Object obj4;
            Report.Inconsistency inconsistency;
            long l1;
            if (report.statistics.failedEvents.longValue() > 0L)
            {
                byte0 = 2;
            } else
            if (byte0 != 0)
            {
                byte0 = 1;
            } else
            {
                byte0 = 0;
            }
            requestOutputOfShortStatus(byte0);
        }
_L2:
        return;
        statistics = report.statistics;
        hashmap = new HashMap();
        hashset = new HashSet();
        for (map = map.entrySet().iterator(); map.hasNext();)
        {
            obj1 = (java.util.Map.Entry)map.next();
            obj = (String)((java.util.Map.Entry) (obj1)).getKey();
            if (obj == null)
            {
                LogUtils.w(TAG, "Event with null key found in client events map.", new Object[0]);
            } else
            {
                obj1 = (Event)((java.util.Map.Entry) (obj1)).getValue();
                obj2 = ((Event) (obj1)).updated;
                if (obj2 != null)
                {
                    l = ((DateTime) (obj2)).value;
                } else
                {
                    l = 0x8000000000000000L;
                }
                if (((Integer)((GenericData) (obj1)).get("dirty")).intValue() != 0)
                {
                    byte0 = 1;
                } else
                {
                    byte0 = 0;
                }
                obj4 = (String)((GenericData) (obj1)).get("event_id");
                if (((Boolean)((GenericData) (obj1)).get("cc:mark")).booleanValue() || hashset.contains(obj4))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                ((GenericData) (obj1)).remove("event_id");
                ((GenericData) (obj1)).remove("cc:mark");
                obj2 = (Event)map1.remove(obj);
                if (!manualCheck && flag)
                {
                    statistics.skippedEvents = Long.valueOf(statistics.skippedEvents.longValue() + 1L);
                } else
                if (obj2 != null)
                {
                    obj3 = EventComparator.compareEvents(((Event) (obj1)), ((Event) (obj2)));
                    if (((EventComparator.Result) (obj3)).differentFields.isEmpty() && !((EventComparator.Result) (obj3)).shouldReport)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    l1 = ((Event) (obj2)).updated.value;
                    if (!flag)
                    {
                        if (l1 > feedUpdatedTime)
                        {
                            LogUtils.w(TAG, "Backend Event updated after feed update (+%d). Key = %s", new Object[] {
                                Long.valueOf(l1 - feedUpdatedTime), obj
                            });
                            statistics.failedEvents = Long.valueOf(statistics.failedEvents.longValue() + 1L);
                        } else
                        if (byte0 != 0)
                        {
                            LogUtils.w(TAG, "Client Event modified after sync (dirty). Key = %s", new Object[] {
                                obj
                            });
                            statistics.failedEvents = Long.valueOf(statistics.failedEvents.longValue() + 1L);
                        } else
                        if (l > feedUpdatedTime)
                        {
                            LogUtils.w(TAG, "Client Event updated after feed update time. (+%d) Key = %s", new Object[] {
                                Long.valueOf(l - feedUpdatedTime), obj
                            });
                            statistics.failedEvents = Long.valueOf(statistics.failedEvents.longValue() + 1L);
                        } else
                        {
                            hashset.add(obj4);
                            obj4 = getBucket(((Event) (obj1)), false, ((EventComparator.Result) (obj3)));
                            if (report != null)
                            {
                                inconsistency = new Report.Inconsistency();
                                obj4.category = "MismatchEventDifferent";
                                inconsistency.bucket = ((Report.Bucket) (obj4));
                                inconsistency.clientEvent = strippedEventForReport(((Event) (obj1)));
                                inconsistency.backendEvent = strippedEventForReport(((Event) (obj2)));
                                report.inconsistencies.add(inconsistency);
                            }
                            LogUtils.w(TAG, "Inconsistent event. Key = %s", new Object[] {
                                obj
                            });
                            insertInconsistency(((Report.Bucket) (obj4)), hashmap);
                            statistics.inconsistentEvents = Long.valueOf(statistics.inconsistentEvents.longValue() + 1L);
                            if (((EventComparator.Result) (obj3)).shouldReport)
                            {
                                statistics.eventsToReport = Long.valueOf(statistics.eventsToReport.longValue() + 1L);
                            }
                        }
                    }
                } else
                if (l > feedUpdatedTime)
                {
                    LogUtils.w(TAG, "Missing Client Event modified after feed update time. (+%d) Key = %s", new Object[] {
                        Long.valueOf(l - feedUpdatedTime), obj
                    });
                    statistics.failedEvents = Long.valueOf(statistics.failedEvents.longValue() + 1L);
                } else
                if (byte0 != 0)
                {
                    LogUtils.w(TAG, "Missing Client Event modified after sync (dirty). Key = %s", new Object[] {
                        obj
                    });
                    statistics.failedEvents = Long.valueOf(statistics.failedEvents.longValue() + 1L);
                } else
                if (isOverfetched(((Event) (obj1))))
                {
                    LogUtils.w(TAG, "Client-only Event appears overfetched. Key = %s", new Object[] {
                        obj
                    });
                    statistics.overfetchedClientEvents = Long.valueOf(statistics.overfetchedClientEvents.longValue() + 1L);
                } else
                {
                    hashset.add(obj4);
                    LogUtils.w(TAG, "Event for Key ( %s ) not found on the server", new Object[] {
                        obj
                    });
                    obj = getBucket(((Event) (obj1)), false, EventComparator.UNCLASSIFIED);
                    reportMissingEvent(report, ((Event) (obj1)), ((Report.Bucket) (obj)), "MismatchEventDeviceOnly");
                    insertInconsistency(((Report.Bucket) (obj)), hashmap);
                    statistics.inconsistentEvents = Long.valueOf(statistics.inconsistentEvents.longValue() + 1L);
                    statistics.eventsToReport = Long.valueOf(statistics.eventsToReport.longValue() + 1L);
                }
                statistics.checkedEvents = Long.valueOf(statistics.checkedEvents.longValue() + 1L);
            }
        }

        if (!map1.isEmpty())
        {
            for (map = map1.entrySet().iterator(); map.hasNext();)
            {
                obj = (java.util.Map.Entry)map.next();
                map1 = (String)((java.util.Map.Entry) (obj)).getKey();
                obj = (Event)((java.util.Map.Entry) (obj)).getValue();
                if (map1 == null)
                {
                    LogUtils.w(TAG, "Event with null key found in server events map.", new Object[0]);
                } else
                {
                    l = ((Event) (obj)).updated.value;
                    if (l > feedUpdatedTime)
                    {
                        LogUtils.d(TAG, "Missing Backend Event modified after sync (+%d). Key = %s", new Object[] {
                            Long.valueOf(l - feedUpdatedTime), map1
                        });
                        statistics.failedEvents = Long.valueOf(statistics.failedEvents.longValue() + 1L);
                    } else
                    {
                        if (((Event) (obj)).status != null && ((Event) (obj)).status.equals("cancelled"))
                        {
                            byte0 = 1;
                        } else
                        {
                            byte0 = 0;
                        }
                        if (byte0 != 0)
                        {
                            LogUtils.d(TAG, "Consistency check ignores cancelled event with key ( %s )", new Object[] {
                                map1
                            });
                        } else
                        if (isOverfetched(((Event) (obj))))
                        {
                            LogUtils.w(TAG, "Server-only Event appears overfetched. Key = %s", new Object[] {
                                map1
                            });
                            statistics.overfetchedBackendEvents = Long.valueOf(statistics.overfetchedBackendEvents.longValue() + 1L);
                        } else
                        {
                            LogUtils.w(TAG, "Event with key ( %s ) not found on a client", new Object[] {
                                map1
                            });
                            map1 = getBucket(((Event) (obj)), true, EventComparator.UNCLASSIFIED);
                            reportMissingEvent(report, ((Event) (obj)), map1, "MismatchEventServerOnly");
                            insertInconsistency(map1, hashmap);
                            statistics.inconsistentEvents = Long.valueOf(statistics.inconsistentEvents.longValue() + 1L);
                            statistics.eventsToReport = Long.valueOf(statistics.eventsToReport.longValue() + 1L);
                        }
                        statistics.checkedEvents = Long.valueOf(statistics.checkedEvents.longValue() + 1L);
                    }
                }
            }

        }
        if (!manualCheck && statistics.failedEvents.longValue() == 0L)
        {
            map1 = analyticsLogger;
            map = null;
            l = statistics.checkedEvents.longValue();
            if (true)
            {
                map = "";
            }
            map1.trackEvent("Consistency", "SessionDone", map, l, null);
            if (statistics.inconsistentEvents.longValue() > 0L)
            {
                map1 = analyticsLogger;
                map = null;
                l = statistics.inconsistentEvents.longValue();
                if (true)
                {
                    map = "";
                }
                map1.trackEvent("Consistency", "StoreInconsistent", map, l, null);
                for (map1 = hashmap.keySet().iterator(); map1.hasNext(); ((SyncAnalyticsLoggerExtension) (obj)).trackEvent("Consistency", ((String) (obj1)), map, l, ((Map) (obj2))))
                {
                    obj4 = (Report.Bucket)map1.next();
                    obj = analyticsLogger;
                    obj1 = ((Report.Bucket) (obj4)).category;
                    map = ((Report.Bucket) (obj4)).classification;
                    l = ((Long)hashmap.get(obj4)).longValue();
                    obj2 = new HashMap();
                    ((Map) (obj2)).put(Integer.valueOf(1), ((Report.Bucket) (obj4)).eventTimeType);
                    ((Map) (obj2)).put(Integer.valueOf(2), ((Report.Bucket) (obj4)).eventRecurrence);
                    obj3 = new Joiner(" ");
                    obj4 = ((Report.Bucket) (obj4)).differentFields.iterator();
                    ((Map) (obj2)).put(Integer.valueOf(5), ((Joiner) (obj3)).appendTo(new StringBuilder(), ((Iterator) (obj4))).toString());
                    if (map == null)
                    {
                        map = "";
                    }
                }

            }
            if (statistics.overfetchedClientEvents.longValue() > 0L)
            {
                map1 = analyticsLogger;
                map = null;
                l = statistics.overfetchedClientEvents.longValue();
                if (true)
                {
                    map = "";
                }
                map1.trackEvent("Consistency", "ClientOverFetch", map, l, null);
            }
            if (statistics.overfetchedBackendEvents.longValue() > 0L)
            {
                map1 = analyticsLogger;
                map = null;
                l = statistics.overfetchedBackendEvents.longValue();
                if (true)
                {
                    map = "";
                }
                map1.trackEvent("Consistency", "BackendOverFetch", map, l, null);
            }
            markEventsAsReported(hashset);
        } else
        if (!manualCheck)
        {
            registerAttemptAsFailed("FailedModifiedAfterSync", statistics.failedEvents.longValue(), 0L);
        }
        if (statistics.failedEvents.longValue() != 0L || statistics.eventsToReport.longValue() <= 0L && (!manualCheck || statistics.inconsistentEvents.longValue() <= 0L)) goto _L18; else goto _L17
_L17:
        byte0 = 1;
          goto _L19
_L8:
        flag = false;
          goto _L20
_L12:
        LogUtils.d(TAG, "Trying to send report - %s is corporate", new Object[] {
            calendarId
        });
          goto _L21
_L14:
        l = System.currentTimeMillis();
          goto _L22
_L16:
        context.deleteFile(map1);
          goto _L4
_L6:
label1:
        {
            if (!IS_REPORTING_ENABLED)
            {
                break label1;
            }
            LogUtils.d(TAG, "Not trying to send report - %s is not corporate", new Object[] {
                calendarId
            });
        }
          goto _L4
        LogUtils.d(TAG, "Not trying to send report - reporting disabled", new Object[0]);
          goto _L4
        flag = true;
          goto _L20
    }

    public final boolean fetchFeedUpdateTime()
    {
        if (syncState == null)
        {
            LogUtils.e(TAG, "Aborting check: SyncState == null", new Object[0]);
            return false;
        }
        Object obj = syncState.getFeedState(calendarId);
        if (obj == null)
        {
            LogUtils.e(TAG, "Aborting check: FeedState(%s) == null", new Object[] {
                LogUtils.sanitizeName(TAG, calendarId)
            });
            return false;
        }
        obj = ((FeedState) (obj)).getString("feed_updated_time", null);
        if (obj != null)
        {
            try
            {
                feedUpdatedTime = DateTime.parseRfc3339(((String) (obj))).value;
            }
            catch (NumberFormatException numberformatexception)
            {
                LogUtils.e(TAG, numberformatexception, "Aborting check: couldn't parse FEED_UPDATED_TIME = %s", new Object[] {
                    obj
                });
                return false;
            }
            return true;
        } else
        {
            LogUtils.e(TAG, "Aborting check: FEED_UPDATE_TIME == null", new Object[0]);
            return false;
        }
    }

    public final SharedPreferences getPerAccountPreferences()
    {
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences sharedpreferences1 = context.getApplicationContext().getSharedPreferences(String.valueOf(calendarId).concat("_preferences"), 0);
        if (!sharedpreferences1.contains("LAST_CONSISTENCY_SKIP_LOGGED"))
        {
            android.content.SharedPreferences.Editor editor = sharedpreferences1.edit();
            if (sharedpreferences.contains("TRIES_SINCE_LAST_SKIP_LOGGED"))
            {
                editor.putInt("TRIES_SINCE_LAST_SKIP_LOGGED", sharedpreferences.getInt("TRIES_SINCE_LAST_SKIP_LOGGED", 0));
            }
            if (sharedpreferences.contains("LAST_CONSISTENCY_CHECK_TIME"))
            {
                editor.putLong("LAST_CONSISTENCY_CHECK_TIME", sharedpreferences.getLong("LAST_CONSISTENCY_CHECK_TIME", -1L));
            }
            if (sharedpreferences.contains("LAST_CONSISTENCY_SKIP_LOGGED"))
            {
                editor.putLong("LAST_CONSISTENCY_SKIP_LOGGED", sharedpreferences.getLong("LAST_CONSISTENCY_SKIP_LOGGED", -1L));
            }
            editor.apply();
        }
        return sharedpreferences1;
    }

    public final boolean isWifiConnected()
    {
        Object obj;
        try
        {
            obj = (ConnectivityManager)context.getSystemService("connectivity");
        }
        catch (SecurityException securityexception)
        {
            LogUtils.e(TAG, securityexception, "android.permission.ACCESS_NETWORK_STATE not granted", new Object[0]);
            return false;
        }
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        LogUtils.w(TAG, "Skipping store consistency checking. Failed to retrieve connectivity manager.", new Object[0]);
        return false;
        obj = ((ConnectivityManager) (obj)).getNetworkInfo(1);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        LogUtils.w(TAG, "Skipping store consistency checking. Failed to retrieve network information.", new Object[0]);
        return false;
        if (((NetworkInfo) (obj)).isConnected())
        {
            break MISSING_BLOCK_LABEL_101;
        }
        LogUtils.d(TAG, "Skipping store consistency checking. WiFi not connected.", new Object[0]);
        return false;
        return true;
    }

    final void registerAttemptAsFailed(String s, long l, long l1)
    {
        if (manualCheck)
        {
            requestOutputOfShortStatus(2);
            return;
        }
        String s1;
        SharedPreferences sharedpreferences;
        SyncAnalyticsLoggerExtension syncanalyticsloggerextension;
        long l2;
        if (Clock.mockedTimestamp > 0L)
        {
            l2 = Clock.mockedTimestamp;
        } else
        {
            l2 = System.currentTimeMillis();
        }
        sharedpreferences = getPerAccountPreferences();
        syncanalyticsloggerextension = analyticsLogger;
        if (true)
        {
            s1 = "";
        } else
        {
            s1 = null;
        }
        syncanalyticsloggerextension.trackEvent("Consistency", s, s1, l, null);
        sharedpreferences.edit().putLong("LAST_CONSISTENCY_CHECK_TIME", (l2 - 0x240c8400L) + l1).apply();
    }

    static 
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            IS_REPORTING_ENABLED = ((FeatureConfig)featureconfig).dogfood_features();
            NO_LABEL = null;
            NO_CUSTOM_DIMENSIONS = null;
            JSON_FACTORY = new AndroidJsonFactory();
        }
    }
}
