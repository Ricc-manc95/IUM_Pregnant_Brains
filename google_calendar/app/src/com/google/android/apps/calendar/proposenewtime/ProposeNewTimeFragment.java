// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.floatingactionbutton.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.google.android.apps.calendar.proposenewtime.grid.ProposeNewTimeGridManager;
import com.google.android.apps.calendar.proposenewtime.grid.views.ProposeNewTimeGridDayView;
import com.google.android.apps.calendar.proposenewtime.grid.views.ProposeNewTimeGridViewFrame;
import com.google.android.apps.calendar.proposenewtime.net.ProposeNewTimeRendezvousClient;
import com.google.android.apps.calendar.proposenewtime.slab.ProposalChangeHandler;
import com.google.android.apps.calendar.proposenewtime.slab.ProposeNewTimePagerAdapter;
import com.google.android.apps.calendar.proposenewtime.slab.views.SlabItem;
import com.google.android.apps.calendar.proposenewtime.state.Attendee;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.StateHolder;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.apps.calendar.proposenewtime.utils.ProposeNewTimeUtils;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timely.PagedScrollView;
import com.google.android.calendar.timely.PinchZoomController;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.gridviews.ChipRecyclerManager;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.AttendeeAllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.gridviews.attendees.AttendeeInfoLayout;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.common.collect.CollectPreconditions;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeActivity

