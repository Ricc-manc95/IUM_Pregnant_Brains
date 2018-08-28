// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.newapi.common.loader.CalendarListLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.search.SearchResultsAdapter;
import com.google.android.calendar.timely.gridviews.ChipRecyclerManager;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Referenced classes of package com.google.android.calendar.ical:
//            NewICalActivity, ICalEventReader, ICalEventListController

public class ICalEventListFragment extends Fragment
    implements SingleChoiceDialogListener
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/ICalEventListFragment);
    public ICalEventListController controller;
    public EventClient eventClient;
    private ListenableFuture eventsFuture;
    public boolean importAllEnabled;

    public ICalEventListFragment()
    {
        importAllEnabled = true;
        eventClient = CalendarApi.Events;
    }

    static final ListenableFuture lambda$onCreate$0$ICalEventListFragment(ICalEventReader.OperationLoader operationloader, Optional optional)
    {
        return operationloader.loadEvents((CalendarListEntry)optional.get());
    }

    public static ICalEventListFragment newInstance(Uri uri, List list)
    {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("key_uri", uri);
        ICalEventListFragment icaleventlistfragment = new ICalEventListFragment();
        icaleventlistfragment.setArguments(bundle);
        if (list == null)
        {
            uri = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            uri = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(list);
        }
        icaleventlistfragment.eventsFuture = uri;
        return icaleventlistfragment;
    }

    public final void onActivityCreated(Bundle bundle)
    {
        final SearchResultsAdapter eventAdapter = null;
        super.onActivityCreated(bundle);
        Object obj;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        if (bundle instanceof NewICalActivity)
        {
            com.google.android.calendar.utils.recycler.Recycler recycler;
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            bundle = (NewICalActivity)bundle;
            bundle.setToolbarAsActionBarIfAble(false);
            bundle.initializeActionBar(false);
            bundle = bundle.getSupportActionBar();
            if (bundle != null)
            {
                bundle.setDisplayShowTitleEnabled(true);
                bundle.setTitle(0x7f130305);
                bundle.show();
            }
        }
        obj = (RecyclerView)super.mView.findViewById(0x7f100225);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        recycler = ChipRecyclerManager.getOrCreateRecycler(bundle);
        if (super.mHost == null)
        {
            bundle = eventAdapter;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        eventAdapter = new SearchResultsAdapter(bundle, recycler);
        ((RecyclerView) (obj)).setAdapter(eventAdapter);
        bundle = eventsFuture;
        eventAdapter = new _cls1();
        obj = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (eventAdapter == null)
        {
            throw new NullPointerException();
        } else
        {
            bundle.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(bundle, eventAdapter), ((java.util.concurrent.Executor) (obj)));
            return;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (eventsFuture == null)
        {
            bundle = new ICalEventReader();
            Object obj = getContext().getContentResolver();
            Uri uri = (Uri)getArguments().getParcelable("key_uri");
            bundle = (FluentFuture)CalendarExecutor.DISK.submit(new ICalEventReader..Lambda._cls0(bundle, ((android.content.ContentResolver) (obj)), uri));
            obj = CalendarListEntryCache.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            class .Lambda._cls0
                implements BiFunction
            {

                public static final BiFunction $instance = new .Lambda._cls0();

                public final Object apply(Object obj1, Object obj2)
                {
                    return ICalEventListFragment.lambda$onCreate$0$ICalEventListFragment((ICalEventReader.OperationLoader)obj1, (Optional)obj2);
                }


            private .Lambda._cls0()
            {
            }
            }

            eventsFuture = CalendarFutures.transformAsync(bundle, AbstractTransformFuture.create(((ListenableFutureCache)obj).getValueAsync(), com.google.android.calendar.utils.defaultcalendar.DefaultCalendarUtils..Lambda._cls0.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), .Lambda._cls0..instance, CalendarExecutor.BACKGROUND);
        }
    }

    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        menu.clear();
        menuinflater.inflate(0x7f150007, menu);
        menu.findItem(0x7f10042f).setEnabled(importAllEnabled);
        super.onCreateOptionsMenu(menu, menuinflater);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        setHasOptionsMenu(true);
        return layoutinflater.inflate(0x7f05009c, viewgroup, false);
    }

    public final void onDestroy()
    {
        super.onDestroy();
        eventsFuture.cancel(true);
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        obj = (com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.UiCalendarListEntry)obj;
        controller.onCalendarChosen(((com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.UiCalendarListEntry) (obj)).value());
    }

    public final boolean onOptionsItemSelected(MenuItem menuitem)
    {
        CalendarList calendarlist;
        calendarlist = null;
        if (menuitem.getItemId() != 0x7f10042f || !importAllEnabled)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        menuitem = controller;
        calendarlist = (CalendarList)((ICalEventListController) (menuitem)).writableCalendars.get();
        if (calendarlist == null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        List list;
        com.google.common.base.Predicate predicate;
        list = ((ICalEventListController) (menuitem)).operations;
        predicate = ICalEventListController..Lambda._cls1.$instance;
label0:
        {
            {
                if (list != null)
                {
                    break label0;
                }
                try
                {
                    throw new NullPointerException();
                }
                // Misplaced declaration of an exception variable
                catch (MenuItem menuitem) { }
                // Misplaced declaration of an exception variable
                catch (MenuItem menuitem) { }
                LogUtils.e(ICalEventListController.TAG, menuitem, "Error fetching writable calendars list", new Object[0]);
            }
            return true;
        }
        if (predicate != null)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        throw new NullPointerException();
        if (!Iterables.isEmpty(new com.google.common.collect.Iterables._cls4(list, predicate)))
        {
            break MISSING_BLOCK_LABEL_118;
        }
        menuitem.onCalendarChosen(null);
        continue; /* Loop/switch isn't completed */
        if (calendarlist.calendars.size() > 1)
        {
            ((ICalEventListController) (menuitem)).importAllView.showCalendarChooser(calendarlist);
            continue; /* Loop/switch isn't completed */
        }
        menuitem.onCalendarChosen(calendarlist.getDefaultEntry());
        if (true) goto _L2; else goto _L1
_L1:
        break MISSING_BLOCK_LABEL_155;
_L2:
        break MISSING_BLOCK_LABEL_79;
        if (menuitem.getItemId() == 0x102002c)
        {
            if (super.mHost == null)
            {
                menuitem = calendarlist;
            } else
            {
                menuitem = (FragmentActivity)super.mHost.mActivity;
            }
            menuitem.onBackPressed();
            return true;
        } else
        {
            return super.onOptionsItemSelected(menuitem);
        }
    }


    private class _cls1
        implements FutureCallback
    {

        private final ICalEventListFragment this$0;
        private final SearchResultsAdapter val$eventAdapter;

        public final void onFailure(Throwable throwable)
        {
            Object obj = null;
            throwable = ICalEventListFragment.this;
            boolean flag;
            if (((Fragment) (throwable)).mHost != null && ((Fragment) (throwable)).mAdded)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                throwable = ICalEventListFragment.this;
                if (((Fragment) (throwable)).mHost == null)
                {
                    throwable = null;
                } else
                {
                    throwable = (FragmentActivity)((Fragment) (throwable)).mHost.mActivity;
                }
                Toast.makeText(throwable, 0x7f1302f9, 1).show();
                throwable = ICalEventListFragment.this;
                if (((Fragment) (throwable)).mHost == null)
                {
                    throwable = obj;
                } else
                {
                    throwable = (FragmentActivity)((Fragment) (throwable)).mHost.mActivity;
                }
                throwable.finish();
            }
        }

        public final void onSuccess(Object obj)
        {
            List list = (List)obj;
            ICalEventListFragment icaleventlistfragment = ICalEventListFragment.this;
            obj = ICalEventListFragment.this;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            icaleventlistfragment.controller = new ICalEventListController(((Context) (obj)), list, eventAdapter, eventClient, new _cls2(), new _cls3());
        }

        _cls1()
        {
            this$0 = ICalEventListFragment.this;
            eventAdapter = searchresultsadapter;
            super();
        }

        private class _cls2
            implements ICalEventListController.ImportAllView
        {

            private final ICalEventListFragment this$0;

            public final void setEnabled(boolean flag)
            {
                Object obj = ICalEventListFragment.this;
                obj.importAllEnabled = flag;
                boolean flag1;
                if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    if (((Fragment) (obj)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    ((FragmentActivity) (obj)).invalidateOptionsMenu();
                }
            }

            public final void showCalendarChooser(CalendarList calendarlist)
            {
                new com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory();
                com.google.android.calendar.newapi.segment.calendar.UiCalendarUtils.CalendarPickerFactory.create(getContext(), calendarlist.calendars, ICalEventListFragment.this, -1).show(mFragmentManager, CalendarDialog.TAG);
            }

            _cls2()
            {
                this$0 = ICalEventListFragment.this;
                super();
            }
        }


        private class _cls3
            implements ICalEventListController.ImportAllCallback
        {

            private final ICalEventListFragment this$0;

            public final void onFailure()
            {
                Object obj = ICalEventListFragment.this;
                ICalEventListFragment icaleventlistfragment;
                boolean flag;
                if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if (((Fragment) (obj)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    ICalUtils.showSnackbar(((android.app.Activity) (obj)), 0x7f1302fc, 1);
                }
                icaleventlistfragment = ICalEventListFragment.this;
                if (((Fragment) (icaleventlistfragment)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (icaleventlistfragment)).mHost.mActivity;
                }
                if (obj instanceof com.google.android.calendar.newapi.screen.ics.IcsViewScreenController.DataSetChangedListener)
                {
                    if (((Fragment) (icaleventlistfragment)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (icaleventlistfragment)).mHost.mActivity;
                    }
                    ((com.google.android.calendar.newapi.screen.ics.IcsViewScreenController.DataSetChangedListener)obj).onDataSetChanged();
                }
            }

            public final void onSuccess()
            {
                Object obj = ICalEventListFragment.this;
                ICalEventListFragment icaleventlistfragment;
                boolean flag;
                if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if (((Fragment) (obj)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    ICalUtils.showSnackbar(((android.app.Activity) (obj)), 0x7f1302fd, 1);
                }
                icaleventlistfragment = ICalEventListFragment.this;
                if (((Fragment) (icaleventlistfragment)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (icaleventlistfragment)).mHost.mActivity;
                }
                if (obj instanceof com.google.android.calendar.newapi.screen.ics.IcsViewScreenController.DataSetChangedListener)
                {
                    if (((Fragment) (icaleventlistfragment)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (icaleventlistfragment)).mHost.mActivity;
                    }
                    ((com.google.android.calendar.newapi.screen.ics.IcsViewScreenController.DataSetChangedListener)obj).onDataSetChanged();
                }
            }

            _cls3()
            {
                this$0 = ICalEventListFragment.this;
                super();
            }
        }

    }

}
