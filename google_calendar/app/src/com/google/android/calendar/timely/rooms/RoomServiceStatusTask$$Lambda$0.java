// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.net.grpc.GrpcRequestException;
import com.google.android.calendar.timely.net.grpc.GrpcStubException;
import com.google.android.calendar.timely.net.grpc.environment.GrpcEnvironments;
import com.google.android.calendar.timely.rooms.net.RoomsRequestExecutor;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.calendar.suggest.v2.RoomServiceStatusRequest;
import com.google.calendar.suggest.v2.RoomServiceStatusResponse;

// Referenced classes of package com.google.android.calendar.timely.rooms:
//            RoomServiceStatusTask

public final class arg._cls2
    implements Runnable
{

    private final RoomServiceStatusTask arg$1;
    private final Context arg$2;

    public final void run()
    {
        RoomServiceStatusTask roomservicestatustask;
        Context context;
        Account aaccount[];
        roomservicestatustask = arg$1;
        context = arg$2;
        aaccount = AccountsUtil.getGoogleAccounts(context);
        if (aaccount != null && aaccount.length != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int j;
        j = aaccount.length;
        i = 0;
_L7:
        if (i >= j) goto _L1; else goto _L3
_L3:
        Object obj;
        boolean flag;
        obj = aaccount[i];
        Object obj1 = (com.google.android.calendar.api.settings.arg._cls2)roomservicestatustask.settingsClient.read(((Account) (obj))).await();
        long l2;
        if (((com.google.android.calendar.api.settings.ient) (obj1)).tus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || !AccountUtils.isDasher(((com.google.android.calendar.api.settings.tus) (obj1)).tings())) goto _L5; else goto _L4
_L4:
        obj1 = Utils.sharedPrefKeyForAccount(((Account) (obj)).name, "room_service_status_expires");
        l2 = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getLong(((String) (obj1)), 0L);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (l2 < l)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L6; else goto _L5
_L5:
        i++;
          goto _L7
_L6:
        Object obj2 = new RoomsRequestExecutor(context, ((Account) (obj)).name, GrpcEnvironments.DEFAULT_TARGET_ENVIRONMENT);
        RoomServiceStatusRequest roomservicestatusrequest = RoomServiceStatusRequest.DEFAULT_INSTANCE;
        obj2 = (RoomServiceStatusResponse)((RoomsRequestExecutor) (obj2)).handleCall(((RoomsRequestExecutor) (obj2)).getStatusCall, roomservicestatusrequest);
        if (obj2 == null)
        {
            LogUtils.e(RoomServiceStatusTask.TAG, "RoomServiceStatus response is null.", new Object[0]);
        } else
        {
            boolean flag1 = Boolean.TRUE.equals(Boolean.valueOf(((RoomServiceStatusResponse) (obj2)).roomServiceEnabled_));
            SharedPrefs.setSharedPreference(context, Utils.sharedPrefKeyForAccount(((Account) (obj)).name, "room_service_status"), flag1);
            long l1;
            long l3;
            if (Clock.mockedTimestamp > 0L)
            {
                l1 = Clock.mockedTimestamp;
            } else
            {
                l1 = System.currentTimeMillis();
            }
            if (Boolean.TRUE.equals(Boolean.valueOf(((RoomServiceStatusResponse) (obj2)).roomServiceEnabled_)))
            {
                l3 = 0x9a7ec800L;
            } else
            {
                l3 = 0x5265c00L;
            }
            SharedPrefs.setSharedPreference(context, Utils.sharedPrefKeyForAccount(((Account) (obj)).name, "room_service_status_expires"), l3 + l1);
        }
          goto _L5
        obj;
_L8:
        LogUtils.e(RoomServiceStatusTask.TAG, ((Throwable) (obj)), "Failed to retrieve RoomServiceStatus.", new Object[0]);
          goto _L5
        obj;
          goto _L8
    }

    public (RoomServiceStatusTask roomservicestatustask, Context context)
    {
        arg$1 = roomservicestatustask;
        arg$2 = context;
    }
}
