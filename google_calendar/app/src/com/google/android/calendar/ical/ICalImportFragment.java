// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.NewViewScreenFactory;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalTimelineEvent, ICalEventOperation, ICalUtils, ICalDeleteFragment, 
//            ICalEventReader

public class ICalImportFragment extends Fragment
    implements OnLaunchDetailsHandler
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/ICalImportFragment);
    private ListenableFuture defaultCalendar;
    private ListenableFuture parsedEvents;
    public boolean showList;

    public ICalImportFragment()
    {
    }

    static final ListenableFuture lambda$scheduleRefresh$0$ICalImportFragment(List list)
        throws Exception
    {
        return ((ICalEventReader.OperationLoader)list.get(0)).loadEvents((CalendarListEntry)((Optional)list.get(1)).get());
    }

    private final void launchEventInfo(EventInfoAnimationData eventinfoanimationdata, TimelineItem timelineitem)
    {
        Object obj;
        int i;
        if (timelineitem instanceof ICalTimelineEvent)
        {
            i = ((ICalTimelineEvent)timelineitem).operation.getImportType();
        } else
        {
            i = -1;
        }
        if (i == 5 || i == 6)
        {
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            ICalUtils.showSnackbar(((android.app.Activity) (obj)), 0x7f1302f8, 1);
        }
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        eventinfoanimationdata = NewViewScreenFactory.createViewScreen(((Context) (obj)), timelineitem, eventinfoanimationdata, null);
        timelineitem = new _cls2();
        obj = CalendarExecutor.MAIN;
        if (timelineitem == null)
        {
            throw new NullPointerException();
        } else
        {
            eventinfoanimationdata.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(eventinfoanimationdata, timelineitem), ((java.util.concurrent.Executor) (obj)));
            return;
        }
    }

    public static ICalImportFragment newInstance(Uri uri)
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("calendar_uri", uri);
        uri = new ICalImportFragment();
        uri.setArguments(bundle);
        return uri;
    }

    final void launchICalEventInfo(EventInfoAnimationData eventinfoanimationdata, ICalEventOperation icaleventoperation)
    {
        if (icaleventoperation.canceled())
        {
            eventinfoanimationdata = new ICalDeleteFragment();
            Bundle bundle = new Bundle();
            if (icaleventoperation == null)
            {
                throw new NullPointerException();
            } else
            {
                bundle.putParcelable("operation", (Parcelable)icaleventoperation);
                eventinfoanimationdata.setArguments(bundle);
                eventinfoanimationdata.show(super.mFragmentManager, ICalDeleteFragment.TAG);
                return;
            }
        }
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        launchEventInfo(eventinfoanimationdata, icaleventoperation.toTimelineEvent(((Context) (obj))));
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Uri uri;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        uri = (Uri)getArguments().getParcelable("calendar_uri");
        if (uri == null)
        {
            throw new NullPointerException();
        }
        uri = (Uri)uri;
        ICalEventReader icaleventreader = new ICalEventReader();
        bundle = bundle.getContentResolver();
        parsedEvents = (FluentFuture)CalendarExecutor.DISK.submit(new ICalEventReader..Lambda._cls0(icaleventreader, bundle, uri));
        bundle = CalendarListEntryCache.instance;
        if (bundle == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            defaultCalendar = AbstractTransformFuture.create(((ListenableFutureCache)bundle).getValueAsync(), com.google.android.calendar.utils.defaultcalendar.DefaultCalendarUtils..Lambda._cls0.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            scheduleRefresh();
            return;
        }
    }

    public final void onLaunchDetails(TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata)
    {
        if (timelineitem instanceof ICalTimelineEvent)
        {
            launchICalEventInfo(eventinfoanimationdata, ((ICalTimelineEvent)timelineitem).operation);
            return;
        } else
        {
            launchEventInfo(eventinfoanimationdata, timelineitem);
            return;
        }
    }

    final void onLaunchEventFailed()
    {
        Object obj1 = null;
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Object obj;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            Toast.makeText(((Context) (obj)), 0x7f1302f9, 1).show();
            if (super.mHost == null)
            {
                obj = obj1;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            ((FragmentActivity) (obj)).finish();
        }
    }

    public final void scheduleRefresh()
    {
        class .Lambda._cls0
            implements AsyncFunction
        {

            public static final AsyncFunction $instance = new .Lambda._cls0();

            public final ListenableFuture apply(Object obj)
            {
                return ICalImportFragment.lambda$scheduleRefresh$0$ICalImportFragment((List)obj);
            }


            private .Lambda._cls0()
            {
            }
        }

        ListenableFuture listenablefuture = AbstractTransformFuture.create(new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(new ListenableFuture[] {
            parsedEvents, defaultCalendar
        }), true), .Lambda._cls0..instance, CalendarExecutor.DISK);
        _cls1 _lcls1 = new _cls1();
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (_lcls1 == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, _lcls1), calendarexecutor);
            return;
        }
    }


    private class _cls2
        implements FutureCallback
    {

        private final ICalImportFragment this$0;

        public final void onFailure(Throwable throwable)
        {
            onLaunchEventFailed();
        }

        public final void onSuccess(Object obj)
        {
            OverlayFragment overlayfragment = (OverlayFragment)obj;
            obj = ICalImportFragment.this;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            if (obj instanceof NewICalActivity)
            {
                obj = ICalImportFragment.this;
                if (((Fragment) (obj)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                }
                ((NewICalActivity)obj).showOverlayFragment("ViewScreenController", overlayfragment);
            }
        }

        _cls2()
        {
            this$0 = ICalImportFragment.this;
            super();
        }
    }


    private class _cls1
        implements FutureCallback
    {

        private final ICalImportFragment this$0;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e(ICalImportFragment.TAG, throwable, "Failed to parse events", new Object[0]);
            onLaunchEventFailed();
        }

        public final void onSuccess(Object obj)
        {
            ICalImportFragment icalimportfragment;
            List list;
label0:
            {
                Object obj1 = null;
                list = (List)obj;
                icalimportfragment = ICalImportFragment.this;
                boolean flag;
                if (!list.isEmpty())
                {
                    if (((Fragment) (icalimportfragment)).mHost != null && ((Fragment) (icalimportfragment)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break label0;
                    }
                }
                if (((Fragment) (icalimportfragment)).mHost != null && ((Fragment) (icalimportfragment)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if (((Fragment) (icalimportfragment)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
                    }
                    Toast.makeText(((Context) (obj)), 0x7f1302f9, 1).show();
                    if (((Fragment) (icalimportfragment)).mHost == null)
                    {
                        obj = obj1;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
                    }
                    ((FragmentActivity) (obj)).finish();
                }
                return;
            }
            if (((Fragment) (icalimportfragment)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
            }
            if (obj instanceof NewICalActivity)
            {
                if (((Fragment) (icalimportfragment)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
                }
                ((NewICalActivity)obj).findViewById(0x7f10010c).setVisibility(8);
            }
            if (icalimportfragment.showList || list.size() > 1)
            {
                obj = (Uri)icalimportfragment.getArguments().getParcelable("calendar_uri");
                if (obj == null)
                {
                    throw new NullPointerException();
                } else
                {
                    obj = ICalEventListFragment.newInstance((Uri)obj, list);
                    ((Fragment) (icalimportfragment)).mFragmentManager.beginTransaction().replace(0x1020002, ((Fragment) (obj)), ICalEventListFragment.TAG).commitAllowingStateLoss();
                    icalimportfragment.showList = true;
                    return;
                }
            } else
            {
                icalimportfragment.launchICalEventInfo(null, (ICalEventOperation)list.get(0));
                return;
            }
        }

        _cls1()
        {
            this$0 = ICalImportFragment.this;
            super();
        }
    }

}
