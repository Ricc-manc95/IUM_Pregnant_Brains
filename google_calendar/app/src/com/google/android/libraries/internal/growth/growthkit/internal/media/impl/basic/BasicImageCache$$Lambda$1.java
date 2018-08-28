// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.social.filecache.FileCache;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic:
//            BasicImageCache, URL

final class arg._cls5
    implements Runnable
{

    private final BasicImageCache arg$1;
    private final String arg$2;
    private final int arg$3;
    private final int arg$4;
    private final SettableFuture arg$5;

    public final void run()
    {
        List list;
        HashMap hashmap;
        SettableFuture settablefuture;
        String s;
        BasicImageCache basicimagecache;
        int i;
        int j;
        list = null;
        hashmap = null;
        basicimagecache = arg$1;
        s = arg$2;
        i = arg$3;
        j = arg$4;
        settablefuture = arg$5;
        Object obj;
        String s1;
        s1 = BasicImageCache.HASH_FUNCTION.hashString(s, Charset.forName("UTF-8")).toString();
        obj = new File(basicimagecache.fileCache.getCacheFilePath(s1));
        Object obj1;
        String s2;
        String s3;
        if (!((File) (obj)).exists())
        {
            obj = null;
        }
        if (obj != null) goto _L2; else goto _L1
_L1:
        if (i != -1 || j != -1) goto _L4; else goto _L3
_L3:
        obj1 = "";
_L9:
        obj = list;
        obj1 = (HttpURLConnection)basicimagecache.urlFactory.(s.replace("=%@", ((CharSequence) (obj1)))).url.openConnection();
        ((HttpURLConnection) (obj1)).setDoInput(true);
        hashmap = new HashMap();
        i = 0;
_L6:
        s2 = ((HttpURLConnection) (obj1)).getHeaderFieldKey(i);
        s3 = ((HttpURLConnection) (obj1)).getHeaderField(i);
        if (s2 == null && s3 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_224;
        }
        list = (List)hashmap.get(s2);
        obj = list;
        if (list != null)
        {
            break MISSING_BLOCK_LABEL_215;
        }
        obj = new ArrayList();
        hashmap.put(s2, obj);
        ((List) (obj)).add(s3);
        i++;
        if (true) goto _L6; else goto _L5
_L4:
        if (i == -1 || j == -1) goto _L8; else goto _L7
_L7:
        Object obj2;
        boolean flag;
        try
        {
            obj1 = (new StringBuilder(26)).append("=w").append(j).append("-h").append(j).toString();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            settablefuture.setException(((Throwable) (obj)));
            return;
        }
          goto _L9
_L26:
        obj1 = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(obj1).length())).append("=").append(((String) (obj))).append(((String) (obj1))).toString();
          goto _L9
_L23:
        obj = (new StringBuilder(12)).append("w").append(i).toString();
          goto _L10
_L25:
        obj1 = (new StringBuilder(12)).append("h").append(j).toString();
        break MISSING_BLOCK_LABEL_286;
_L5:
        if (((HttpURLConnection) (obj1)).getResponseCode() != 200) goto _L12; else goto _L11
_L11:
        obj = (List)hashmap.get("Content-Encoding");
        if (obj == null) goto _L14; else goto _L13
_L13:
        if (((List) (obj)).isEmpty()) goto _L14; else goto _L15
_L15:
        flag = ((String)((List) (obj)).get(0)).equalsIgnoreCase("gzip");
_L17:
        obj = BasicImageCache.readStream(((HttpURLConnection) (obj1)), flag);
        basicimagecache.fileCache.write(s1, ByteBuffer.wrap(((byte []) (obj))));
        if (obj1 == null) goto _L2; else goto _L16
_L16:
        ((HttpURLConnection) (obj1)).disconnect();
_L2:
        settablefuture.set(null);
        return;
_L14:
        flag = false;
          goto _L17
_L12:
        throw new ception(((HttpURLConnection) (obj1)).getResponseMessage(), ((HttpURLConnection) (obj1)).getResponseCode());
        obj2;
_L21:
        obj = obj1;
        BasicImageCache.logger.w(((Throwable) (obj2)), "Failed to retrieve fife URL %s", new Object[] {
            s
        });
        if (obj1 == null) goto _L2; else goto _L18
_L18:
        ((HttpURLConnection) (obj1)).disconnect();
          goto _L2
        obj2;
        obj1 = obj;
        obj = obj2;
_L20:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_548;
        }
        ((HttpURLConnection) (obj1)).disconnect();
        throw obj;
        obj;
        if (true) goto _L20; else goto _L19
_L19:
        obj2;
        obj1 = hashmap;
          goto _L21
_L8:
        if (i != -1) goto _L23; else goto _L22
_L22:
        obj = "";
_L10:
        if (j != -1) goto _L25; else goto _L24
_L24:
        obj1 = "";
          goto _L26
    }

    ception(BasicImageCache basicimagecache, String s, int i, int j, SettableFuture settablefuture)
    {
        arg$1 = basicimagecache;
        arg$2 = s;
        arg$3 = i;
        arg$4 = j;
        arg$5 = settablefuture;
    }
}
