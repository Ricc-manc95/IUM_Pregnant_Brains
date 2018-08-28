// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import com.google.android.calendar.timely.PagedScrollView;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            ExpandableChipColumnView, AllDayHeaderView, AllDayHeaderArrow

public final class StickyAllDayManager
    implements android.view.View.OnClickListener, android.view.View.OnTouchListener
{

    public final HashMap allDayInfo = new HashMap();
    private final int allDaySectionMinHeightPhoneDayView;
    private final int eventsSpacing;
    private final boolean isPhoneDayView;
    public AllDayInfo mainAllDayInfo;
    private final int overHeight;
    public AllDayHeaderArrow sharedArrow;
    public int sharedState;
    private final int topMargin;

    public StickyAllDayManager(Resources resources, boolean flag)
    {
        isPhoneDayView = flag;
        int i;
        if (flag)
        {
            i = resources.getDimensionPixelOffset(0x7f0e0309);
        } else
        {
            i = 0;
        }
        topMargin = i;
        if (flag)
        {
            i = 0x7f0e0308;
        } else
        {
            i = 0x7f0e01cf;
        }
        eventsSpacing = resources.getDimensionPixelSize(i);
        if (flag)
        {
            i = 0x7f0e00ae;
        } else
        {
            i = 0x7f0e03dd;
        }
        overHeight = resources.getDimensionPixelOffset(i);
        sharedState = -1;
        allDaySectionMinHeightPhoneDayView = resources.getDimensionPixelSize(0x7f0e0051);
    }

    private static int getViewType(View view, AllDayInfo alldayinfo)
    {
        if (alldayinfo.allDayScrollView == view)
        {
            return 1;
        }
        if (alldayinfo.daysScrollView == view)
        {
            return 3;
        }
        if (alldayinfo.allDayHeaderArrow == view)
        {
            return 4;
        }
        if (alldayinfo.allDayContent == view)
        {
            return 2;
        }
        return alldayinfo.collapseButton != view ? 0 : 5;
    }

    public final void addPage(View view, AllDayHeaderView alldayheaderview, ScrollView scrollview, PagedScrollView pagedscrollview, AllDayHeaderArrow alldayheaderarrow, View view1)
    {
        AllDayInfo alldayinfo = new AllDayInfo();
        alldayinfo.allDayContent = alldayheaderview;
        alldayinfo.allDayScrollView = scrollview;
        alldayinfo.daysScrollView = pagedscrollview;
        alldayinfo.allDayHeaderArrow = alldayheaderarrow;
        alldayinfo.collapseButton = view1;
        alldayinfo.parentView = view;
        alldayinfo.countOfChips = ((ExpandableChipColumnView) (alldayheaderview)).items.size();
        alldayinfo.allDayContent.stateManager = this;
        if (alldayinfo.allDayHeaderArrow != null)
        {
            alldayinfo.allDayHeaderArrow.setOnClickListener(this);
        }
        if (alldayinfo.collapseButton != null)
        {
            alldayinfo.collapseButton.setOnClickListener(this);
        }
        allDayInfo.put(alldayheaderview, alldayinfo);
        if (sharedState == -1)
        {
            setNonSharedState(alldayinfo, 1, alldayinfo.countOfChips);
            return;
        } else
        {
            setSharedState(sharedState);
            return;
        }
    }

    final void changeState(AllDayInfo alldayinfo)
    {
        if (alldayinfo == null)
        {
            return;
        }
        int i;
        if (((ExpandableChipColumnView) (alldayinfo.allDayContent)).isExpanded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        setNonSharedState(alldayinfo, i, alldayinfo.countOfChips);
    }

    final AllDayInfo getInfo(View view)
    {
        for (Iterator iterator = allDayInfo.values().iterator(); iterator.hasNext();)
        {
            AllDayInfo alldayinfo = (AllDayInfo)iterator.next();
            if (getViewType(view, alldayinfo) != 0)
            {
                return alldayinfo;
            }
        }

        return null;
    }

    public final void onClick(View view)
    {
        if (view != sharedArrow) goto _L2; else goto _L1
_L1:
        setSharedState((sharedState + 1) % 2);
_L4:
        return;
_L2:
        AllDayInfo alldayinfo = getInfo(view);
        if (alldayinfo == null) goto _L4; else goto _L3
_L3:
        switch (getViewType(view, alldayinfo))
        {
        default:
            return;

        case 4: // '\004'
            if (((AllDayHeaderArrow)view).arrowState != 0)
            {
                changeState(alldayinfo);
                return;
            }
            break;

        case 5: // '\005'
            setNonSharedState(alldayinfo, 1, alldayinfo.countOfChips);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public final boolean onTouch(View view, MotionEvent motionevent)
    {
        motionevent = getInfo(view);
        if (motionevent == null)
        {
            return false;
        }
        switch (getViewType(view, motionevent))
        {
        default:
            return false;

        case 1: // '\001'
            return true;
        }
    }

    public final void removePage(AllDayHeaderView alldayheaderview)
    {
        AllDayInfo alldayinfo = (AllDayInfo)allDayInfo.get(alldayheaderview);
        if (alldayinfo != null)
        {
            alldayinfo.allDayContent.stateManager = null;
            if (alldayinfo.allDayHeaderArrow != null)
            {
                alldayinfo.allDayHeaderArrow.setOnClickListener(null);
            }
            alldayinfo.allDayScrollView.setScrollY(0);
            alldayinfo.allDayScrollView.setOnTouchListener(this);
            alldayinfo.allDayContent.clear();
            allDayInfo.remove(alldayheaderview);
        }
        if (sharedState != -1)
        {
            setSharedState(sharedState);
        }
    }

    public final int setNonSharedState(final AllDayInfo view, final int finalHeight, int i)
    {
        int k = ((ExpandableChipColumnView) (view.allDayContent)).config.chipHeight;
        int j = eventsSpacing;
        final ScrollView view;
        final int finalHeight;
        boolean flag;
        if (!isPhoneDayView || i == 0)
        {
            finalHeight = 0;
        } else
        if (i == 1)
        {
            finalHeight = topMargin * 2;
        } else
        {
            finalHeight = topMargin;
        }
        j = finalHeight + i * (j + k);
        finalHeight = j;
        if (isPhoneDayView)
        {
            finalHeight = j;
            if (i > 0)
            {
                finalHeight = Math.max(j, allDaySectionMinHeightPhoneDayView);
            }
        }
        view = view.allDayContent;
        if (finalHeight == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag != ((ExpandableChipColumnView) (view)).isExpanded)
        {
            view.isExpanded = flag;
            view.applyExpandedOrCollapsedState();
            view.onExpandStateChanged$51D2ILG_0();
        }
        if (i <= ((ExpandableChipColumnView) (view.allDayContent)).config.maxChipsIfCollapsed)
        {
            if (view.allDayHeaderArrow != null)
            {
                view.allDayHeaderArrow.setState(0);
            }
            if (view.collapseButton != null)
            {
                view.collapseButton.setVisibility(8);
            }
            view.allDayScrollView.setScrollY(0);
            view.allDayScrollView.setOnTouchListener(this);
            view = view.allDayScrollView;
            view.post(new _cls1());
            return finalHeight;
        }
        if (view.allDayHeaderArrow != null)
        {
            view = view.allDayHeaderArrow;
            if (finalHeight == 1)
            {
                j = 2;
            } else
            {
                j = 1;
            }
            view.setState(j);
        }
        if (finalHeight == 1)
        {
            j = 8;
        } else
        {
            j = 0;
        }
        if (view.collapseButton != null)
        {
            view.collapseButton.setVisibility(j);
        }
        ScrollView scrollview;
        switch (finalHeight)
        {
        default:
            return 0;

        case 1: // '\001'
            i = ((ExpandableChipColumnView) (view.allDayContent)).config.maxChipsIfCollapsed;
            finalHeight = eventsSpacing;
            finalHeight = ((ExpandableChipColumnView) (view.allDayContent)).config.maxChipsIfCollapsed;
            if (!isPhoneDayView || finalHeight == 0)
            {
                finalHeight = 0;
            } else
            if (finalHeight == 1)
            {
                finalHeight = topMargin * 2;
            } else
            {
                finalHeight = topMargin;
            }
            finalHeight += (finalHeight + k) * i;
            view = view.allDayScrollView;
            view.post(new _cls1());
            view.allDayScrollView.setScrollY(0);
            view.allDayScrollView.setOnTouchListener(this);
            return finalHeight;

        case 0: // '\0'
            finalHeight = view.parentView.getHeight() - overHeight;
            j = (finalHeight - topMargin) / (eventsSpacing + k);
            scrollview = view.allDayScrollView;
            view = this;
            break;
        }
        if (i > j)
        {
            view = null;
        }
        scrollview.setOnTouchListener(view);
        if (i <= j)
        {
            view = view.allDayScrollView;
            view.post(new _cls1());
            return finalHeight;
        } else
        {
            view = view.allDayScrollView;
            view.post(new _cls1());
            return finalHeight;
        }
    }

    public final void setSharedState(int i)
    {
        boolean flag;
        flag = false;
        sharedState = i;
        if (mainAllDayInfo == null) goto _L2; else goto _L1
_L1:
        int j = mainAllDayInfo.countOfChips;
_L4:
        Iterator iterator = allDayInfo.values().iterator();
        for (i = 0; iterator.hasNext(); i = setNonSharedState((AllDayInfo)iterator.next(), sharedState, j)) { }
        break MISSING_BLOCK_LABEL_124;
_L2:
        Iterator iterator1 = allDayInfo.values().iterator();
        i = 0;
        do
        {
            j = i;
            if (!iterator1.hasNext())
            {
                break;
            }
            AllDayInfo alldayinfo = (AllDayInfo)iterator1.next();
            if (alldayinfo.countOfChips > i)
            {
                i = alldayinfo.countOfChips;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        final AllDayHeaderArrow view = sharedArrow;
        view.post(new _cls1());
        if (j <= 3)
        {
            view = sharedArrow;
            i = ((flag) ? 1 : 0);
        } else
        {
            view = sharedArrow;
            if (sharedState == 1)
            {
                i = 2;
            } else
            {
                i = 1;
            }
        }
        view.setState(i);
        return;
    }

    private class AllDayInfo
    {

        public AllDayHeaderView allDayContent;
        public AllDayHeaderArrow allDayHeaderArrow;
        public ScrollView allDayScrollView;
        public View collapseButton;
        public int countOfChips;
        public PagedScrollView daysScrollView;
        public View parentView;

        protected AllDayInfo()
        {
        }
    }


    private class _cls1
        implements Runnable
    {

        private final int val$finalHeight;
        private final View val$view;

        public final void run()
        {
            android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
            layoutparams.height = finalHeight;
            view.setLayoutParams(layoutparams);
        }

        _cls1()
        {
            view = view1;
            finalHeight = i;
            super();
        }
    }

}
