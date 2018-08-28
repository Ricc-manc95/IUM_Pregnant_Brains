// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.V2AEventsApi;
import com.google.android.apps.calendar.timebox.cp.CpEventsApi;
import com.google.android.apps.calendar.timebox.cp.EventsQueryInfo;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.android.calendar.AlternateTimelineUtils;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.timely.EventRangedQueryHandler;
import com.google.android.calendar.timely.RangedData;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.common.util.concurrent.FluentFuture;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.search:
//            OnSearchQueryFinishedListener, TaskSearchHandler

public final class SearchQueryHandler extends EventRangedQueryHandler
    implements OnSearchQueryFinishedListener
{

    public boolean eventSearchDone;
    private SearchResults eventSearchResults;
    private Handler handler;
    public OnSearchQueryFinishedListener searchOnQueryFinishedListener;
    public String searchQuery;
    public boolean taskSearchDone;
    public final TaskSearchHandler taskSearchHandler;
    private SearchResults taskSearchResults;

    SearchQueryHandler(Context context)
    {
        super(context, false);
        handler = new Handler();
        if (AlternateTimelineUtils.isDataFactoryEnabled(context))
        {
            context = null;
        } else
        {
            context = new TaskSearchHandler(context);
        }
        taskSearchHandler = context;
    }

    private final void processEventAndTaskResults()
    {
        final SearchResults results = new SearchResults(eventSearchResults, taskSearchResults);
        if (searchOnQueryFinishedListener != null)
        {
            handler.post(new _cls1());
        }
    }

    protected final com.google.android.calendar.timely.RangedData.EventResults createStorage(int i)
    {
        return new SearchResults(i);
    }

    protected final FluentFuture getQueryFuture(RangedData rangeddata, EventsApiImpl eventsapiimpl, boolean flag)
    {
        boolean flag1 = true;
        int i = rangeddata.getStartDay();
        int j = rangeddata.getEndDay();
        Object obj = searchQuery;
        boolean flag2;
        if (eventsapiimpl.v2aEventsApi != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            rangeddata = eventsapiimpl.v2aEventsApi.getAsync(i, j, null, new com.google.android.apps.calendar.timebox.V2AEventsApi..Lambda._cls11(flag, new com.google.android.apps.calendar.timebox.V2AEventsApi.SearchFilter(((String) (obj)))));
        } else
        {
            rangeddata = Collections.emptyList();
            if (rangeddata == null)
            {
                rangeddata = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                rangeddata = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(rangeddata);
            }
        }
        eventsapiimpl = eventsapiimpl.cpEventsApi;
        if (i > j)
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException();
        }
        android.net.Uri.Builder builder = android.provider.CalendarContract.Instances.CONTENT_SEARCH_BY_DAY_URI.buildUpon();
        ContentUris.appendId(builder, i);
        ContentUris.appendId(builder, j);
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            builder.appendPath((String)obj);
            obj = builder.build();
            obj = eventsapiimpl.queryAsync(((CpEventsApi) (eventsapiimpl)).context, ((Uri) (obj)), EventsQueryInfo.getInstancesSelection(flag, flag2), null, null);
            eventsapiimpl = ApiOperation.EVENT_INSTANCES_SEARCH;
            rangeddata = CalendarFutures.transform(rangeddata, ((com.google.common.util.concurrent.ListenableFuture) (obj)), com.google.android.apps.calendar.timebox.EventsApiImpl..Lambda._cls3.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            return (FluentFuture)MetricUtils.withMetrics(new com.google.common.base.Functions.ConstantFunction(com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS), rangeddata, eventsapiimpl);
        }
    }

    public final void onSearchQueryFinished(SearchResults searchresults)
    {
        taskSearchDone = true;
        taskSearchResults = searchresults;
        if (eventSearchDone)
        {
            processEventAndTaskResults();
        }
    }

    protected final void processResults(RangedData rangeddata, com.google.android.calendar.timely.RangedData.EventResults eventresults)
    {
        eventSearchDone = true;
        eventSearchResults = (SearchResults)eventresults;
        if (taskSearchDone)
        {
            processEventAndTaskResults();
        }
    }

    private class SearchResults
        implements com.google.android.calendar.timely.RangedData.EventResults
    {

        public List items;

        public final void addTimelineItem(TimelineItem timelineitem)
        {
            items.add(timelineitem);
        }

        SearchResults(int i)
        {
            items = new ArrayList(i);
        }

        SearchResults(SearchResults searchresults, SearchResults searchresults1)
        {
            int j = 0;
            super();
            int i;
            if (searchresults != null)
            {
                i = searchresults.items.size();
            } else
            {
                i = 0;
            }
            if (searchresults1 != null)
            {
                j = searchresults1.items.size();
            }
            i = j + i;
            if (i == 0)
            {
                items = null;
            } else
            {
                items = new ArrayList(i);
                if (searchresults != null)
                {
                    items.addAll(searchresults.items);
                }
                if (searchresults1 != null)
                {
                    items.addAll(searchresults1.items);
                    return;
                }
            }
        }

        public SearchResults(Collection collection)
        {
            items = new ArrayList(collection);
        }
    }


    private class _cls1
        implements Runnable
    {

        private final SearchQueryHandler this$0;
        private final SearchResults val$results;

        public final void run()
        {
            searchOnQueryFinishedListener.onSearchQueryFinished(results);
        }

        _cls1()
        {
            this$0 = SearchQueryHandler.this;
            results = searchresults;
            super();
        }
    }

}
