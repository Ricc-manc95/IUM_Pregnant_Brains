// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterDay;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnLayoutUpdater, ColumnViewportController, ColumnViewport

final class val.day
    implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.ew.ActionHandler
{

    public final ColumnLayoutUpdater this$0;
    private final AdapterDay val$day;

    public final boolean focus()
    {
        return false;
    }

    public final Optional scroll(boolean flag, Integer integer)
    {
        Object obj = viewportController;
        int j = ((ColumnViewportController) (obj)).viewport.gridTopMsOfDay;
        ColumnViewport columnviewport = ((ColumnViewportController) (obj)).viewport;
        int i = columnviewport.gridTopMsOfDay;
        int k = columnviewport.gridHeightPx;
        i = columnviewport.gridMsPerVerticalPixel * k + i;
        k = ((ColumnViewportController) (obj)).viewport.gridHeightPx * ((ColumnViewportController) (obj)).viewport.gridMsPerVerticalPixel;
        class .Lambda._cls0
            implements Function
        {

            private final ColumnLayoutUpdater._cls3 arg$1;
            private final AdapterDay arg$2;
            private final boolean arg$3;
            private final Integer arg$4;

            public final Object apply(Object obj1)
            {
                ColumnLayoutUpdater._cls3 _lcls3 = arg$1;
                AdapterDay adapterday = arg$2;
                boolean flag1 = arg$3;
                Integer integer1 = arg$4;
                class .Lambda._cls1
                    implements Function
                {

                    private final ColumnLayoutUpdater._cls3 arg$1;
                    private final AdapterDay arg$2;
                    private final boolean arg$3;
                    private final Integer arg$4;

                    public final Object apply(Object obj2)
                    {
                        Object obj3;
                        Integer integer2;
                        ImmutableList immutablelist;
                        int l;
                        int i1;
                        obj3 = arg$1;
                        obj2 = arg$2;
                        boolean flag2 = arg$3;
                        integer2 = arg$4;
                        ColumnLayoutUpdater columnlayoutupdater = ((ColumnLayoutUpdater._cls3) (obj3)).this$0;
                        immutablelist = ((AdapterDay) (obj2)).getTimedEvents();
                        int k1 = columnlayoutupdater.viewport.julianDayToGridStartPx(((AdapterDay) (obj2)).getJulianDay());
                        obj2 = columnlayoutupdater.viewport;
                        int l1 = (int)(((long)((ColumnViewport) (obj2)).gridWidthPx * (long)1 << 32) / ((ColumnViewport) (obj2)).widthDaysFp16);
                        if (integer2 == null)
                        {
                            l = 1;
                        } else
                        {
                            l = 0;
                        }
                        obj2 = null;
                        i1 = 0;
_L5:
                        if (i1 >= immutablelist.size()) goto _L2; else goto _L1
_L1:
                        int j1;
                        if (flag2)
                        {
                            j1 = i1;
                        } else
                        {
                            j1 = immutablelist.size() - i1 - 1;
                        }
                        obj3 = (AdapterEvent)immutablelist.get(j1);
                        if (columnlayoutupdater.placeTimedEvent$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBR1CHGN0T35E8NK2P31E1Q6ASI5EPIMST1R954LKJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TKMQS3C5TQN8QBC5T96AORK7CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQ6IRB5DHKMSP9FC5M78PBIDPGN8P9FEPKMATPFD5MN0R1FDHGNIRRLEGNM6RRCELMMSBQ3DTM7ARBE9HGNIRRLEHAN0P31EHIN4927E9KM8J31F5NNAT2ICLPNAR3K7C______0(((AdapterEvent) (obj3)), k1, l1 >> 16, false, columnlayoutupdater.timedRect) != android.support.v4.content.ModernAsyncTask.Status.WITHIN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FCDNMOTBDDONK6RRCELMMSJ31F5NNAT2LE1I62T35E8I4ESJ9CH662UBFELQ54PBJELM78EO_0)
                        {
                            continue; /* Loop/switch isn't completed */
                        }
                        if (l == 0) goto _L4; else goto _L3
_L3:
                        l = ((AdapterEvent) (obj3)).getPosition();
_L6:
                        return Integer.valueOf(l);
_L4:
                        if (integer2.intValue() == ((AdapterEvent) (obj3)).getPosition())
                        {
                            l = 1;
                        } else
                        if (obj2 == null)
                        {
                            obj2 = obj3;
                        }
                        i1++;
                          goto _L5
_L2:
                        if (obj2 == null)
                        {
                            l = -1;
                        } else
                        {
                            l = ((AdapterEvent) (obj2)).getPosition();
                        }
                          goto _L6
                    }

                        .Lambda._cls1(AdapterDay adapterday, boolean flag, Integer integer)
                        {
                            arg$1 = ColumnLayoutUpdater._cls3.this;
                            arg$2 = adapterday;
                            arg$3 = flag;
                            arg$4 = integer;
                        }
                }

                return (FluentFuture)AbstractTransformFuture.create((FluentFuture)obj1, _lcls3. new .Lambda._cls1(adapterday, flag1, integer1), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }

            .Lambda._cls0(AdapterDay adapterday, boolean flag, Integer integer)
            {
                arg$1 = ColumnLayoutUpdater._cls3.this;
                arg$2 = adapterday;
                arg$3 = flag;
                arg$4 = integer;
            }
        }

        if (flag)
        {
            i = Math.min(TimeUtils.DAY_MS, k + i) - i;
        } else
        {
            i = Math.max(0, j - k) - j;
        }
        if (i == 0)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = ((ColumnViewportController) (obj)).animateVerticalViewportChange(j + i);
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj = new Present(obj);
        }
        return ((Optional) (obj)).transform(new .Lambda._cls0(val$day, flag, integer));
    }

    public final boolean showOnScreen()
    {
        return false;
    }

    .Lambda._cls0()
    {
        this$0 = final_columnlayoutupdater;
        val$day = AdapterDay.this;
        super();
    }
}