public final class ProposeNewTimeFragment extends Fragment
    implements StateHolder, com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback
{

    private Recycler chipRecycler;
    public EventsApiImpl eventsApi;
    private ProposeNewTimeGridManager gridManager;
    private PagedScrollView hoursScrollView;
    public PagedScrollView mainGridScrollView;
    private ProposeNewTimePagerAdapter pagerAdapter;
    public ProposeNewTimeGridDayView proposalGrid;
    private TimelineEvent proposalItem;
    public ProposeNewTimeRendezvousClient rendezvousClient;
    public ProposeNewTimeState state;
    public TimeBoxToTimelineAdapter timeBoxToTimelineAdapter;
    public ViewPager viewPager;

    public ProposeNewTimeFragment()
    {
    }

    private final TimeProposal getCurrentProposal()
    {
        boolean flag;
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return state.getTimeProposal();
        }
        Attendee attendee = state.getAttendeeForNthProposal(state.getSelectedProposalIndex());
        if (attendee != null)
        {
            return attendee.getProposal();
        } else
        {
            return null;
        }
    }

    static final boolean lambda$filterOutProposalTarget$11$ProposeNewTimeFragment(EventKey eventkey, TimeRangeEntry timerangeentry)
    {
        return !eventkey.equals(timerangeentry.getKey());
    }

    static final Integer lambda$getScopeIndex$9$ProposeNewTimeFragment(com.google.android.calendar.event.scope.ScopeSelectionDialog.Scope scope)
    {
        return Integer.valueOf(scope.value());
    }

    static final void lambda$onCreateView$2$ProposeNewTimeFragment$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNM2R3CCHGNIBQ1EHQ6ARJ4CLIK2R3C8HGNII35C5I6ASIMD5INEEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FCTP6IP3MD5INESPFC5M6OP31F4NK2R3C8HGNII35C5I6ASI1E9P6UTPR9HGMSP3IDTKM8BRMD5INEBQMD5INEEP9AO______0(AttendeeAllDayHeaderView attendeealldayheaderview, AllDayHeaderArrow alldayheaderarrow)
    {
        int i = 1;
        boolean flag;
        if (!((ExpandableChipColumnView) (attendeealldayheaderview)).isExpanded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            i = 2;
        }
        alldayheaderarrow.setState(i);
        if (flag != ((ExpandableChipColumnView) (attendeealldayheaderview)).isExpanded)
        {
            attendeealldayheaderview.isExpanded = flag;
            attendeealldayheaderview.applyExpandedOrCollapsedState();
            attendeealldayheaderview.onExpandStateChanged$51D2ILG_0();
        }
    }

    static final void lambda$onCreateView$3$ProposeNewTimeFragment(AllDayHeaderArrow alldayheaderarrow, AttendeeInfoLayout attendeeinfolayout, ViewGroup viewgroup, int i, int j)
    {
        alldayheaderarrow.setState(j);
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)alldayheaderarrow.getLayoutParams();
        layoutparams.height = attendeeinfolayout.height + i;
        if (viewgroup.getHeight() != 0)
        {
            layoutparams.height = Math.min(layoutparams.height, viewgroup.getHeight());
        }
        alldayheaderarrow.setLayoutParams(layoutparams);
    }

    private final void updateProposalChipTime()
    {
        Object obj;
        TimelineEvent timelineevent;
        ProposeNewTimeGridDayView proposenewtimegriddayview;
        int j;
        timelineevent = null;
        Context context;
        boolean flag;
        long l;
        long l2;
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = state.getTimeProposal();
        } else
        {
            obj = state.getAttendeeForNthProposal(state.getSelectedProposalIndex());
            if (obj != null)
            {
                obj = ((Attendee) (obj)).getProposal();
            } else
            {
                obj = null;
            }
        }
        l = ((TimeProposal) (obj)).getStartTimeMillis();
        l2 = Math.max(l, ((TimeProposal) (obj)).getEndTimeMillis());
        proposalItem.timeRange = TimeRange.newNonAllDay(Utils.getTimeZone(getContext()), l, l2);
        proposenewtimegriddayview = proposalGrid;
        context = getContext();
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        obj = state.getTimeProposal();
_L4:
        j = Utils.getJulianDay(context, ((TimeProposal) (obj)).getStartTimeMillis());
        timelineevent = proposalItem;
        proposenewtimegriddayview.currentProposal = timelineevent;
        obj = new TimelineItem[1];
        obj[0] = timelineevent;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L2:
        Attendee attendee = state.getAttendeeForNthProposal(state.getSelectedProposalIndex());
        obj = timelineevent;
        if (attendee != null)
        {
            obj = attendee.getProposal();
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i = obj.length;
        CollectPreconditions.checkNonnegative(i, "arraySize");
        long l1 = 5L + (long)i + (long)(i / 10);
        ArrayList arraylist;
        if (l1 > 0x7fffffffL)
        {
            i = 0x7fffffff;
        } else
        if (l1 < 0xffffffff80000000L)
        {
            i = 0x80000000;
        } else
        {
            i = (int)l1;
        }
        arraylist = new ArrayList(i);
        Collections.addAll(arraylist, ((Object []) (obj)));
        proposenewtimegriddayview.setChips(j, arraylist);
        return;
    }

    final void autoscroll(boolean flag)
    {
label0:
        {
label1:
            {
                int k = 0x7fffffff;
                Object obj1 = proposalGrid;
                Object obj = ((GridDayView) (obj1)).items.allChipsView;
                obj1 = new com.google.android.calendar.timely.gridviews.GridDayView..Lambda._cls2(((GridDayView) (obj1)));
                obj = ((Iterable) (obj)).iterator();
                int i;
                for (i = 0x7fffffff; ((Iterator) (obj)).hasNext(); i = ((com.google.android.calendar.utils.collection.Iterables2.IntFolder) (obj1)).onFold(((Iterator) (obj)).next(), i)) { }
                if (i >= mainGridScrollView.getScrollY())
                {
                    obj1 = proposalGrid;
                    obj = ((GridDayView) (obj1)).items.allChipsView;
                    obj1 = new com.google.android.calendar.timely.gridviews.GridDayView..Lambda._cls2(((GridDayView) (obj1)));
                    obj = ((Iterable) (obj)).iterator();
                    int j;
                    for (j = k; ((Iterator) (obj)).hasNext(); j = ((com.google.android.calendar.utils.collection.Iterables2.IntFolder) (obj1)).onFold(((Iterator) (obj)).next(), j)) { }
                    if (j <= mainGridScrollView.getScrollY() + mainGridScrollView.getHeight())
                    {
                        break label1;
                    }
                }
                if (!flag)
                {
                    break label0;
                }
                obj = mainGridScrollView;
                obj1 = proposalGrid;
                PagedScrollView pagedscrollview1 = mainGridScrollView;
                ((PagedScrollView) (obj)).smoothScrollTo(0, Math.max(0, ((GridDayView) (obj1)).getFirstChipCenterYCoordinate(-1) - pagedscrollview1.getHeight() / 2));
            }
            return;
        }
        PagedScrollView pagedscrollview = mainGridScrollView;
        ProposeNewTimeGridDayView proposenewtimegriddayview = proposalGrid;
        PagedScrollView pagedscrollview2 = mainGridScrollView;
        pagedscrollview.scrollTo(0, Math.max(0, proposenewtimegriddayview.getFirstChipCenterYCoordinate(-1) - pagedscrollview2.getHeight() / 2));
    }

    final ProposalValidity getProposalValidity()
    {
        Object obj = getCurrentProposal();
        if (((TimeProposal) (obj)).getStartTimeMillis() == state.getOriginalEventStartTime() && ((TimeProposal) (obj)).getEndTimeMillis() == state.getOriginalEventEndTime())
        {
            return ProposalValidity.PROPOSAL_SAME_AS_INITIAL;
        }
        long l1 = ((TimeProposal) (obj)).getStartTimeMillis();
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (l1 < l)
        {
            return ProposalValidity.IN_THE_PAST;
        }
        Object obj1 = Utils.getTimeZone(getContext());
        l = ((TimeProposal) (obj)).getStartTimeMillis();
        obj1 = Calendar.getInstance(((java.util.TimeZone) (obj1)));
        ((Calendar) (obj1)).setTimeInMillis(l);
        java.util.TimeZone timezone = Utils.getTimeZone(getContext());
        l = ((TimeProposal) (obj)).getEndTimeMillis();
        obj = Calendar.getInstance(timezone);
        ((Calendar) (obj)).setTimeInMillis(l);
        if (!Utils.isValidEventTime(((Calendar) (obj1)), ((Calendar) (obj)), false))
        {
            return ProposalValidity.END_BEFORE_START;
        } else
        {
            return ProposalValidity.VALID;
        }
    }

    public final ProposeNewTimeState getState()
    {
        return state;
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        class .Lambda._cls0
            implements Supplier
        {

            private final ProposeNewTimeFragment arg$1;

            public final Object get()
            {
                return Utils.getTimeZone(arg$1.getContext());
            }

            .Lambda._cls0()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
        }

        if (bundle != null)
        {
            state = (ProposeNewTimeState)bundle.getParcelable("initial_state");
        } else
        if (getArguments() != null)
        {
            state = (ProposeNewTimeState)getArguments().getParcelable("initial_state");
        }
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        chipRecycler = ChipRecyclerManager.getOrCreateRecycler(bundle);
        eventsApi = new EventsApiImpl(getContext(), new .Lambda._cls0());
        timeBoxToTimelineAdapter = new TimeBoxToTimelineAdapter(getContext());
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = (ViewGroup)layoutinflater.inflate(0x7f05012f, viewgroup, false);
        bundle = (Toolbar)viewgroup.findViewById(0x7f100113);
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final ProposeNewTimeFragment arg$1;

            public final void onClick(View view)
            {
                view = arg$1;
                if (((Fragment) (view)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                }
                view.finish();
            }

            .Lambda._cls1()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
        }

        class .Lambda._cls2
            implements android.support.v7.widget.Toolbar.OnMenuItemClickListener
        {

            private final ProposeNewTimeFragment arg$1;

            public final boolean onMenuItemClick(MenuItem menuitem)
            {
                Object obj1 = arg$1;
                int j = menuitem.getItemId();
                if (j == 0x7f100431)
                {
                    if (((Fragment) (obj1)).mHost == null)
                    {
                        menuitem = null;
                    } else
                    {
                        menuitem = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
                    }
                    menuitem = (ProposeNewTimeActivity)menuitem;
                    obj1 = AnalyticsLoggerHolder.instance;
                    if (obj1 == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    } else
                    {
                        ((AnalyticsLogger)obj1).trackEvent(menuitem, "propose_new_time", "delete_proposal");
                        obj1 = menuitem.getIntent();
                        ((Intent) (obj1)).putExtra("propose_new_time_proposal", null);
                        menuitem.setResult(-1, ((Intent) (obj1)));
                        menuitem.finish();
                        return true;
                    }
                }
                if (j == 0x7f100430)
                {
                    FeedbackUtil.sendFeedback(((Fragment) (obj1)).getContext(), "calendar-pnt+feedback+android@google.com", "Internal feedback: Propose New Time", "You're giving feedback on the propose new time feature. What is working well or not working well in this feature?", ((ProposeNewTimeFragment) (obj1)).state.getAccount());
                    return true;
                } else
                {
                    return false;
                }
            }

            .Lambda._cls2()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
        }

        class .Lambda._cls3
            implements android.view.View.OnClickListener
        {

            private final AttendeeAllDayHeaderView arg$1;
            private final AllDayHeaderArrow arg$2;

            public final void onClick(View view)
            {
                ProposeNewTimeFragment.lambda$onCreateView$2$ProposeNewTimeFragment$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NMESJ9CHR6IPBNECNM2R3CCHGNIBQ1EHQ6ARJ4CLIK2R3C8HGNII35C5I6ASIMD5INEEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FCTP6IP3MD5INESPFC5M6OP31F4NK2R3C8HGNII35C5I6ASI1E9P6UTPR9HGMSP3IDTKM8BRMD5INEBQMD5INEEP9AO______0(arg$1, arg$2);
            }

            .Lambda._cls3(AttendeeAllDayHeaderView attendeealldayheaderview, AllDayHeaderArrow alldayheaderarrow)
            {
                arg$1 = attendeealldayheaderview;
                arg$2 = alldayheaderarrow;
            }
        }

        class .Lambda._cls4
            implements com.google.android.calendar.timely.gridviews.allday.AttendeeAllDayHeaderView.OnMeasurementChangedListener
        {

            private final AllDayHeaderArrow arg$1;
            private final AttendeeInfoLayout arg$2;
            private final ViewGroup arg$3;

            public final void onMeasurementChanged(int j, int k)
            {
                ProposeNewTimeFragment.lambda$onCreateView$3$ProposeNewTimeFragment(arg$1, arg$2, arg$3, j, k);
            }

            .Lambda._cls4(AllDayHeaderArrow alldayheaderarrow, AttendeeInfoLayout attendeeinfolayout, ViewGroup viewgroup)
            {
                arg$1 = alldayheaderarrow;
                arg$2 = attendeeinfolayout;
                arg$3 = viewgroup;
            }
        }

        class .Lambda._cls5
            implements Function
        {

            private final ProposeNewTimeFragment arg$1;

            public final Object apply(Object obj1)
            {
                ProposeNewTimeFragment proposenewtimefragment = arg$1;
                int j = ((Integer)obj1).intValue();
                class .Lambda._cls13
                    implements Function
                {

                    private final ProposeNewTimeFragment arg$1;

                    public final Object apply(Object obj2)
                    {
                        Object obj3 = arg$1;
                        obj2 = (List)obj2;
                        TimeBoxToTimelineAdapter timeboxtotimelineadapter = ((ProposeNewTimeFragment) (obj3)).timeBoxToTimelineAdapter;
                        obj3 = ((ProposeNewTimeFragment) (obj3)).state.getEventKey();
                        class .Lambda._cls14
                            implements Predicate
                        {

                            private final EventKey arg$1;

                            public final boolean apply(Object obj4)
                            {
                                return ProposeNewTimeFragment.lambda$filterOutProposalTarget$11$ProposeNewTimeFragment(arg$1, (TimeRangeEntry)obj4);
                            }

                                .Lambda._cls14(EventKey eventkey)
                                {
                                    arg$1 = eventkey;
                                }
                        }

                        if (obj2 instanceof FluentIterable)
                        {
                            obj2 = (FluentIterable)obj2;
                        } else
                        {
                            obj2 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj2)), ((Iterable) (obj2)));
                        }
                        obj3 = new .Lambda._cls14(((EventKey) (obj3)));
                        obj2 = (Iterable)((FluentIterable) (obj2)).iterableDelegate.or(obj2);
                        if (obj2 == null)
                        {
                            throw new NullPointerException();
                        }
                        if (obj3 == null)
                        {
                            throw new NullPointerException();
                        }
                        obj2 = new com.google.common.collect.Iterables._cls4(((Iterable) (obj2)), ((Predicate) (obj3)));
                        if (obj2 instanceof FluentIterable)
                        {
                            obj2 = (FluentIterable)obj2;
                        } else
                        {
                            obj2 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj2)), ((Iterable) (obj2)));
                        }
                        return timeboxtotimelineadapter.entriesToItems(ImmutableList.copyOf((Iterable)((FluentIterable) (obj2)).iterableDelegate.or(obj2)));
                    }

                        .Lambda._cls13()
                        {
                            arg$1 = ProposeNewTimeFragment.this;
                        }
                }

                return (FluentFuture)AbstractTransformFuture.create(proposenewtimefragment.eventsApi.getAsync(j, j, true), proposenewtimefragment. new .Lambda._cls13(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }

            .Lambda._cls5()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
        }

        class .Lambda._cls6
            implements Function
        {

            private final ProposeNewTimeFragment arg$1;

            public final Object apply(Object obj1)
            {
                Object obj2 = arg$1;
                int j = ((Integer)obj1).intValue();
                if (((ProposeNewTimeFragment) (obj2)).rendezvousClient == null)
                {
                    obj1 = new RuntimeException("Rendezvous client not initialized.");
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateFailedFuture(((Throwable) (obj1)));
                    }
                } else
                {
                    obj1 = ((ProposeNewTimeFragment) (obj2)).rendezvousClient;
                    com.google.android.apps.calendar.proposenewtime.net.AutoValue_Request.Builder builder = new com.google.android.apps.calendar.proposenewtime.net.AutoValue_Request.Builder();
                    builder.setAttendees((ImmutableList)ImmutableList.copyOf(((ProposeNewTimeFragment) (obj2)).state.getAttendees()).subList(1, ((ProposeNewTimeFragment) (obj2)).state.getAttendees().size()));
                    builder.setStartTimeMillis(Utils.getMillisFromJulianDay(j));
                    builder.setEndTimeMillis(Utils.getMillisFromJulianDay(j + 1));
                    builder.setTimeZone(Utils.getTimeZone(((Fragment) (obj2)).getContext()));
                    builder.setEventId(((ProposeNewTimeFragment) (obj2)).state.getEventId());
                    builder.setCalendarId(((ProposeNewTimeFragment) (obj2)).state.getCalendarId());
                    obj2 = builder.build();
                    return ((BaseClientFragment) (obj1)).client.sendRequest(obj2);
                }
            }

            .Lambda._cls6()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
        }

        class .Lambda._cls7
            implements com.google.android.apps.calendar.proposenewtime.grid.views.ProposeNewTimeGridDayView.TapListener
        {

            private final ProposeNewTimeFragment arg$1;

            public final void onTap(int j, int k)
            {
                boolean flag2 = false;
                ProposeNewTimeFragment proposenewtimefragment = arg$1;
                boolean flag1;
                if (proposenewtimefragment.state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    if (k < 30)
                    {
                        k = ((flag2) ? 1 : 0);
                    } else
                    {
                        k = 30;
                    }
                    proposenewtimefragment.state = proposenewtimefragment.state.toBuilder().setTimeProposal(proposenewtimefragment.state.getTimeProposal().withNewStartTime(j, k, Utils.getTimeZone(proposenewtimefragment.getContext()))).build();
                    proposenewtimefragment.refreshViews();
                }
            }

            .Lambda._cls7()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
        }

        Object obj;
        ProposeNewTimeGridViewFrame proposenewtimegridviewframe;
        Recycler recycler;
        int i;
        boolean flag;
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 0x7f1303be;
        } else
        {
            i = 0x7f1303bf;
        }
        bundle.setTitle(i);
        layoutinflater = new .Lambda._cls1();
        bundle.ensureNavButtonView();
        ((Toolbar) (bundle)).mNavButtonView.setOnClickListener(layoutinflater);
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        StatusbarAnimatorCompat.createInstance(layoutinflater.getWindow()).tryApplyLightStatusbar(true, requireContext().getResources().getColor(0x7f0d0214), 0xff000000, 0);
        setHasOptionsMenu(true);
        (new SupportMenuInflater(bundle.getContext())).inflate(0x7f150008, bundle.getMenu());
        layoutinflater = bundle.getMenu().findItem(0x7f100431);
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && ((Attendee)state.getAttendees().get(0)).getProposal() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        layoutinflater.setVisible(flag);
        bundle.mOnMenuItemClickListener = new .Lambda._cls2();
        viewPager = (ViewPager)viewgroup.findViewById(0x7f1001ed);
        layoutinflater = (FloatingActionButton)viewgroup.findViewById(0x7f100146);
        if (requireContext().getResources().getConfiguration().orientation == 1 || getContext().getResources().getBoolean(0x7f0c0016))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            bundle = viewPager;
            if (bundle != null)
            {
                bundle.setVisibility(8);
            }
            if (layoutinflater != null)
            {
                layoutinflater.setVisibility(8);
            }
        } else
        {
            class .Lambda._cls9
                implements android.view.View.OnClickListener
            {

                private final ProposeNewTimeFragment arg$1;

                public final void onClick(View view)
                {
                    Object obj1;
                    ProposeNewTimeFragment proposenewtimefragment;
                    boolean flag4;
                    boolean flag5;
                    obj1 = null;
                    flag4 = true;
                    flag5 = true;
                    proposenewtimefragment = arg$1;
                    if (proposenewtimefragment.getProposalValidity().isValid) goto _L2; else goto _L1
_L1:
                    boolean flag1;
                    if (proposenewtimefragment.state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1) goto _L3; else goto _L2
_L2:
                    if (proposenewtimefragment.state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag1) goto _L5; else goto _L4
_L4:
                    ScopeSelectionDialog scopeselectiondialog;
                    List list;
label0:
                    {
                        list = ProposeNewTimeUtils.createProposeNewTimeScopes();
                        scopeselectiondialog = ScopeSelectionDialog.newInstance(proposenewtimefragment, (new com.google.android.calendar.event.scope..AutoValue_ScopeSelectionDialog_Config.Builder()).additionalArguments(new Bundle()).scopes(list).title(0x7f1303bb).positiveButton(0x7f1303ba).build());
                        obj1 = proposenewtimefragment.state.getResponseStatus();
                        if (obj1 != null)
                        {
                            view = ((View) (obj1));
                            if (obj1 != com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION)
                            {
                                break label0;
                            }
                        }
                        view = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE;
                    }
                    class .Lambda._cls12
                        implements Function
                    {

                        public static final Function $instance = new .Lambda._cls12();

                        public final Object apply(Object obj2)
                        {
                            return ProposeNewTimeFragment.lambda$getScopeIndex$9$ProposeNewTimeFragment((com.google.android.calendar.event.scope.ScopeSelectionDialog.Scope)obj2);
                        }


                            private .Lambda._cls12()
                            {
                            }
                    }

                    obj1 = .Lambda._cls12..instance;
                    if (list instanceof RandomAccess)
                    {
                        obj1 = new com.google.common.collect.Lists.TransformingRandomAccessList(list, ((Function) (obj1)));
                    } else
                    {
                        obj1 = new com.google.common.collect.Lists.TransformingSequentialList(list, ((Function) (obj1)));
                    }
                    scopeselectiondialog.checkedItem = ((List) (obj1)).indexOf(Integer.valueOf(view.ordinal()));
                    scopeselectiondialog.show(((Fragment) (proposenewtimefragment)).mFragmentManager, null);
_L7:
                    return;
_L5:
                    ProposeNewTimeActivity proposenewtimeactivity;
                    boolean flag2;
                    if (((Fragment) (proposenewtimefragment)).mHost == null)
                    {
                        view = null;
                    } else
                    {
                        view = (FragmentActivity)((Fragment) (proposenewtimefragment)).mHost.mActivity;
                    }
                    proposenewtimeactivity = (ProposeNewTimeActivity)view;
                    if (proposenewtimefragment.state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if (flag2)
                    {
                        view = proposenewtimefragment.state.getTimeProposal();
                    } else
                    {
                        Attendee attendee = proposenewtimefragment.state.getAttendeeForNthProposal(proposenewtimefragment.state.getSelectedProposalIndex());
                        view = ((View) (obj1));
                        if (attendee != null)
                        {
                            view = attendee.getProposal();
                        }
                    }
                    obj1 = proposenewtimefragment.state.getResponseStatus();
                    if (proposenewtimefragment.state.getMode() != com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
                    {
                        flag5 = false;
                    }
                    proposenewtimeactivity.onProposalAccepted(view, ((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj1)), flag5);
                    return;
_L3:
                    ProposalValidity proposalvalidity = proposenewtimefragment.getProposalValidity();
                    boolean flag3;
                    if (proposalvalidity.errorMessage != null)
                    {
                        flag3 = flag4;
                    } else
                    {
                        flag3 = false;
                    }
                    if (flag3)
                    {
                        proposenewtimefragment.refreshViews();
                        if (((Fragment) (proposenewtimefragment)).mHost == null)
                        {
                            view = null;
                        } else
                        {
                            view = (FragmentActivity)((Fragment) (proposenewtimefragment)).mHost.mActivity;
                        }
                        view = view.getResources();
                        view = (new android.app.AlertDialog.Builder(proposenewtimefragment.getContext())).setMessage(proposalvalidity.errorMessage.intValue()).setNegativeButton(view.getString(0x104000a), null).create();
                        view.setCanceledOnTouchOutside(false);
                        view.show();
                        return;
                    }
                    if (true) goto _L7; else goto _L6
_L6:
                }

            .Lambda._cls9()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
            }

            class .Lambda._cls10
                implements android.view.View.OnLayoutChangeListener
            {

                private final ProposeNewTimeFragment arg$1;
                private final FloatingActionButton arg$2;

                public final void onLayoutChange(View view, int j, int k, int l, int i1, int j1, int k1, 
                        int l1, int i2)
                {
                    view = arg$1;
                    arg$2.setTranslationY(view.requireContext().getResources().getDimensionPixelSize(0x7f0e031f) / 2 - ((ProposeNewTimeFragment) (view)).viewPager.getHeight());
                }

            .Lambda._cls10(FloatingActionButton floatingactionbutton)
            {
                arg$1 = ProposeNewTimeFragment.this;
                arg$2 = floatingactionbutton;
            }
            }

            if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0x7f130070;
            } else
            {
                i = 0x7f130050;
            }
            layoutinflater.setContentDescription(requireContext().getResources().getString(i));
            bundle = requireContext();
            if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0x7f020232;
            } else
            {
                i = 0x7f0201e4;
            }
            layoutinflater.setImageDrawable(ContextCompat.getDrawable(bundle, i));
            layoutinflater.setOnClickListener(new .Lambda._cls9());
            viewPager.addOnLayoutChangeListener(new .Lambda._cls10(layoutinflater));
            layoutinflater = null;
            if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                layoutinflater = new ProposalChangeHandler(getContext(), super.mFragmentManager, this, Utils.getTimeZone(getContext()));
                class .Lambda._cls11
                    implements Runnable
                {

                    private final ProposeNewTimeFragment arg$1;

                    public final void run()
                    {
                        arg$1.autoscroll(false);
                    }

            .Lambda._cls11()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
                }

                layoutinflater.postUpdateAction = new .Lambda._cls11();
            }
            pagerAdapter = new ProposeNewTimePagerAdapter(this, layoutinflater, layoutinflater);
            viewPager.setAdapter(pagerAdapter);
            if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                viewPager.setCurrentItem(state.getSelectedProposalIndex());
            }
            viewPager.addOnPageChangeListener(new _cls1());
        }
        bundle = (AttendeeInfoLayout)viewgroup.findViewById(0x7f10000a);
        layoutinflater = (AllDayHeaderArrow)viewgroup.findViewById(0x7f100302);
        obj = (AttendeeAllDayHeaderView)viewgroup.findViewById(0x7f100005);
        layoutinflater.setOnClickListener(new .Lambda._cls3(((AttendeeAllDayHeaderView) (obj)), layoutinflater));
        obj.measurementChangedListener = new .Lambda._cls4(layoutinflater, bundle, viewgroup);
        obj.chipRecycler = chipRecycler;
        proposenewtimegridviewframe = (ProposeNewTimeGridViewFrame)viewgroup.findViewById(0x7f100008);
        proposalGrid = proposenewtimegridviewframe.proposalView;
        proposalGrid.initialize(chipRecycler, null, 1);
        proposalGrid.longEventVisibility = true;
        recycler = chipRecycler;
        if (super.mLayoutInflater == null)
        {
            super.mLayoutInflater = onGetLayoutInflater(null);
            layoutinflater = super.mLayoutInflater;
        } else
        {
            layoutinflater = super.mLayoutInflater;
        }
        gridManager = new ProposeNewTimeGridManager(proposenewtimegridviewframe, ((AttendeeAllDayHeaderView) (obj)), bundle, recycler, layoutinflater, new .Lambda._cls5(), new .Lambda._cls6(), this);
        bundle = gridManager;
        obj = getContext();
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            layoutinflater = state.getTimeProposal();
        } else
        {
            layoutinflater = state.getAttendeeForNthProposal(state.getSelectedProposalIndex());
            if (layoutinflater != null)
            {
                layoutinflater = layoutinflater.getProposal();
            } else
            {
                layoutinflater = null;
            }
        }
        bundle.switchToDay(Utils.getJulianDay(((Context) (obj)), layoutinflater.getStartTimeMillis()));
        proposalGrid.tapListener = new .Lambda._cls7();
        proposalItem = new TimelineEvent();
        proposalItem.title = "";
        proposalItem.color = state.getEventColor();
        proposalItem.selfAttendeeStatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;
        updateProposalChipTime();
        mainGridScrollView = (PagedScrollView)viewgroup.findViewById(0x7f10019f);
        hoursScrollView = (PagedScrollView)viewgroup.findViewById(0x7f10019e);
        layoutinflater = new com.google.android.calendar.timely.PagedScrollView.ScrollManager();
        bundle = mainGridScrollView;
        bundle.setVerticalScrollPositionFromBottom(((com.google.android.calendar.timely.PagedScrollView.ScrollManager) (layoutinflater)).verticalScrollPositionFromBottom, false);
        ((com.google.android.calendar.timely.PagedScrollView.ScrollManager) (layoutinflater)).scrollViews.add(bundle);
        bundle.addOnLayoutChangeListener(layoutinflater);
        bundle.scrollManager = layoutinflater;
        bundle.pinchZoomController = new PinchZoomController(bundle, layoutinflater);
        ((PagedScrollView) (bundle)).pinchZoomController.bottomPadding = 0;
        bundle = hoursScrollView;
        bundle.setVerticalScrollPositionFromBottom(((com.google.android.calendar.timely.PagedScrollView.ScrollManager) (layoutinflater)).verticalScrollPositionFromBottom, false);
        ((com.google.android.calendar.timely.PagedScrollView.ScrollManager) (layoutinflater)).scrollViews.add(bundle);
        bundle.addOnLayoutChangeListener(layoutinflater);
        bundle.scrollManager = layoutinflater;
        bundle.pinchZoomController = new PinchZoomController(bundle, layoutinflater);
        ((PagedScrollView) (bundle)).pinchZoomController.bottomPadding = 0;
        return viewgroup;
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("initial_state", state);
        super.onSaveInstanceState(bundle);
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus = null;
        boolean flag1 = true;
        ProposeNewTimeActivity proposenewtimeactivity;
        boolean flag;
        if (super.mHost == null)
        {
            config = null;
        } else
        {
            config = (FragmentActivity)super.mHost.mActivity;
        }
        proposenewtimeactivity = (ProposeNewTimeActivity)config;
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            config = state.getTimeProposal();
        } else
        {
            Attendee attendee = state.getAttendeeForNthProposal(state.getSelectedProposalIndex());
            config = responsestatus;
            if (attendee != null)
            {
                config = attendee.getProposal();
            }
        }
        responsestatus = ProposeNewTimeUtils.indexToResponseStatus(i);
        if (state.getMode() != com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            flag1 = false;
        }
        proposenewtimeactivity.onProposalAccepted(config, responsestatus, flag1);
    }

    public final void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        class .Lambda._cls8
            implements Runnable
        {

            private final ProposeNewTimeFragment arg$1;

            public final void run()
            {
                ProposeNewTimeFragment proposenewtimefragment = arg$1;
                ProposeNewTimeGridDayView proposenewtimegriddayview = proposenewtimefragment.proposalGrid;
                PagedScrollView pagedscrollview = proposenewtimefragment.mainGridScrollView;
                int i = Math.max(0, proposenewtimegriddayview.getFirstChipCenterYCoordinate(-1) - pagedscrollview.getHeight() / 2);
                proposenewtimefragment.mainGridScrollView.scrollTo(0, i);
            }

            .Lambda._cls8()
            {
                arg$1 = ProposeNewTimeFragment.this;
            }
        }

        mainGridScrollView.post(new .Lambda._cls8());
    }

    final void refreshViews()
    {
        Object obj;
        Object obj1;
        obj1 = null;
        boolean flag;
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (requireContext().getResources().getConfiguration().orientation == 1 || getContext().getResources().getBoolean(0x7f0c0016))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Object obj2 = (SlabItem)viewPager.findViewWithTag(Integer.valueOf(viewPager.getCurrentItem()));
                Context context;
                if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = state.getTimeProposal();
                } else
                {
                    obj = state.getAttendeeForNthProposal(state.getSelectedProposalIndex());
                    if (obj != null)
                    {
                        obj = ((Attendee) (obj)).getProposal();
                    } else
                    {
                        obj = null;
                    }
                }
                ((SlabItem) (obj2)).setTimeProposal(((TimeProposal) (obj)));
            }
        }
        updateProposalChipTime();
        obj2 = gridManager;
        context = getContext();
        if (state.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        obj = state.getTimeProposal();
_L4:
        ((ProposeNewTimeGridManager) (obj2)).switchToDay(Utils.getJulianDay(context, ((TimeProposal) (obj)).getStartTimeMillis()));
        return;
_L2:
        Attendee attendee = state.getAttendeeForNthProposal(state.getSelectedProposalIndex());
        obj = obj1;
        if (attendee != null)
        {
            obj = attendee.getProposal();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setState(ProposeNewTimeState proposenewtimestate)
    {
        state = proposenewtimestate;
        refreshViews();
    }

    private class ProposalValidity extends Enum
    {

        private static final ProposalValidity $VALUES[];
        public static final ProposalValidity END_BEFORE_START;
        public static final ProposalValidity IN_THE_PAST;
        public static final ProposalValidity PROPOSAL_SAME_AS_INITIAL;
        public static final ProposalValidity VALID;
        public final Integer errorMessage;
        public final boolean isValid;

        public static ProposalValidity[] values()
        {
            return (ProposalValidity[])$VALUES.clone();
        }

        static 
        {
            VALID = new ProposalValidity("VALID", 0, true, null);
            END_BEFORE_START = new ProposalValidity("END_BEFORE_START", 1, false, Integer.valueOf(0x7f13030a));
            PROPOSAL_SAME_AS_INITIAL = new ProposalValidity("PROPOSAL_SAME_AS_INITIAL", 2, false, Integer.valueOf(0x7f1303b8));
            IN_THE_PAST = new ProposalValidity("IN_THE_PAST", 3, false, Integer.valueOf(0x7f1303b7));
            $VALUES = (new ProposalValidity[] {
                VALID, END_BEFORE_START, PROPOSAL_SAME_AS_INITIAL, IN_THE_PAST
            });
        }

        private ProposalValidity(String s, int i, boolean flag, Integer integer)
        {
            super(s, i);
            isValid = flag;
            errorMessage = integer;
        }
    }


    private class _cls1
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        private final ProposeNewTimeFragment this$0;

        public final void onPageScrollStateChanged(int i)
        {
        }

        public final void onPageScrolled(int i, float f, int j)
        {
        }

        public final void onPageSelected(int i)
        {
            ProposeNewTimeState proposenewtimestate = state.toBuilder().setSelectedProposalIndex(i).build();
            ProposeNewTimeFragment proposenewtimefragment = ProposeNewTimeFragment.this;
            proposenewtimefragment.state = proposenewtimestate;
            proposenewtimefragment.refreshViews();
            autoscroll(true);
        }

        _cls1()
        {
            this$0 = ProposeNewTimeFragment.this;
            super();
        }
    }

}
