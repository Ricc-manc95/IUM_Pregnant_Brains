// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.phenotypesupport;

import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.gms.phenotype.PhenotypeApi;
import com.google.android.gms.phenotype.PhenotypeFlag;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;

public class PhenotypeManager
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/config/phenotypesupport/PhenotypeManager);

    public PhenotypeManager()
    {
    }

    public static void initialize(Context context, int i, com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties.BuildVariant buildvariant, String as[])
    {
        PhenotypeFlag.init(context);
        GoogleApiClient googleapiclient = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context)).addApi(Phenotype.API).build();
        googleapiclient.connect();
        PhenotypeApi phenotypeapi = Phenotype.PhenotypeApi;
        com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties.Builder builder = (com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        builder.copyOnWrite();
        com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties applicationproperties = (com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties)builder.instance;
        if (buildvariant == null)
        {
            throw new NullPointerException();
        } else
        {
            applicationproperties.bitField0_ = applicationproperties.bitField0_ | 1;
            applicationproperties.buildVariant_ = buildvariant.value;
            class .Lambda._cls0
                implements ResultCallback
            {

                private final GoogleApiClient arg$1;

                public final void onResult(Result result)
                {
                    GoogleApiClient googleapiclient1 = arg$1;
                    result = (Status)result;
                    LogUtils.d(PhenotypeManager.TAG, "Registered: %s", new Object[] {
                        result
                    });
                    googleapiclient1.disconnect();
                }

            .Lambda._cls0(GoogleApiClient googleapiclient)
            {
                arg$1 = googleapiclient;
            }
            }

            phenotypeapi.register(googleapiclient, "com.google.android.calendar", i, as, ((com.google.android.calendar.phenotype.features.ApplicationPropertiesProto.ApplicationProperties)(GeneratedMessageLite)builder.build()).toByteArray()).setResultCallback(new .Lambda._cls0(googleapiclient));
            buildvariant = new Intent("com.google.android.gms.phenotype.UPDATE");
            buildvariant.setPackage(context.getPackageName());
            context.sendBroadcast(buildvariant);
            return;
        }
    }

    public static void initializeFlagsOnly(Context context)
    {
        PhenotypeFlag.init(context);
    }

}
