// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.contract;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Entity;
import android.content.SyncResult;
import android.os.Bundle;
import com.google.android.syncadapters.calendar.SyncHooksContext;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;

public interface SyncHooks
{

    public abstract String extractCalendarIdFromSubscriptionUrl(String s);

    public abstract String generateSubscriptionUrl(ContentValues contentvalues);

    public abstract String getHookSyncTypePrefix();

    public abstract void initialize(SyncHooksContext synchookscontext);

    public abstract boolean isHookSpecificSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357CKLK___0(Bundle bundle);

    public abstract void onAfterConvertEntityToEvent(Entity entity, Event event, boolean flag);

    public abstract void onAfterDownSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0(Account account, Bundle bundle, ContentProviderClient contentproviderclient, SyncResult syncresult);

    public abstract void onAfterUpSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0(Account account, Bundle bundle);

    public abstract void onBeforeApplyEventToEntity(Event event, Entity entity, ContentValues contentvalues);

    public abstract void onBeforeDownSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0();

    public abstract void onBeforeUpSync$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0();

    public abstract void onInitializeSync(Account account, ContentProviderClient contentproviderclient);

    public abstract void onSyncInitiated$51662RJ4E9NMIP1FC5HM6RRLDPQ76BQ1CDHMUTBEEGTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6ARJKA1P6UTJ9CHIN4GRCD5IMST1R9HHMUR9FCTNMUPRCCKNM2S395TPMASJMD5HMASPFCDGMOPBECHGN4BQ3C5M6ARJ4C5P3MJ31DPI74RR9CGNM6RREEHIMST1FADSMSOQICLPNAR3K7CKLC___0(Bundle bundle);

    public abstract void performHookSpecificSync(Account account, Bundle bundle, ContentProviderClient contentproviderclient, Calendar calendar, SyncResult syncresult);
}
