// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.birthday;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheetController;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.overflow.OverflowMenuController;
import com.google.android.calendar.newapi.screen.ViewScreen;
import com.google.android.calendar.newapi.screen.ViewScreenController;
import com.google.android.calendar.newapi.segment.birthday.BirthdayViewSegment;
import com.google.android.calendar.newapi.segment.title.BirthdayTitleViewSegment;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineItem;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.birthday:
//            BirthdayViewScreenModel, BirthdayViewScreenLoader

public final class BirthdayViewScreenController extends ViewScreenController
{

    public BirthdayViewScreenController()
    {
    }

    protected final void createBodySegments(ViewScreenModel viewscreenmodel, List list)
    {
        BirthdayViewScreenModel birthdayviewscreenmodel = (BirthdayViewScreenModel)viewscreenmodel;
        if (super.mHost == null)
        {
            viewscreenmodel = null;
        } else
        {
            viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new BirthdayViewSegment(viewscreenmodel, birthdayviewscreenmodel));
    }

    protected final volatile BottomBarController createCommandBarController(MoreOptionsSheetController moreoptionssheetcontroller)
    {
        return null;
    }

    protected final View createHeaderSegment(ViewScreenModel viewscreenmodel)
    {
        return new BirthdayTitleViewSegment(getContext(), viewscreenmodel);
    }

    public final Loader createLoader(boolean flag)
    {
        return new BirthdayViewScreenLoader((TimelineBirthday)super.model.timelineItem);
    }

    public final ViewScreenModel createModel(TimelineItem timelineitem)
    {
        return new BirthdayViewScreenModel((TimelineBirthday)timelineitem);
    }

    protected final MoreOptionsSheetController createMoreOptionsSheetController()
    {
        return null;
    }

    protected final volatile OverflowMenuController createOverflowMenuController()
    {
        return null;
    }

    protected final ViewScreen createViewScreen()
    {
        return new ViewScreen(getContext());
    }

    protected final void showEditScreen()
    {
    }
}
