// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public final class TypefaceCompatUtil
{

    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i)
    {
        context = getTempFile(context);
        if (context == null)
        {
            return null;
        }
        boolean flag = copyToFile(context, resources, i);
        if (!flag)
        {
            context.delete();
            return null;
        }
        resources = mmap(context);
        context.delete();
        return resources;
        resources;
        context.delete();
        throw resources;
    }

    public static boolean copyToFile(File file, Resources resources, int i)
    {
        Resources resources1 = null;
        resources = resources.openRawResource(i);
        resources1 = resources;
        boolean flag = copyToFile(file, ((InputStream) (resources)));
        if (resources != null)
        {
            try
            {
                resources.close();
            }
            // Misplaced declaration of an exception variable
            catch (File file)
            {
                return flag;
            }
        }
        return flag;
        file;
        if (resources1 != null)
        {
            try
            {
                resources1.close();
            }
            // Misplaced declaration of an exception variable
            catch (Resources resources) { }
        }
        throw file;
    }

    public static boolean copyToFile(File file, InputStream inputstream)
    {
        Object obj = new FileOutputStream(file, false);
        file = ((File) (obj));
        byte abyte0[] = new byte[1024];
_L2:
        file = ((File) (obj));
        int i = inputstream.read(abyte0);
        if (i == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        file = ((File) (obj));
        ((FileOutputStream) (obj)).write(abyte0, 0, i);
        if (true) goto _L2; else goto _L1
        file;
        inputstream = ((InputStream) (obj));
        obj = file;
_L6:
        file = inputstream;
        Log.e("TypefaceCompatUtil", (new StringBuilder("Error copying resource contents to temp file: ")).append(((IOException) (obj)).getMessage()).toString());
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (File file)
            {
                return false;
            }
        }
        return false;
_L1:
        if (obj != null)
        {
            try
            {
                ((Closeable) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (File file) { }
        }
        return true;
        inputstream;
        file = null;
_L4:
        if (file != null)
        {
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch (File file) { }
        }
        throw inputstream;
        inputstream;
        if (true) goto _L4; else goto _L3
_L3:
        obj;
        inputstream = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static File getTempFile(Context context)
    {
        String s;
        int i;
        s = (new StringBuilder(".font")).append(Process.myPid()).append("-").append(Process.myTid()).append("-").toString();
        i = 0;
_L3:
        if (i >= 100) goto _L2; else goto _L1
_L1:
        File file = new File(context.getCacheDir(), (new StringBuilder()).append(s).append(i).toString());
        boolean flag = file.createNewFile();
        if (flag)
        {
            return file;
        }
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        i++;
          goto _L3
_L2:
        return null;
    }

    public static ByteBuffer mmap(Context context, CancellationSignal cancellationsignal, Uri uri)
    {
        Object obj;
        context = context.getContentResolver();
        long l;
        try
        {
            uri = context.openFileDescriptor(uri, "r", cancellationsignal);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        if (uri != null) goto _L2; else goto _L1
_L1:
        if (uri == null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        uri.close();
        cancellationsignal = null;
_L4:
        return cancellationsignal;
_L2:
        obj = new FileInputStream(uri.getFileDescriptor());
        context = ((FileInputStream) (obj)).getChannel();
        l = context.size();
        context = context.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        ((FileInputStream) (obj)).close();
        cancellationsignal = context;
        if (uri == null) goto _L4; else goto _L3
_L3:
        uri.close();
        return context;
        cancellationsignal;
        throw cancellationsignal;
        context;
_L12:
        if (cancellationsignal == null) goto _L6; else goto _L5
_L5:
        ((FileInputStream) (obj)).close();
_L11:
        try
        {
            throw context;
        }
        // Misplaced declaration of an exception variable
        catch (CancellationSignal cancellationsignal) { }
        throw cancellationsignal;
        context;
_L13:
        if (uri == null) goto _L8; else goto _L7
_L7:
        if (cancellationsignal == null) goto _L10; else goto _L9
_L9:
        uri.close();
_L8:
        throw context;
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(cancellationsignal, ((Throwable) (obj)));
          goto _L11
_L6:
        ((FileInputStream) (obj)).close();
          goto _L11
        uri;
        ThrowableExtension.STRATEGY.addSuppressed(cancellationsignal, uri);
          goto _L8
_L10:
        uri.close();
          goto _L8
        context;
        cancellationsignal = null;
          goto _L12
        context;
        cancellationsignal = null;
          goto _L13
    }

    private static ByteBuffer mmap(File file)
    {
        Throwable throwable;
        Object obj;
        long l;
        try
        {
            obj = new FileInputStream(file);
        }
        // Misplaced declaration of an exception variable
        catch (File file)
        {
            return null;
        }
        file = ((FileInputStream) (obj)).getChannel();
        l = file.size();
        file = file.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        ((FileInputStream) (obj)).close();
        return file;
        throwable;
        throw throwable;
        file;
_L3:
        if (throwable == null)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        ((FileInputStream) (obj)).close();
_L1:
        throw file;
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(throwable, ((Throwable) (obj)));
          goto _L1
        ((FileInputStream) (obj)).close();
          goto _L1
        file;
        throwable = null;
        if (true) goto _L3; else goto _L2
_L2:
    }
}
