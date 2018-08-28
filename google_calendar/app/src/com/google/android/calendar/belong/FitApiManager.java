// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import android.accounts.Account;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.Application;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.Set;

class FitApiManager
{

    public static final DataSource DATA_SOURCE;
    public static final DataType DATA_TYPE;
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/belong/FitApiManager);
    public final GoogleApiClient client;

    FitApiManager(Context context, Account account)
    {
        context = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context.getApplicationContext())).addApi(Fitness.RECORDING_API).addApi(Fitness.HISTORY_API);
        context.zzafe = account;
        account = Fitness.SCOPE_ACTIVITY_READ;
        if (account == null)
        {
            throw new NullPointerException(String.valueOf("Scope must not be null"));
        } else
        {
            ((com.google.android.gms.common.api.GoogleApiClient.Builder) (context)).zzaIZ.add(account);
            client = context.build();
            return;
        }
    }

    final boolean connect()
    {
        client.blockingConnect();
        ConnectionResult connectionresult = client.blockingConnect();
        if (connectionresult != null)
        {
            boolean flag;
            if (connectionresult.zzaEP == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    final void unsubscribe()
    {
        boolean flag;
        if (Fitness.RecordingApi.unsubscribe(client, DATA_TYPE).await().getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.e(TAG, "Unsubscription failed", new Object[0]);
        }
    }

    static 
    {
        DATA_TYPE = DataType.TYPE_ACTIVITY_SEGMENT;
        com.google.android.gms.fitness.data.DataSource.Builder builder = new com.google.android.gms.fitness.data.DataSource.Builder();
        builder.zzbhO = Application.fromPackage("com.google.android.gms");
        builder.zzbhk = DataType.TYPE_ACTIVITY_SEGMENT;
        builder.type = 1;
        builder.zzbhP = "merge_activity_segments_local";
        DATA_SOURCE = builder.build();
    }
}
