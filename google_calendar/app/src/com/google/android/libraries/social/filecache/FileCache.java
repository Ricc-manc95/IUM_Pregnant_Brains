// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.filecache;

import android.content.Context;
import android.util.Log;
import com.google.android.libraries.stitch.util.ByteBufferUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public final class FileCache
{

    private File mCacheDir;
    private String mCachePath;
    private final String mCacheSubDirectory;
    private long mCapacity;
    private final Context mContext;
    private final long mMaxCapacity;
    private final float mMaxPortionOfFreeStorage;
    private final float mMaxPortionOfTotalStorage;
    private final long mMinCapacity;
    private final StatFsProvider mStatFsProvider;

    public FileCache(Context context, String s, long l, long l1, float f, 
            float f1)
    {
        this(context, s, 0L, 0x500000L, 0.1F, 0.05F, ((StatFsProvider) (new DefaultStatFsProvider())));
    }

    private FileCache(Context context, String s, long l, long l1, float f, 
            float f1, StatFsProvider statfsprovider)
    {
        mContext = context;
        mCacheSubDirectory = s;
        mMinCapacity = l;
        mMaxCapacity = l1;
        mMaxPortionOfTotalStorage = f;
        mMaxPortionOfFreeStorage = f1;
        mStatFsProvider = statfsprovider;
    }

    private final long getUsedSpace(File file)
    {
        long l = 0L;
        file = file.listFiles();
        long l1 = l;
        if (file != null)
        {
            int i = 0;
            do
            {
                l1 = l;
                if (i >= file.length)
                {
                    break;
                }
                File file1 = file[i];
                if (file1.isDirectory())
                {
                    l1 = getUsedSpace(file1);
                } else
                {
                    l1 = file1.length();
                }
                l += l1;
                i++;
            } while (true);
        }
        return l1;
    }

    public final void collectCacheFiles(File file, ArrayList arraylist)
    {
        file = file.listFiles();
        if (file != null)
        {
            int i = 0;
            while (i < file.length) 
            {
                File file1 = file[i];
                if (file1.isDirectory())
                {
                    collectCacheFiles(file1, arraylist);
                } else
                {
                    arraylist.add(file1);
                }
                i++;
            }
        }
    }

    public final File getCacheDir()
    {
        if (mCacheDir == null)
        {
            mCacheDir = new File(mContext.getCacheDir(), mCacheSubDirectory);
        }
        return mCacheDir;
    }

    public final String getCacheFilePath(String s)
    {
        if (mCachePath == null)
        {
            mCachePath = getCacheDir().getPath();
        }
        StringBuilder stringbuilder = new StringBuilder(mCachePath.length() + s.length() + 3);
        if (mCachePath == null)
        {
            mCachePath = getCacheDir().getPath();
        }
        stringbuilder.append(mCachePath);
        stringbuilder.append(File.separatorChar);
        stringbuilder.append(s.charAt(0));
        stringbuilder.append(File.separatorChar);
        stringbuilder.append(s);
        return stringbuilder.toString();
    }

    public final long getCapacity()
    {
        this;
        JVM INSTR monitorenter ;
        if (mCapacity != 0L) goto _L2; else goto _L1
_L1:
        long l1;
        long l2;
        l1 = mMaxCapacity;
        l2 = mStatFsProvider.getTotalBytes();
        long l = l1;
        if ((float)l1 > (float)l2 * mMaxPortionOfTotalStorage)
        {
            l = (long)((float)l2 * mMaxPortionOfTotalStorage);
        }
        l1 = l;
        if (l < mMinCapacity)
        {
            l1 = mMinCapacity;
        }
        l2 = mStatFsProvider.getFreeBytes();
        if ((float)l1 >= (float)l2 * mMaxPortionOfFreeStorage) goto _L4; else goto _L3
_L3:
        mCapacity = l1;
_L2:
        l = mCapacity;
        this;
        JVM INSTR monitorexit ;
        return l;
_L4:
        if (!getCacheDir().exists())
        {
            break MISSING_BLOCK_LABEL_162;
        }
        l = getUsedSpace(getCacheDir());
_L5:
        float f = l + l2;
        float f1 = mMaxPortionOfFreeStorage;
        l1 = (long)(f * f1);
          goto _L3
        l = 0L;
          goto _L5
        Exception exception;
        exception;
        throw exception;
          goto _L3
    }

    public final void write(String s, ByteBuffer bytebuffer)
    {
        String s1 = getCacheFilePath(s);
        try
        {
            ByteBufferUtils.toFile(bytebuffer, s1);
            return;
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            s = (new File(s1)).getParentFile();
            if (!s.exists())
            {
                try
                {
                    s.mkdirs();
                }
                // Misplaced declaration of an exception variable
                catch (ByteBuffer bytebuffer)
                {
                    s = String.valueOf(s);
                    Log.e("FileCache", (new StringBuilder(String.valueOf(s).length() + 31)).append("Cannot create cache directory: ").append(s).toString(), bytebuffer);
                    throw new RuntimeException("Cannot create cache directory", bytebuffer);
                }
            } else
            {
                s = String.valueOf(s1);
                if (s.length() != 0)
                {
                    s = "Cannot write file to cache: ".concat(s);
                } else
                {
                    s = new String("Cannot write file to cache: ");
                }
                Log.e("FileCache", s, filenotfoundexception);
            }
            try
            {
                ByteBufferUtils.toFile(bytebuffer, s1);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                s = String.valueOf(s1);
            }
            if (s.length() != 0)
            {
                s = "Cannot write file to cache: ".concat(s);
            } else
            {
                s = new String("Cannot write file to cache: ");
            }
            Log.e("FileCache", s, filenotfoundexception);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ByteBuffer bytebuffer)
        {
            s = String.valueOf(s1);
        }
        if (s.length() != 0)
        {
            s = "Cannot write file to cache: ".concat(s);
        } else
        {
            s = new String("Cannot write file to cache: ");
        }
        Log.e("FileCache", s, bytebuffer);
    }

    private class DefaultStatFsProvider
        implements StatFsProvider
    {

        public final long getFreeBytes()
        {
            return (new StatFs(Environment.getDataDirectory().getPath())).getFreeBytes();
        }

        public final long getTotalBytes()
        {
            return (new StatFs(Environment.getDataDirectory().getPath())).getTotalBytes();
        }

        DefaultStatFsProvider()
        {
        }
    }


    private class StatFsProvider
    {

        public abstract long getFreeBytes();

        public abstract long getTotalBytes();
    }

}
