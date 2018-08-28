// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.network;

import com.google.android.libraries.hats20.Constants;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.libraries.hats20.network:
//            GcsConnection

public final class JavanetGcsConnection
    implements GcsConnection
{

    public JavanetGcsConnection()
    {
    }

    public final void send(String s, byte abyte0[], Map map, GcsConnection.Callbacks callbacks)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        s = (HttpURLConnection)(new URL(s)).openConnection();
        s.setDoOutput(true);
        s.setInstanceFollowRedirects(false);
        s.setRequestMethod("POST");
        for (map = map.entrySet().iterator(); map.hasNext(); s.setRequestProperty((String)((java.util.Map.Entry) (obj)).getKey(), (String)((java.util.Map.Entry) (obj)).getValue()))
        {
            obj = (java.util.Map.Entry)map.next();
        }

          goto _L1
        abyte0;
_L9:
        obj = s;
        callbacks.onFailed(abyte0);
        if (s != null)
        {
            s.disconnect();
        }
_L6:
        return;
_L1:
        s.setUseCaches(false);
        (new DataOutputStream(s.getOutputStream())).write(abyte0);
        abyte0 = new BufferedReader(new InputStreamReader(s.getInputStream(), Constants.UTF_8));
        map = new StringBuilder();
_L4:
        obj = abyte0.readLine();
        if (obj == null) goto _L3; else goto _L2
_L2:
        map.append(((String) (obj)));
          goto _L4
        abyte0;
_L7:
        if (s != null)
        {
            s.disconnect();
        }
        throw abyte0;
_L3:
        abyte0.close();
        callbacks.onSucceeded(s.getResponseCode(), map.toString(), s.getHeaderFields());
        if (s == null) goto _L6; else goto _L5
_L5:
        s.disconnect();
        return;
        abyte0;
        s = ((String) (obj));
          goto _L7
        abyte0;
        s = obj1;
        if (true) goto _L9; else goto _L8
_L8:
    }
}
