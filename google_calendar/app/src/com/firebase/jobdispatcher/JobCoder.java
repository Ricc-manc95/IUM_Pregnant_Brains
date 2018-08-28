// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.firebase.jobdispatcher:
//            ObservedUri, Constraint, RetryStrategy, Trigger, 
//            JobParameters

final class JobCoder
{

    private final String prefix;

    JobCoder(String s)
    {
        prefix = s;
    }

    private static List convertJsonToObservedUris(String s)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        Object obj;
        int i;
        int j;
        int k;
        try
        {
            obj = new JSONObject(s);
            s = ((JSONObject) (obj)).getJSONArray("uri_flags");
            obj = ((JSONObject) (obj)).getJSONArray("uris");
            j = s.length();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new RuntimeException(s);
        }
        i = 0;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        k = s.getInt(i);
        arraylist.add(new ObservedUri(Uri.parse(((JSONArray) (obj)).getString(i)), k));
        i++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_39;
_L1:
        return arraylist;
    }

    private static String convertObservedUrisToJsonString(List list)
    {
        JSONObject jsonobject = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONArray jsonarray1 = new JSONArray();
        ObservedUri observeduri;
        for (list = list.iterator(); list.hasNext(); jsonarray1.put(observeduri.uri))
        {
            observeduri = (ObservedUri)list.next();
            jsonarray.put(observeduri.flags);
        }

        try
        {
            jsonobject.put("uri_flags", jsonarray);
            jsonobject.put("uris", jsonarray1);
        }
        // Misplaced declaration of an exception variable
        catch (List list)
        {
            throw new RuntimeException(list);
        }
        return jsonobject.toString();
    }

    public final JobInvocation.Builder decode(Bundle bundle)
    {
        Object obj;
        Bundle bundle1;
        int ai[];
        String s2;
        String s3;
        int i;
        boolean flag;
        boolean flag1;
        if (bundle == null)
        {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        bundle1 = new Bundle(bundle);
        bundle = String.valueOf(prefix);
        obj = String.valueOf("recurring");
        String s;
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        flag = bundle1.getBoolean(bundle);
        bundle = String.valueOf(prefix);
        obj = String.valueOf("replace_current");
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        flag1 = bundle1.getBoolean(bundle);
        bundle = String.valueOf(prefix);
        obj = String.valueOf("persistent");
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        i = bundle1.getInt(bundle);
        bundle = String.valueOf(prefix);
        obj = String.valueOf("constraints");
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        ai = Constraint.uncompact(bundle1.getInt(bundle));
        bundle = String.valueOf(prefix);
        obj = String.valueOf("trigger_type");
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        bundle1.getInt(bundle);
        JVM INSTR tableswitch 1 3: default 228
    //                   1 444
    //                   2 437
    //                   3 545;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_545;
_L1:
        bundle = null;
_L5:
        obj = String.valueOf(prefix);
        s = String.valueOf("retry_policy");
        String s1;
        int j;
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        j = bundle1.getInt(((String) (obj)));
        if (j != 1 && j != 2)
        {
            obj = RetryStrategy.DEFAULT_EXPONENTIAL;
        } else
        {
            obj = String.valueOf(prefix);
            s1 = String.valueOf("initial_backoff_seconds");
            int k;
            if (s1.length() != 0)
            {
                obj = ((String) (obj)).concat(s1);
            } else
            {
                obj = new String(((String) (obj)));
            }
            k = bundle1.getInt(((String) (obj)));
            obj = String.valueOf(prefix);
            s1 = String.valueOf("maximum_backoff_seconds");
            if (s1.length() != 0)
            {
                obj = ((String) (obj)).concat(s1);
            } else
            {
                obj = new String(((String) (obj)));
            }
            obj = new RetryStrategy(j, k, bundle1.getInt(((String) (obj))));
        }
        s1 = String.valueOf(prefix);
        s2 = String.valueOf("tag");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        s2 = bundle1.getString(s1);
        s1 = String.valueOf(prefix);
        s3 = String.valueOf("service");
        if (s3.length() != 0)
        {
            s1 = s1.concat(s3);
        } else
        {
            s1 = new String(s1);
        }
        s3 = bundle1.getString(s1);
        if (s2 == null || s3 == null || bundle == null || obj == null)
        {
            return null;
        }
        break MISSING_BLOCK_LABEL_746;
_L3:
        bundle = Trigger.NOW;
          goto _L5
_L2:
        bundle = String.valueOf(prefix);
        obj = String.valueOf("window_start");
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        j = bundle1.getInt(bundle);
        bundle = String.valueOf(prefix);
        obj = String.valueOf("window_end");
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        bundle = Trigger.executionWindow(j, bundle1.getInt(bundle));
          goto _L5
        bundle = String.valueOf(prefix);
        obj = String.valueOf("observed_uris");
        if (((String) (obj)).length() != 0)
        {
            bundle = bundle.concat(((String) (obj)));
        } else
        {
            bundle = new String(bundle);
        }
        bundle = Trigger.contentUriTrigger(Collections.unmodifiableList(convertJsonToObservedUris(bundle1.getString(bundle))));
          goto _L5
        JobInvocation.Builder builder = new JobInvocation.Builder();
        builder.tag = s2;
        builder.service = s3;
        builder.trigger = bundle;
        builder.retryStrategy = ((RetryStrategy) (obj));
        builder.recurring = flag;
        builder.lifetime = i;
        builder.constraints = ai;
        builder.replaceCurrent = flag1;
        if (!TextUtils.isEmpty(prefix))
        {
            bundle = bundle1.keySet().iterator();
            do
            {
                if (!bundle.hasNext())
                {
                    break;
                }
                if (((String)bundle.next()).startsWith(prefix))
                {
                    bundle.remove();
                }
            } while (true);
        }
        builder.extras.putAll(bundle1);
        return builder;
    }

    final Bundle encode(JobParameters jobparameters, Bundle bundle)
    {
        if (bundle == null)
        {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        Object obj = jobparameters.getExtras();
        if (obj != null)
        {
            bundle.putAll(((Bundle) (obj)));
        }
        obj = String.valueOf(prefix);
        Object obj1 = String.valueOf("persistent");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putInt(((String) (obj)), jobparameters.getLifetime());
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("recurring");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putBoolean(((String) (obj)), jobparameters.isRecurring());
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("replace_current");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putBoolean(((String) (obj)), jobparameters.shouldReplaceCurrent());
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("tag");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putString(((String) (obj)), jobparameters.getTag());
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("service");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putString(((String) (obj)), jobparameters.getService());
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("constraints");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putInt(((String) (obj)), Constraint.compact(jobparameters.getConstraints()));
        obj1 = jobparameters.getTrigger();
        if (obj1 == Trigger.NOW)
        {
            obj = String.valueOf(prefix);
            obj1 = String.valueOf("trigger_type");
            if (((String) (obj1)).length() != 0)
            {
                obj = ((String) (obj)).concat(((String) (obj1)));
            } else
            {
                obj = new String(((String) (obj)));
            }
            bundle.putInt(((String) (obj)), 2);
        } else
        if (obj1 instanceof JobTrigger.ExecutionWindowTrigger)
        {
            obj1 = (JobTrigger.ExecutionWindowTrigger)obj1;
            obj = String.valueOf(prefix);
            String s = String.valueOf("trigger_type");
            if (s.length() != 0)
            {
                obj = ((String) (obj)).concat(s);
            } else
            {
                obj = new String(((String) (obj)));
            }
            bundle.putInt(((String) (obj)), 1);
            obj = String.valueOf(prefix);
            s = String.valueOf("window_start");
            if (s.length() != 0)
            {
                obj = ((String) (obj)).concat(s);
            } else
            {
                obj = new String(((String) (obj)));
            }
            bundle.putInt(((String) (obj)), ((JobTrigger.ExecutionWindowTrigger) (obj1)).windowStart);
            obj = String.valueOf(prefix);
            s = String.valueOf("window_end");
            if (s.length() != 0)
            {
                obj = ((String) (obj)).concat(s);
            } else
            {
                obj = new String(((String) (obj)));
            }
            bundle.putInt(((String) (obj)), ((JobTrigger.ExecutionWindowTrigger) (obj1)).windowEnd);
        } else
        if (obj1 instanceof JobTrigger.ContentUriTrigger)
        {
            obj = String.valueOf(prefix);
            String s1 = String.valueOf("trigger_type");
            if (s1.length() != 0)
            {
                obj = ((String) (obj)).concat(s1);
            } else
            {
                obj = new String(((String) (obj)));
            }
            bundle.putInt(((String) (obj)), 3);
            obj1 = convertObservedUrisToJsonString(((JobTrigger.ContentUriTrigger)obj1).uris);
            obj = String.valueOf(prefix);
            s1 = String.valueOf("observed_uris");
            if (s1.length() != 0)
            {
                obj = ((String) (obj)).concat(s1);
            } else
            {
                obj = new String(((String) (obj)));
            }
            bundle.putString(((String) (obj)), ((String) (obj1)));
        } else
        {
            throw new IllegalArgumentException("Unsupported trigger.");
        }
        obj = jobparameters.getRetryStrategy();
        jobparameters = ((JobParameters) (obj));
        if (obj == null)
        {
            jobparameters = RetryStrategy.DEFAULT_EXPONENTIAL;
        }
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("retry_policy");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putInt(((String) (obj)), ((RetryStrategy) (jobparameters)).policy);
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("initial_backoff_seconds");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putInt(((String) (obj)), ((RetryStrategy) (jobparameters)).initialBackoff);
        obj = String.valueOf(prefix);
        obj1 = String.valueOf("maximum_backoff_seconds");
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        bundle.putInt(((String) (obj)), ((RetryStrategy) (jobparameters)).maximumBackoff);
        return bundle;
    }
}
