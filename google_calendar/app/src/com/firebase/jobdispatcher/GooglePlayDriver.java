// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;
import java.util.List;

// Referenced classes of package com.firebase.jobdispatcher:
//            Driver, GooglePlayJobWriter, DefaultJobValidator, GooglePlayReceiver, 
//            Job, JobParameters, Trigger, Constraint, 
//            RetryStrategy, JobCoder, JobCallback, ExecutionDelegator, 
//            ObservedUri, JobValidator

public final class GooglePlayDriver
    implements Driver
{

    private final Context context;
    private final PendingIntent token;
    private final JobValidator validator;
    private final GooglePlayJobWriter writer = new GooglePlayJobWriter();

    public GooglePlayDriver(Context context1)
    {
        context = context1;
        token = PendingIntent.getBroadcast(context1, 0, new Intent(), 0);
        validator = new DefaultJobValidator(context1);
    }

    private final Intent createSchedulerIntent(String s)
    {
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("scheduler_action", s);
        intent.putExtra("app", token);
        intent.putExtra("source", 8);
        intent.putExtra("source_version", 1);
        return intent;
    }

    public final int cancel(String s)
    {
        Context context1 = context;
        Intent intent = createSchedulerIntent("CANCEL_TASK");
        intent.putExtra("tag", s);
        intent.putExtra("component", new ComponentName(context, com/firebase/jobdispatcher/GooglePlayReceiver));
        context1.sendBroadcast(intent);
        return 0;
    }

    public final JobValidator getValidator()
    {
        return validator;
    }

    public final boolean isAvailable()
    {
        return true;
    }

    public final int schedule(Job job)
    {
        boolean flag = true;
        Object obj = GooglePlayReceiver.callbacks;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (SimpleArrayMap)GooglePlayReceiver.callbacks.get(job.service);
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        obj;
        JVM INSTR monitorexit ;
_L3:
        Context context1 = context;
        Intent intent = createSchedulerIntent("SCHEDULE_TASK");
        GooglePlayJobWriter googleplayjobwriter = writer;
        Bundle bundle = intent.getExtras();
        bundle.putString("tag", job.getTag());
        bundle.putBoolean("update_current", job.shouldReplaceCurrent());
        int i;
        int j;
        boolean flag1;
        if (job.getLifetime() == 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        bundle.putBoolean("persisted", flag1);
        bundle.putString("service", com/firebase/jobdispatcher/GooglePlayReceiver.getName());
        obj = job.getTrigger();
        if (obj == Trigger.NOW)
        {
            bundle.putInt("trigger_type", 2);
            bundle.putLong("window_start", 0L);
            bundle.putLong("window_end", 1L);
        } else
        if (obj instanceof JobTrigger.ExecutionWindowTrigger)
        {
            obj = (JobTrigger.ExecutionWindowTrigger)obj;
            bundle.putInt("trigger_type", 1);
            if (job.isRecurring())
            {
                bundle.putLong("period", ((JobTrigger.ExecutionWindowTrigger) (obj)).windowEnd);
                bundle.putLong("period_flex", ((JobTrigger.ExecutionWindowTrigger) (obj)).windowEnd - ((JobTrigger.ExecutionWindowTrigger) (obj)).windowStart);
            } else
            {
                bundle.putLong("window_start", ((JobTrigger.ExecutionWindowTrigger) (obj)).windowStart);
                bundle.putLong("window_end", ((JobTrigger.ExecutionWindowTrigger) (obj)).windowEnd);
            }
        } else
        if (obj instanceof JobTrigger.ContentUriTrigger)
        {
            obj = (JobTrigger.ContentUriTrigger)obj;
            bundle.putInt("trigger_type", 3);
            int k = ((JobTrigger.ContentUriTrigger) (obj)).uris.size();
            int ai[] = new int[k];
            Uri auri[] = new Uri[k];
            for (i = 0; i < k; i++)
            {
                ObservedUri observeduri = (ObservedUri)((JobTrigger.ContentUriTrigger) (obj)).uris.get(i);
                ai[i] = observeduri.flags;
                auri[i] = observeduri.uri;
            }

            bundle.putIntArray("content_uri_flags_array", ai);
            bundle.putParcelableArray("content_uri_array", auri);
        } else
        {
            job = String.valueOf(obj.getClass());
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(job).length() + 17)).append("Unknown trigger: ").append(job).toString());
        }
        j = Constraint.compact(job.getConstraints());
        if ((j & 4) == 4)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        bundle.putBoolean("requiresCharging", flag1);
        if ((j & 8) == 8)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        bundle.putBoolean("requiresIdle", flag1);
        if ((j & 2) == 2)
        {
            i = 0;
        } else
        {
            i = 2;
        }
        if ((j & 1) == 1)
        {
            i = 1;
        }
        bundle.putInt("requiredNetwork", i);
        obj = job.getRetryStrategy();
        obj1 = new Bundle();
        i = ((flag) ? 1 : 0);
        switch (((RetryStrategy) (obj)).policy)
        {
        default:
            i = 0;
            // fall through

        case 2: // '\002'
            ((Bundle) (obj1)).putInt("retry_policy", i);
            break;
        }
        ((Bundle) (obj1)).putInt("initial_backoff_seconds", ((RetryStrategy) (obj)).initialBackoff);
        ((Bundle) (obj1)).putInt("maximum_backoff_seconds", ((RetryStrategy) (obj)).maximumBackoff);
        bundle.putBundle("retryStrategy", ((Bundle) (obj1)));
        obj1 = job.getExtras();
        obj = obj1;
        if (obj1 == null)
        {
            obj = new Bundle();
        }
        bundle.putBundle("extras", googleplayjobwriter.jobCoder.encode(job, ((Bundle) (obj))));
        intent.putExtras(bundle);
        context1.sendBroadcast(intent);
        return 0;
_L2:
        if ((JobCallback)((SimpleArrayMap) (obj1)).get(job.tag) != null)
        {
            break MISSING_BLOCK_LABEL_398;
        }
        obj;
        JVM INSTR monitorexit ;
          goto _L3
        job;
        obj;
        JVM INSTR monitorexit ;
        throw job;
        obj;
        JVM INSTR monitorexit ;
        obj = new JobInvocation.Builder();
        obj.tag = job.tag;
        obj.service = job.service;
        obj.trigger = job.trigger;
        ExecutionDelegator.stopJob(((JobInvocation.Builder) (obj)).build(), false);
          goto _L3
    }
}
