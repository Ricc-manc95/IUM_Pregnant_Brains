// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.libraries.hats20.storage.HatsDataStore;
import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HatsCookieManager
{

    private static volatile HatsCookieManager hatsCookieManager;
    private final CookieManager cookieManager;
    private final HatsDataStore hatsDataStore;

    private HatsCookieManager(HatsDataStore hatsdatastore)
    {
        String s;
        cookieManager = new CookieManager();
        hatsDataStore = hatsdatastore;
        s = hatsdatastore.sharedPreferences.getString("SET_COOKIE_URI", "");
        if (TextUtils.isEmpty(s))
        {
            break MISSING_BLOCK_LABEL_84;
        }
        hatsdatastore = new ArrayList(hatsdatastore.sharedPreferences.getStringSet("SET_COOKIE_VALUE", Collections.emptySet()));
        cookieManager.put(new URI(s), Collections.singletonMap("Set-Cookie", hatsdatastore));
        return;
        hatsdatastore;
_L2:
        Log.e("HatsCookieManager", "Failed to restore cookies from persistence.", hatsdatastore);
        return;
        hatsdatastore;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static HatsCookieManager getInstance(Context context)
    {
        if (hatsCookieManager != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/hats20/cookie/HatsCookieManager;
        JVM INSTR monitorenter ;
        if (hatsCookieManager == null)
        {
            hatsCookieManager = new HatsCookieManager(new HatsDataStore(HatsDataStore.getSharedPreferences(context.getApplicationContext())));
        }
        com/google/android/libraries/hats20/cookie/HatsCookieManager;
        JVM INSTR monitorexit ;
_L2:
        return hatsCookieManager;
        context;
        com/google/android/libraries/hats20/cookie/HatsCookieManager;
        JVM INSTR monitorexit ;
        throw context;
    }

    public final String getCookie(String s)
    {
        for (s = cookieManager.get(new URI(s), Collections.emptyMap()).entrySet().iterator(); s.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)s.next();
            if ("Cookie".equalsIgnoreCase((String)entry.getKey()))
            {
                return TextUtils.join(";", (Iterable)entry.getValue());
            }
        }

        return "";
        s;
_L2:
        Log.e("HatsCookieManager", "Failed to get cookies", s);
        return "";
        s;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final void putCookie(String s, Map map)
    {
        try
        {
            cookieManager.put(new URI(s), map);
            Object obj = map.entrySet().iterator();
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                map = (java.util.Map.Entry)((Iterator) (obj)).next();
                if (!"Set-Cookie".equalsIgnoreCase((String)map.getKey()))
                {
                    continue;
                }
                obj = hatsDataStore;
                map = (List)map.getValue();
                ((HatsDataStore) (obj)).sharedPreferences.edit().putString("SET_COOKIE_URI", s).putStringSet("SET_COOKIE_VALUE", new HashSet(map)).apply();
                break;
            } while (true);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        Log.e("HatsCookieManager", "Failed to store cookies", s);
    }
}
