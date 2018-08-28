// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatBaseImpl

class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl
{

    TypefaceCompatApi21Impl()
    {
    }

    private static File getFile(ParcelFileDescriptor parcelfiledescriptor)
    {
label0:
        {
            try
            {
                parcelfiledescriptor = Os.readlink((new StringBuilder("/proc/self/fd/")).append(parcelfiledescriptor.getFd()).toString());
                if (!OsConstants.S_ISREG(Os.stat(parcelfiledescriptor).st_mode))
                {
                    break label0;
                }
                parcelfiledescriptor = new File(parcelfiledescriptor);
            }
            // Misplaced declaration of an exception variable
            catch (ParcelFileDescriptor parcelfiledescriptor)
            {
                return null;
            }
            return parcelfiledescriptor;
        }
        return null;
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        if (afontinfo.length > 0) goto _L2; else goto _L1
_L1:
        context = null;
_L7:
        return context;
_L2:
        Object obj;
        afontinfo = (android.support.v4.provider.FontsContractCompat.FontInfo)TypefaceCompatBaseImpl.findBestFont(afontinfo, i, new TypefaceCompatBaseImpl._cls1(this));
        obj = context.getContentResolver();
        try
        {
            afontinfo = ((ContentResolver) (obj)).openFileDescriptor(((android.support.v4.provider.FontsContractCompat.FontInfo) (afontinfo)).mUri, "r", cancellationsignal);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        cancellationsignal = getFile(afontinfo);
        if (cancellationsignal == null) goto _L4; else goto _L3
_L3:
        if (cancellationsignal.canRead()) goto _L5; else goto _L4
_L4:
        obj = new FileInputStream(afontinfo.getFileDescriptor());
        cancellationsignal = TypefaceCompatBaseImpl.createFromInputStream(context, ((java.io.InputStream) (obj)));
        ((FileInputStream) (obj)).close();
        context = cancellationsignal;
        if (afontinfo == null) goto _L7; else goto _L6
_L6:
        afontinfo.close();
        return cancellationsignal;
        cancellationsignal;
        throw cancellationsignal;
        context;
_L16:
        if (cancellationsignal == null) goto _L9; else goto _L8
_L8:
        ((FileInputStream) (obj)).close();
_L14:
        try
        {
            throw context;
        }
        // Misplaced declaration of an exception variable
        catch (CancellationSignal cancellationsignal) { }
        throw cancellationsignal;
        context;
_L17:
        if (afontinfo == null) goto _L11; else goto _L10
_L10:
        if (cancellationsignal == null) goto _L13; else goto _L12
_L12:
        afontinfo.close();
_L11:
        throw context;
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(cancellationsignal, ((Throwable) (obj)));
          goto _L14
_L9:
        ((FileInputStream) (obj)).close();
          goto _L14
_L5:
        cancellationsignal = Typeface.createFromFile(cancellationsignal);
        context = cancellationsignal;
        if (afontinfo == null) goto _L7; else goto _L15
_L15:
        afontinfo.close();
        return cancellationsignal;
        afontinfo;
        ThrowableExtension.STRATEGY.addSuppressed(cancellationsignal, afontinfo);
          goto _L11
_L13:
        afontinfo.close();
          goto _L11
        context;
        cancellationsignal = null;
          goto _L16
        context;
        cancellationsignal = null;
          goto _L17
    }
}
