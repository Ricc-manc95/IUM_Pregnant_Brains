// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.material.Material;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Locale;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl, ViewLayoutParams, TimelineAdapter, AdapterDay, 
//            AdapterEvent

public final class AllDayMoreViewHolder extends TimelineAdapterViewHolderImpl
{

    private final TimelineAdapter adapter;
    private int lastCount;
    private Locale lastLocale;
    public int position;
    private ViewMode viewMode;

    public AllDayMoreViewHolder(Context context, TimelineAdapter timelineadapter, DimensConverter dimensconverter, ObservableReference observablereference, ObservableReference observablereference1)
    {
        TextView textview = new TextView(context);
        class .Lambda._cls0
            implements Consumer
        {

            private final AllDayMoreViewHolder arg$1;

            public final void accept(Object obj)
            {
                arg$1.update();
            }

            .Lambda._cls0()
            {
                arg$1 = AllDayMoreViewHolder.this;
            }
        }

        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final ObservableReference arg$1;

            public final void onClick(View view)
            {
                AllDayMoreViewHolder.lambda$new$1$AllDayMoreViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T7M4SR5E9R62OJCCL96APJ5E9IMSOR57D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(arg$1);
            }

            .Lambda._cls1(ObservableReference observablereference)
            {
                arg$1 = observablereference;
            }
        }

        Typeface typeface;
        if (Material.robotoMedium != null)
        {
            typeface = Material.robotoMedium;
        } else
        {
            typeface = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = typeface;
        }
        textview.setTypeface(typeface);
        textview.setGravity(0x800013);
        textview.setTextColor(ContextCompat.getColor(context, 0x7f0d021a));
        textview.setPaddingRelative(dimensconverter.getPixelOffset(4F), 0, 0, dimensconverter.getPixelOffset(2.0F));
        super(textview);
        adapter = timelineadapter;
        context = itemView;
        context.addOnAttachStateChangeListener(new com.google.android.apps.calendar.util.android.Views._cls1(context, observablereference, new .Lambda._cls0(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false));
        itemView.setOnClickListener(new .Lambda._cls1(observablereference1));
    }

    static final void lambda$new$1$AllDayMoreViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T7M4SR5E9R62OJCCL96APJ5E9IMSOR57D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(ObservableReference observablereference)
    {
        observablereference.set(Boolean.valueOf(true));
    }

    public final void onLayoutUpdate(ViewLayoutParams viewlayoutparams)
    {
        viewlayoutparams = viewlayoutparams.getViewMode();
        if (viewMode != viewlayoutparams)
        {
            viewMode = viewlayoutparams;
        }
        update();
    }

    final void update()
    {
        Object obj = adapter.getDay(position - CalendarViewType.ALL_DAY_MORE_HEADER.minPosition);
        if (viewMode != ViewMode.ONE_DAY_GRID) goto _L2; else goto _L1
_L1:
        int k = ((AdapterDay) (obj)).getAllDayEvents().size() - 2;
_L4:
        obj = Locale.getDefault();
        if (lastCount != k || lastLocale != obj)
        {
            lastCount = k;
            lastLocale = ((Locale) (obj));
            ((TextView)itemView).setText(String.format(((Locale) (obj)), "+%d", new Object[] {
                Integer.valueOf(k)
            }));
        }
        return;
_L2:
        obj = (ImmutableList)((AdapterDay) (obj)).getAllDayEvents();
        int l = ((ImmutableList) (obj)).size();
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        int j = 0;
        int i = 0;
        do
        {
            k = i;
            if (j >= l)
            {
                continue;
            }
            Object obj1 = ((ImmutableList) (obj)).get(j);
            j++;
            if (((AdapterEvent)obj1).getGridAllDaySlot().intValue() >= 2)
            {
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }
}
