// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.common.base.Predicate;
import com.google.common.util.concurrent.FutureCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.android.timezonepicker:
//            TimeZoneManager, TimeZoneParams

public final class val.progressBar
    implements FutureCallback
{

    private final TimeZoneManager this$0;
    private final View val$progressBar;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.wtf(TimeZoneManager.TAG, "Loading of time zones failed.", new Object[0]);
    }

    public final void onSuccess(Object obj)
    {
        List list;
        TimeZoneManager timezonemanager;
        ArrayList arraylist;
        Iterator iterator;
        list = (List)obj;
        val$progressBar.setVisibility(8);
        obj = TimeZoneManager.this;
        timezonemanager = TimeZoneManager.this;
        obj.timeZoneGroupsList = TimeZoneManager.groupUpTimeZones(list);
        timezonemanager = TimeZoneManager.this;
        obj = TimeZoneManager.this;
        obj = initialTimeZoneIds;
        arraylist = new ArrayList(((List) (obj)).size());
        iterator = ((List) (obj)).iterator();
_L8:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        ambda._cls1 _lcls1;
        Iterator iterator1;
        _lcls1 = new ambda._cls1((String)iterator.next());
        iterator1 = list.iterator();
        if (iterator1 == null)
        {
            throw new NullPointerException();
        }
        if (_lcls1 == null)
        {
            throw new NullPointerException();
        }
_L6:
        if (!iterator1.hasNext()) goto _L4; else goto _L3
_L3:
        obj = iterator1.next();
        if (!_lcls1.apply(obj)) goto _L6; else goto _L5
_L5:
        obj = (TimeZoneParams)obj;
        if (obj != null)
        {
            arraylist.add(((TimeZoneParams) (obj)).toBuilder().setCountryAbbreviation(null).setNameAbbreviation(null).build());
        }
        continue; /* Loop/switch isn't completed */
_L4:
        obj = null;
        if (true) goto _L5; else goto _L2
_L2:
        timezonemanager.initialTimeZones = arraylist;
        obj = TimeZoneManager.this;
        String s = filteringRequest;
        obj.filteringRequest = s;
        if (((TimeZoneManager) (obj)).timeZoneGroupsList != null)
        {
            ((TimeZoneManager) (obj)).filter.filter(s);
        }
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public meZoneFilter()
    {
        this$0 = final_timezonemanager;
        val$progressBar = View.this;
        super();
    }
}
