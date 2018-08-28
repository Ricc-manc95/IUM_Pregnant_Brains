// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.libraries.internal.growth.growthkit.internal.common.BuildInfo;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.proto.GrowthKitProperties;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.GeneratedMessageLite;

public class GrowthKitInternalCommonModule
{

    private static final Logger logger = new Logger();

    static final SharedPreferences lambda$provideGrowthKitSharedPrefs$0$GrowthKitInternalCommonModule(Context context)
        throws Exception
    {
        return context.getSharedPreferences("growthkit_shared_prefs", 0);
    }

    static Optional provideAppVersionCode(Context context)
    {
        try
        {
            context = Integer.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            context = logger;
            String s = "Did not find own package, this should be impossible.";
            Object aobj[] = new Object[0];
            String s1 = ((Logger) (context)).tag;
            context = s;
            if (aobj != null)
            {
                context = s;
                if (aobj.length > 0)
                {
                    context = String.format("Did not find own package, this should be impossible.", aobj);
                }
            }
            Log.w(s1, context, namenotfoundexception);
            return Absent.INSTANCE;
        }
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        throw new NullPointerException();
        context = new Present(context);
        return context;
    }

    static Optional provideAppVersionName(Context context)
    {
        try
        {
            context = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            context = logger;
            String s = "Did not find own package, this should be impossible.";
            Object aobj[] = new Object[0];
            String s1 = ((Logger) (context)).tag;
            context = s;
            if (aobj != null)
            {
                context = s;
                if (aobj.length > 0)
                {
                    context = String.format("Did not find own package, this should be impossible.", aobj);
                }
            }
            Log.w(s1, context, namenotfoundexception);
            return Absent.INSTANCE;
        }
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        return Absent.INSTANCE;
        context = new Present(context);
        return context;
    }

    static String provideApplicationPackageName(Context context)
    {
        return context.getPackageName();
    }

    public static ListeningExecutorService provideBlockingExecutor(Optional optional, ListeningExecutorService listeningexecutorservice)
    {
        return (ListeningExecutorService)optional.or(listeningexecutorservice);
    }

    static GrowthKitProperties provideGrowthKitProperties()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.experiments.proto.GrowthKitProperties.Builder builder = (com.google.android.libraries.internal.growth.growthkit.internal.experiments.proto.GrowthKitProperties.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)GrowthKitProperties.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        BuildInfo.getChangelistNumber();
        builder.copyOnWrite();
        GrowthKitProperties growthkitproperties = (GrowthKitProperties)builder.instance;
        growthkitproperties.bitField0_ = growthkitproperties.bitField0_ | 1;
        growthkitproperties.growthkitVersion_ = 0xc528754L;
        return (GrowthKitProperties)(GeneratedMessageLite)builder.build();
    }

    public static ListenableFuture provideGrowthKitSharedPrefs(Context context, ListeningExecutorService listeningexecutorservice)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final Context arg$1;

            public final Object call()
            {
                return GrowthKitInternalCommonModule.lambda$provideGrowthKitSharedPrefs$0$GrowthKitInternalCommonModule(arg$1);
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
        }

        return listeningexecutorservice.submit(new .Lambda._cls0(context));
    }

}
