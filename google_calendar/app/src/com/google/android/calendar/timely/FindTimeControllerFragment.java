// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.Window;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CancelableFutureCallback;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.event.FindTimeSuggestionUi;
import com.google.android.calendar.timely.findatime.net.FindTimeClient;
import com.google.android.calendar.timely.findatime.net.FindTimeExchangeClient;
import com.google.android.calendar.timely.findatime.net.FindTimeRendezvousClient;
import com.google.android.calendar.timely.findatime.ui.DurationTimeframe;
import com.google.android.calendar.timely.findatime.ui.DurationTimeframeFragment;
import com.google.android.calendar.timely.findatime.ui.FindTime2UiSuggestionFragment;
import com.google.android.calendar.timely.findatime.ui.LabelsUtil;
import com.google.android.calendar.timely.findatime.ui.SuggestionBucket;
import com.google.android.calendar.timely.findatime.ui.SuggestionBuckets;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeAttendee, SuggestionRow, TimelineSuggestion, TimelineEvent, 
//            SuggestionRows, FindTimeGridData, FindTimeGridFragment, FindTimeGridUi, 
//            FindTimeTimeframe, FindTimeUtil

public class FindTimeControllerFragment extends Fragment
    implements com.google.android.calendar.event.FindTimeSuggestionUi.Listener, FindTimeGridUi.Listener, com.google.android.calendar.timely.findatime.ui.DurationTimeframeFragment.Listener
{
    public static interface OnFinishListener
    {

        public abstract void onFinishedWithCancel();

        public abstract void onFinishedWithSlot$5154KJ3AC5R62BRCC5N6EBQJEHP6IRJ77D66KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(long l, long l1, String s);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/FindTimeControllerFragment);
    private Account account;
    private ArrayList attendees;
    private CancelableFutureCallback callback;
    private FindTimeClient client;
    public com.google.android.calendar.timely.findatime.net.FindTimeClient.Result clientResult;
    private DurationTimeframe durationTimeframe;
    private int eventColor;
    private FindTimeGridUi gridUi;
    private boolean needsRestoreState;
    private OnFinishListener onFinishListener;
    private boolean savedConsiderExistingRooms;
    private FindTimeTimeframe savedTimeframe;
    public int state;
    private int suggestionIndex;
    private SuggestionRows suggestionRows;
    public FindTimeSuggestionUi suggestionUi;
    private TimeZone timezone;

    public FindTimeControllerFragment()
    {
        callback = new CancelableFutureCallback(null);
    }

    public static void addArguments(Bundle bundle, long l, long l1, int i, List list, Account account1, 
            String s, String s1, boolean flag, boolean flag1, String s2, String s3)
    {
        Object obj;
        if (account1 == null)
        {
            throw new NullPointerException();
        }
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (!AccountUtil.isGoogleExchangeAccount(account1) && !AccountUtil.isGoogleAccount(account1) && !AccountUtil.isGoogleExchangeGoAccount(account1))
        {
            bundle = account1.type;
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(bundle).length() + 27)).append("Account type ").append(bundle).append(" not supported").toString());
        }
        bundle.putInt("event_color", i);
        FindTimeAttendee findtimeattendee;
        if (list instanceof ArrayList)
        {
            list = (ArrayList)list;
        } else
        {
            list = new ArrayList(list);
        }
        obj = list.iterator();
        if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        findtimeattendee = (FindTimeAttendee)((Iterator) (obj)).next();
        if (!findtimeattendee.isOrganizer)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        obj = findtimeattendee.email;
_L4:
        Collections.sort(list, new FindTimeAttendee.RequesterFirstComparator(((String) (obj))));
        bundle.putParcelableArrayList("attendees", list);
        bundle.putParcelable("account", account1);
        bundle.putLong("startMillis", l);
        bundle.putLong("endMillis", l1);
        bundle.putString("timezone", s);
        bundle.putString("event_reference_id", s1);
        bundle.putBoolean("is_recurring_event", flag);
        bundle.putBoolean("is_test_environment", flag1);
        bundle.putString("existing_event_id", s2);
        bundle.putString("existing_event_calendar_id", s3);
        return;
