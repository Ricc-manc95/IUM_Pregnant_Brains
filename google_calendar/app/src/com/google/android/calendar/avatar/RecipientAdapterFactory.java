// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.content.Context;
import com.android.ex.chips.BaseRecipientAdapter;
import com.google.android.gms.chips.GmsPhotoManager;
import com.google.android.gms.chips.GmsRecipientAdapter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.people.People;

public final class RecipientAdapterFactory
{

    private static volatile GoogleApiClient apiClient = null;
    private static final Object apiClientLock = new Object();

    public static BaseRecipientAdapter create(Context context)
    {
        GoogleApiClient googleapiclient = getGoogleApiClient(context);
        return new GmsRecipientAdapter(context, null, googleapiclient, new GmsPhotoManager(googleapiclient, context.getContentResolver()));
    }

    public static GoogleApiClient getGoogleApiClient(Context context)
    {
        if (apiClient != null) goto _L2; else goto _L1
_L1:
        Object obj = apiClientLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        if (apiClient != null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        context = context.getApplicationContext();
        obj1 = new com.google.android.gms.people.People.PeopleOptions1p.Builder();
        obj1.zzbTn = 139;
        boolean flag;
        if (((com.google.android.gms.people.People.PeopleOptions1p.Builder) (obj1)).zzbTn >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        throw new IllegalArgumentException(String.valueOf("Must provide valid client application ID!"));
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        obj1 = new com.google.android.gms.people.People.PeopleOptions1p(((com.google.android.gms.people.People.PeopleOptions1p.Builder) (obj1)));
        context = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context)).addApi(People.API_1P, ((com.google.android.gms.common.api.Api.ApiOptions.HasOptions) (obj1))).build();
        apiClient = context;
        context.connect();
        obj;
        JVM INSTR monitorexit ;
_L2:
        return apiClient;
    }

}
