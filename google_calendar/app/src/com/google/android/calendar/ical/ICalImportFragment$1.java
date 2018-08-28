// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.FutureCallback;
import java.util.List;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalImportFragment, NewICalActivity, ICalEventListFragment, ICalEventOperation

final class this._cls0
    implements FutureCallback
{

    private final ICalImportFragment this$0;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e(ICalImportFragment.TAG, throwable, "Failed to parse events", new Object[0]);
        onLaunchEventFailed();
    }

    public final void onSuccess(Object obj)
    {
        ICalImportFragment icalimportfragment;
        List list;
label0:
        {
            Object obj1 = null;
            list = (List)obj;
            icalimportfragment = ICalImportFragment.this;
            boolean flag;
            if (!list.isEmpty())
            {
                if (((Fragment) (icalimportfragment)).mHost != null && ((Fragment) (icalimportfragment)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            if (((Fragment) (icalimportfragment)).mHost != null && ((Fragment) (icalimportfragment)).mAdded)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (((Fragment) (icalimportfragment)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
                }
                Toast.makeText(((android.content.Context) (obj)), 0x7f1302f9, 1).show();
                if (((Fragment) (icalimportfragment)).mHost == null)
                {
                    obj = obj1;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
                }
                ((FragmentActivity) (obj)).finish();
            }
            return;
        }
        if (((Fragment) (icalimportfragment)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
        }
        if (obj instanceof NewICalActivity)
        {
            if (((Fragment) (icalimportfragment)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (icalimportfragment)).mHost.mActivity;
            }
            ((NewICalActivity)obj).findViewById(0x7f10010c).setVisibility(8);
        }
        if (icalimportfragment.showList || list.size() > 1)
        {
            obj = (Uri)icalimportfragment.getArguments().getParcelable("calendar_uri");
            if (obj == null)
            {
                throw new NullPointerException();
            } else
            {
                obj = ICalEventListFragment.newInstance((Uri)obj, list);
                ((Fragment) (icalimportfragment)).mFragmentManager.beginTransaction().replace(0x1020002, ((Fragment) (obj)), ICalEventListFragment.TAG).commitAllowingStateLoss();
                icalimportfragment.showList = true;
                return;
            }
        } else
        {
            icalimportfragment.launchICalEventInfo(null, (ICalEventOperation)list.get(0));
            return;
        }
    }

    ()
    {
        this$0 = ICalImportFragment.this;
        super();
    }
}
