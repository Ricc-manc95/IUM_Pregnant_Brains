// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.gms;

import com.google.android.gms.common.api.Status;

public final class ResultException extends RuntimeException
{

    ResultException(Status status)
    {
        boolean flag1 = true;
        int i = status.zzaEP;
        String s = status.zzaIk;
        super((new StringBuilder(String.valueOf(s).length() + 26)).append("Bad status [").append(i).append("]: ").append(s).toString());
        boolean flag;
        if (status.zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            return;
        }
    }
}
