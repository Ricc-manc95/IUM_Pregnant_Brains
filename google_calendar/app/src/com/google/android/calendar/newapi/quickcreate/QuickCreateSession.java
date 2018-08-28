// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcelable;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.android.calendar.newapi.quickcreate.annotation.RequestFactory;
import com.google.android.calendar.newapi.quickcreate.annotation.TaskAssistServiceFactory;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            AutoValue_QuickCreateSession, QuickCreateType

public abstract class QuickCreateSession
    implements Parcelable
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/quickcreate/QuickCreateSession);
    public ListenableFuture warmupResult;

    public QuickCreateSession()
    {
    }

    public static QuickCreateSession create(Context context, QuickCreateType quickcreatetype, QuickCreateSession quickcreatesession, Account account, TaskAssistServiceFactory taskassistservicefactory)
    {
        if (quickcreatesession != null && quickcreatesession.getAccount().equals(account))
        {
            quickcreatesession.warmupResult = null;
            return quickcreatesession;
        }
        quickcreatesession = Long.toString(System.nanoTime());
        taskassistservicefactory = RequestFactory.createWarmupRequest(context, quickcreatetype, quickcreatesession);
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).fishfood_debug();
            context = (new TaskAssistService(context, account.name, ExperimentalOptions.isTaskAssistStagingEnabled(context))).startRequest(new com.google.android.calendar.net.taskassist.TaskAssistService..Lambda._cls0(taskassistservicefactory));
            quickcreatetype = new AutoValue_QuickCreateSession(account, quickcreatesession, quickcreatetype);
            quickcreatetype.warmupResult = context;
            return quickcreatetype;
        }
    }

    public abstract Account getAccount();

    public abstract String getId();

    public abstract QuickCreateType getType();

}
