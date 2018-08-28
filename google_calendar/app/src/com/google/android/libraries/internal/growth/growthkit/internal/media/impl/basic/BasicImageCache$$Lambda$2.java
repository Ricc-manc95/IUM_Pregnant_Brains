// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;

import android.graphics.BitmapFactory;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.social.filecache.FileCache;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic:
//            BasicImageCache

final class arg._cls2
    implements Callable
{

    private final BasicImageCache arg$1;
    private final String arg$2;

    public final Object call()
    {
        BasicImageCache basicimagecache = arg$1;
        String s = arg$2;
        String s1 = BasicImageCache.HASH_FUNCTION.hashString(s, Charset.forName("UTF-8")).toString();
        File file = new File(basicimagecache.fileCache.getCacheFilePath(s1));
        if (!file.exists())
        {
            file = null;
        }
        if (file != null)
        {
            return BitmapFactory.decodeFile(basicimagecache.fileCache.getCacheFilePath(s1));
        } else
        {
            BasicImageCache.logger.w("Couldn't find image %s in cache", new Object[] {
                s
            });
            return null;
        }
    }

    (BasicImageCache basicimagecache, String s)
    {
        arg$1 = basicimagecache;
        arg$2 = s;
    }
}
