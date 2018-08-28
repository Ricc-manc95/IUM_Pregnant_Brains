// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.android.calendar.timely.SuggestionRow;
import com.google.android.calendar.timely.SuggestionRows;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            FindTime2UiSuggestionFragment, FindTime2UiSuggestionItemView

final class computeCount extends ArrayAdapter
    implements equestListener
{

    public int count;
    public android.widget.estionFragment.SuggestionAdapter.getItemId onItemSecondaryClickListener;
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
    //                   0 246
    //                   1 81
    //                   2 585
    //                   3 709;
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
            suggestionrow.estionFragment(((ListFragment) (findtime2uisuggestionfragment)).mList, findtime2uisuggestionitemview, i, getItemId(i));
        }
    }

    public equestListener(Context context, List list)
    {
        this$0 = FindTime2UiSuggestionFragment.this;
        super(context, 0x7f050067, list);
        totalCount = list.size();
        count = computeCount();
    }
}
