// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import com.google.android.libraries.performance.primes.PrimesLog;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.libraries.performance.primes.metriccapture:
//            DirStatsCapture

final class listFilesPatterns
{

    public final File baseDir;
    private final List dirStats;
    private final List listFilesPatterns;
    private final int maxDepth;

    final long scanDir(Dir dir)
        throws IOException
    {
        class Dir
        {

            public final int depth;
            public final String relativeDir;
            public final DirStatsCapture.Traversal this$0;

            Dir()
            {
                this$0 = DirStatsCapture.Traversal.this;
                super();
                relativeDir = "";
                depth = 0;
            }

            Dir(Dir dir, String s)
            {
                this$0 = DirStatsCapture.Traversal.this;
                super();
                if (dir.depth != 0)
                {
                    traversal = dir.relativeDir;
                    s = (new StringBuilder(String.valueOf(DirStatsCapture.Traversal.this).length() + 1 + String.valueOf(s).length())).append(DirStatsCapture.Traversal.this).append('/').append(s).toString();
                }
                relativeDir = s;
                depth = dir.depth + 1;
            }
        }

        logs.proto.wireless.performance.mobile.nano.Capture.Traversal.Dir dir1;
        long l;
        long l1;
        long l2;
        l = 0L;
        dir1 = new logs.proto.wireless.performance.mobile.nano.it>();
        dir1.Path = dir.relativeDir;
        dirStats.add(dir1);
        l1 = l;
        l2 = l;
        File afile[] = (new File(dir._fld0.baseDir, dir.relativeDir)).listFiles();
        l1 = l;
        l2 = l;
        if (dir.depth >= maxDepth) goto _L2; else goto _L1
_L1:
        l1 = l;
        l2 = l;
        if (dirStats.size() < 512) goto _L3; else goto _L2
_L2:
        l1 = l;
        l2 = l;
        l = DirStatsCapture.subtreeSize(afile);
        l1 = l;
_L5:
        dir1.eBytes = Long.valueOf(l1);
        return l1;
_L3:
        l1 = l;
        l2 = l;
        int j = afile.length;
        int i = 0;
_L16:
        l1 = l;
        if (i >= j) goto _L5; else goto _L4
_L4:
        File file;
        file = afile[i];
        l1 = l;
        l2 = l;
        if (DirStatsCapture.isSymlink(file)) goto _L7; else goto _L6
_L6:
        l1 = l;
        l2 = l;
        if (!file.isFile()) goto _L9; else goto _L8
_L8:
        l1 = l;
        l2 = l;
        String s = file.getName();
        l1 = l;
        l2 = l;
        if (dir.depth != 0) goto _L11; else goto _L10
_L10:
        l1 = l;
        l2 = l;
        Iterator iterator = listFilesPatterns.iterator();
_L13:
        l1 = l;
        l2 = l;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_581;
        }
        l1 = l;
        l2 = l;
        if (!((Pattern)iterator.next()).matcher(s).matches()) goto _L13; else goto _L12
_L12:
        Object obj;
        Object obj1;
        long l3;
        for (boolean flag = true; !flag; flag = false)
        {
            break MISSING_BLOCK_LABEL_397;
        }

        l1 = l;
        l2 = l;
        if (dirStats.size() >= 512)
        {
            break MISSING_BLOCK_LABEL_397;
        }
        l1 = l;
        l2 = l;
        obj1 = new logs.proto.wireless.performance.mobile.nano.it>();
        l1 = l;
        l2 = l;
        obj1.Path = s;
        l1 = l;
        l2 = l;
        obj1.eBytes = Long.valueOf(file.length());
        l1 = l;
        l2 = l;
        dirStats.add(obj1);
        l1 = l;
        l2 = l;
        l = file.length() + l;
        break; /* Loop/switch isn't completed */
_L11:
        l1 = l;
        l2 = l;
        obj1 = dir.relativeDir;
        l1 = l;
        l2 = l;
        s = (new StringBuilder(String.valueOf(obj1).length() + 1 + String.valueOf(s).length())).append(((String) (obj1))).append('/').append(s).toString();
        if (true) goto _L10; else goto _L7
_L9:
        l1 = l;
        l2 = l;
        if (!file.isDirectory()) goto _L7; else goto _L14
_L14:
        l1 = l;
        l2 = l;
        l3 = scanDir(new Dir(dir, file.getName()));
        l = l3 + l;
          goto _L7
        obj;
_L15:
        PrimesLog.log(3, "DirStatsCapture", ((Throwable) (obj)), "exception while collecting DirStats for dir %s", new Object[] {
            dir.relativeDir
        });
          goto _L5
        obj;
        l1 = l2;
          goto _L15
_L7:
        i++;
          goto _L16
    }

    transient Dir(File file, List list, int i, Pattern apattern[])
    {
        baseDir = file;
        maxDepth = i;
        dirStats = list;
        if (apattern.length == 0)
        {
            file = Collections.emptyList();
        } else
        {
            file = Arrays.asList(apattern);
        }
        listFilesPatterns = file;
    }
}
