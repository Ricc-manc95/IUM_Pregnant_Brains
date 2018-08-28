// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.api.CreationMode;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl, AdapterEvent, TimelineAdapter, ViewLayoutParams

public final class EventViewHolder extends TimelineAdapterViewHolderImpl
{

    public AdapterEvent adapterEvent;
    private final CreationMode creationMode;
    private final ItemViewFactory itemViewFactory;
    private float textScale;
    private final TimelineAdapter timelineAdapter;
    private ViewMode viewMode;
    private int zOrder;

    EventViewHolder(ItemViewFactory itemviewfactory, CreationMode creationmode, TimelineAdapter timelineadapter)
    {
        super(itemviewfactory.createView());
        itemViewFactory = itemviewfactory;
        creationMode = creationmode;
        timelineAdapter = timelineadapter;
    }

    public final void onLayoutUpdate(ViewLayoutParams viewlayoutparams)
    {
        boolean flag1 = true;
        int i = 0;
        boolean flag = i;
        if (adapterEvent != null)
        {
            AdapterEvent adapterevent = timelineAdapter.getEvent(adapterEvent.getPosition());
            flag = i;
            if (adapterevent != null)
            {
                flag = i;
                if (adapterevent.getItemVersion() > adapterEvent.getItemVersion())
                {
                    adapterEvent = adapterevent;
                    flag = true;
                }
            }
        }
        ViewMode viewmode = viewlayoutparams.getViewMode();
        if (viewMode != viewmode)
        {
            viewMode = viewmode;
            flag = true;
        }
        float f;
        if (viewlayoutparams.hasTextScale())
        {
            f = viewlayoutparams.getTextScale();
        } else
        {
            f = 1.0F;
        }
        if (textScale != f)
        {
            textScale = f;
            flag = true;
        }
        if (viewlayoutparams.hasZOrder())
        {
            i = viewlayoutparams.getZOrder();
        } else
        if (adapterEvent != null)
        {
            i = CalendarViewType.forPosition(adapterEvent.getPosition()).defaultZOrder;
        } else
        {
            i = zOrder;
        }
        if (zOrder != i)
        {
            zOrder = i;
            flag = flag1;
        }
        if (flag)
        {
            rebind();
        }
    }

    public final void onRecycled()
    {
        viewMode = null;
        adapterEvent = null;
        itemViewFactory.onRecycle(itemView);
    }

    final void rebind()
    {
        boolean flag1;
        for (flag1 = false; viewMode == null || adapterEvent == null;)
        {
            return;
        }

        Object obj = CalendarViewType.forPosition(adapterEvent.getPosition());
        class .Lambda._cls0
            implements Consumer
        {

            private final EventViewHolder arg$1;

            public final void accept(Object obj2)
            {
                EventViewHolder eventviewholder = arg$1;
                obj2 = (com.google.android.apps.calendar.timeline.alternate.view.api.ItemViewFactory.DragMode)obj2;
                eventviewholder.itemView.startDrag(null, new EmptyDragShadowBuilder(), Pair.create(obj2, eventviewholder.adapterEvent), 0);
            }

            .Lambda._cls0()
            {
                arg$1 = EventViewHolder.this;
            }

            private class EmptyDragShadowBuilder extends android.view.View.DragShadowBuilder
            {

                public final void onDrawShadow(Canvas canvas)
                {
                }

                public final void onProvideShadowMetrics(Point point, Point point1)
                {
                    point.set(1, 1);
                    point1.set(0, 0);
                }

                public EmptyDragShadowBuilder()
                {
                }
            }

        }

        View view;
        Object obj1;
        ViewMode viewmode;
        int i;
        int j;
        boolean flag;
        if (((CalendarViewType) (obj)).equals(CalendarViewType.DRAG_EVENT))
        {
            i = android.support.v4.content.ModernAsyncTask.Status.DRAG_GHOST$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0;
        } else
        if (((CalendarViewType) (obj)).equals(CalendarViewType.CREATE_EVENT))
        {
            i = android.support.v4.content.ModernAsyncTask.Status.CREATE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0;
        } else
        {
            i = android.support.v4.content.ModernAsyncTask.Status.REGULAR$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0;
        }
        if (zOrder == 998 || creationMode.scrim && i == android.support.v4.content.ModernAsyncTask.Status.CREATE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        super.useOutlineProvider = flag;
        super.updateOutlineProvider(super.clip, flag);
        obj = itemViewFactory;
        view = itemView;
        obj1 = adapterEvent.getItem();
        j = adapterEvent.getJulianDay();
        viewmode = viewMode;
        flag = flag1;
        if (zOrder > 0)
        {
            flag = flag1;
            if (zOrder != 998)
            {
                flag = true;
            }
        }
        ((ItemViewFactory) (obj)).bindView$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HL62TJ15TM62RJ75T7M4QJ5CDQ3MIACCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNM2S395TB6IPBN9LNM8P9RB9666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR1E1KIUIBKCLMLCQB5ET362ORKDTP7I923D1KN0L3PE1IJMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NNAT39DGNMCTBECDQ6IRRE5T1MURJJELMMASHR8OKLC___0(view, obj1, j, viewmode, flag, i, new .Lambda._cls0(), textScale);
    }
}
