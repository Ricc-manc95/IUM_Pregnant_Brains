// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.ConnectivityState;
import java.util.ArrayList;

final class ConnectivityStateManager
{

    private ArrayList listeners;
    private volatile ConnectivityState state;

    ConnectivityStateManager()
    {
        listeners = new ArrayList();
        state = ConnectivityState.IDLE;
    }

    final void gotoState(ConnectivityState connectivitystate)
    {
        if (connectivitystate == null)
        {
            throw new NullPointerException(String.valueOf("newState"));
        }
        if (state != connectivitystate && state != ConnectivityState.SHUTDOWN)
        {
            state = connectivitystate;
            if (!listeners.isEmpty())
            {
                connectivitystate = listeners;
                listeners = new ArrayList();
                connectivitystate = (ArrayList)connectivitystate;
                if (connectivitystate.size() < 0)
                {
                    connectivitystate.get(0);
                    throw new NoSuchMethodError();
                }
            }
        }
    }
}
