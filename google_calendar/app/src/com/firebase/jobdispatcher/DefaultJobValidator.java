// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobValidator, RetryStrategy, JobParameters, Trigger

public final class DefaultJobValidator
    implements JobValidator
{

    private final Context context;

    public DefaultJobValidator(Context context1)
    {
        context = context1;
    }

    private static List getValidationErrorsForRetryStrategy(RetryStrategy retrystrategy)
    {
        int i = retrystrategy.policy;
        int j = retrystrategy.initialBackoff;
        int k = retrystrategy.maximumBackoff;
        retrystrategy = new ArrayList();
        if (i != 1 && i != 2)
        {
            retrystrategy.add("Unknown retry policy provided");
        }
        if (k < j)
        {
            retrystrategy.add("Maximum backoff must be greater than or equal to initial backoff");
        }
        if (k < 300)
        {
            retrystrategy.add("Maximum backoff must be greater than 300s (5 minutes)");
        }
        if (j < 30)
        {
            retrystrategy.add("Initial backoff must be at least 30s");
        }
        return retrystrategy;
    }

    public final List validate(JobParameters jobparameters)
    {
        Object obj;
        ArrayList arraylist1;
        arraylist1 = new ArrayList();
        obj = jobparameters.getTrigger();
        Bundle bundle;
        if (obj != Trigger.NOW && !(obj instanceof JobTrigger.ExecutionWindowTrigger) && !(obj instanceof JobTrigger.ContentUriTrigger))
        {
            obj = Collections.singletonList("Unknown trigger provided");
        } else
        {
            obj = Collections.emptyList();
        }
        arraylist1.addAll(((java.util.Collection) (obj)));
        arraylist1.addAll(getValidationErrorsForRetryStrategy(jobparameters.getRetryStrategy()));
        bundle = jobparameters.getExtras();
        if (bundle == null || bundle.isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            ArrayList arraylist = new ArrayList();
            obj = Parcel.obtain();
            bundle.writeToParcel(((Parcel) (obj)), 0);
            int i = ((Parcel) (obj)).dataSize();
            ((Parcel) (obj)).recycle();
            if (i > 10240)
            {
                arraylist.add(String.format(Locale.US, "Extras too large: %d bytes is > the max (%d bytes)", new Object[] {
                    Integer.valueOf(i), Integer.valueOf(10240)
                }));
            }
            if (jobparameters.getLifetime() > 1)
            {
                Iterator iterator = bundle.keySet().iterator();
                while (iterator.hasNext()) 
                {
                    String s = (String)iterator.next();
                    obj = bundle.get(s);
                    if (obj == null || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean))
                    {
                        obj = Collections.emptyList();
                    } else
                    {
                        Locale locale = Locale.US;
                        if (obj == null)
                        {
                            obj = null;
                        } else
                        {
                            obj = obj.getClass();
                        }
                        obj = Collections.singletonList(String.format(locale, "Received value of type '%s' for key '%s', but only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean", new Object[] {
                            obj, s
                        }));
                    }
                    arraylist.addAll(((java.util.Collection) (obj)));
                }
            }
            obj = arraylist;
        }
        arraylist1.addAll(((java.util.Collection) (obj)));
        obj = jobparameters.getTag();
        if (obj == null)
        {
            obj = Collections.singletonList("Tag can't be null");
        } else
        if (((String) (obj)).length() > 100)
        {
            obj = Collections.singletonList("Tag must be shorter than 100");
        } else
        {
            obj = Collections.emptyList();
        }
        arraylist1.addAll(((java.util.Collection) (obj)));
        obj = jobparameters.getService();
        if (obj == null || ((String) (obj)).isEmpty())
        {
            obj = Collections.singletonList("Service can't be empty");
        } else
        if (context == null)
        {
            obj = Collections.singletonList("Context is null, can't query PackageManager");
        } else
        {
            Object obj1 = context.getPackageManager();
            if (obj1 == null)
            {
                obj = Collections.singletonList("PackageManager is null, can't validate service");
            } else
            {
                Intent intent = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
                intent.setClassName(context, ((String) (obj)));
                obj1 = ((PackageManager) (obj1)).queryIntentServices(intent, 0);
                if (obj1 == null || ((List) (obj1)).isEmpty())
                {
                    Log.e("FJD.GooglePlayReceiver", (new StringBuilder(String.valueOf(obj).length() + 145)).append("Couldn't find a registered service with the name ").append(((String) (obj))).append(". Is it declared in the manifest with the right intent-filter? If not, the job won't be started.").toString());
                    obj = Collections.emptyList();
                } else
                {
label0:
                    {
                        obj1 = ((List) (obj1)).iterator();
                        ResolveInfo resolveinfo;
                        do
                        {
                            if (!((Iterator) (obj1)).hasNext())
                            {
                                break label0;
                            }
                            resolveinfo = (ResolveInfo)((Iterator) (obj1)).next();
                        } while (resolveinfo.serviceInfo == null || !resolveinfo.serviceInfo.enabled);
                        obj = Collections.emptyList();
                    }
                }
            }
        }
_L1:
        arraylist1.addAll(((java.util.Collection) (obj)));
        if (jobparameters.isRecurring() && jobparameters.getTrigger() == Trigger.NOW)
        {
            arraylist1.add("Trigger.NOW can't be used with recurring jobs");
        }
        if (arraylist1.isEmpty())
        {
            return null;
        } else
        {
            return arraylist1;
        }
        obj = Collections.singletonList(String.valueOf(obj).concat(" is disabled."));
          goto _L1
    }

    public final List validate(RetryStrategy retrystrategy)
    {
        List list = getValidationErrorsForRetryStrategy(retrystrategy);
        retrystrategy = list;
        if (list.isEmpty())
        {
            retrystrategy = null;
        }
        return retrystrategy;
    }
}
