// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.net.TrafficStats;
import android.text.format.Time;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.FeedFetcher;
import com.google.android.calendar.time.clock.Clock;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventReminder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            FeedState, Utils, SyncLog

class EventFeedFetcher extends FeedFetcher
{
    static class EventQueue extends LinkedBlockingQueue
    {

        private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/EventFeedFetcher$EventQueue);
        public EventFeedFetcher fetcher;

        public void put(Object obj)
            throws InterruptedException
        {
            obj = (Event)obj;
            Event event;
            if (fetcher != null)
            {
                event = (Event)((GenericData) (obj)).set("EventFeedFetcher.requestParams", fetcher.paramsOfCurrentlyProcessedRequest);
            } else
            {
                LogUtils.wtf(TAG, "Fetcher not set on EventQueue.", new Object[0]);
            }
            super.put(obj);
        }


        EventQueue(int i)
        {
            super(i);
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/EventFeedFetcher);
    public String accessRole;
    public final List defaultAllDayReminders = new ArrayList();
    public volatile List defaultReminders;
    private final FeedState feedSyncState;
    public volatile String lastUpdated;
    private final int maxAttendees;
    private final int maxResults;
    public Map paramsOfCurrentlyProcessedRequest;
    private final int threadStatsTag;
    public volatile String timeZone;

    public EventFeedFetcher(JsonFactory jsonfactory, com.google.api.services.calendar.Calendar.Events.List list, BlockingQueue blockingqueue, Event event, String s, FeedState feedstate, int i, 
            int j, int k)
    {
        super(jsonfactory, list, "items", com/google/api/services/calendar/model/Event, blockingqueue, event, s);
        defaultReminders = null;
        feedSyncState = feedstate;
        maxAttendees = i;
        maxResults = j;
        threadStatsTag = k;
        jsonfactory = feedSyncState.getInProgressParams("in_progress_params");
        if (jsonfactory != null)
        {
            LogUtils.v(TAG, "Resuming inProgress sync: %s", new Object[] {
                jsonfactory
            });
            Utils.setRequestFromMap(jsonfactory, list);
        } else
        {
            if (feedSyncState.getLong("upgrade_min_start", 0L) != 0L)
            {
                long l = feedSyncState.getLong("upgrade_min_start", 0L);
                long l2 = feedSyncState.getLong("upgrade_max_start", 0L);
                list.timeMin = new DateTime(l, 0);
                list.timeMax = new DateTime(l2, 0);
                list.supportsAllDayReminders = Boolean.valueOf(true);
                list.maxAttendees = Integer.valueOf(maxAttendees);
                return;
            }
            long l1 = feedSyncState.getLong("window_end", 0L);
            long l3 = feedSyncState.getLong("new_window_end", 0L);
            boolean flag;
            boolean flag1;
            if (l1 > 0L)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (l3 != 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            jsonfactory = feedSyncState;
            list.maxResults = Integer.valueOf(maxResults);
            flag1 = jsonfactory.getBoolean("do_incremental_sync", false);
            jsonfactory = jsonfactory.getString("feed_updated_time", null);
            LogUtils.v(TAG, "EventFeedFetcher#setBaseParams doIncSync: %b noInc: %b feedUpdTime: %s", new Object[] {
                Boolean.valueOf(flag1), Boolean.valueOf(flag), jsonfactory
            });
            if (flag1 && !flag && jsonfactory != null)
            {
                list.updatedMin = DateTime.parseRfc3339(jsonfactory);
            }
            list.maxAttendees = Integer.valueOf(maxAttendees);
            list.supportsAllDayReminders = Boolean.valueOf(true);
            if (list.updatedMin != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (!flag)
            {
                if (i != 0)
                {
                    list.timeMax = new DateTime(l1, 0);
                }
                if (j == 0)
                {
                    jsonfactory = new Time("UTC");
                    if (Clock.mockedTimestamp > 0L)
                    {
                        l1 = Clock.mockedTimestamp;
                    } else
                    {
                        l1 = System.currentTimeMillis();
                    }
                    jsonfactory.set(l1);
                    jsonfactory.year = ((Time) (jsonfactory)).year - 1;
                    jsonfactory.normalize(true);
                    list.timeMin = new DateTime(jsonfactory.toMillis(true), 0);
                    return;
                }
            } else
            {
                list.timeMin = new DateTime(l1, 0);
                list.timeMax = new DateTime(l3, 0);
                return;
            }
        }
    }

    public static Map getRequestParamsForEvent(Event event)
    {
        return (Map)event.get("EventFeedFetcher.requestParams");
    }

    protected final HttpResponse executeUnparsed(AbstractGoogleJsonClientRequest abstractgooglejsonclientrequest)
        throws IOException
    {
        SyncLog.start("API: Get Events List");
        abstractgooglejsonclientrequest = super.executeUnparsed(abstractgooglejsonclientrequest);
        SyncLog.stop("API: Get Events List");
        return abstractgooglejsonclientrequest;
        abstractgooglejsonclientrequest;
        SyncLog.stop("API: Get Events List");
        throw abstractgooglejsonclientrequest;
    }

    protected final void onExecute(AbstractGoogleJsonClientRequest abstractgooglejsonclientrequest)
    {
        paramsOfCurrentlyProcessedRequest = Utils.getMapFromRequest(abstractgooglejsonclientrequest);
    }

    protected final boolean parseField(JsonParser jsonparser, String s)
        throws IOException, InterruptedException
    {
        boolean flag = false;
        if (s.equals("timeZone"))
        {
            timeZone = (String)jsonparser.parse(java/lang/String, false, null);
            flag = true;
        } else
        {
            if (s.equals("updated"))
            {
                lastUpdated = (String)jsonparser.parse(java/lang/String, false, null);
                return true;
            }
            if (s.equals("defaultReminders"))
            {
                defaultReminders = new ArrayList();
                jsonparser.parseArray(null, defaultReminders, com/google/api/services/calendar/model/EventReminder, new ArrayList(), null);
                return true;
            }
            if (s.equals("defaultAllDayReminders"))
            {
                jsonparser.parseArray(null, defaultAllDayReminders, com/google/api/services/calendar/model/EventReminder, new ArrayList(), null);
                return true;
            }
            if (s.equals("accessRole"))
            {
                accessRole = (String)jsonparser.parse(java/lang/String, false, null);
                return true;
            }
        }
        return flag;
    }

    public void run()
    {
        TrafficStats.setThreadStatsTag(threadStatsTag | 4);
        super.run();
        TrafficStats.incrementOperationCount(threadStatsTag | 4, 1);
    }

}
