// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import com.google.android.calendar.timely.geometry.PartitionItem;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            ExpandableChipColumnView, AllDayHeaderView

final class this._cls0
    implements com.google.android.calendar.timeline.chip.ener
{

    private final AllDayHeaderView this$0;

    public final void onChipPrimaryAction(Chip chip)
    {
        Object obj = items.iterator();
_L4:
        if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        nView.Registry registry = (nView.Registry)((Iterator) (obj)).next();
        if (registry.chip != chip) goto _L4; else goto _L3
_L3:
        obj = registry.timelineItem;
_L6:
        if (obj == null)
        {
            LogUtils.w(AllDayHeaderView.TAG, "Not propagating chip primary action, getItemForChip() returned null.", new Object[0]);
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        obj = null;
        if (true) goto _L6; else goto _L5
_L5:
        Object obj1 = VisualElementHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)obj1).recordChipTap(getContext(), chip);
            obj1 = createChipFragmentInfo();
            int i = chip.partitionInfo.getStartDay();
            chip = new EventInfoAnimationData(chip, chip.viewModel, ((com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo) (obj1)), i);
            ((OnLaunchDetailsHandler)getContext()).onLaunchDetails(((com.google.android.calendar.timely.TimelineItem) (obj)), chip);
            CreateNewEventView.removeSelectedTime();
            return;
        }
    }

    public final void onChipSecondaryAction(Chip chip)
    {
    }

    tacher()
    {
        this$0 = AllDayHeaderView.this;
        super();
    }
}
