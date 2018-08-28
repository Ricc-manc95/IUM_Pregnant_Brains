// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker.fullscreen;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import com.android.timezonepicker.TimeZoneManager;
import com.android.timezonepicker.TimeZoneParams;
import com.android.timezonepicker.TimeZonePickerUtils;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public final class TimeZoneFullScreenAdapter extends BaseAdapter
    implements android.widget.AdapterView.OnItemClickListener, com.android.timezonepicker.TimeZoneManager.DataListener
{

    private CharSequence constraint;
    private Context context;
    private TimeZoneManager manager;
    private Date timeToDisplay;
    private TimeZonePickerInitializer.Listener timeZoneSetListener;

    public TimeZoneFullScreenAdapter(Context context1, TimeZoneManager timezonemanager, TimeZonePickerInitializer.Listener listener, long l)
    {
        context = context1;
        timeZoneSetListener = listener;
        manager = timezonemanager;
        timeToDisplay = new Date(l);
    }

    private final CharSequence highlightSecondString(int i, String s, String s1)
    {
        s = context.getResources().getString(i, new Object[] {
            s, s1
        });
        SpannableString spannablestring = new SpannableString(s);
        i = s.lastIndexOf(s1);
        spannablestring.setSpan(new StyleSpan(1), i, constraint.length() + i, 0);
        return spannablestring;
    }

    private static void setUpTile(TextTileView texttileview, CharSequence charsequence, CharSequence charsequence1, int i, boolean flag)
    {
        texttileview.setPrimaryText(new CharSequence[] {
            charsequence
        });
        texttileview.setSecondaryText(new CharSequence[] {
            charsequence1
        });
        texttileview.setIconDrawable(i);
        texttileview.setEnabled(flag);
    }

    private final CharSequence tryHighlightPrefix(String s)
    {
        if (!TextUtils.isEmpty(constraint))
        {
            SpannableString spannablestring = new SpannableString(s);
            String s1 = s.toLowerCase();
            String as[] = s1.split(" ");
            String s2 = constraint.toString().toLowerCase();
            int k = as.length;
            int i = 0;
            int j = 0;
            while (i < k) 
            {
                String s3 = as[i];
                if (s1.substring(j).startsWith(s2))
                {
                    spannablestring.setSpan(new StyleSpan(1), j, constraint.length() + j, 0);
                    return spannablestring;
                }
                j += s3.length() + 1;
                i++;
            }
        }
        return s;
    }

    public final int getCount()
    {
        return manager.onDisplay.size();
    }

    public final Object getItem(int i)
    {
        return (TimeZoneParams)manager.onDisplay.get(i);
    }

    public final long getItemId(int i)
    {
        return TimeZoneManager.getIdByIndex(i);
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        view = (TextTileView)view;
        viewgroup = view;
        if (view == null)
        {
            viewgroup = new TextTileView(context);
        }
        Object obj2 = (TimeZoneParams)manager.onDisplay.get(i);
        if (TimeZoneParams.STUB == obj2)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            setUpTile(viewgroup, context.getResources().getString(0x7f13049d), "", 0x7f020233, false);
            return viewgroup;
        }
        view = StringUtils.capitalizeStandalone(((TimeZoneParams) (obj2)).getDisplayName(), Locale.getDefault());
        Object obj = ((TimeZoneParams) (obj2)).getNameAbbreviation();
        Object obj1;
        String s;
        StringBuilder stringbuilder;
        if (obj != null)
        {
            obj = highlightSecondString(0x7f13049f, view, ((String) (obj)));
        } else
        {
            obj = tryHighlightPrefix(view);
        }
        view = context;
        obj1 = timeToDisplay;
        i = ((TimeZoneParams) (obj2)).getOffset();
        s = ((TimeZoneParams) (obj2)).getId();
        stringbuilder = new StringBuilder(50);
        DateUtils.formatDateRange(view, new Formatter(stringbuilder, Locale.getDefault()), ((Date) (obj1)).getTime(), ((Date) (obj1)).getTime(), 0x80001, s);
        stringbuilder.append("  ");
        TimeZonePickerUtils.appendGmtOffset(stringbuilder, i);
        obj1 = stringbuilder.toString();
        s = ((TimeZoneParams) (obj2)).getCountry();
        view = ((View) (obj1));
        if (s != null)
        {
            view = ((TimeZoneParams) (obj2)).getCountryAbbreviation();
            obj2 = ((TimeZoneParams) (obj2)).getCity();
            if (view != null)
            {
                view = TextUtils.concat(new CharSequence[] {
                    obj1, "\n", highlightSecondString(0x7f13049b, s, view)
                });
            } else
            if (obj2 != null)
            {
                view = TextUtils.concat(new CharSequence[] {
                    obj1, "\n", highlightSecondString(0x7f13049c, s, ((String) (obj2)))
                });
            } else
            {
                view = TextUtils.concat(new CharSequence[] {
                    obj1, "\n", tryHighlightPrefix(s)
                });
            }
        }
        setUpTile(viewgroup, ((CharSequence) (obj)), view, 0x7f020222, true);
        return viewgroup;
    }

    public final void onDataChanged(CharSequence charsequence)
    {
        constraint = charsequence;
        notifyDataSetChanged();
    }

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if (timeZoneSetListener != null)
        {
            adapterview = (TimeZoneParams)manager.onDisplay.get(i);
            if (adapterview != null)
            {
                if (TimeZoneParams.STUB == adapterview)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    timeZoneSetListener.onTimeZoneSelected(adapterview);
                }
            }
        }
    }
}