_L2:
        obj = account1.name;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final FindTimeSuggestionUi findOrCreateFindTimeSuggestionFragment()
    {
        Fragment fragment = super.mFragmentManager.findFragmentByTag("find_time_suggestion_fragment");
        if (fragment != null)
        {
            return (FindTimeSuggestionUi)fragment;
        } else
        {
            int i = eventColor;
            DurationTimeframe durationtimeframe = durationTimeframe;
            String s = timezone.getID();
            Account account1 = account;
            FindTime2UiSuggestionFragment findtime2uisuggestionfragment = new FindTime2UiSuggestionFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("event_color", i);
            bundle.putParcelable("duration_timeframe", durationtimeframe);
            bundle.putString("timezone", s);
            bundle.putString("account_name", account1.name);
            bundle.putString("account_type", account1.type);
            findtime2uisuggestionfragment.setArguments(bundle);
            super.mFragmentManager.beginTransaction().add(0x7f10013c, findtime2uisuggestionfragment, "find_time_suggestion_fragment").commit();
            return (FindTimeSuggestionUi)findtime2uisuggestionfragment;
        }
    }

    private final List getAttendees(DurationTimeframe durationtimeframe)
    {
        ArrayList arraylist = new ArrayList(attendees);
        if (durationtimeframe != null && !durationtimeframe.considerExistingRooms)
        {
            durationtimeframe = (ArrayList)attendees;
            int k = durationtimeframe.size();
            int i = 0;
            do
            {
                if (i >= k)
                {
                    break;
                }
                Object obj = durationtimeframe.get(i);
                int j = i + 1;
                obj = (FindTimeAttendee)obj;
                i = j;
                if (((FindTimeAttendee) (obj)).email.endsWith("resource.calendar.google.com"))
                {
                    arraylist.remove(obj);
                    i = j;
                }
            } while (true);
        }
        return arraylist;
    }

    private final int getBackendSuggestionPosition(TimelineSuggestion timelinesuggestion)
    {
        boolean flag;
        for (int i = 0; i < clientResult.suggestions.size(); i++)
        {
            Object obj = clientResult.suggestions.get(i);
            if (obj == timelinesuggestion || obj != null && obj.equals(timelinesuggestion))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return i;
            }
        }

        return -1;
    }

    private final SuggestionRows getSuggestionRows(List list, int i, List list1)
    {
        Object obj;
        Object obj1;
        Object obj2;
        int j;
        int k;
        obj1 = requireContext().getResources().getString(0x7f130210);
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj1 = new SuggestionBuckets(((Context) (obj)), ((String) (obj1)));
        obj2 = timezone;
        obj = new ArrayList();
        if (list1 != null && !list1.isEmpty())
        {
            ((List) (obj)).add(new SuggestionRow(list1));
        }
        if (i == 0)
        {
            ((List) (obj)).add(new SuggestionRow());
        }
        k = SuggestionBuckets.getBestTimesCount(list, i);
        if (!list.isEmpty()) goto _L2; else goto _L1
_L1:
        list1 = SuggestionBuckets.EMPTY_SUGGESTION_BUCKET;
_L6:
        obj1 = new ArrayList();
        j = 0;
_L4:
        if (j >= list1.size())
        {
            break; /* Loop/switch isn't completed */
        }
        obj2 = (SuggestionBucket)list1.get(j);
        ((List) (obj1)).add(new SuggestionRow(((SuggestionBucket) (obj2)).displayLabel, j));
        SimpleArrayMap simplearraymap;
        String s;
        for (k = 0; k < ((SuggestionBucket) (obj2)).suggestions.size(); k++)
        {
            ((List) (obj1)).add(new SuggestionRow((TimelineSuggestion)((SuggestionBucket) (obj2)).suggestions.get(k), ((SuggestionBucket) (obj2)).isBestBucket, j));
        }

        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (k < 0 || k > list.size())
        {
            LogUtils.wtf(SuggestionBuckets.TAG, "Best times size must be between 0 and suggestion size %d", new Object[] {
                Integer.valueOf(k)
            });
            list1 = SuggestionBuckets.EMPTY_SUGGESTION_BUCKET;
        } else
        {
            if (k > list.size())
            {
                LogUtils.wtf(SuggestionBuckets.TAG, "Best times size must not exceed suggestion size %d", new Object[] {
                    Integer.valueOf(k)
                });
                list1 = SuggestionBuckets.EMPTY_SUGGESTION_BUCKET;
            } else
            {
                list1 = new ArrayList();
                simplearraymap = new SimpleArrayMap();
                for (j = k; j < list.size(); j++)
                {
                    s = Utils.getDisplayedSingleDate(((TimelineEvent) ((TimelineSuggestion)list.get(j))).timeRange.getUtcStartMillis(), ((TimeZone) (obj2)).getID(), 16, ((SuggestionBuckets) (obj1)).context);
                    if (!simplearraymap.containsKey(s))
                    {
                        simplearraymap.put(s, new ArrayList());
                    }
                    ((List)simplearraymap.get(s)).add((TimelineSuggestion)list.get(j));
                }

                j = 0;
                while (j < simplearraymap.size()) 
                {
                    Collections.sort((List)simplearraymap.mArray[(j << 1) + 1], SuggestionBuckets.SUGGESTION_STARTTIME_COMPARATOR);
                    list1.add(new SuggestionBucket((String)simplearraymap.mArray[j << 1], (List)simplearraymap.mArray[(j << 1) + 1], false));
                    j++;
                }
            }
            Collections.sort(list1, SuggestionBuckets.BUCKET_STARTTIME_COMPARATOR);
            if (k > 0)
            {
                obj2 = new ArrayList();
                for (j = 0; j < k; j++)
                {
                    ((List) (obj2)).add((TimelineSuggestion)list.get(j));
                }

                list1.add(0, new SuggestionBucket(((SuggestionBuckets) (obj1)).bestTimeLabel, ((List) (obj2)), true));
            }
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        ((List) (obj)).addAll(((java.util.Collection) (obj1)));
        return new SuggestionRows(((List) (obj)), SuggestionBuckets.getBestTimesCount(list, i));
        if (true) goto _L6; else goto _L5
_L5:
    }

    private final void logSuggestionSelected(boolean flag, int i, String s)
    {
        Object obj;
        Context context;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        context = ((FragmentActivity) (obj)).getApplicationContext();
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        AnalyticsLogger analyticslogger = (AnalyticsLogger)obj;
        if (i == -1)
        {
            obj = "custom";
        } else
        {
            obj = String.valueOf(i);
        }
        analyticslogger.setCustomDimension(context, 39, ((String) (obj)));
        if (flag)
        {
            obj = "best";
        } else
        {
            obj = "no_best";
        }
        analyticslogger.setCustomDimension(context, 40, ((String) (obj)));
        analyticslogger.trackEvent(context, "find_a_time", s, "timeslot_selected", null);
    }

    private final void terminateWithSlot(long l, long l1, String s)
    {
        if (state != 2 && state != 6 && state != 8)
        {
            LogUtils.wtf(TAG, "Cannot select any suggestions at state: %d", new Object[] {
                Integer.valueOf(state)
            });
        } else
        {
            setState(-1);
            if (onFinishListener != null)
            {
                onFinishListener._mth5154KJ3AC5R62BRCC5N6EBQJEHP6IRJ77D66KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(l, l1, timezone.getID());
                return;
            }
        }
    }

    private final void transitionBackFromDurationTimeframeFilter(DurationTimeframe durationtimeframe)
    {
        int i = state;
        switch (i)
        {
        default:
            LogUtils.wtf(TAG, "Should not transition back from %d", new Object[] {
                Integer.valueOf(i)
            });
            return;

        case 9: // '\t'
            super.mFragmentManager.popBackStackImmediate();
            break;
        }
        suggestionUi = (FindTime2UiSuggestionFragment)super.mFragmentManager.findFragmentByTag("find_time_suggestion_fragment");
        setState(2);
        suggestionUi.setBackFromFilters();
        if (durationtimeframe != null)
        {
            onQuery(durationtimeframe);
            return;
        }
        if (clientResult != null)
        {
            transitionToListShowData();
            return;
        } else
        {
            suggestionUi.setLoading(null);
            return;
        }
    }

    private final boolean transitionToGridShow(int i)
    {
        suggestionIndex = i;
        i = state;
        switch (i)
        {
        default:
            LogUtils.wtf(TAG, "Should not transition to show grid state from %d", new Object[] {
                Integer.valueOf(i)
            });
            return false;

        case 2: // '\002'
        case 6: // '\006'
            suggestionRows = getSuggestionRows(clientResult.suggestions, clientResult.acceptableSuggestions, clientResult.omittedAttendees);
            break;
        }
        FindTimeGridData findtimegriddata = new FindTimeGridData();
        findtimegriddata.suggestions = suggestionRows.timelineSuggestions;
        findtimegriddata.index = suggestionIndex;
        i = suggestionRows.bestTimesCount;
        FindTimeGridFragment findtimegridfragment = (FindTimeGridFragment)super.mFragmentManager.findFragmentByTag("find_time_grid_fragment");
        if (findtimegridfragment != null)
        {
            if (findtimegridfragment.gridData.hashCode() != findtimegriddata.hashCode())
            {
                findtimegridfragment.suggestionIndex = findtimegriddata.index;
                findtimegridfragment.currentSuggestion = (TimelineSuggestion)findtimegriddata.suggestions.get(findtimegridfragment.suggestionIndex);
                findtimegridfragment.isManualTimeSlot = false;
            }
            findtimegridfragment.gridData = findtimegriddata;
            findtimegridfragment.bestTimesCount = i;
            findtimegridfragment.updateBottomPage();
            findtimegridfragment.updateGrid(false);
        } else
        {
            String s = timezone.getID();
            String s1 = account.type;
            String s2 = account.name;
            findtimegridfragment = new FindTimeGridFragment();
            Bundle bundle = new Bundle();
            bundle.putString("timezone", s);
            bundle.putString("account_type", s1);
            bundle.putString("account_name", s2);
            bundle.putParcelable("grid_data", findtimegriddata);
            bundle.putInt("best_times_count", i);
            findtimegridfragment.setArguments(bundle);
            super.mFragmentManager.beginTransaction().replace(0x7f10013c, findtimegridfragment, "find_time_grid_fragment").addToBackStack("find_time_grid").commit();
            super.mFragmentManager.executePendingTransactions();
        }
        gridUi = findtimegridfragment;
        gridUi.setListener(this);
        setState(6);
        return true;
    }

    private final void transitionToLoading(com.google.android.calendar.timely.findatime.net.FindTimeClient.Request request, DurationTimeframe durationtimeframe)
    {
        clientResult = null;
        savedTimeframe = request.timeframe;
        savedConsiderExistingRooms = request.considerExistingRooms;
        state;
        JVM INSTR tableswitch 0 3: default 56
    //                   0 125
    //                   1 125
    //                   2 125
    //                   3 125;
           goto _L1 _L2 _L2 _L2 _L2
_L1:
        callback.nestedFutureCallbackReference.set(null);
        callback = new CancelableFutureCallback(new _cls1());
        request = client.sendRequest(request);
        durationtimeframe = callback;
        com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (durationtimeframe == null)
        {
            throw new NullPointerException();
        } else
        {
            request.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(request, durationtimeframe), _lcls0);
            return;
        }
_L2:
        suggestionUi = findOrCreateFindTimeSuggestionFragment();
        suggestionUi.setListener(this);
        suggestionUi.setLoading(durationtimeframe);
        setState(1);
          goto _L1
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        if (i != 1001)
        {
            super.onActivityResult(i, j, intent);
        } else
        if (j == -1)
        {
            onQuery(durationTimeframe);
            return;
        }
    }

    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            onFinishListener = (OnFinishListener)activity;
            return;
        }
        catch (ClassCastException classcastexception)
        {
            throw new ClassCastException(String.valueOf(activity.toString()).concat(" must implement OnFinishListener"));
        }
    }

    public final void onCancelled()
    {
        transitionBack();
    }

    public final void onCreate(Bundle bundle)
    {
        Object obj = null;
        super.onCreate(bundle);
        Bundle bundle1 = getArguments();
        Object obj1 = bundle1.getString("timezone");
        attendees = bundle1.getParcelableArrayList("attendees");
        timezone = TimeZone.getTimeZone(((String) (obj1)));
        boolean flag;
        if (bundle != null)
        {
            state = bundle.getInt("state");
            savedTimeframe = (FindTimeTimeframe)bundle.getParcelable("timeframe");
            if (bundle.getByte("consider_existing_rooms", (byte)0).byteValue() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            savedConsiderExistingRooms = flag;
            suggestionIndex = bundle.getInt("suggestion_index", -1);
            durationTimeframe = (DurationTimeframe)bundle.getParcelable("duration_timeframe");
        } else
        {
            state = 0;
            savedTimeframe = null;
            savedConsiderExistingRooms = false;
            suggestionIndex = -1;
            long l = bundle1.getLong("startMillis");
            long l1 = bundle1.getLong("endMillis");
            bundle = timezone;
            obj1 = attendees;
            ArrayList arraylist = Utils.loadStringArray(requireContext().getResources(), 0x7f0b0029);
            ArrayList arraylist1 = Utils.loadStringArray(requireContext().getResources(), 0x7f0b0028);
            ArrayList arraylist2 = Utils.loadIntegerArray(requireContext().getResources(), 0x7f0b002a);
            durationTimeframe = DurationTimeframe.getDefault(l, l1, bundle, arraylist, arraylist1, LabelsUtil.getDurationLabels(requireContext().getResources(), arraylist2, false), arraylist2, FindTimeUtil.containsRooms(((List) (obj1))));
        }
        needsRestoreState = true;
        clientResult = null;
        account = (Account)bundle1.getParcelable("account");
        flag = bundle1.getBoolean("is_test_environment");
        obj1 = account;
        bundle = (FindTimeClient)super.mFragmentManager.findFragmentByTag("find_time_client_fragment");
        if (bundle == null)
        {
            if (AccountUtil.isGoogleAccount(((Account) (obj1))))
            {
                String s;
                Bundle bundle2;
                if (super.mHost == null)
                {
                    bundle = null;
                } else
                {
                    bundle = (FragmentActivity)super.mHost.mActivity;
                }
                bundle = bundle.getApplicationContext();
                s = ((Account) (obj1)).name;
                obj1 = new FindTimeRendezvousClient();
                bundle2 = new Bundle();
                bundle = bundle.getResources().getConfiguration().locale;
                if (bundle != null)
                {
                    bundle = bundle.getLanguage();
                } else
                {
                    bundle = null;
                }
                bundle2.putString("language", bundle);
                bundle2.putString("account_email", s);
                bundle2.putBoolean("is_test_environment", flag);
                ((Fragment) (obj1)).setArguments(bundle2);
                bundle = ((Bundle) (obj1));
            } else
            if (AccountUtil.isGoogleExchangeAccount(((Account) (obj1))) || AccountUtil.isGoogleExchangeGoAccount(((Account) (obj1))))
            {
                if (super.mHost == null)
                {
                    bundle = null;
                } else
                {
                    bundle = (FragmentActivity)super.mHost.mActivity;
                }
                bundle = new FindTimeExchangeClient(bundle.getApplicationContext(), ((Account) (obj1)).name);
            } else
            {
                bundle = ((Account) (obj1)).type;
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(bundle).length() + 27)).append("Account type ").append(bundle).append(" not supported").toString());
            }
            super.mFragmentManager.beginTransaction().add(0x7f10013c, (Fragment)bundle, "find_time_client_fragment").commit();
        }
        client = bundle;
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        eventColor = bundle1.getInt("event_color", ContextCompat.getColor(bundle, 0x7f0d0084));
        suggestionUi = findOrCreateFindTimeSuggestionFragment();
    }

    public final void onDestroy()
    {
        client = null;
        suggestionUi = null;
        gridUi = null;
        super.onDestroy();
    }

    public final void onDetach()
    {
        onFinishListener = null;
        super.onDetach();
    }

    public final void onFilterApply(DurationTimeframe durationtimeframe)
    {
        Object obj;
        Object obj1;
        if (clientResult != null)
        {
            boolean flag;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = CalendarClientLogger.getInstance(((Context) (obj)));
            obj1 = durationTimeframe;
            if (obj1 == durationtimeframe || obj1 != null && obj1.equals(durationtimeframe))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj1 = getArguments().getString("event_reference_id");
                String s = clientResult.responseId;
                ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_FILTERS_CHANGED, s, 0x80000000, false, null, null, null, null, null, ((String) (obj1))));
            }
        }
        transitionBackFromDurationTimeframeFilter(durationtimeframe);
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).getApplicationContext();
        obj1 = AnalyticsLoggerHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj1).trackEvent(((Context) (obj)), "find_a_time", "filter_timeframe_v2", String.format("applied:%s", new Object[] {
            durationtimeframe.getTimeframeTag()
        }), null);
        obj1 = AnalyticsLoggerHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)obj1).trackEvent(((Context) (obj)), "find_a_time", "filter_duration_v2", String.format("applied:%s", new Object[] {
                Integer.valueOf(durationtimeframe.durationInMinutes)
            }), null);
            durationTimeframe = new DurationTimeframe(durationtimeframe);
            return;
        }
    }

    public final void onFilterBack()
    {
        transitionBackFromDurationTimeframeFilter(null);
        Object obj;
        AnalyticsLogger analyticslogger;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).getApplicationContext();
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(((Context) (obj)), "find_a_time", "filter_v2", "back", null);
            return;
        }
    }

    public final void onFiltersOpen(DurationTimeframe durationtimeframe)
    {
        durationtimeframe = new DurationTimeframe(durationtimeframe);
        int i = state;
        switch (i)
        {
        default:
            LogUtils.wtf(TAG, "Should not transition to filters state from %d", new Object[] {
                Integer.valueOf(i)
            });
            return;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            i = eventColor;
            break;
        }
        Object obj = timezone.getID();
        DurationTimeframeFragment durationtimeframefragment = new DurationTimeframeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("event_color", i);
        bundle.putString("timezone", ((String) (obj)));
        bundle.putParcelable("duration_timeframe", durationtimeframe);
        durationtimeframefragment.setArguments(bundle);
        durationtimeframefragment.setTargetFragment(this, -1);
        super.mFragmentManager.beginTransaction().replace(0x7f10013c, durationtimeframefragment, "find_time_filters_fragment").addToBackStack("find_time_filters").commit();
        super.mFragmentManager.executePendingTransactions();
        setState(9);
        if (super.mHost == null)
        {
            durationtimeframe = null;
        } else
        {
            durationtimeframe = (FragmentActivity)super.mHost.mActivity;
        }
        durationtimeframe.getWindow().getDecorView().sendAccessibilityEvent(32);
        if (super.mHost == null)
        {
            durationtimeframe = null;
        } else
        {
            durationtimeframe = (FragmentActivity)super.mHost.mActivity;
        }
        durationtimeframe = durationtimeframe.getApplicationContext();
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)obj).trackEvent(durationtimeframe, "find_a_time", "filter_v2", "opened", null);
            return;
        }
    }

    public final void onGridCancelled()
    {
        transitionBack();
    }

    public final void onGridSuggestionSwiped(TimelineSuggestion timelinesuggestion, int i)
    {
        Object obj;
        String s;
        String s1;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = CalendarClientLogger.getInstance(((Context) (obj)));
        s = getArguments().getString("event_reference_id");
        s1 = clientResult.responseId;
        timelinesuggestion = timelinesuggestion.suggestionId;
        ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_GRID_VIEW_OPENED, s1, 0x80000000, false, timelinesuggestion, Integer.valueOf(i), null, null, null, s));
    }

    public final void onGridTimeSlotSelected(TimelineSuggestion timelinesuggestion, boolean flag, boolean flag1)
    {
        Object obj;
label0:
        {
            logSuggestionSelected(flag, getBackendSuggestionPosition(timelinesuggestion), "grid_view");
            terminateWithSlot(((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis(), ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis(), null);
            if (clientResult != null)
            {
                String s;
                String s2;
                long l;
                long l1;
                if (super.mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)super.mHost.mActivity;
                }
                obj = CalendarClientLogger.getInstance(((Context) (obj)));
                if (!flag1)
                {
                    break label0;
                }
                s = getArguments().getString("event_reference_id");
                s2 = clientResult.responseId;
                l = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis();
                l1 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis();
                ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_GRID_VIEW_CUSTOM_TIME_ACCEPTED, s2, 0x80000000, false, null, null, Long.valueOf(l), Long.valueOf(l1), null, s));
            }
            return;
        }
        int i = suggestionRows.timelineSuggestions.indexOf(timelinesuggestion);
        String s1 = getArguments().getString("event_reference_id");
        String s3 = clientResult.responseId;
        String s4 = timelinesuggestion.suggestionId;
        if (i == -1)
        {
            timelinesuggestion = null;
        } else
        {
            timelinesuggestion = Integer.valueOf(i);
        }
        ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_GRID_VIEW_SUGGESTION_ACCEPTED, s3, 0x80000000, false, s4, timelinesuggestion, null, null, null, s1));
    }

    public final void onQuery(DurationTimeframe durationtimeframe)
    {
        transitionToLoading(new com.google.android.calendar.timely.findatime.net.FindTimeClient.Request(getAttendees(durationtimeframe), durationtimeframe.getTimeframe(timezone), timezone, durationtimeframe.considerExistingRooms, getArguments().getString("event_reference_id"), getArguments().getString("existing_event_id"), getArguments().getString("existing_event_calendar_id")), durationtimeframe);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putInt("state", state);
        bundle.putParcelable("timeframe", savedTimeframe);
        byte byte0;
        if (savedConsiderExistingRooms)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        bundle.putByte("consider_existing_rooms", byte0);
        bundle.putInt("suggestion_index", suggestionIndex);
        bundle.putParcelable("duration_timeframe", durationTimeframe);
        super.onSaveInstanceState(bundle);
    }

    public final void onShowMore()
    {
        suggestionUi.showMore();
        if (clientResult != null)
        {
            Object obj;
            String s;
            String s1;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = CalendarClientLogger.getInstance(((Context) (obj)));
            s = getArguments().getString("event_reference_id");
            s1 = clientResult.responseId;
            ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_SHOW_MORE_CLICKED, s1, 0x80000000, false, null, null, null, null, null, s));
        }
    }

    public final void onStart()
    {
        super.onStart();
        if (suggestionUi != null)
        {
            suggestionUi.setListener(this);
        }
        if (gridUi != null)
        {
            gridUi.setListener(this);
        }
        if (needsRestoreState)
        {
            int i = state;
            if (clientResult == null)
            {
                FindTimeTimeframe findtimetimeframe;
                List list;
                boolean flag;
                boolean flag1;
                if (savedTimeframe != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    flag1 = savedConsiderExistingRooms;
                } else
                {
                    flag1 = durationTimeframe.considerExistingRooms;
                }
                list = getAttendees(durationTimeframe);
                if (flag)
                {
                    findtimetimeframe = savedTimeframe;
                } else
                {
                    findtimetimeframe = durationTimeframe.getTimeframe(timezone);
                }
                transitionToLoading(new com.google.android.calendar.timely.findatime.net.FindTimeClient.Request(list, findtimetimeframe, timezone, flag1, getArguments().getString("event_reference_id"), getArguments().getString("existing_event_id"), getArguments().getString("existing_event_calendar_id")), durationTimeframe);
            }
            if (clientResult != null && suggestionIndex >= 0 && i == 6)
            {
                transitionToGridShow(suggestionIndex);
            }
            if (clientResult != null && i == 9)
            {
                setState(9);
            }
            needsRestoreState = false;
        }
    }

    public final void onStop()
    {
        if (state == 1)
        {
            needsRestoreState = true;
        }
        callback.nestedFutureCallbackReference.set(null);
        if (suggestionUi != null)
        {
            suggestionUi.setListener(null);
        }
        if (gridUi != null)
        {
            gridUi.setListener(null);
        }
        super.onStop();
    }

    public final void onTimeSlotMoreSelected(TimelineSuggestion timelinesuggestion)
    {
        int i = suggestionRows.timelineSuggestions.indexOf(timelinesuggestion);
        if (i == -1)
        {
            LogUtils.wtf(TAG, "Could not find position of suggestion, this is totally unexpected!", new Object[0]);
            i = 0;
        }
        if (transitionToGridShow(i) && clientResult != null)
        {
            Object obj;
            String s;
            String s1;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = CalendarClientLogger.getInstance(((Context) (obj)));
            s = getArguments().getString("event_reference_id");
            s1 = clientResult.responseId;
            timelinesuggestion = timelinesuggestion.suggestionId;
            ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_GRID_VIEW_OPENED, s1, 0x80000000, false, timelinesuggestion, Integer.valueOf(i), null, null, null, s));
        }
    }

    public final void onTimeSlotSelected(SuggestionRow suggestionrow)
    {
        int i = getBackendSuggestionPosition(suggestionrow.suggestion);
        logSuggestionSelected(suggestionrow.isBestTime, i, "suggestion_view");
        terminateWithSlot(((TimelineEvent) (suggestionrow.suggestion)).timeRange.getUtcStartMillis(), ((TimelineEvent) (suggestionrow.suggestion)).timeRange.getUtcEndMillis(), null);
        if (clientResult != null)
        {
            Object obj;
            String s;
            String s1;
            String s2;
            int j;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = CalendarClientLogger.getInstance(((Context) (obj)));
            j = suggestionRows.timelineSuggestions.indexOf(suggestionrow.suggestion);
            s = getArguments().getString("event_reference_id");
            s1 = clientResult.responseId;
            s2 = suggestionrow.suggestion.suggestionId;
            if (j == -1)
            {
                suggestionrow = null;
            } else
            {
                suggestionrow = Integer.valueOf(j);
            }
            ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_SUGGESTION_VIEW_SUGGESTION_ACCEPTED, s1, 0x80000000, false, s2, suggestionrow, null, null, null, s));
        }
    }

    final void setState(int i)
    {
        state = i;
        DurationTimeframeFragment durationtimeframefragment;
        switch (state)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        default:
            return;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            suggestionUi.customizeSystemDecorations();
            return;

        case 6: // '\006'
            gridUi.customizeSystemDecorations();
            return;

        case 9: // '\t'
            durationtimeframefragment = (DurationTimeframeFragment)super.mFragmentManager.findFragmentByTag("find_time_filters_fragment");
            break;
        }
        Object obj;
        if (((Fragment) (durationtimeframefragment)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (durationtimeframefragment)).mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).getWindow();
        i = durationtimeframefragment.eventColor;
        obj = StatusbarAnimatorCompat.createInstance(((Window) (obj)));
        ((StatusbarAnimatorCompat) (obj)).setStatusbarColor(ColorUtils.blend(i, 0x33000000));
        ((StatusbarAnimatorCompat) (obj)).setLightStatusbar(false);
    }

    public final void transitionBack()
    {
        state;
        JVM INSTR tableswitch 1 9: default 56
    //                   1 81
    //                   2 81
    //                   3 81
    //                   4 56
    //                   5 56
    //                   6 114
    //                   7 56
    //                   8 56
    //                   9 157;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L3 _L1 _L1 _L4
_L1:
        LogUtils.wtf(TAG, "Should not transition back from %d", new Object[] {
            Integer.valueOf(state)
        });
_L6:
        return;
_L2:
        setState(-1);
        callback.nestedFutureCallbackReference.set(null);
        if (onFinishListener == null) goto _L6; else goto _L5
_L5:
        onFinishListener.onFinishedWithCancel();
        return;
_L3:
        super.mFragmentManager.popBackStackImmediate();
        suggestionUi = (FindTime2UiSuggestionFragment)super.mFragmentManager.findFragmentByTag("find_time_suggestion_fragment");
        setState(2);
        suggestionUi.setBackFromGrid();
        transitionToListShowData();
        return;
_L4:
        transitionBackFromDurationTimeframeFilter(null);
        Object obj;
        AnalyticsLogger analyticslogger;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).getApplicationContext();
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(((Context) (obj)), "find_a_time", "filter_v2", "back", null);
            return;
        }
    }

    final void transitionToListShowData()
    {
        Object obj1 = null;
        suggestionUi = findOrCreateFindTimeSuggestionFragment();
        suggestionUi.setListener(this);
        switch (state)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        default:
            LogUtils.wtf(TAG, "Should not transition to list show state from %d", new Object[] {
                Integer.valueOf(state)
            });
            // fall through

        case 6: // '\006'
        case 9: // '\t'
            return;

        case 1: // '\001'
        case 2: // '\002'
            setState(2);
            break;
        }
        Object obj2 = clientResult;
        Object obj;
        if (((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).suggestions.isEmpty() || ((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).consideredAttendees.size() <= 1)
        {
            suggestionUi.setNoSuggestion(((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).consideredAttendees, ((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).omittedAttendees);
        } else
        {
            obj = (ImmutableList)((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).suggestions;
            int k = ((ImmutableList) (obj)).size();
            Object obj3 = (UnmodifiableIterator)null;
            for (int i = 0; i < k;)
            {
                obj3 = ((ImmutableList) (obj)).get(i);
                i++;
                ((TimelineSuggestion)obj3).color = eventColor;
            }

            suggestionRows = getSuggestionRows(((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).suggestions, ((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).acceptableSuggestions, ((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).omittedAttendees);
            suggestionUi.setSuggestions$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL6TB7CTIN6T39DTN54RRNECTKOQJ1EPGIUTBKD5M2UJ39EDQ3MJ3AC5R62BRLEHKMOBQCD5PN8EP9AO______0(suggestionRows);
            int j;
            boolean flag;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = CalendarClientLogger.getInstance(((Context) (obj)));
            obj3 = getArguments().getString("event_reference_id");
            obj2 = ((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj2)).responseId;
            j = suggestionRows.bestTimesCount;
            flag = getArguments().getBoolean("is_recurring_event", false);
            ((CalendarClientLogger) (obj)).log(account, ((CalendarClientLogger) (obj)).getFindTimeProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.FIND_TIME_SUGGESTION_VIEW_SHOWN, ((String) (obj2)), j, flag, null, null, null, null, null, ((String) (obj3))));
        }
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ((FragmentActivity) (obj)).getWindow().getDecorView().sendAccessibilityEvent(32);
    }


    private class _cls1
        implements FutureCallback
    {

        private final FindTimeControllerFragment this$0;

        public final void onFailure(Throwable throwable)
        {
            FindTimeControllerFragment findtimecontrollerfragment;
            findtimecontrollerfragment = FindTimeControllerFragment.this;
            findtimecontrollerfragment.suggestionUi = (FindTime2UiSuggestionFragment)((Fragment) (findtimecontrollerfragment)).mFragmentManager.findFragmentByTag("find_time_suggestion_fragment");
            findtimecontrollerfragment.suggestionUi.setListener(findtimecontrollerfragment);
            findtimecontrollerfragment.state;
            JVM INSTR tableswitch 1 3: default 60
        //                       1 84
        //                       2 60
        //                       3 84;
               goto _L1 _L2 _L1 _L2
_L1:
            LogUtils.wtf(FindTimeControllerFragment.TAG, "Should not transition to no connection state from %d", new Object[] {
                Integer.valueOf(findtimecontrollerfragment.state)
            });
_L8:
            return;
_L2:
            findtimecontrollerfragment.setState(3);
            findtimecontrollerfragment.suggestionUi.setException();
            Object obj;
            boolean flag;
            if (throwable instanceof Exception)
            {
                obj = throwable.getCause();
            } else
            {
                LogUtils.wtf(FindTimeControllerFragment.TAG, throwable, "Error while retrieving result.", new Object[0]);
                return;
            }
_L13:
            if (obj == null) goto _L4; else goto _L3
_L3:
            if (!(obj instanceof UserRecoverableAuthException)) goto _L6; else goto _L5
_L5:
            obj = (UserRecoverableAuthException)obj;
_L14:
            if (obj != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag) goto _L8; else goto _L7
_L7:
            throwable = throwable.getCause();
_L15:
            if (throwable == null) goto _L10; else goto _L9
_L9:
            if (!(throwable instanceof UserRecoverableAuthException)) goto _L12; else goto _L11
_L11:
            throwable = (UserRecoverableAuthException)throwable;
_L16:
            if (throwable == null)
            {
                throwable = null;
            } else
            if (((UserRecoverableAuthException) (throwable)).mIntent == null)
            {
                throwable = null;
            } else
            {
                throwable = new Intent(((UserRecoverableAuthException) (throwable)).mIntent);
            }
            findtimecontrollerfragment.startActivityForResult(throwable, 1001);
            return;
_L6:
            obj = ((Throwable) (obj)).getCause();
              goto _L13
_L4:
            obj = null;
              goto _L14
_L12:
            throwable = throwable.getCause();
              goto _L15
_L10:
            throwable = null;
              goto _L16
        }

        public final void onSuccess(Object obj)
        {
            obj = (com.google.android.calendar.timely.findatime.net.FindTimeClient.Result)obj;
            clientResult = ((com.google.android.calendar.timely.findatime.net.FindTimeClient.Result) (obj));
            transitionToListShowData();
        }

        _cls1()
        {
            this$0 = FindTimeControllerFragment.this;
            super();
        }
    }

}
