// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timeline.chipviewmodelfactory:
//            ChipFragmentInfo

public final class ChipFragmentInfoFactory
    implements Function
{

    public final ChipFragmentInfo infos[];

    public ChipFragmentInfoFactory()
    {
        ViewMode aviewmode[];
        int i;
        int j;
        infos = new ChipFragmentInfo[ViewMode.values().length];
        aviewmode = ViewMode.values();
        j = aviewmode.length;
        i = 0;
_L8:
        ChipFragmentInfo achipfragmentinfo[];
        ChipFragmentInfo.Builder builder;
        int k;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_257;
        }
        ViewMode viewmode = aviewmode[i];
        achipfragmentinfo = infos;
        k = viewmode.ordinal();
        builder = new ChipFragmentInfo.Builder();
        String s;
        boolean flag;
        if (viewmode == ViewMode.SCHEDULE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        builder.shouldShowImages = flag;
        if (viewmode == ViewMode.SCHEDULE || viewmode == ViewMode.ONE_DAY_GRID)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        builder.showMultidayAnnotations = flag;
        builder.swipeConfigProvider = new _cls1();
        viewmode.ordinal();
        JVM INSTR tableswitch 0 4: default 148
    //                   0 202
    //                   1 233
    //                   2 239
    //                   3 245
    //                   4 251;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_251;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        s = String.valueOf(viewmode);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 22)).append("Unexpected view mode: ").append(s).toString());
_L2:
        byte byte0 = -1;
_L9:
        builder.viewType = Integer.valueOf(byte0);
        achipfragmentinfo[k] = builder.build();
        i++;
        if (true) goto _L8; else goto _L7
_L7:
        byte0 = 0;
          goto _L9
_L4:
        byte0 = 3;
          goto _L9
_L5:
        byte0 = 1;
          goto _L9
        byte0 = 2;
          goto _L9
    }

    public final Object apply(Object obj)
    {
        obj = (ViewMode)obj;
        return infos[((ViewMode) (obj)).ordinal()];
    }

    private class _cls1
        implements ChipViewModelFactory.SwipeConfigProvider
    {

        public final int getSupportedSwipeDirections(TimelineItem timelineitem)
        {
            return SwipeUtils.getSupportedSwipeDirections(timelineitem);
        }

        public final Integer getSwipeIcon(TimelineItem timelineitem, int i)
        {
            return SwipeUtils.getSwipeIcon(timelineitem, i);
        }

        _cls1()
        {
        }
    }

}
