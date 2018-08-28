// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import com.google.common.base.Strings;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

// Referenced classes of package io.grpc:
//            CallCredentials, Deadline

public final class CallOptions
{

    public static final CallOptions DEFAULT = new CallOptions();
    public String authority;
    public String compressorName;
    public CallCredentials credentials;
    public Object customOptions[][];
    public Deadline deadline;
    public Executor executor;
    public Integer maxInboundMessageSize;
    public Integer maxOutboundMessageSize;
    public List streamTracerFactories;
    public boolean waitForReady;

    private CallOptions()
    {
        customOptions = (Object[][])Array.newInstance(java/lang/Object, new int[] {
            0, 2
        });
        streamTracerFactories = Collections.emptyList();
    }

    public CallOptions(CallOptions calloptions)
    {
        customOptions = (Object[][])Array.newInstance(java/lang/Object, new int[] {
            0, 2
        });
        streamTracerFactories = Collections.emptyList();
        deadline = calloptions.deadline;
        authority = calloptions.authority;
        credentials = calloptions.credentials;
        executor = calloptions.executor;
        compressorName = calloptions.compressorName;
        customOptions = calloptions.customOptions;
        waitForReady = calloptions.waitForReady;
        maxInboundMessageSize = calloptions.maxInboundMessageSize;
        maxOutboundMessageSize = calloptions.maxOutboundMessageSize;
        streamTracerFactories = calloptions.streamTracerFactories;
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("deadline", deadline).add("authority", authority).add("callCredentials", credentials);
        Class class1;
        if (executor != null)
        {
            class1 = executor.getClass();
        } else
        {
            class1 = null;
        }
        return tostringhelper.add("executor", class1).add("compressorName", compressorName).add("customOptions", Arrays.deepToString(((Object []) (customOptions)))).add("waitForReady", waitForReady).add("maxInboundMessageSize", maxInboundMessageSize).add("maxOutboundMessageSize", maxOutboundMessageSize).add("streamTracerFactories", streamTracerFactories).toString();
    }

    public final CallOptions withMaxInboundMessageSize(int i)
    {
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("invalid maxsize %s", new Object[] {
                Integer.valueOf(i)
            }));
        } else
        {
            CallOptions calloptions = new CallOptions(this);
            calloptions.maxInboundMessageSize = Integer.valueOf(i);
            return calloptions;
        }
    }

    public final CallOptions withMaxOutboundMessageSize(int i)
    {
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("invalid maxsize %s", new Object[] {
                Integer.valueOf(i)
            }));
        } else
        {
            CallOptions calloptions = new CallOptions(this);
            calloptions.maxOutboundMessageSize = Integer.valueOf(i);
            return calloptions;
        }
    }

    public final CallOptions withOption(Key key, Object obj)
    {
        CallOptions calloptions;
        int i;
        if (key == null)
        {
            throw new NullPointerException(String.valueOf("key"));
        }
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("value"));
        }
        calloptions = new CallOptions(this);
        i = 0;
_L3:
        if (i >= customOptions.length)
        {
            break MISSING_BLOCK_LABEL_190;
        }
        if (!key.equals(customOptions[i][0])) goto _L2; else goto _L1
_L1:
        int k = customOptions.length;
        int j;
        if (i == -1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        calloptions.customOptions = (Object[][])Array.newInstance(java/lang/Object, new int[] {
            j + k, 2
        });
        System.arraycopy(((Object) (customOptions)), 0, ((Object) (calloptions.customOptions)), 0, customOptions.length);
        if (i == -1)
        {
            calloptions.customOptions[customOptions.length] = (new Object[] {
                key, obj
            });
            return calloptions;
        } else
        {
            calloptions.customOptions[i][1] = obj;
            return calloptions;
        }
_L2:
        i++;
          goto _L3
        i = -1;
          goto _L1
    }

    public final CallOptions withStreamTracerFactory(ClientStreamTracer.Factory factory)
    {
        CallOptions calloptions = new CallOptions(this);
        ArrayList arraylist = new ArrayList(streamTracerFactories.size() + 1);
        arraylist.addAll(streamTracerFactories);
        arraylist.add(factory);
        calloptions.streamTracerFactories = Collections.unmodifiableList(arraylist);
        return calloptions;
    }

}
