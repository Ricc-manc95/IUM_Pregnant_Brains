// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.safetynet;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.os.RemoteException;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.config.remote.SafetyNetFeature;
import com.google.android.gms.phenotype.PhenotypeFlag;
import com.google.android.syncadapters.calendar.ProviderHelper;

public class SafetyNetV3
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/safetynet/SafetyNetV3);

    public SafetyNetV3()
    {
    }

    public static void discardDeletions(Account account, ContentProviderClient contentproviderclient)
        throws RemoteException, ParseException
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("deleted", "0");
        contentvalues.put("sync_data7", "1");
        ProviderHelper.asSyncAdapter(account).update(contentproviderclient, android.provider.CalendarContract.Events.CONTENT_URI, contentvalues, "deleted=?", new String[] {
            "1"
        });
    }

    public static int getMinimalNumberOfEvents()
    {
        SafetyNetFeature safetynetfeature = RemoteFeatureConfig.SAFETY_NET_V_3;
        if (safetynetfeature.numberOfDeletions == null)
        {
            safetynetfeature.numberOfDeletions = (Integer)safetynetfeature.numberOfDeletionsFlag.get();
        }
        return safetynetfeature.numberOfDeletions.intValue();
    }

    public static int getMinimalPercentageOfEvents()
    {
        SafetyNetFeature safetynetfeature = RemoteFeatureConfig.SAFETY_NET_V_3;
        if (safetynetfeature.percentageOfDeletions == null)
        {
            safetynetfeature.percentageOfDeletions = (Integer)safetynetfeature.percentageOfDeletionsFlag.get();
        }
        return safetynetfeature.percentageOfDeletions.intValue();
    }

    public static int getVersion()
    {
        return 3;
    }

}
