// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.event.FindTimeSuggestionUi;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.android.calendar.timely.OmittedAttendee;
import com.google.android.calendar.timely.SuggestionRow;
import com.google.android.calendar.timely.SuggestionRows;
import com.google.android.calendar.timely.widgets.fullscreenerror.FullScreenErrorPage;
import com.google.android.calendar.timely.widgets.spinner.LabeledSpinner;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.android.calendar.utils.network.NetworkUtil;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            DurationTimeframe, LabelsUtil, FindTime2UiSuggestionItemView

public class FindTime2UiSuggestionFragment extends ListFragment
    implements android.view.View.OnClickListener, android.widget.AbsListView.OnScrollListener, FindTimeSuggestionUi
{
    final class SuggestionAdapter extends ArrayAdapter
        implements FindTime2UiSuggestionItemView.OnMoreInformationRequestListener
    {

        public int count;
        public android.widget.AdapterView.OnItemClickListener onItemSecondaryClickListener;
        public SuggestionRows suggestionRows;
        private final FindTime2UiSuggestionFragment this$0;
        public int totalCount;

        private final int computeCount()
        {
            int i;
            int j;
            boolean flag;
            boolean flag1 = true;
            ImmutableList immutablelist;
            Object obj;
            int k;
            int l;
            if (suggestionRows == null)
            {
                i = 0;
            } else
            {
                SuggestionRows suggestionrows = suggestionRows;
                if (suggestionrows.bestTimesCount == 0)
                {
                    i = 0;
                } else
                {
                    i = suggestionrows.bestTimesCount + 1;
                }
            }
            if (suggestionRows == null) goto _L2; else goto _L1
_L1:
            immutablelist = (ImmutableList)suggestionRows.suggestions;
            k = immutablelist.size();
            obj = (UnmodifiableIterator)null;
            j = 0;
_L5:
            if (j >= k) goto _L2; else goto _L3
_L3:
            obj = immutablelist.get(j);
            j++;
            if (((SuggestionRow)obj).type != 2) goto _L5; else goto _L4
_L4:
            j = 1;
_L6:
            if (suggestionRows == null)
            {
                break MISSING_BLOCK_LABEL_208;
            }
            immutablelist = (ImmutableList)suggestionRows.suggestions;
            l = immutablelist.size();
            obj = (UnmodifiableIterator)null;
            k = 0;
            do
            {
                if (k >= l)
                {
                    break MISSING_BLOCK_LABEL_208;
                }
                obj = immutablelist.get(k);
                k++;
            } while (((SuggestionRow)obj).type != 3);
            flag = true;
_L7:
            l = Math.min(i, 4);
            if (j != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (flag)
            {
                j = ((flag1) ? 1 : 0);
            } else
            {
                j = 0;
            }
            return i + l + j;
_L2:
            j = 0;
              goto _L6
            flag = false;
              goto _L7
        }

        public final void add(Object obj)
        {
            super.add((SuggestionRow)obj);
            totalCount = totalCount + 1;
            count = computeCount();
        }

        public final void addAll(Collection collection)
        {
            super.addAll(collection);
            totalCount = totalCount + collection.size();
            count = computeCount();
        }

        public final boolean areAllItemsEnabled()
        {
            return false;
        }

        public final void clear()
        {
            super.clear();
            count = 0;
            totalCount = 0;
        }

        public final int getCount()
        {
            return count;
        }

        public final int getItemViewType(int i)
        {
            SuggestionRow suggestionrow = (SuggestionRow)getItem(i);
            if (suggestionrow.type != 1 && suggestionrow.type != 0 && suggestionrow.type != 2 && suggestionrow.type != 3)
            {
                LogUtils.wtf(FindTime2UiSuggestionFragment.TAG, "Unrecognized row type %d", new Object[] {
                    Integer.valueOf(suggestionrow.type)
                });
                return 1;
            } else
            {
                return suggestionrow.type;
            }
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            Object obj;
            Object obj1;
            SuggestionRow suggestionrow;
            int j;
            obj1 = null;
            obj = null;
            j = getItemViewType(i);
            suggestionrow = (SuggestionRow)getItem(i);
            j;
            JVM INSTR tableswitch 0 3: default 56
        //                       0 246
        //                       1 81
        //                       2 585
        //                       3 709;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            LogUtils.wtf(FindTime2UiSuggestionFragment.TAG, "Row type unknown %d", new Object[] {
                Integer.valueOf(j)
            });
            viewgroup = null;
_L7:
            return viewgroup;
_L3:
            view = (TextView)view;
            int k;
            if (view == null)
            {
                view = FindTime2UiSuggestionFragment.this;
                int l;
                if (((Fragment) (view)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                }
                viewgroup = (TextView)LayoutInflater.from(view).inflate(0x7f05006e, listView, false);
                if (Material.robotoMedium != null)
                {
                    view = Material.robotoMedium;
                } else
                {
                    view = Typeface.create("sans-serif-medium", 0);
                    Material.robotoMedium = view;
                }
                viewgroup.setTypeface(view);
                view = viewgroup;
            }
            view.setText(suggestionrow.headerName);
            k = requireContext().getResources().getDimensionPixelOffset(0x7f0e0164);
            l = view.getPaddingLeft();
            if (suggestionrow.bucket == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = k;
            }
            view.setPadding(l, i, view.getPaddingRight(), view.getPaddingBottom());
            return view;
_L2:
            viewgroup = (FindTime2UiSuggestionItemView)view;
            view = viewgroup;
            if (viewgroup == null)
            {
                view = new FindTime2UiSuggestionItemView(getContext(), timezone);
                view.onMoreInformationRequestListener = this;
            }
            view.timezone = timezone;
            viewgroup = FindTimeUtil.getInstance(context).getDescription(suggestionrow.suggestion, accountName, accountType);
            if (suggestionrow.type == 1)
            {
                LogUtils.wtf(FindTime2UiSuggestionItemView.TAG, "Should be a suggestion row, not a header row!", new Object[0]);
                return view;
            }
            view.suggestionRow = suggestionrow;
            view.suggestionDescription = viewgroup;
            obj = new StringBuilder();
            viewgroup = new StringBuilder();
            FindTimeUtil.getInstance(view.getContext()).getDisplayedDateTime(((StringBuilder) (obj)), viewgroup, ((FindTime2UiSuggestionItemView) (view)).suggestionRow.suggestion, ((FindTime2UiSuggestionItemView) (view)).timezone.getID());
            ((FindTime2UiSuggestionItemView) (view)).dateView.setText(((CharSequence) (obj)));
            ((FindTime2UiSuggestionItemView) (view)).timeView.setText(viewgroup);
            if (TextUtils.isEmpty(((FindTime2UiSuggestionItemView) (view)).suggestionDescription))
            {
                ((FindTime2UiSuggestionItemView) (view)).descriptionView.setVisibility(8);
            } else
            {
                ((FindTime2UiSuggestionItemView) (view)).descriptionView.setText(((FindTime2UiSuggestionItemView) (view)).suggestionDescription);
                ((FindTime2UiSuggestionItemView) (view)).descriptionView.setVisibility(0);
            }
            obj1 = ((FindTime2UiSuggestionItemView) (view)).dateView;
            if (((FindTime2UiSuggestionItemView) (view)).suggestionRow.isBestTime)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((TextView) (obj1)).setVisibility(i);
            if (((FindTime2UiSuggestionItemView) (view)).suggestionRow.isBestTime)
            {
                i = view.getResources().getDimensionPixelOffset(0x7f0e0185);
            } else
            {
                i = view.getResources().getDimensionPixelOffset(0x7f0e0188);
            }
            view.setMinimumHeight(i);
            obj = ((StringBuilder) (obj)).toString();
            viewgroup = viewgroup.toString();
            obj1 = ((FindTime2UiSuggestionItemView) (view)).suggestionDescription;
            view.setContentDescription(view.getResources().getString(0x7f13005d, new Object[] {
                obj, viewgroup, obj1
            }));
            return view;
_L4:
            viewgroup = view;
            if (view == null)
            {
                view = FindTime2UiSuggestionFragment.this;
                if (((Fragment) (view)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                }
                viewgroup = LayoutInflater.from(view).inflate(0x7f05006f, listView, false);
            }
            view = FindTime2UiSuggestionFragment.this;
            if (((Fragment) (view)).mHost == null)
            {
                view = ((View) (obj));
            } else
            {
                view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
            }
            view = view.getString(0x7f130211, new Object[] {
                Utils.join(suggestionrow.omittedAttendees, ", ")
            });
            ((TextView)viewgroup.findViewById(0x7f1001a4)).setText(view);
            return viewgroup;
_L5:
            viewgroup = view;
            if (view == null)
            {
                view = FindTime2UiSuggestionFragment.this;
                TextView textview;
                if (((Fragment) (view)).mHost == null)
                {
                    view = ((View) (obj1));
                } else
                {
                    view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                }
                viewgroup = LayoutInflater.from(view).inflate(0x7f050066, listView, false);
                textview = (TextView)viewgroup.findViewById(0x7f100199);
                if (Material.robotoMedium != null)
                {
                    view = Material.robotoMedium;
                } else
                {
                    view = Typeface.create("sans-serif-medium", 0);
                    Material.robotoMedium = view;
                }
                textview.setTypeface(view);
                return viewgroup;
            }
            if (true) goto _L7; else goto _L6
_L6:
        }

        public final int getViewTypeCount()
        {
            return 4;
        }

        public final boolean isEnabled(int i)
        {
            SuggestionRow suggestionrow = (SuggestionRow)getItem(i);
            return suggestionrow.type == 0 || suggestionrow.type == 3;
        }

        public final void onMoreInformationRequest(FindTime2UiSuggestionItemView findtime2uisuggestionitemview, SuggestionRow suggestionrow)
        {
            if (onItemSecondaryClickListener != null)
            {
                int i = getPosition(suggestionrow);
                suggestionrow = onItemSecondaryClickListener;
                FindTime2UiSuggestionFragment findtime2uisuggestionfragment = FindTime2UiSuggestionFragment.this;
                findtime2uisuggestionfragment.ensureList();
                suggestionrow.onItemClick(((ListFragment) (findtime2uisuggestionfragment)).mList, findtime2uisuggestionitemview, i, getItemId(i));
            }
        }

        public SuggestionAdapter(Context context1, List list)
        {
            this$0 = FindTime2UiSuggestionFragment.this;
            super(context1, 0x7f050067, list);
            totalCount = list.size();
            count = computeCount();
        }
    }


    private static final List EMPTY_SUGGESTION = new ArrayList();
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/ui/FindTime2UiSuggestionFragment);
    public String accountName;
    public String accountType;
    public SuggestionAdapter adapter;
    public Context context;
    public DurationTimeframe durationTimeframe;
    private View emptyView;
    private int eventColor;
    private View footer;
    public ListView listView;
    public com.google.android.calendar.event.FindTimeSuggestionUi.Listener listener;
    private LabeledSpinner loadingSpinner;
    private FullScreenErrorPage noResultsView;
    private int numberOfItemsShown;
    private View showMoreView;
    private TextView timeframeDurationChangeButtonView;
    private TextView timeframeDurationView;
    public TimeZone timezone;
    private Toolbar toolbar;
    private int toolbarElevation;

    public FindTime2UiSuggestionFragment()
    {
        numberOfItemsShown = -2;
    }

    private final void setErrorScreen(boolean flag, boolean flag1, boolean flag2)
    {
        emptyView.setVisibility(8);
        loadingSpinner.setVisibility(8);
        adapter.clear();
        adapter.notifyDataSetChanged();
        Object obj;
        if (flag)
        {
            View view = footer;
            if (view != null)
            {
                view.setVisibility(8);
            }
            noResultsView.setTitle(0x7f1301cf);
            noResultsView.setSubtitle(0x7f1301ce);
        } else
        if (flag1)
        {
            noResultsView.setTitle(0x7f130206);
            if (flag2)
            {
                obj = footer;
                if (obj != null)
                {
                    ((View) (obj)).setVisibility(8);
                }
                noResultsView.setSubtitle(0x7f130209);
            } else
            {
                obj = footer;
                if (obj != null)
                {
                    ((View) (obj)).setVisibility(0);
                }
                noResultsView.setSubtitle(0x7f130205);
            }
        } else
        {
            obj = footer;
            if (obj != null)
            {
                ((View) (obj)).setVisibility(0);
            }
            noResultsView.setTitle(0x7f130208);
            noResultsView.setSubtitle(0x7f130207);
        }
        A11yHelper.getInstance();
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (A11yHelper.isAccessibilityEnabled(((Context) (obj))))
        {
            noResultsView.sendAccessibilityEvent(32768);
        }
    }

    private final void showListFooter(boolean flag)
    {
        if (flag)
        {
            if (listView.getFooterViewsCount() == 0)
            {
                listView.addFooterView(showMoreView);
                showMoreView.setVisibility(0);
            }
            return;
        } else
        {
            showMoreView.setVisibility(8);
            listView.removeFooterView(showMoreView);
            return;
        }
    }

    public final void customizeSystemDecorations()
    {
        Object obj;
        int i;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).getWindow();
        i = eventColor;
        obj = StatusbarAnimatorCompat.createInstance(((android.view.Window) (obj)));
        ((StatusbarAnimatorCompat) (obj)).setStatusbarColor(ColorUtils.blend(i, 0x33000000));
        ((StatusbarAnimatorCompat) (obj)).setLightStatusbar(false);
    }

    public final String getLoadingString()
    {
        Object obj = loadingSpinner;
        if (((LabeledSpinner) (obj)).getVisibility() == 0)
        {
            obj = (String)((LabeledSpinner) (obj)).labels.get(((LabeledSpinner) (obj)).currentLabel);
        } else
        {
            obj = null;
        }
        return Platform.nullToEmpty(((String) (obj)));
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        toolbarElevation = requireContext().getResources().getDimensionPixelSize(0x7f0e0147);
        super.ensureList();
        listView = super.mList;
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        showMoreView = LayoutInflater.from(((Context) (obj))).inflate(0x7f050070, listView, false);
        showMoreView.setOnClickListener(this);
        listView.addFooterView(showMoreView);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(new _cls2());
        if (bundle != null)
        {
            durationTimeframe = (DurationTimeframe)bundle.getParcelable("duration_timeframe");
            numberOfItemsShown = bundle.getInt("number_of_items_shown");
        } else
        {
            bundle = AnalyticsLoggerHolder.instance;
            if (bundle == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            bundle = (AnalyticsLogger)bundle;
            bundle.trackEvent(context, "find_a_time", "suggestion_view", "opened", null);
            bundle.trackEvent(context, "find_a_time", "filter_duration", String.format("initial:%s", new Object[] {
                String.valueOf(durationTimeframe.durationInMinutes)
            }), null);
            bundle.trackEvent(context, "find_a_time", "filter_timeframe", String.format("initial:%s", new Object[] {
                durationTimeframe.getTimeframeTag()
            }), null);
        }
        durationTimeframe = durationTimeframe;
        setTimeframeDurationLabel();
        timeframeDurationChangeButtonView.setOnClickListener(this);
        adapter.onItemSecondaryClickListener = new _cls3();
    }

    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    public void onClick(View view)
    {
        int i = view.getId();
        if (i == 0x7f1001ab)
        {
            listener.onFiltersOpen(durationTimeframe);
        } else
        if (i == 0x7f1001a5)
        {
            listener.onShowMore();
            return;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        durationTimeframe = (DurationTimeframe)getArguments().getParcelable("duration_timeframe");
        timezone = TimeZone.getTimeZone(getArguments().getString("timezone"));
        accountName = getArguments().getString("account_name");
        accountType = getArguments().getString("account_type");
        eventColor = getArguments().getInt("event_color");
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        adapter = new SuggestionAdapter(bundle, EMPTY_SUGGESTION);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = (ViewGroup)layoutinflater.inflate(0x7f050071, viewgroup, false);
        toolbar = (Toolbar)viewgroup.findViewById(0x7f100113);
        loadingSpinner = (LabeledSpinner)viewgroup.findViewById(0x7f1001a7);
        loadingSpinner.setLabels(ImmutableList.copyOf(requireContext().getResources().getStringArray(0x7f0b002b)));
        loadingSpinner.setSpinnerColor(eventColor);
        noResultsView = (FullScreenErrorPage)viewgroup.findViewById(0x1020004);
        noResultsView.setTitle(0x7f130208);
        noResultsView.setSubtitle(0x7f130207);
        emptyView = viewgroup.findViewById(0x7f1001a8);
        toolbar.setBackgroundColor(eventColor);
        layoutinflater = toolbar;
        bundle = new _cls1();
        layoutinflater.ensureNavButtonView();
        ((Toolbar) (layoutinflater)).mNavButtonView.setOnClickListener(bundle);
        viewgroup.findViewById(0x7f1001a6);
        footer = viewgroup.findViewById(0x7f1001a9);
        if (Material.robotoMedium != null)
        {
            layoutinflater = Material.robotoMedium;
        } else
        {
            layoutinflater = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = layoutinflater;
        }
        timeframeDurationView = (TextView)viewgroup.findViewById(0x7f100191);
        timeframeDurationChangeButtonView = (TextView)viewgroup.findViewById(0x7f1001ab);
        timeframeDurationChangeButtonView.setTypeface(layoutinflater);
        return viewgroup;
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        int i;
        if (adapter.getCount() == 0)
        {
            i = -2;
        } else
        {
            i = adapter.getCount();
        }
        bundle.putInt("number_of_items_shown", i);
        bundle.putParcelable("duration_timeframe", durationTimeframe);
        super.onSaveInstanceState(bundle);
    }

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        j = 1;
        if (listView == null) goto _L2; else goto _L1
_L1:
        if (listView.getFirstVisiblePosition() == 0) goto _L4; else goto _L3
_L3:
        if (j != 0)
        {
            toolbar.setElevation(toolbarElevation);
            return;
        } else
        {
            toolbar.setElevation(0.0F);
            return;
        }
_L4:
        abslistview = listView.getChildAt(0);
        if (abslistview == null)
        {
            i = 0;
        } else
        {
            i = abslistview.getTop() - listView.getPaddingTop();
        }
        if (i != 0) goto _L3; else goto _L2
_L2:
        j = 0;
          goto _L3
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }

    public final void setBackFromFilters()
    {
        TextView textview = timeframeDurationChangeButtonView;
        textview.setVisibility(8);
        textview.jumpDrawablesToCurrentState();
        textview.setVisibility(0);
    }

    public final void setBackFromGrid()
    {
        ListView listview = listView;
        listview.setVisibility(8);
        listview.jumpDrawablesToCurrentState();
        listview.setVisibility(0);
    }

    public final void setException()
    {
        boolean flag;
        if (!NetworkUtil.isConnectedToInternet(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setErrorScreen(flag, true, false);
    }

    public final void setListener(com.google.android.calendar.event.FindTimeSuggestionUi.Listener listener1)
    {
        listener = listener1;
    }

    public final void setLoading(DurationTimeframe durationtimeframe)
    {
        listView.setVisibility(8);
        noResultsView.setVisibility(8);
        emptyView.setVisibility(8);
        loadingSpinner.setVisibility(0);
        if (durationtimeframe != null)
        {
            durationTimeframe = durationtimeframe;
            setTimeframeDurationLabel();
        }
    }

    public final void setNoSuggestion(List list, List list1)
    {
        boolean flag2;
        flag2 = false;
        boolean flag;
        boolean flag1;
        if (!NetworkUtil.isConnectedToInternet(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (list.size() <= 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        list = list1.iterator();
        if (!list.hasNext()) goto _L2; else goto _L1
_L1:
        if (((OmittedAttendee)list.next()).reason == 2)
        {
            break MISSING_BLOCK_LABEL_35;
        }
_L4:
        setErrorScreen(flag, flag1, flag2);
        return;
_L2:
        flag2 = true;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setSuggestions$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL6TB7CTIN6T39DTN54RRNECTKOQJ1EPGIUTBKD5M2UJ39EDQ3MJ3AC5R62BRLEHKMOBQCD5PN8EP9AO______0(SuggestionRows suggestionrows)
    {
        boolean flag1 = true;
        loadingSpinner.setVisibility(8);
        emptyView.setVisibility(8);
        noResultsView.setVisibility(8);
        SuggestionAdapter suggestionadapter = adapter;
        suggestionadapter.suggestionRows = suggestionrows;
        suggestionadapter.clear();
        suggestionadapter.addAll(suggestionrows.suggestions);
        if (numberOfItemsShown != -1 && numberOfItemsShown != -2)
        {
            SuggestionAdapter suggestionadapter1 = adapter;
            int i = numberOfItemsShown;
            suggestionadapter1.count = Math.min(suggestionadapter1.totalCount, i);
            numberOfItemsShown = -1;
        }
        boolean flag;
        if (suggestionrows.bestTimesCount > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            suggestionrows = adapter;
            suggestionrows.count = ((SuggestionAdapter) (suggestionrows)).totalCount;
        }
        adapter.notifyDataSetChanged();
        suggestionrows = adapter;
        if (((SuggestionAdapter) (suggestionrows)).count >= ((SuggestionAdapter) (suggestionrows)).totalCount)
        {
            flag1 = false;
        }
        showListFooter(flag1);
        suggestionrows = footer;
        if (suggestionrows != null)
        {
            suggestionrows.setVisibility(0);
        }
    }

    final void setTimeframeDurationLabel()
    {
        String s = LabelsUtil.getTimeframeDurationLabel(this, durationTimeframe, false);
        timeframeDurationView.setText(s);
        s = requireContext().getResources().getString(0x7f130065, new Object[] {
            s
        });
        timeframeDurationView.setContentDescription(s);
    }

    public final void showMore()
    {
        Object obj = adapter;
        obj.count = ((SuggestionAdapter) (obj)).totalCount;
        adapter.notifyDataSetChanged();
        obj = adapter;
        boolean flag;
        if (((SuggestionAdapter) (obj)).count < ((SuggestionAdapter) (obj)).totalCount)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        showListFooter(flag);
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)obj).trackEvent(context, "find_a_time", "suggestion_view", "show_more", null);
            return;
        }
    }


    private class _cls2
        implements android.widget.AdapterView.OnItemClickListener
    {

        private final FindTime2UiSuggestionFragment this$0;

        public final void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            if (view.getId() == 0x7f1001a5)
            {
                onClick(view);
            } else
            {
                adapterview = (SuggestionRow)listView.getItemAtPosition(i);
                switch (((SuggestionRow) (adapterview)).type)
                {
                case 1: // '\001'
                case 2: // '\002'
                default:
                    return;

                case 0: // '\0'
                    view = FindTime2UiSuggestionFragment.this;
                    if (adapterview != null)
                    {
                        ((FindTime2UiSuggestionFragment) (view)).listener.onTimeSlotSelected(adapterview);
                        return;
                    } else
                    {
                        ((FindTime2UiSuggestionFragment) (view)).listener.onCancelled();
                        return;
                    }

                case 3: // '\003'
                    adapterview = FindTime2UiSuggestionFragment.this;
                    view = AnalyticsLoggerHolder.instance;
                    break;
                }
                if (view == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)view).trackEvent(((FindTime2UiSuggestionFragment) (adapterview)).context, "find_a_time", "suggestion_view", "try_later", null);
                adapterview = DateTimeUtils.getNowDateTime(timezone.getID());
                adapterview = durationTimeframe.getTimeframeEnd(adapterview).plusPeriod(new Period(0, 0, 0, 1, 0, 0, 0L));
                durationTimeframe.timeframeOption = 3;
                durationTimeframe.customDate = DateTimeUtils.getNowDateTime(timezone.getID()).withDate(adapterview.getYear(), adapterview.getMonthOfYear(), adapterview.getDayOfMonth());
                setTimeframeDurationLabel();
                adapterview = FindTime2UiSuggestionFragment.this;
                if (((FindTime2UiSuggestionFragment) (adapterview)).listener != null)
                {
                    ((FindTime2UiSuggestionFragment) (adapterview)).listener.onQuery(((FindTime2UiSuggestionFragment) (adapterview)).durationTimeframe);
                    return;
                }
            }
        }

        _cls2()
        {
            this$0 = FindTime2UiSuggestionFragment.this;
            super();
        }
    }


    private class _cls3
        implements android.widget.AdapterView.OnItemClickListener
    {

        private final FindTime2UiSuggestionFragment this$0;

        public final void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            if (adapter.getItemViewType(i) == 1)
            {
                LogUtils.wtf(FindTime2UiSuggestionFragment.TAG, "Cannot get the suggestion for a header %d", new Object[] {
                    Integer.valueOf(i)
                });
                return;
            } else
            {
                listener.onTimeSlotMoreSelected(((SuggestionRow)adapter.getItem(i)).suggestion);
                return;
            }
        }

        _cls3()
        {
            this$0 = FindTime2UiSuggestionFragment.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final FindTime2UiSuggestionFragment this$0;

        public final void onClick(View view)
        {
            view = FindTime2UiSuggestionFragment.this;
            if (false)
            {
                ((FindTime2UiSuggestionFragment) (view)).listener.onTimeSlotSelected(null);
                return;
            } else
            {
                ((FindTime2UiSuggestionFragment) (view)).listener.onCancelled();
                return;
            }
        }

        _cls1()
        {
            this$0 = FindTime2UiSuggestionFragment.this;
            super();
        }
    }

}
