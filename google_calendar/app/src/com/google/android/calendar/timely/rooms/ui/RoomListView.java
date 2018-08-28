// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ListView;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import java.util.Collection;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomBookingAdapter, RoomUiItem, RoomUiItemViewProvider

public final class RoomListView
{

    private final RoomBookingAdapter adapter;
    private final View footer;
    private final InfiniteScrollListener infiniteScrollListener = new InfiniteScrollListener();
    public final ListView listView;
    public Listener listener;

    public RoomListView(ListView listview, RoomUiItemViewProvider roomuiitemviewprovider)
    {
        listView = listview;
        adapter = new RoomBookingAdapter(listView.getContext(), roomuiitemviewprovider);
        class .Lambda._cls0
            implements android.widget.AdapterView.OnItemClickListener
        {

            private final RoomListView arg$1;

            public final void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                arg$1.listener.onItemClicked((RoomUiItem)view.getTag());
            }

            .Lambda._cls0()
            {
                arg$1 = RoomListView.this;
            }

            private class Listener
            {

                public abstract void onItemClicked(RoomUiItem roomuiitem);

                public abstract void onNextPageRequested();
            }

        }

        listView.setOnItemClickListener(new .Lambda._cls0());
        listView.setOnScrollListener(infiniteScrollListener);
        footer = LayoutInflater.from(listView.getContext()).inflate(0x7f050146, listView, false);
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final RoomListView arg$1;

            public final void onClick(View view)
            {
                arg$1.listener.onNextPageRequested();
            }

            .Lambda._cls1()
            {
                arg$1 = RoomListView.this;
            }
        }

        footer.findViewById(0x7f100347).setOnClickListener(new .Lambda._cls1());
        listView.addFooterView(footer, null, false);
        listView.setAdapter(adapter);
    }

    public final void setFooterMode(int i)
    {
        boolean flag = false;
        View view = footer.findViewById(0x7f100233);
        int j;
        boolean flag1;
        if (i == 3)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (view != null)
        {
            if (j != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            view.setVisibility(j);
        }
        if (i == 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        view = footer.findViewById(0x7f10010c);
        if (flag1 && view.getVisibility() != 0)
        {
            view.setAlpha(0.0F);
            view.animate().alpha(1.0F).setStartDelay(500L).setDuration(300L).start();
        }
        if (view != null)
        {
            if (flag1)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
        }
        infiniteScrollListener.enabled = flag1;
    }

    public final void setItems(Collection collection)
    {
        RoomBookingAdapter roombookingadapter;
        int i;
        int j;
        roombookingadapter = adapter;
        roombookingadapter.clear();
        roombookingadapter.addAll(collection);
        j = roombookingadapter.getCount();
        roombookingadapter.sections.clear();
        collection = "";
        roombookingadapter.sections.add(new RoomBookingAdapter.Section("", 0));
        roombookingadapter.sectionMap = new int[j];
        i = 0;
_L12:
        if (i >= j) goto _L2; else goto _L1
_L1:
        Object obj = (RoomUiItem)roombookingadapter.getItem(i);
        ((RoomUiItem) (obj)).type;
        JVM INSTR tableswitch 2 2: default 104
    //                   2 166;
           goto _L3 _L4
_L3:
        obj = null;
_L9:
        if (obj == null) goto _L6; else goto _L5
_L5:
        boolean flag;
        if (obj == collection || obj != null && obj.equals(collection))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L7; else goto _L6
_L6:
        roombookingadapter.sectionMap[i] = roombookingadapter.sections.size() - 1;
        obj = collection;
_L10:
        i++;
        collection = ((Collection) (obj));
        continue; /* Loop/switch isn't completed */
_L4:
        if (TextUtils.isEmpty(((CharSequence) (obj = ((RoomUiItem) (obj)).hierarchyNode.getName())))) goto _L3; else goto _L8
_L8:
        obj = ((String) (obj)).substring(0, 1).toUpperCase();
          goto _L9
_L7:
        roombookingadapter.sections.add(new RoomBookingAdapter.Section(((String) (obj)), i));
        roombookingadapter.sectionMap[i] = roombookingadapter.sections.size() - 1;
          goto _L10
_L2:
        listView.setFastScrollEnabled(false);
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            collection = listView;
            ListView listview = listView;
            listview.getClass();
            class .Lambda._cls2
                implements Runnable
            {

                private final ListView arg$1;

                public final void run()
                {
                    arg$1.invalidateViews();
                }

            .Lambda._cls2(ListView listview)
            {
                arg$1 = listview;
            }
            }

            collection.post(new .Lambda._cls2(listview));
        }
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    private class InfiniteScrollListener
        implements android.widget.AbsListView.OnScrollListener
    {

        public boolean enabled;
        private long lastNextPageRequest;
        private final RoomListView this$0;

        public final void onScroll(AbsListView abslistview, int i, int j, int k)
        {
            long l;
            if (enabled && listener != null && k - i - j <= 3)
            {
                if ((l = SystemClock.uptimeMillis()) - lastNextPageRequest > 500L)
                {
                    listener.onNextPageRequested();
                    lastNextPageRequest = l;
                    return;
                }
            }
        }

        public final void onScrollStateChanged(AbsListView abslistview, int i)
        {
        }

        InfiniteScrollListener()
        {
            this$0 = RoomListView.this;
            super();
            enabled = false;
            lastNextPageRequest = 0L;
        }
    }

}
