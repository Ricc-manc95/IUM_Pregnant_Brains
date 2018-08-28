// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.metriccapture;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.stitch.util.ThreadUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class DirStatsCapture
{

    public static transient logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats[] getDirStats(Context context, int i, Pattern apattern[])
    {
        boolean flag = false;
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag = true;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        throw new RuntimeException("Must be called on a background thread");
        ArrayList arraylist = new ArrayList();
        File file = new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).dataDir);
        context = file;
_L2:
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        context = new Traversal(context, arraylist, i, apattern);
        class Traversal.Dir
        {

            public final int depth;
            public final String relativeDir;
            public final Traversal this$0;

            Traversal.Dir()
            {
                this$0 = Traversal.this;
                super();
                relativeDir = "";
                depth = 0;
            }

            Traversal.Dir(Traversal.Dir dir, String s)
            {
                this$0 = Traversal.this;
                super();
                if (dir.depth != 0)
                {
                    traversal = dir.relativeDir;
                    s = (new StringBuilder(String.valueOf(Traversal.this).length() + 1 + String.valueOf(s).length())).append(Traversal.this).append('/').append(s).toString();
                }
                relativeDir = s;
                depth = dir.depth + 1;
            }
        }

        context.scanDir(context. new Traversal.Dir());
        boolean flag1 = arraylist.isEmpty();
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_205;
        }
        return null;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        PrimesLog.log(5, "DirStatsCapture", "Failed to use package manager getting data directory from context instead.", new Object[0]);
        context = context.getFilesDir();
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_222;
        }
        try
        {
            context = context.getParentFile();
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Context context) { }
        finally
        {
            throw context;
        }
        context = String.valueOf(context);
        PrimesLog.log(5, "DirStatsCapture", (new StringBuilder(String.valueOf(context).length() + 29)).append("Failed to retrieve DirStats: ").append(context).toString(), new Object[0]);
        return null;
        context = (logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats[])arraylist.toArray(new logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats[0]);
        return context;
        context = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static boolean isSymlink(File file)
        throws IOException
    {
        boolean flag = true;
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            flag = Files.isSymbolicLink(file.toPath());
        } else
        {
            boolean flag1;
            try
            {
                File file1 = new File(file.getParentFile().getCanonicalFile(), file.getName());
                flag1 = file1.getCanonicalFile().equals(file1.getAbsoluteFile());
            }
            catch (IOException ioexception)
            {
                PrimesLog.log(5, "DirStatsCapture", "Could not check symlink for file: %s, assuming symlink.", new Object[] {
                    file
                });
                return true;
            }
            if (flag1)
            {
                return false;
            }
        }
        return flag;
    }

    static long subtreeSize(File afile[])
    {
        int i;
        long l;
        long l1;
        l = 0L;
        File file;
        int j;
        try
        {
            j = afile.length;
        }
        // Misplaced declaration of an exception variable
        catch (File afile[])
        {
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (File afile[])
        {
            continue; /* Loop/switch isn't completed */
        }
        l = 0L;
        i = 0;
_L6:
        l1 = l;
        if (i >= j) goto _L2; else goto _L1
_L1:
        file = afile[i];
        l1 = l;
        if (isSymlink(file))
        {
            break MISSING_BLOCK_LABEL_132;
        }
        if (file.isFile())
        {
            l1 = l + file.length();
            break MISSING_BLOCK_LABEL_132;
        }
        if (file.isDirectory())
        {
            l1 = l + subtreeSize(file.listFiles());
            break MISSING_BLOCK_LABEL_132;
        }
        PrimesLog.log(5, "DirStatsCapture", "not a link / dir / regular file: %s", new Object[] {
            file
        });
        l1 = l;
        break MISSING_BLOCK_LABEL_132;
        afile;
_L4:
        PrimesLog.log(5, "DirStatsCapture", afile, "failure computing subtree size", new Object[0]);
        l1 = l;
_L2:
        return l1;
        afile;
        if (true) goto _L4; else goto _L3
_L3:
        i++;
        l = l1;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private class Traversal
    {

        public final File baseDir;
        private final List dirStats;
        private final List listFilesPatterns;
        private final int maxDepth;

        final long scanDir(Dir dir)
            throws IOException
        {
            logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats dirstats;
            long l;
            long l1;
            long l2;
            l = 0L;
            dirstats = new logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats();
            dirstats.dirPath = dir.relativeDir;
            dirStats.add(dirstats);
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
            dirstats.sizeBytes = Long.valueOf(l1);
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
            obj1 = new logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats();
            l1 = l;
            l2 = l;
            obj1.dirPath = s;
            l1 = l;
            l2 = l;
            obj1.sizeBytes = Long.valueOf(file.length());
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

        transient Traversal(File file, List list, int i, Pattern apattern[])
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

}
