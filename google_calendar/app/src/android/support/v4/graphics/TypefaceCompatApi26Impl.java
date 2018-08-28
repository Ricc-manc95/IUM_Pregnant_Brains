// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.v4.provider.FontsContractCompat;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatApi21Impl, TypefaceCompatBaseImpl

public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl
{

    private final Method mAbortCreation;
    private final Method mAddFontFromAssetManager;
    private final Method mAddFontFromBuffer;
    public final Method mCreateFromFamiliesWithDefault;
    public final Class mFontFamily;
    private final Constructor mFontFamilyCtor;
    private final Method mFreeze;

    public TypefaceCompatApi26Impl()
    {
        Method method;
        method = null;
        super();
        Object obj;
        Method method1;
        Method method2;
        Method method3;
        Constructor constructor;
        Class class1;
        Method method4;
        class1 = Class.forName("android.graphics.FontFamily");
        constructor = class1.getConstructor(new Class[0]);
        method3 = class1.getMethod("addFontFromAssetManager", new Class[] {
            android/content/res/AssetManager, java/lang/String, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, [Landroid/graphics/fonts/FontVariationAxis;
        });
        method2 = class1.getMethod("addFontFromBuffer", new Class[] {
            java/nio/ByteBuffer, Integer.TYPE, [Landroid/graphics/fonts/FontVariationAxis;, Integer.TYPE, Integer.TYPE
        });
        method1 = class1.getMethod("freeze", new Class[0]);
        method4 = class1.getMethod("abortCreation", new Class[0]);
        obj = obtainCreateFromFamiliesWithDefaultMethod(class1);
        method = method4;
_L2:
        mFontFamily = class1;
        mFontFamilyCtor = constructor;
        mAddFontFromAssetManager = method3;
        mAddFontFromBuffer = method2;
        mFreeze = method1;
        mAbortCreation = method;
        mCreateFromFamiliesWithDefault = ((Method) (obj));
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
_L3:
        Log.e("TypefaceCompatApi26Impl", (new StringBuilder("Unable to collect necessary methods for class ")).append(classnotfoundexception.getClass().getName()).toString(), classnotfoundexception);
        classnotfoundexception = null;
        method1 = null;
        method2 = null;
        method3 = null;
        constructor = null;
        class1 = null;
        if (true) goto _L2; else goto _L1
_L1:
        classnotfoundexception;
          goto _L3
    }

    private final boolean addFontFromAssetManager(Context context, Object obj, String s, int i, int j, int k, FontVariationAxis afontvariationaxis[])
    {
        boolean flag = ((Boolean)mAddFontFromAssetManager.invoke(obj, new Object[] {
            context.getAssets(), s, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), afontvariationaxis
        })).booleanValue();
        return flag;
        context;
_L2:
        throw new RuntimeException(context);
        context;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final boolean addFontFromBuffer(Object obj, ByteBuffer bytebuffer, int i, int j, int k)
    {
        boolean flag = ((Boolean)mAddFontFromBuffer.invoke(obj, new Object[] {
            bytebuffer, Integer.valueOf(i), null, Integer.valueOf(j), Integer.valueOf(k)
        })).booleanValue();
        return flag;
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final boolean freeze(Object obj)
    {
        boolean flag = ((Boolean)mFreeze.invoke(obj, new Object[0])).booleanValue();
        return flag;
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final Object newFamily()
    {
        Object obj = mFontFamilyCtor.newInstance(new Object[0]);
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

    protected Typeface createFromFamiliesWithDefault(Object obj)
    {
        Object obj1 = Array.newInstance(mFontFamily, 1);
        Array.set(obj1, 0, obj);
        obj = (Typeface)mCreateFromFamiliesWithDefault.invoke(null, new Object[] {
            obj1, Integer.valueOf(-1), Integer.valueOf(-1)
        });
        return ((Typeface) (obj));
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final Typeface createFromFontFamilyFilesResourceEntry(Context context, android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry fontfamilyfilesresourceentry, Resources resources, int i)
    {
        int k;
        if (mAddFontFromAssetManager == null)
        {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        boolean flag;
        if (mAddFontFromAssetManager != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return super.createFromFontFamilyFilesResourceEntry(context, fontfamilyfilesresourceentry, resources, i);
        }
        resources = ((Resources) (newFamily()));
        fontfamilyfilesresourceentry = fontfamilyfilesresourceentry.mEntries;
        k = fontfamilyfilesresourceentry.length;
        i = 0;
_L5:
        if (i >= k) goto _L2; else goto _L1
_L1:
        Object obj = fontfamilyfilesresourceentry[i];
        String s = ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj)).mFileName;
        int l = ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj)).mTtcIndex;
        int i1 = ((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj)).mWeight;
        int j;
        if (((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj)).mItalic)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (addFontFromAssetManager(context, resources, s, l, i1, j, FontVariationAxis.fromFontVariationSettings(((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry) (obj)).mVariationSettings))) goto _L4; else goto _L3
_L3:
        mAbortCreation.invoke(resources, new Object[0]);
        return null;
        context;
_L6:
        throw new RuntimeException(context);
_L4:
        i++;
          goto _L5
_L2:
        if (!freeze(resources))
        {
            return null;
        } else
        {
            return createFromFamiliesWithDefault(resources);
        }
        context;
          goto _L6
    }

    public final Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        if (afontinfo.length > 0) goto _L2; else goto _L1
_L1:
        context = null;
_L6:
        return context;
_L2:
        if (mAddFontFromAssetManager == null)
        {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        boolean flag;
        if (mAddFontFromAssetManager != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        android.support.v4.provider.FontsContractCompat.FontInfo fontinfo = (android.support.v4.provider.FontsContractCompat.FontInfo)TypefaceCompatBaseImpl.findBestFont(afontinfo, i, new TypefaceCompatBaseImpl._cls1(this));
        context = context.getContentResolver();
        try
        {
            afontinfo = context.openFileDescriptor(fontinfo.mUri, "r", cancellationsignal);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        cancellationsignal = null;
        if (afontinfo != null)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        if (afontinfo == null)
        {
            break MISSING_BLOCK_LABEL_90;
        }
        afontinfo.close();
        return null;
        context = (new android.graphics.Typeface.Builder(afontinfo.getFileDescriptor())).setWeight(fontinfo.mWeight).setItalic(fontinfo.mItalic).build();
        cancellationsignal = context;
        context = cancellationsignal;
        if (afontinfo == null) goto _L6; else goto _L5
_L5:
        afontinfo.close();
        return cancellationsignal;
        cancellationsignal;
        throw cancellationsignal;
        context;
_L19:
        if (afontinfo == null)
        {
            break MISSING_BLOCK_LABEL_162;
        }
        if (cancellationsignal == null)
        {
            break MISSING_BLOCK_LABEL_176;
        }
        afontinfo.close();
_L7:
        throw context;
        afontinfo;
        ThrowableExtension.STRATEGY.addSuppressed(cancellationsignal, afontinfo);
          goto _L7
        afontinfo.close();
          goto _L7
_L4:
        int j;
        int k;
        int l;
        context = FontsContractCompat.prepareFontData(context, afontinfo, cancellationsignal);
        cancellationsignal = ((CancellationSignal) (newFamily()));
        l = afontinfo.length;
        j = 0;
        k = 0;
_L12:
        if (j >= l) goto _L9; else goto _L8
_L8:
        android.support.v4.provider.FontsContractCompat.FontInfo fontinfo1 = afontinfo[j];
        ByteBuffer bytebuffer = (ByteBuffer)context.get(fontinfo1.mUri);
        if (bytebuffer == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i1 = fontinfo1.mTtcIndex;
        int j1 = fontinfo1.mWeight;
        if (fontinfo1.mItalic)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (addFontFromBuffer(cancellationsignal, bytebuffer, i1, j1, k)) goto _L11; else goto _L10
_L10:
        mAbortCreation.invoke(cancellationsignal, new Object[0]);
        return null;
        context;
_L17:
        throw new RuntimeException(context);
_L11:
        k = 1;
        j++;
          goto _L12
_L9:
        if (k != 0) goto _L14; else goto _L13
_L13:
        mAbortCreation.invoke(cancellationsignal, new Object[0]);
        return null;
        context;
_L16:
        throw new RuntimeException(context);
_L14:
        if (!freeze(cancellationsignal))
        {
            return null;
        } else
        {
            return Typeface.create(createFromFamiliesWithDefault(cancellationsignal), i);
        }
        context;
        if (true) goto _L16; else goto _L15
_L15:
        context;
          goto _L17
        context;
        if (true) goto _L19; else goto _L18
_L18:
    }

    public final Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String s, int j)
    {
        Object obj;
        obj = null;
        if (mAddFontFromAssetManager == null)
        {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        boolean flag;
        if (mAddFontFromAssetManager != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        context = super.createFromResourcesFontFile(context, resources, i, s, j);
_L5:
        return context;
_L2:
        resources = ((Resources) (newFamily()));
        if (addFontFromAssetManager(context, resources, s, 0, -1, -1, null)) goto _L4; else goto _L3
_L3:
        mAbortCreation.invoke(resources, new Object[0]);
        return null;
        context;
_L6:
        throw new RuntimeException(context);
_L4:
        context = obj;
        if (freeze(resources))
        {
            return createFromFamiliesWithDefault(resources);
        }
          goto _L5
        context;
          goto _L6
    }

    protected Method obtainCreateFromFamiliesWithDefaultMethod(Class class1)
        throws NoSuchMethodException
    {
        class1 = android/graphics/Typeface.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] {
            Array.newInstance(class1, 1).getClass(), Integer.TYPE, Integer.TYPE
        });
        class1.setAccessible(true);
        return class1;
    }
}
