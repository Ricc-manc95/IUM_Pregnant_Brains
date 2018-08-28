// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import com.google.common.base.Optional;

final class AutoValue_MetricUtils_Result extends MetricUtils.Result
{

    private final Optional code;
    private final Optional source;
    private final MetricUtils.Result.Status status;

    AutoValue_MetricUtils_Result(MetricUtils.Result.Status status1, Optional optional, Optional optional1)
    {
        if (status1 == null)
        {
            throw new NullPointerException("Null status");
        }
        status = status1;
        if (optional == null)
        {
            throw new NullPointerException("Null source");
        }
        source = optional;
        if (optional1 == null)
        {
            throw new NullPointerException("Null code");
        } else
        {
            code = optional1;
            return;
        }
    }

    final Optional code()
    {
        return code;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof MetricUtils.Result)
            {
                if (!status.equals(((MetricUtils.Result) (obj = (MetricUtils.Result)obj)).status()) || !source.equals(((MetricUtils.Result) (obj)).source()) || !code.equals(((MetricUtils.Result) (obj)).code()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return ((status.hashCode() ^ 0xf4243) * 0xf4243 ^ source.hashCode()) * 0xf4243 ^ code.hashCode();
    }

    final Optional source()
    {
        return source;
    }

    public final MetricUtils.Result.Status status()
    {
        return status;
    }

    public final String toString()
    {
        String s = String.valueOf(status);
        String s1 = String.valueOf(source);
        String s2 = String.valueOf(code);
        return (new StringBuilder(String.valueOf(s).length() + 31 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("Result{status=").append(s).append(", source=").append(s1).append(", code=").append(s2).append("}").toString();
    }
}
