// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.LruCache;
import android.util.Log;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatApi28Impl, TypefaceCompatApi26Impl, TypefaceCompatApi24Impl, TypefaceCompatApi21Impl, 
//            TypefaceCompatBaseImpl

public final class TypefaceCompat
{

    public static final LruCache sTypefaceCache;
    public static final TypefaceCompatBaseImpl sTypefaceCompatImpl = new TypefaceCompatApi28Impl();

    public static Typeface createFromResourcesFamilyXml(Context context, android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry familyresourceentry, Resources resources, int i, int j, android.support.v4.content.res.ResourcesCompat.FontCallback fontcallback, Handler handler, boolean flag)
    {
        boolean flag1 = true;
        if (familyresourceentry instanceof android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry)
        {
            familyresourceentry = (android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry)familyresourceentry;
            int k;
            if (flag)
            {
                if (((android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry) (familyresourceentry)).mStrategy != 0)
                {
                    flag1 = false;
                }
            } else
            if (fontcallback != null)
            {
                flag1 = false;
            }
            if (flag)
            {
                k = ((android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry) (familyresourceentry)).mTimeoutMs;
            } else
            {
                k = -1;
            }
            context = FontsContractCompat.getFontSync(context, ((android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry) (familyresourceentry)).mRequest, fontcallback, handler, flag1, k, j);
        } else
        {
            familyresourceentry = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry)familyresourceentry, resources, j);
            context = familyresourceentry;
            if (fontcallback != null)
            {
                if (familyresourceentry != null)
                {
                    context = handler;
                    if (handler == null)
                    {
                        context = new Handler(Looper.getMainLooper());
                    }
                    context.post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls1(fontcallback, familyresourceentry));
                    context = familyresourceentry;
                } else
                {
                    context = handler;
                    if (handler == null)
                    {
                        context = new Handler(Looper.getMainLooper());
                    }
                    context.post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls2(fontcallback, -3));
                    context = familyresourceentry;
                }
            }
        }
        if (context != null)
        {
            sTypefaceCache.put(createResourceUid(resources, i, j), context);
        }
        return context;
    }

    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String s, int j)
    {
        context = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i, s, j);
        if (context != null)
        {
            resources = createResourceUid(resources, i, j);
            sTypefaceCache.put(resources, context);
        }
        return context;
    }

    public static String createResourceUid(Resources resources, int i, int j)
    {
        return (new StringBuilder()).append(resources.getResourcePackageName(i)).append("-").append(i).append("-").append(j).toString();
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT < 28) goto _L2; else goto _L1
_L1:
_L4:
        sTypefaceCache = new LruCache(16);
        return;
_L2:
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
            continue; /* Loop/switch isn't completed */
        }
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            if (TypefaceCompatApi24Impl.sAddFontWeightStyle == null)
            {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            boolean flag;
            if (TypefaceCompatApi24Impl.sAddFontWeightStyle != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
                continue; /* Loop/switch isn't completed */
            }
        }
        sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        if (true) goto _L4; else goto _L3
_L3:
    }
}
