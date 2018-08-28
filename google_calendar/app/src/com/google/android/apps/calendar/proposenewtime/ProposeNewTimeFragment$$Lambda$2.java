// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.MenuItem;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.utils.feedback.FeedbackUtil;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeActivity, ProposeNewTimeFragment

final class arg._cls1
    implements android.support.v7.widget.imeFragment..Lambda._cls2
{

    private final ProposeNewTimeFragment arg$1;

    public final boolean onMenuItemClick(MenuItem menuitem)
    {
        Object obj = arg$1;
        int i = menuitem.getItemId();
        if (i == 0x7f100431)
        {
            if (((Fragment) (obj)).mHost == null)
            {
                menuitem = null;
            } else
            {
                menuitem = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            menuitem = (ProposeNewTimeActivity)menuitem;
            obj = AnalyticsLoggerHolder.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)obj).trackEvent(menuitem, "propose_new_time", "delete_proposal");
                obj = menuitem.getIntent();
                ((Intent) (obj)).putExtra("propose_new_time_proposal", null);
                menuitem.setResult(-1, ((Intent) (obj)));
                menuitem.finish();
                return true;
            }
        }
        if (i == 0x7f100430)
        {
            FeedbackUtil.sendFeedback(((Fragment) (obj)).getContext(), "calendar-pnt+feedback+android@google.com", "Internal feedback: Propose New Time", "You're giving feedback on the propose new time feature. What is working well or not working well in this feature?", ((ProposeNewTimeFragment) (obj)).state.getAccount());
            return true;
        } else
        {
            return false;
        }
    }

    (ProposeNewTimeFragment proposenewtimefragment)
    {
        arg$1 = proposenewtimefragment;
    }
}
