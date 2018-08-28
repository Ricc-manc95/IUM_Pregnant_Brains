// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.timebox.task.TasksApi;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.SearchActivity;
import com.google.android.calendar.Utils;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.EventRangedQueryHandler;
import com.google.android.calendar.timely.gridviews.ChipRecyclerManager;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.search:
//            OnSearchQueryFinishedListener, SearchQueryHandler, TaskSearchHandler, SearchResultsAdapter

public final class SearchFragment extends Fragment
    implements OnSearchQueryFinishedListener
{

    public final Runnable homeTimeUpdater = new _cls1();
    private LinearLayoutManager layoutManager;
    private View noResultsLabel;
    public RecyclerView recyclerView;
    private SearchQueryHandler searchQueryHandler;
    private String searchingText;
    private SwipeRefreshLayout swipeRefreshLayout;

    public SearchFragment()
    {
    }

    public final void doSearch()
    {
        noResultsLabel.setVisibility(8);
        Object obj;
        String s;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        s = ((SearchActivity)obj).query;
        if (s == null)
        {
            return;
        }
        swipeRefreshLayout.announceForAccessibility(searchingText);
        swipeRefreshLayout.setRefreshing(true);
        obj = searchQueryHandler;
        obj.searchQuery = s;
        obj.eventSearchDone = false;
        obj.taskSearchDone = false;
        ((EventRangedQueryHandler) (obj)).refreshData(new SearchQueryHandler.SearchData(), 0);
        Object obj2 = ((SearchQueryHandler) (obj)).searchQuery;
        if (((SearchQueryHandler) (obj)).taskSearchHandler != null)
        {
            Object obj1 = ((SearchQueryHandler) (obj)).taskSearchHandler;
            java.util.TimeZone timezone = ((TaskSearchHandler) (obj1)).timeZone;
            int i;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            i = TimeBoxUtil.msToJulianDay(timezone, l);
            obj2 = ((String) (obj2)).toLowerCase(Locale.getDefault());
            obj1 = (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(((TaskSearchHandler) (obj1)).api.getAsync(i - 366, i + 366, false), new TaskSearchHandler..Lambda._cls0(((String) (obj2))), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND)), new TaskSearchHandler..Lambda._cls1(((TaskSearchHandler) (obj1))), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND));
            obj = new TaskSearchHandler._cls1(((OnSearchQueryFinishedListener) (obj)));
            obj2 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
            if (obj == null)
            {
                throw new NullPointerException();
            } else
            {
                ((ListenableFuture) (obj1)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj1)), ((com.google.common.util.concurrent.FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj2)));
                return;
            }
        }
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        TaskDataFactory.instance.searchTasks(((String) (obj2)), ((OnSearchQueryFinishedListener) (obj)));
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        searchQueryHandler = new SearchQueryHandler(((Context) (obj)));
        searchQueryHandler.searchOnQueryFinishedListener = this;
        if (bundle == null || bundle.containsKey("bundleRotation"))
        {
            doSearch();
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        Utils.getTimeZoneId(bundle, homeTimeUpdater);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        bundle = null;
        ViewGroup viewgroup1 = (ViewGroup)layoutinflater.inflate(0x7f050154, null);
        recyclerView = (RecyclerView)viewgroup1.findViewById(0x7f10035c);
        RecyclerView recyclerview = recyclerView;
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        if (super.mHost == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = (FragmentActivity)super.mHost.mActivity;
        }
        recyclerview.setAdapter(new SearchResultsAdapter(layoutinflater, ChipRecyclerManager.getOrCreateRecycler(viewgroup)));
        if (super.mHost == null)
        {
            layoutinflater = bundle;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        layoutManager = new LinearLayoutManager(layoutinflater);
        recyclerView.setLayoutManager(layoutManager);
        noResultsLabel = viewgroup1.findViewById(0x7f10035b);
        swipeRefreshLayout = (SwipeRefreshLayout)viewgroup1.findViewById(0x7f100114);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setColorSchemeResources(new int[] {
            0x7f0d02fa, 0x7f0d02fb, 0x7f0d02fc, 0x7f0d02fd
        });
        searchingText = requireContext().getResources().getString(0x7f130432);
        layoutinflater = recyclerView;
        viewgroup = new _cls2();
        if (((RecyclerView) (layoutinflater)).mScrollListeners == null)
        {
            layoutinflater.mScrollListeners = new ArrayList();
        }
        ((RecyclerView) (layoutinflater)).mScrollListeners.add(viewgroup);
        return viewgroup1;
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putBoolean("bundleRotation", true);
        super.onSaveInstanceState(bundle);
    }

    public final void onSearchQueryFinished(SearchQueryHandler.SearchResults searchresults)
    {
        swipeRefreshLayout.setRefreshing(false);
        SearchResultsAdapter searchresultsadapter;
        int i;
        if (searchresults == null)
        {
            searchresults = null;
        } else
        {
            searchresults = searchresults.items;
        }
        if (searchresults != null)
        {
            i = searchresults.size();
        } else
        {
            i = 0;
        }
        if (i > 0)
        {
            noResultsLabel.setVisibility(8);
        } else
        {
            noResultsLabel.setVisibility(0);
        }
        searchresultsadapter = (SearchResultsAdapter)recyclerView.mAdapter;
        searchresultsadapter.swapItemList(searchresults);
        ((android.support.v7.widget.RecyclerView.Adapter) (searchresultsadapter)).mObservable.notifyChanged();
        layoutManager.scrollToPositionWithOffset(searchresultsadapter.daysToItems.indexOfKey(searchresultsadapter.dayToScroll), 0);
        if (super.mHost == null)
        {
            searchresults = null;
        } else
        {
            searchresults = (FragmentActivity)super.mHost.mActivity;
        }
        if (searchresults != null && AccessibilityUtils.isAccessibilityEnabled(searchresults))
        {
            if (i > 0)
            {
                searchresults = searchresults.getResources().getQuantityString(0x7f120045, i, new Object[] {
                    Integer.valueOf(i)
                });
            } else
            {
                searchresults = searchresults.getResources().getString(0x7f130355);
            }
            recyclerView.announceForAccessibility(searchresults);
        }
    }

    private class _cls1
        implements Runnable
    {

        private final SearchFragment this$0;

        public final void run()
        {
            Object obj = SearchFragment.this;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            Utils.getTimeZoneId(((Context) (obj)), homeTimeUpdater);
            if (recyclerView != null)
            {
                recyclerView.mAdapter.mObservable.notifyChanged();
            }
        }

        _cls1()
        {
            this$0 = SearchFragment.this;
            super();
        }
    }


    private class _cls2 extends android.support.v7.widget.RecyclerView.OnScrollListener
    {

        public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
        {
            for (i = 0; i < recyclerview.getChildCount(); i++)
            {
                View view = recyclerview.getChildAt(i);
                if (view instanceof com.google.android.calendar.timely.DualTimelineGridFragment.SimpleOnScrollListener)
                {
                    ((com.google.android.calendar.timely.DualTimelineGridFragment.SimpleOnScrollListener)view).onScrolled(i);
                }
            }

        }

        _cls2()
        {
        }
    }

}
