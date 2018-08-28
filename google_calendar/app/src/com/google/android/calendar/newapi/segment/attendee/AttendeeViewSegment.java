// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventResponseSummary;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.common.view.NinjaLinearLayoutManager;
import com.google.android.calendar.newapi.model.AccountHolder;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class AttendeeViewSegment extends LinearLayout
    implements ViewSegment
{

    private final AttendeeAdapter adapter;
    private final Comparator attendeeOrganiserFirstComparator = new _cls1();
    public boolean hasResponseSummary;
    public final AccountHolder model;
    public final ProposalAttendeeTileView.Callback proposeNewTimeCallback;

    public AttendeeViewSegment(Activity activity, AccountHolder accountholder, ProposalAttendeeTileView.Callback callback)
    {
        super(activity);
        adapter = new AttendeeAdapter(activity);
        setImportantForAccessibility(2);
        setOrientation(1);
        inflate(getContext(), 0x7f0500c0, this);
        RecyclerView recyclerview = (RecyclerView)findViewById(0x7f10024e);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.mHasFixedSize = true;
        recyclerview.setImportantForAccessibility(2);
        activity = new NinjaLinearLayoutManager(activity, 1, false);
        activity.mAutoMeasure = true;
        recyclerview.setLayoutManager(activity);
        recyclerview.setAdapter(adapter);
        model = accountholder;
        proposeNewTimeCallback = callback;
    }

    private final void addResponseCount(int i, int j, List list)
    {
        if (i > 0)
        {
            list.add(getContext().getResources().getQuantityString(j, i, new Object[] {
                Integer.valueOf(i)
            }));
        }
    }

    public final void updateUi()
    {
        Object obj1;
        Object obj2;
        boolean flag;
        flag = false;
        if (!AttendeeUtils.hasGuests(((EventHolder)model).getEvent()))
        {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj4)
            {
                return AttendeeUtils.isPerson((Attendee)obj4);
            }


            private .Lambda._cls0()
            {
            }
        }

        com.google.common.collect.ImmutableList immutablelist;
        boolean flag1;
        if (((EventHolder)model).getEvent().getResponseSummary() != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        hasResponseSummary = flag1;
        immutablelist = ((EventHolder)model).getEvent().getAttendees();
        obj1 = .Lambda._cls0..instance;
        if (immutablelist == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj1 = new AttendeesUtils.AttendeeMap(new com.google.common.collect.Iterables._cls4(immutablelist, ((Predicate) (obj1))), attendeeOrganiserFirstComparator);
        obj2 = (TextTileView)findViewById(0x7f10024d);
        if (!hasResponseSummary) goto _L2; else goto _L1
_L1:
        int k;
        EventResponseSummary eventresponsesummary = ((EventHolder)model).getEvent().getResponseSummary();
        k = eventresponsesummary.getNumAccepted() + eventresponsesummary.getNumDeclined() + eventresponsesummary.getNumNeedAction() + eventresponsesummary.getNumTentative();
_L13:
        Object obj;
        ((TextTileView) (obj2)).setPrimaryText(new CharSequence[] {
            getResources().getQuantityString(0x7f12001f, k, new Object[] {
                Integer.valueOf(k)
            })
        });
        obj = ((EventHolder)model).getEvent().getResponseSummary();
        if (obj == null) goto _L4; else goto _L3
_L3:
        Object obj3;
        obj3 = new ArrayList();
        addResponseCount(((EventResponseSummary) (obj)).getNumAccepted(), 0x7f12003f, ((List) (obj3)));
        addResponseCount(((EventResponseSummary) (obj)).getNumDeclined(), 0x7f120040, ((List) (obj3)));
        addResponseCount(((EventResponseSummary) (obj)).getNumTentative(), 0x7f120042, ((List) (obj3)));
        addResponseCount(((EventResponseSummary) (obj)).getNumNeedAction(), 0x7f120041, ((List) (obj3)));
        ((List) (obj3)).size();
        JVM INSTR tableswitch 1 4: default 344
    //                   1 631
    //                   2 646
    //                   3 686
    //                   4 737;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        obj = null;
_L15:
        if (obj == null) goto _L4; else goto _L10
_L10:
        ((TextTileView) (obj2)).setSecondaryText(new CharSequence[] {
            obj
        });
        obj = ((TextTileView) (obj2)).getSecondaryTextView();
        obj2 = new Joiner(String.valueOf('\n'));
        obj3 = ((Iterable) (obj3)).iterator();
        ((TextView) (obj)).setContentDescription(((Joiner) (obj2)).appendTo(new StringBuilder(), ((Iterator) (obj3))).toString());
          goto _L11
_L2:
        j = 0;
        i = 0;
_L14:
        k = i;
        if (j >= ((AttendeesUtils.AttendeeMap) (obj1)).attendees.size()) goto _L13; else goto _L12
_L12:
        i += ((Collection)((AttendeesUtils.AttendeeMap) (obj1)).attendees.valueAt(j)).size();
        j++;
          goto _L14
          goto _L13
_L6:
        obj = (CharSequence)((List) (obj3)).get(0);
          goto _L15
_L7:
        obj = getContext().getString(0x7f130123, new Object[] {
            ((List) (obj3)).get(0), ((List) (obj3)).get(1)
        });
          goto _L15
_L8:
        obj = getContext().getString(0x7f130122, new Object[] {
            ((List) (obj3)).get(0), ((List) (obj3)).get(1), ((List) (obj3)).get(2)
        });
          goto _L15
_L9:
        obj = getContext().getString(0x7f130121, new Object[] {
            ((List) (obj3)).get(0), ((List) (obj3)).get(1), ((List) (obj3)).get(2), ((List) (obj3)).get(3)
        });
          goto _L15
_L4:
        String s;
        if (((EventHolder)model).getEvent().isAttendeesOmitted())
        {
            s = getContext().getString(0x7f130464);
        } else
        {
            s = null;
        }
        ((TextTileView) (obj2)).setSecondaryText(new CharSequence[] {
            s
        });
_L11:
        adapter.items.clear();
        obj = adapter.sectionOrdering;
        k = obj.length;
        int i = 0;
        for (int j = ((flag) ? 1 : 0); j < k; j++)
        {
            obj3 = obj[j];
            obj2 = (Collection)((AttendeesUtils.AttendeeMap) (obj1)).attendees.get(((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj3)).ordinal(), Collections.emptyList());
            if (((Collection) (obj2)).isEmpty())
            {
                continue;
            }
            if (i != 0)
            {
                adapter.items.add(null);
            }
            obj3 = new HeadlineRecord(((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj3)), ((Collection) (obj2)).size());
            adapter.items.add(obj3);
            for (obj2 = ((Collection) (obj2)).iterator(); ((Iterator) (obj2)).hasNext(); adapter.items.add(obj3))
            {
                obj3 = (Attendee)((Iterator) (obj2)).next();
            }

            i = 1;
        }

        ((android.support.v7.widget.RecyclerView.Adapter) (adapter)).mObservable.notifyChanged();
        return;
          goto _L15
    }

    private class _cls1
        implements Comparator
    {

        private final AttendeeViewSegment this$0;

        public final int compare(Object obj, Object obj1)
        {
            byte byte0 = -1;
            obj = (Attendee)obj;
            obj1 = (Attendee)obj1;
            int i;
            if (AttendeeUtils.isSameAttendee(((EventHolder)model).getEvent().getOrganizer(), ((Attendee) (obj)).attendeeDescriptor))
            {
                i = -1;
            } else
            {
                i = 0;
            }
            if (!AttendeeUtils.isSameAttendee(((EventHolder)model).getEvent().getOrganizer(), ((Attendee) (obj1)).attendeeDescriptor))
            {
                byte0 = 0;
            }
            i -= byte0;
            if (i != 0)
            {
                return i;
            } else
            {
                return AttendeesUtils.getAttendeeName(((Attendee) (obj))).compareToIgnoreCase(AttendeesUtils.getAttendeeName(((Attendee) (obj1))));
            }
        }

        _cls1()
        {
            this$0 = AttendeeViewSegment.this;
            super();
        }
    }


    private class AttendeeAdapter extends android.support.v7.widget.RecyclerView.Adapter
        implements android.view.View.OnClickListener
    {

        private final Activity activity;
        public final List items = new ArrayList();
        private final String responseHeadersStandalone[];
        private final String responseHeadersWithCount[];
        public final com.google.android.calendar.api.event.attendee.Response.ResponseStatus sectionOrdering[];
        private final int statusBucketSpacing;
        private final AttendeeViewSegment this$0;

        public final int getItemCount()
        {
            return items.size();
        }

        public final int getItemViewType(int i)
        {
            if (items.get(i) instanceof Attendee)
            {
                return 1;
            }
            return !(items.get(i) instanceof HeadlineRecord) ? 3 : 2;
        }

        public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
        {
            viewholder = (AttendeeViewHolder)viewholder;
            getItemViewType(i);
            JVM INSTR tableswitch 1 2: default 32
        //                       1 33
        //                       2 119;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            Attendee attendee = (Attendee)items.get(i);
            ((AttendeeTileView)((AttendeeViewHolder) (viewholder)).itemView).setData(model.getAccount(), attendee, AttendeeUtils.isSameAttendee(((EventHolder)model).getEvent().getOrganizer(), attendee.attendeeDescriptor), ((EventHolder)model).getEvent().getStartMillis());
            return;
_L3:
            HeadlineRecord headlinerecord = (HeadlineRecord)items.get(i);
            Object obj = (HeadlineTileView)((AttendeeViewHolder) (viewholder)).itemView;
            if (hasResponseSummary)
            {
                viewholder = responseHeadersStandalone[headlinerecord.status.ordinal()];
            } else
            {
                viewholder = String.format(responseHeadersWithCount[headlinerecord.status.ordinal()], new Object[] {
                    Integer.valueOf(headlinerecord.attendeeCount)
                });
            }
            ((HeadlineTileView) (obj)).text.setText(viewholder);
            if (!hasResponseSummary && Locale.getDefault().getLanguage().equalsIgnoreCase("en"))
            {
                viewholder = ((HeadlineTileView) (obj)).text;
                obj = responseHeadersStandalone[headlinerecord.status.ordinal()];
                i = headlinerecord.attendeeCount;
                viewholder.setContentDescription((new StringBuilder(String.valueOf(obj).length() + 12)).append(((String) (obj))).append("\n").append(i).toString());
                return;
            }
            if (true) goto _L1; else goto _L4
_L4:
        }

        public final void onClick(View view)
        {
label0:
            {
                if (view.getTag() instanceof Attendee)
                {
                    view = (Attendee)view.getTag();
                    if (AttendeesUtils.requestContactsPermissionsIfMissing(activity))
                    {
                        break label0;
                    }
                }
                return;
            }
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)analyticslogger).trackEvent(activity, "event_action", "tap_guest");
                SmartProfileHelper.showSmartProfile(activity, model.getAccount().name, AttendeesUtils.getContactInfo(model.getAccount().name, view));
                return;
            }
        }

        public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
        {
            switch (i)
            {
            default:
                viewgroup = new Space(activity);
                viewgroup.setMinimumHeight(statusBucketSpacing);
                return new AttendeeViewHolder(viewgroup);

            case 1: // '\001'
                if (ExperimentalOptions.isProposeNewTimeEnabled(getContext()))
                {
                    Activity activity1;
                    boolean flag;
                    if (((PermissionHolder)model).getPermissions() != null && !((PermissionHolder)model).getPermissions().isReadOnly() && AttendeeUtils.isOrganizerCopy(((EventHolder)model).getEvent()))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    activity1 = activity;
                    if (flag)
                    {
                        viewgroup = proposeNewTimeCallback;
                    } else
                    {
                        viewgroup = null;
                    }
                    viewgroup = new ProposalAttendeeTileView(activity1, flag, viewgroup);
                } else
                {
                    viewgroup = new AttendeeTileView(activity);
                }
                viewgroup.treatAsButton(true).setOnClickListener(this);
                viewgroup.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
                return new AttendeeViewHolder(viewgroup);

            case 2: // '\002'
                viewgroup = new HeadlineTileView(activity);
                viewgroup.indentContent().setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
                return new AttendeeViewHolder(viewgroup);
            }
        }

        public AttendeeAdapter(Activity activity1)
        {
            this$0 = AttendeeViewSegment.this;
            super();
            activity = activity1;
            responseHeadersStandalone = activity1.getResources().getStringArray(0x7f0b0010);
            responseHeadersWithCount = activity1.getResources().getStringArray(0x7f0b000f);
            statusBucketSpacing = activity1.getResources().getDimensionPixelOffset(0x7f0e0395);
            sectionOrdering = (new com.google.android.calendar.api.event.attendee.Response.ResponseStatus[] {
                com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION
            });
        }

        private class AttendeeViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
        {

            public AttendeeViewHolder(View view)
            {
                super(view);
            }
        }

    }


    private class HeadlineRecord
    {

        public int attendeeCount;
        public com.google.android.calendar.api.event.attendee.Response.ResponseStatus status;

        public HeadlineRecord(com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, int i)
        {
            status = responsestatus;
            attendeeCount = i;
        }
    }

}
