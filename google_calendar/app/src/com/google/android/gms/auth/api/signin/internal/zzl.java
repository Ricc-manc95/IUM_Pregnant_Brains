// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public final class zzl
{

    private static final Lock zzalT = new ReentrantLock();
    private static zzl zzalU;
    private final Lock zzalV = new ReentrantLock();
    private final SharedPreferences zzalW;

    private zzl(Context context)
    {
        zzalW = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    private static String zzC(String s, String s1)
    {
        String s2 = String.valueOf(":");
        return (new StringBuilder(String.valueOf(s).length() + 0 + String.valueOf(s2).length() + String.valueOf(s1).length())).append(s).append(s2).append(s1).toString();
    }

    public static zzl zzae(Context context)
    {
        if (context == null)
        {
            throw new NullPointerException("null reference");
        }
        zzalT.lock();
        if (zzalU == null)
        {
            zzalU = new zzl(context.getApplicationContext());
        }
        context = zzalU;
        zzalT.unlock();
        return context;
        context;
        zzalT.unlock();
        throw context;
    }

    public final GoogleSignInAccount zzbY(String s)
    {
        if (!TextUtils.isEmpty(s))
        {
            if ((s = zzca(zzC("googleSignInAccount", s))) != null)
            {
                try
                {
                    s = GoogleSignInAccount.zzbU(s);
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    return null;
                }
                return s;
            }
        }
        return null;
    }

    public final GoogleSignInOptions zzbZ(String s)
    {
        if (!TextUtils.isEmpty(s))
        {
            if ((s = zzca(zzC("googleSignInOptions", s))) != null)
            {
                try
                {
                    s = GoogleSignInOptions.zzbW(s);
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    return null;
                }
                return s;
            }
        }
        return null;
    }

    public final String zzca(String s)
    {
        zzalV.lock();
        s = zzalW.getString(s, null);
        zzalV.unlock();
        return s;
        s;
        zzalV.unlock();
        throw s;
    }

}
