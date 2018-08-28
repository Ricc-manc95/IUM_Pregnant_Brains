// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ChipClickListener;
import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timeline.alternate.AlternateTimelineRescheduleManager;
import com.google.android.calendar.timeline.alternate.DefaultBundleFactory;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfoFactory;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls4
    implements ChipClickListener
{

    private final AllInOneCalendarActivity arg$1;
    private final AlternateTimelineRescheduleManager arg$2;
    private final TimeBoxToTimelineAdapter arg$3;
    private final ChipFragmentInfoFactory arg$4;

    public final void onClick$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCD5N6ABR3D1KN0BQ3D1KN0EQCD9GNCO9FDHGMSPPF9TH6KPB3EGTKIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NN8QBDCLM6IRJ55TGMOT35E9N62T355TR6IPBN5TGN0Q9FAPKMATQDDTI6AEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNM2S395T4N8PBDAPKMATQ6C5HN8RRIF4I46Q39E1A7IS357CKLC___0(Chip chip, Object obj, int i, ViewMode viewmode, int j)
    {
        AllInOneCalendarActivity allinonecalendaractivity;
        ChipFragmentInfoFactory chipfragmentinfofactory;
        AlternateTimelineRescheduleManager alternatetimelinereschedulemanager;
        TimeBoxToTimelineAdapter timeboxtotimelineadapter;
        allinonecalendaractivity = arg$1;
        alternatetimelinereschedulemanager = arg$2;
        timeboxtotimelineadapter = arg$3;
        chipfragmentinfofactory = arg$4;
        obj = (TimeRangeEntry)obj;
        if (j != android.support.v4.content.MUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUOBGD4NKIT35DLB6IPBN8PGM6T3FE9SI8GR8D5O58UBGCKTG____0) goto _L2; else goto _L1
_L1:
        allinonecalendaractivity.onLaunchInsertOrEdit(DefaultBundleFactory.createDefaultBundleForTime(((TimeRangeEntry) (obj)).getRange().getUtcStartMillis()));
        obj = ((FragmentActivity) (allinonecalendaractivity)).mFragments.mHost.mFragmentManager;
        chip = ((FragmentManager) (obj)).findFragmentById(0x7f100115);
        if (chip == null)
        {
            chip = ((FragmentManager) (obj)).findFragmentById(0x7f10011a);
        }
        if (!(chip instanceof CalendarFragment)) goto _L4; else goto _L3
_L3:
        ((CalendarFragment)chip).clearCreatePhantom();
_L6:
        return;
_L4:
        LogUtils.wtf("AllInOneCalendarAct", "This method is only supported by CalendarFragment.", new Object[0]);
        return;
_L2:
        if (!alternatetimelinereschedulemanager.isPendingReschedule(((TimeRangeEntry) (obj)).getKey()))
        {
            obj = timeboxtotimelineadapter.createTimelineItem(((TimeRangeEntry) (obj)));
            viewmode = chipfragmentinfofactory.infos[viewmode.ordinal()];
            allinonecalendaractivity.onLaunchDetails(((com.google.android.calendar.timely.TimelineItem) (obj)), new EventInfoAnimationData(chip, chip.viewModel, viewmode, i));
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    pFragmentInfoFactory(AllInOneCalendarActivity allinonecalendaractivity, AlternateTimelineRescheduleManager alternatetimelinereschedulemanager, TimeBoxToTimelineAdapter timeboxtotimelineadapter, ChipFragmentInfoFactory chipfragmentinfofactory)
    {
        arg$1 = allinonecalendaractivity;
        arg$2 = alternatetimelinereschedulemanager;
        arg$3 = timeboxtotimelineadapter;
        arg$4 = chipfragmentinfofactory;
    }
}
