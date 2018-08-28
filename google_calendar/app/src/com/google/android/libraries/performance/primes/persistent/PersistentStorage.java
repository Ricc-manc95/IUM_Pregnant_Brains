// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.persistent;

import android.content.SharedPreferences;
import android.util.Base64;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;

public final class PersistentStorage
{

    public final SharedPreferences sharedPreferences;

    public PersistentStorage(SharedPreferences sharedpreferences)
    {
        sharedPreferences = sharedpreferences;
    }

    public final boolean readProto(String s, MessageNano messagenano)
    {
        s = Base64.decode(sharedPreferences.getString(s, ""), 0);
        if (s == null || s.length == 0)
        {
            PrimesLog.log(5, "PersistStorage", "unknown key", new Object[0]);
            return false;
        }
        if (s[0] != 1) goto _L2; else goto _L1
_L1:
        MessageNano.mergeFrom(messagenano, s, 1, s.length - 1);
        return true;
        s;
        PrimesLog.log(5, "PersistStorage", s, "failure reading proto", new Object[0]);
_L4:
        return false;
_L2:
        PrimesLog.log(5, "PersistStorage", "wrong header", new Object[0]);
        if (true) goto _L4; else goto _L3
_L3:
    }
}
