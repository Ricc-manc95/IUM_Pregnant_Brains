// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatBaseImpl, TypefaceCompatUtil

final class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl
{

    public static final Method sAddFontWeightStyle;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;

    TypefaceCompatApi24Impl()
    {
    }

    private static boolean addFontWeightStyle(Object obj, ByteBuffer bytebuffer, int i, int j, boolean flag)
    {
        flag = ((Boolean)sAddFontWeightStyle.invoke(obj, new Object[] {
            bytebuffer, Integer.valueOf(i), null, Integer.valueOf(j), Boolean.valueOf(flag)
        })).booleanValue();
        return flag;
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static Typeface createFromFamiliesWithDefault(Object obj)
    {
        Object obj1 = Array.newInstance(sFontFamily, 1);
        Array.set(obj1, 0, obj);
        obj = (Typeface)sCreateFromFamiliesWithDefault.invoke(null, new Object[] {
            obj1
        });
        return ((Typeface) (obj));
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static Object newFamily()
    {
        Object obj = sFontFamilyCtor.newInstance(new Object[0]);
        return obj;
        Object obj1;
        obj1;
_L2:
        throw new RuntimeException(((Throwable) (obj1)));
        obj1;
        continue; /* Loop/switch isn't completed */
        obj1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final Typeface createFromFontFamilyFilesResourceEntry(Context context, android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry fontfamilyfilesresourceentry, Resources resources, int i)
    {
        Object obj = newFamily();
        fontfamilyfilesresourceentry = fontfamilyfilesresourceentry.mEntries;
        int j = fontfamilyfilesresourceentry.length;
        for (i = 0; i < j; i++)
        {
            Object obj1 = fontfamilyfilesresourceentry[i];
            for (ByteBuffer bytebuffer = TypefaceCompatUtil.copyToDirectBuffer(context, resources, ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj1)).mResourceId); bytebuffer == null || !addFontWeightStyle(obj, bytebuffer, ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj1)).mTtcIndex, ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj1)).mWeight, ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj1)).mItalic);)
            {
                return null;
            }

        }

        return createFromFamiliesWithDefault(obj);
    }

    public final Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        Object obj = newFamily();
        SimpleArrayMap simplearraymap = new SimpleArrayMap();
        int k = afontinfo.length;
        for (int j = 0; j < k; j++)
        {
            android.support.v4.provider.FontsContractCompat.FontInfo fontinfo = afontinfo[j];
            android.net.Uri uri = fontinfo.mUri;
            ByteBuffer bytebuffer1 = (ByteBuffer)simplearraymap.get(uri);
            ByteBuffer bytebuffer = bytebuffer1;
            if (bytebuffer1 == null)
            {
                bytebuffer = TypefaceCompatUtil.mmap(context, cancellationsignal, uri);
                simplearraymap.put(uri, bytebuffer);
            }
            if (!addFontWeightStyle(obj, bytebuffer, fontinfo.mTtcIndex, fontinfo.mWeight, fontinfo.mItalic))
            {
                return null;
            }
        }

        return Typeface.create(createFromFamiliesWithDefault(obj), i);
    }

    static 
    {
        Method method = null;
        Object obj;
        Constructor constructor;
        Class class1;
        Method method1;
        class1 = Class.forName("android.graphics.FontFamily");
        constructor = class1.getConstructor(new Class[0]);
        method1 = class1.getMethod("addFontWeightStyle", new Class[] {
            java/nio/ByteBuffer, Integer.TYPE, java/util/List, Integer.TYPE, Boolean.TYPE
        });
        obj = android/graphics/Typeface.getMethod("createFromFamiliesWithDefault", new Class[] {
            Array.newInstance(class1, 1).getClass()
        });
        method = method1;
_L2:
        sFontFamilyCtor = constructor;
        sFontFamily = class1;
        sAddFontWeightStyle = method;
        sCreateFromFamiliesWithDefault = ((Method) (obj));
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
_L3:
        Log.e("TypefaceCompatApi24Impl", classnotfoundexception.getClass().getName(), classnotfoundexception);
        classnotfoundexception = null;
        constructor = null;
        class1 = null;
        if (true) goto _L2; else goto _L1
_L1:
        classnotfoundexception;
          goto _L3
    }
}
