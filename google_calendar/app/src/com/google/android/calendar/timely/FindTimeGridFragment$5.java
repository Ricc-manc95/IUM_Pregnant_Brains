// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.ViewPager;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridFragment, FindTimeGridViewPager, FindTimeGridSlabPage, FindTimeUtil, 
//            FindTimeGridData

final class this._cls0
    implements Runnable
{

    private final FindTimeGridFragment this$0;

    public final void run()
    {
        FindTimeGridSlabPage findtimegridslabpage = (FindTimeGridSlabPage)bottomPager.findViewWithTag(Integer.valueOf(bottomPager.getCurrentItem()));
        String s = FindTimeUtil.getInstance(context).getDescription(currentSuggestion, accountName, accountType);
        TimelineSuggestion timelinesuggestion = currentSuggestion;
        FindTimeGridFragment findtimegridfragment = FindTimeGridFragment.this;
        boolean flag;
        if (suggestionIndex == findtimegridfragment.gridData.suggestions.size() - 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        findtimegridslabpage.setTimelineSuggestion(timelinesuggestion, s, flag);
    }

    ()
    {
        this$0 = FindTimeGridFragment.this;
        super();
    }
}
