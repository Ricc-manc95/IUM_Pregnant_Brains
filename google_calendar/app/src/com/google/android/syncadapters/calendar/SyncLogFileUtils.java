// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

class SyncLogFileUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/SyncLogFileUtils);
    public Context context;

    SyncLogFileUtils()
    {
    }

    static String archivedFileName(String s, int i)
    {
        if (i == 0)
        {
            return s;
        } else
        {
            return (new StringBuilder(String.valueOf(s).length() + 12)).append(s).append(".").append(i).toString();
        }
    }

    private static void compress(File file, GZIPOutputStream gzipoutputstream)
        throws IOException
    {
        file = new BufferedInputStream(new FileInputStream(file));
        gzipoutputstream = new BufferedOutputStream(gzipoutputstream);
        IOUtils.copyStream(file, gzipoutputstream, false, 1024);
        gzipoutputstream.flush();
        file.close();
        return;
        gzipoutputstream;
        LogUtils.e(TAG, gzipoutputstream, "Compressing a logfile", new Object[0]);
        file.close();
        return;
        gzipoutputstream;
        file.close();
        throw gzipoutputstream;
    }

    private static ByteArrayOutputStream getCombinedCompressedLogs(Context context1, String s, int i)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(bytearrayoutputstream);
        while (i >= 0) 
        {
            Object obj = context1.getFileStreamPath(archivedFileName(s, i));
            Object obj1 = new SyncLogFileUtils();
            obj1.context = context1;
            String s1 = ((File) (obj)).getName();
            obj1 = ((SyncLogFileUtils) (obj1)).context.getFileStreamPath(String.valueOf(s1).concat(".gz"));
            if (!((File) (obj1)).exists() && ((File) (obj)).canRead())
            {
                try
                {
                    obj = new BufferedInputStream(new FileInputStream(((File) (obj))));
                    IOUtils.copyStream(((InputStream) (obj)), bufferedoutputstream, false, 1024);
                    ((BufferedInputStream) (obj)).close();
                }
                catch (IOException ioexception)
                {
                    LogUtils.e(TAG, ioexception, "Copying a log file to the output.", new Object[0]);
                }
            } else
            if (((File) (obj1)).canRead())
            {
                try
                {
                    BufferedInputStream bufferedinputstream = new BufferedInputStream(new FileInputStream(((File) (obj1))));
                    obj1 = new GZIPInputStream(bufferedinputstream);
                    IOUtils.copyStream(((InputStream) (obj1)), bufferedoutputstream, false, 1024);
                    bufferedinputstream.close();
                    ((GZIPInputStream) (obj1)).close();
                }
                catch (IOException ioexception1)
                {
                    LogUtils.e(TAG, ioexception1, "Reading a logfile and uncompressing it to the output.", new Object[0]);
                }
            }
            i--;
        }
        try
        {
            bufferedoutputstream.flush();
        }
        // Misplaced declaration of an exception variable
        catch (Context context1)
        {
            LogUtils.e(TAG, context1, "Flushing the output stream.", new Object[0]);
            return bytearrayoutputstream;
        }
        return bytearrayoutputstream;
    }

    public static ByteArrayOutputStream getCombinedLogs(Context context1, String s, int i)
    {
        return getCombinedCompressedLogs(context1, s, i);
    }

    final void offsetToCompressedFile(File file, File file1)
    {
        Object obj;
        obj = file.getName();
        obj = context.getFileStreamPath(String.valueOf(obj).concat(".gz"));
        if (!((File) (obj)).exists()) goto _L2; else goto _L1
_L1:
        file = file1.getName();
        ((File) (obj)).renameTo(context.getFileStreamPath(String.valueOf(file).concat(".gz")));
        file1.delete();
_L4:
        return;
_L2:
        if (!file.exists()) goto _L4; else goto _L3
_L3:
        obj = file1.getName();
        obj = context.getFileStreamPath(String.valueOf(obj).concat(".gz"));
        obj = new FileOutputStream(((File) (obj)));
        obj = new GZIPOutputStream(((java.io.OutputStream) (obj)));
        compress(file, ((GZIPOutputStream) (obj)));
        ((GZIPOutputStream) (obj)).close();
_L6:
        file.delete();
        file1.delete();
        return;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        try
        {
            throw new NullPointerException();
        }
        catch (IOException ioexception)
        {
            LogUtils.e(TAG, ioexception, "Reading a logfile", new Object[0]);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

}
