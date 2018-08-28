// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.util.function.Consumer;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomUiItemViewProvider, RoomUiItem

public final class RoomBookingHeaderAdapter
    implements android.view.View.OnClickListener
{

    private final LinearLayout containerView;
    private final View expandCollapseView;
    public boolean isExpanded;
    public List items;
    private final Consumer roomRemovedCallback;
    private final RoomUiItemViewProvider viewProvider;

    public RoomBookingHeaderAdapter(LinearLayout linearlayout, RoomUiItemViewProvider roomuiitemviewprovider, Consumer consumer)
    {
        isExpanded = false;
        viewProvider = roomuiitemviewprovider;
        containerView = linearlayout;
        linearlayout = viewProvider;
        roomuiitemviewprovider = containerView;
        expandCollapseView = ((RoomUiItemViewProvider) (linearlayout)).inflator.inflate(0x7f05014e, roomuiitemviewprovider, false);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final RoomBookingHeaderAdapter arg$1;

            public final void onClick(View view)
            {
                view = arg$1;
                boolean flag;
                if (!((RoomBookingHeaderAdapter) (view)).isExpanded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                view.isExpanded = flag;
                view.updateWithItems(((RoomBookingHeaderAdapter) (view)).items);
            }

            .Lambda._cls0()
            {
                arg$1 = RoomBookingHeaderAdapter.this;
            }
        }

        expandCollapseView.setOnClickListener(new .Lambda._cls0());
        roomRemovedCallback = consumer;
    }

    public final void onClick(View view)
    {
        view.announceForAccessibility(view.getResources().getString(0x7f130012));
        roomRemovedCallback.accept(((RoomUiItem)view.getTag()).room);
    }

    public final void updateWithItems(List list)
    {
        items = list;
        containerView.removeAllViews();
        LinearLayout linearlayout = containerView;
        int i;
        if (!list.isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (linearlayout != null)
        {
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            linearlayout.setVisibility(i);
        }
        if (list.isEmpty())
        {
            return;
        }
        int k = list.size();
        boolean flag;
        if (list.size() > 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && !isExpanded)
        {
            k = 2;
        }
        for (int l = 0; l < k; l++)
        {
            RoomUiItem roomuiitem = (RoomUiItem)list.get(l);
            View view = viewProvider.getView(roomuiitem, null, containerView);
            if (roomuiitem.type == 3 && !RemoteFeatureConfig.SRB.enabled())
            {
                View view1 = view.findViewById(0x7f100031);
                view1.setTag(roomuiitem);
                view1.setOnClickListener(this);
            }
            containerView.addView(view);
        }

        if (flag)
        {
            list = (TextView)expandCollapseView.findViewById(0x7f100042);
            RoomUiItemViewProvider roomuiitemviewprovider;
            LinearLayout linearlayout1;
            int j;
            if (isExpanded)
            {
                j = 0x7f130418;
            } else
            {
                j = 0x7f130419;
            }
            list.setText(j);
            containerView.addView(expandCollapseView);
        }
        list = containerView;
        roomuiitemviewprovider = viewProvider;
        linearlayout1 = containerView;
        list.addView(roomuiitemviewprovider.inflator.inflate(0x7f050151, linearlayout1, false));
    }
}
