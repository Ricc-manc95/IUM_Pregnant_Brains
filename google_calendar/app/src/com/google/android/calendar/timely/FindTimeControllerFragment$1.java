// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.FindTimeSuggestionUi;
import com.google.android.calendar.timely.findatime.ui.FindTime2UiSuggestionFragment;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeControllerFragment

final class this._cls0
    implements FutureCallback
{

    private final FindTimeControllerFragment this$0;

    public final void onFailure(Throwable throwable)
    {
        FindTimeControllerFragment findtimecontrollerfragment;
        findtimecontrollerfragment = FindTimeControllerFragment.this;
        findtimecontrollerfragment.suggestionUi = (FindTime2UiSuggestionFragment)((Fragment) (findtimecontrollerfragment)).mFragmentManager.findFragmentByTag("find_time_suggestion_fragment");
        findtimecontrollerfragment.suggestionUi.setListener(findtimecontrollerfragment);
        findtimecontrollerfragment.state;
        JVM INSTR tableswitch 1 3: default 60
    //                   1 84
    //                   2 60
    //                   3 84;
           goto _L1 _L2 _L1 _L2
_L1:
        LogUtils.wtf(FindTimeControllerFragment.TAG, "Should not transition to no connection state from %d", new Object[] {
            Integer.valueOf(findtimecontrollerfragment.state)
        });
_L8:
        return;
_L2:
        findtimecontrollerfragment.setState(3);
        findtimecontrollerfragment.suggestionUi.setException();
        Object obj;
        boolean flag;
        if (throwable instanceof Exception)
        {
            obj = throwable.getCause();
        } else
        {
            LogUtils.wtf(FindTimeControllerFragment.TAG, throwable, "Error while retrieving result.", new Object[0]);
            return;
        }
_L13:
        if (obj == null) goto _L4; else goto _L3
_L3:
        if (!(obj instanceof UserRecoverableAuthException)) goto _L6; else goto _L5
_L5:
        obj = (UserRecoverableAuthException)obj;
_L14:
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L8; else goto _L7
_L7:
        throwable = throwable.getCause();
_L15:
        if (throwable == null) goto _L10; else goto _L9
_L9:
        if (!(throwable instanceof UserRecoverableAuthException)) goto _L12; else goto _L11
_L11:
        throwable = (UserRecoverableAuthException)throwable;
_L16:
        if (throwable == null)
        {
            throwable = null;
        } else
        if (((UserRecoverableAuthException) (throwable)).mIntent == null)
        {
            throwable = null;
        } else
        {
            throwable = new Intent(((UserRecoverableAuthException) (throwable)).mIntent);
        }
        findtimecontrollerfragment.startActivityForResult(throwable, 1001);
        return;
_L6:
        obj = ((Throwable) (obj)).getCause();
          goto _L13
_L4:
        obj = null;
          goto _L14
_L12:
        throwable = throwable.getCause();
          goto _L15
_L10:
        throwable = null;
          goto _L16
    }

    public final void onSuccess(Object obj)
    {
        obj = (com.google.android.calendar.timely.findatime.net.)obj;
        clientResult = ((com.google.android.calendar.timely.findatime.net.) (obj));
        transitionToListShowData();
    }

    Result()
    {
        this$0 = FindTimeControllerFragment.this;
        super();
    }
}
