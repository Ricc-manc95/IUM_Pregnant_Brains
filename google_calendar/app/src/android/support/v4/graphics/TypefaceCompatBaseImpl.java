// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatUtil, TypefaceCompat

public class TypefaceCompatBaseImpl
{

    TypefaceCompatBaseImpl()
    {
    }

    protected static Typeface createFromInputStream(Context context, InputStream inputstream)
    {
        context = TypefaceCompatUtil.getTempFile(context);
        if (context == null)
        {
            return null;
        }
        boolean flag;
        try
        {
            flag = TypefaceCompatUtil.copyToFile(context, inputstream);
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            context.delete();
            return null;
        }
        finally
        {
            context.delete();
        }
        if (!flag)
        {
            context.delete();
            return null;
        }
        inputstream = Typeface.createFromFile(context.getPath());
        context.delete();
        return inputstream;
        throw inputstream;
    }

    static Object findBestFont(Object aobj[], int i, StyleExtractor styleextractor)
    {
        Object obj;
        char c;
        int j;
        int l;
        boolean flag;
        if ((i & 1) == 0)
        {
            c = '\u0190';
        } else
        {
            c = '\u02BC';
        }
        if ((i & 2) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = null;
        j = 0x7fffffff;
        l = aobj.length;
        i = 0;
        while (i < l) 
        {
            Object obj1 = aobj[i];
            int i1 = Math.abs(styleextractor.getWeight(obj1) - c);
            int k;
            if (styleextractor.isItalic(obj1) == flag)
            {
                k = 0;
            } else
            {
                k = 1;
            }
            k += i1 << 1;
            if (obj == null || j > k)
            {
                j = k;
                obj = obj1;
            }
            i++;
        }
        return obj;
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry fontfamilyfilesresourceentry, Resources resources, int i)
    {
        fontfamilyfilesresourceentry = (android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry)findBestFont(fontfamilyfilesresourceentry.mEntries, i, new _cls2());
        if (fontfamilyfilesresourceentry == null)
        {
            return null;
        } else
        {
            return TypefaceCompat.createFromResourcesFontFile(context, resources, ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (fontfamilyfilesresourceentry)).mResourceId, ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (fontfamilyfilesresourceentry)).mFileName, i);
        }
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        Object obj = null;
        if (afontinfo.length > 0) goto _L2; else goto _L1
_L1:
        afontinfo = null;
_L4:
        return afontinfo;
_L2:
        cancellationsignal = (android.support.v4.provider.FontsContractCompat.FontInfo)findBestFont(afontinfo, i, new _cls1());
        cancellationsignal = context.getContentResolver().openInputStream(((android.support.v4.provider.FontsContractCompat.FontInfo) (cancellationsignal)).mUri);
        context = createFromInputStream(context, cancellationsignal);
        afontinfo = context;
        if (cancellationsignal != null)
        {
            try
            {
                cancellationsignal.close();
            }
            // Misplaced declaration of an exception variable
            catch (CancellationSignal cancellationsignal)
            {
                return context;
            }
            return context;
        }
        if (true) goto _L4; else goto _L3
_L3:
        context;
        context = null;
_L8:
        if (context != null)
        {
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch (Context context) { }
        }
        return null;
        context;
        cancellationsignal = obj;
_L6:
        if (cancellationsignal != null)
        {
            try
            {
                cancellationsignal.close();
            }
            // Misplaced declaration of an exception variable
            catch (CancellationSignal cancellationsignal) { }
        }
        throw context;
        context;
        if (true) goto _L6; else goto _L5
_L5:
        context;
        context = cancellationsignal;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String s, int j)
    {
        context = TypefaceCompatUtil.getTempFile(context);
        if (context == null)
        {
            return null;
        }
        boolean flag;
        try
        {
            flag = TypefaceCompatUtil.copyToFile(context, resources, i);
        }
        // Misplaced declaration of an exception variable
        catch (Resources resources)
        {
            context.delete();
            return null;
        }
        finally
        {
            context.delete();
        }
        if (!flag)
        {
            context.delete();
            return null;
        }
        resources = Typeface.createFromFile(context.getPath());
        context.delete();
        return resources;
        throw resources;
    }

    private class StyleExtractor
    {

        public abstract int getWeight(Object obj);

        public abstract boolean isItalic(Object obj);
    }


    private class _cls2
        implements StyleExtractor
    {

        public final int getWeight(Object obj)
        {
            return ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry)obj).mWeight;
        }

        public final boolean isItalic(Object obj)
        {
            return ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry)obj).mItalic;
        }

        _cls2()
        {
        }
    }


    private class _cls1
        implements StyleExtractor
    {

        public final int getWeight(Object obj)
        {
            return ((android.support.v4.provider.FontsContractCompat.FontInfo)obj).mWeight;
        }

        public final boolean isItalic(Object obj)
        {
            return ((android.support.v4.provider.FontsContractCompat.FontInfo)obj).mItalic;
        }

        _cls1()
        {
        }
    }

}
