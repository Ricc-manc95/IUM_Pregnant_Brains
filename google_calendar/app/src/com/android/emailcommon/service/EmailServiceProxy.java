// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.service;

import android.os.RemoteException;
import java.util.List;

public interface EmailServiceProxy
{

    public abstract int getApiVersion();

    public abstract String getProtocolVersion(String s)
        throws RemoteException;

    public abstract List retrieveRecipientAvailabilities(String s, List list, long l, long l1)
        throws RemoteException;
}