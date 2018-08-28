// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.common.collect.ImmutableCollection;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomUiItem, RoomUiItemViewProvider

public class RoomBookingAdapter extends ArrayAdapter
    implements SectionIndexer
{
    static final class Section
    {

        private final String name;
        public final int position;

        public final String toString()
        {
            return name;
        }

        Section(String s, int i)
        {
            name = s;
            position = i;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/rooms/ui/RoomBookingAdapter);
    public int sectionMap[];
    public final List sections = new ArrayList();
    private final RoomUiItemViewProvider viewProvider;

    public RoomBookingAdapter(Context context, RoomUiItemViewProvider roomuiitemviewprovider)
    {
        super(context, 0);
        viewProvider = roomuiitemviewprovider;
    }

    public boolean areAllItemsEnabled()
    {
        return false;
    }

    public int getItemViewType(int i)
    {
        return ((RoomUiItem)getItem(i)).type;
    }

    public int getPositionForSection(int i)
    {
        if (i >= sections.size())
        {
            LogUtils.wtf(TAG, "Wrong section index: %d >= %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(sections.size())
            });
            return 0;
        } else
        {
            return ((Section)sections.get(i)).position;
        }
    }

    public int getSectionForPosition(int i)
    {
        if (i >= sectionMap.length)
        {
            LogUtils.wtf(TAG, "Wrong position: %d >= %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(sectionMap.length)
            });
            return 0;
        } else
        {
            return sectionMap[i];
        }
    }

    public Object[] getSections()
    {
        return sections.toArray();
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        RoomUiItem roomuiitem = (RoomUiItem)getItem(i);
        view = viewProvider.getView(roomuiitem, view, viewgroup);
        view.setLayoutDirection(3);
        view.setTag(roomuiitem);
        return view;
    }

    public int getViewTypeCount()
    {
        return 6;
    }

    public boolean isEnabled(int i)
    {
        RoomUiItem roomuiitem = (RoomUiItem)getItem(i);
        if (RemoteFeatureConfig.SRB.enabled())
        {
            return RoomUiItem.SRB_ACTIONABLE_TYPES.contains(Integer.valueOf(roomuiitem.type));
        } else
        {
            return RoomUiItem.RB_ACTIONABLE_TYPES.contains(Integer.valueOf(roomuiitem.type));
        }
    }

}
