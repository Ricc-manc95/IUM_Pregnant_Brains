// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.provider.FontRequest;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v4.content.res:
//            FontResourcesParserCompat

public final class ResourcesCompat
{

    public static Typeface loadFont(Context context, int i, TypedValue typedvalue, int j, FontCallback fontcallback, Handler handler, boolean flag)
    {
        Resources resources = context.getResources();
        resources.getValue(i, typedvalue, true);
        context = loadFont(context, resources, typedvalue, i, j, fontcallback, handler, flag);
        if (context == null && fontcallback == null)
        {
            throw new android.content.res.Resources.NotFoundException((new StringBuilder("Font resource ID #0x")).append(Integer.toHexString(i)).append(" could not be retrieved.").toString());
        } else
        {
            return context;
        }
    }

    private static Typeface loadFont(Context context, Resources resources, TypedValue typedvalue, int i, int j, final FontCallback final_fontcallback, Handler handler, boolean flag)
    {
        String s;
        if (typedvalue.string == null)
        {
            throw new android.content.res.Resources.NotFoundException((new StringBuilder("Resource \"")).append(resources.getResourceName(i)).append("\" (").append(Integer.toHexString(i)).append(") is not a Font: ").append(typedvalue).toString());
        }
        s = typedvalue.string.toString();
        if (s.startsWith("res/")) goto _L2; else goto _L1
_L1:
        class FontCallback._cls2
            implements Runnable
        {

            private final FontCallback this$0;
            private final int val$reason;

            public final void run()
            {
                onFontRetrievalFailed(reason);
            }

            public FontCallback._cls2()
            {
                this$0 = final_fontcallback;
                reason = I.this;
                super();
            }

            private class FontCallback
            {

                public abstract void onFontRetrievalFailed(int i);

                public abstract void onFontRetrieved(Typeface typeface);

                public FontCallback()
                {
                }
            }

        }

        if (final_fontcallback != null)
        {
            context = handler;
            if (handler == null)
            {
                context = new Handler(Looper.getMainLooper());
            }
            context.post(-3. new FontCallback._cls2());
        }
        context = null;
_L4:
        return context;
_L2:
        typedvalue = (Typeface)TypefaceCompat.sTypefaceCache.get(TypefaceCompat.createResourceUid(resources, i, j));
        if (typedvalue == null)
        {
            break MISSING_BLOCK_LABEL_185;
        }
        context = typedvalue;
        if (final_fontcallback == null) goto _L4; else goto _L3
_L3:
        class FontCallback._cls1
            implements Runnable
        {

            private final FontCallback this$0;
            private final Typeface val$typeface;

            public final void run()
            {
                onFontRetrieved(typeface);
            }

            public FontCallback._cls1()
            {
                this$0 = final_fontcallback;
                typeface = Typeface.this;
                super();
            }
        }

        context = handler;
        if (handler == null)
        {
            context = new Handler(Looper.getMainLooper());
        }
        context.post(typedvalue. new FontCallback._cls1());
        return typedvalue;
        if (!s.toLowerCase().endsWith(".xml")) goto _L6; else goto _L5
_L5:
        typedvalue = resources.getXml(i);
        Object obj;
        String s1;
        String s2;
        int k;
        int l;
        int i1;
        do
        {
            k = typedvalue.next();
        } while (k != 2 && k != 1);
        if (k != 2)
        {
            TypedArray typedarray;
            try
            {
                throw new XmlPullParserException("No start tag found");
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.e("ResourcesCompat", (new StringBuilder("Failed to parse xml resource ")).append(s).toString(), context);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.e("ResourcesCompat", (new StringBuilder("Failed to read xml resource ")).append(s).toString(), context);
            }
            if (final_fontcallback != null)
            {
                context = handler;
                if (handler == null)
                {
                    context = new Handler(Looper.getMainLooper());
                }
                context.post(-3. new FontCallback._cls2());
            }
            return null;
        }
        typedvalue.require(2, null, "font-family");
        if (!typedvalue.getName().equals("font-family")) goto _L8; else goto _L7
_L7:
        typedarray = resources.obtainAttributes(Xml.asAttributeSet(typedvalue), android.support.compat.R.styleable.FontFamily);
        obj = typedarray.getString(android.support.compat.R.styleable.FontFamily_fontProviderAuthority);
        s1 = typedarray.getString(android.support.compat.R.styleable.FontFamily_fontProviderPackage);
        s2 = typedarray.getString(android.support.compat.R.styleable.FontFamily_fontProviderQuery);
        k = typedarray.getResourceId(android.support.compat.R.styleable.FontFamily_fontProviderCerts, 0);
        l = typedarray.getInteger(android.support.compat.R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        i1 = typedarray.getInteger(android.support.compat.R.styleable.FontFamily_fontProviderFetchTimeout, 500);
        typedarray.recycle();
        if (obj == null || s1 == null || s2 == null) goto _L10; else goto _L9
_L9:
        for (; typedvalue.next() != 3; FontResourcesParserCompat.skip(typedvalue)) { }
        typedvalue = new FontResourcesParserCompat.ProviderResourceEntry(new FontRequest(((String) (obj)), s1, s2, FontResourcesParserCompat.readCerts(resources, k)), l, i1);
_L32:
        if (typedvalue != null) goto _L12; else goto _L11
_L11:
        Log.e("ResourcesCompat", "Failed to find font-family tag");
        if (final_fontcallback == null)
        {
            break MISSING_BLOCK_LABEL_1036;
        }
        if (handler != null) goto _L14; else goto _L13
_L13:
        context = new Handler(Looper.getMainLooper());
_L38:
        context.post(-3. new FontCallback._cls2());
        break MISSING_BLOCK_LABEL_1036;
_L10:
        ArrayList arraylist = new ArrayList();
_L31:
        do
        {
            if (typedvalue.next() == 3)
            {
                break MISSING_BLOCK_LABEL_858;
            }
        } while (typedvalue.getEventType() != 2);
        if (!typedvalue.getName().equals("font"))
        {
            break MISSING_BLOCK_LABEL_851;
        }
        obj = resources.obtainAttributes(Xml.asAttributeSet(typedvalue), android.support.compat.R.styleable.FontFamilyFont);
        if (!((TypedArray) (obj)).hasValue(android.support.compat.R.styleable.FontFamilyFont_fontWeight)) goto _L16; else goto _L15
_L15:
        k = android.support.compat.R.styleable.FontFamilyFont_fontWeight;
_L26:
        i1 = ((TypedArray) (obj)).getInt(k, 400);
        if (!((TypedArray) (obj)).hasValue(android.support.compat.R.styleable.FontFamilyFont_fontStyle)) goto _L18; else goto _L17
_L17:
        k = android.support.compat.R.styleable.FontFamilyFont_fontStyle;
_L27:
        int j1;
        boolean flag1;
        if (1 == ((TypedArray) (obj)).getInt(k, 0))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!((TypedArray) (obj)).hasValue(android.support.compat.R.styleable.FontFamilyFont_ttcIndex)) goto _L20; else goto _L19
_L19:
        k = android.support.compat.R.styleable.FontFamilyFont_ttcIndex;
_L28:
        if (!((TypedArray) (obj)).hasValue(android.support.compat.R.styleable.FontFamilyFont_fontVariationSettings)) goto _L22; else goto _L21
_L21:
        l = android.support.compat.R.styleable.FontFamilyFont_fontVariationSettings;
_L29:
        s1 = ((TypedArray) (obj)).getString(l);
        l = ((TypedArray) (obj)).getInt(k, 0);
        if (!((TypedArray) (obj)).hasValue(android.support.compat.R.styleable.FontFamilyFont_font)) goto _L24; else goto _L23
_L23:
        k = android.support.compat.R.styleable.FontFamilyFont_font;
_L30:
        j1 = ((TypedArray) (obj)).getResourceId(k, 0);
        s2 = ((TypedArray) (obj)).getString(k);
        ((TypedArray) (obj)).recycle();
        for (; typedvalue.next() != 3; FontResourcesParserCompat.skip(typedvalue)) { }
          goto _L25
_L16:
        k = android.support.compat.R.styleable.FontFamilyFont_android_fontWeight;
          goto _L26
_L18:
        k = android.support.compat.R.styleable.FontFamilyFont_android_fontStyle;
          goto _L27
_L20:
        k = android.support.compat.R.styleable.FontFamilyFont_android_ttcIndex;
          goto _L28
_L22:
        l = android.support.compat.R.styleable.FontFamilyFont_android_fontVariationSettings;
          goto _L29
_L24:
        k = android.support.compat.R.styleable.FontFamilyFont_android_font;
          goto _L30
_L25:
        arraylist.add(new FontResourcesParserCompat.FontFileResourceEntry(s2, i1, flag1, s1, l, j1));
          goto _L31
        FontResourcesParserCompat.skip(typedvalue);
          goto _L31
        if (!arraylist.isEmpty())
        {
            break MISSING_BLOCK_LABEL_873;
        }
        typedvalue = null;
          goto _L32
        typedvalue = new FontResourcesParserCompat.FontFamilyFilesResourceEntry((FontResourcesParserCompat.FontFileResourceEntry[])arraylist.toArray(new FontResourcesParserCompat.FontFileResourceEntry[arraylist.size()]));
          goto _L32
_L8:
        FontResourcesParserCompat.skip(typedvalue);
        typedvalue = null;
          goto _L32
_L12:
        return TypefaceCompat.createFromResourcesFamilyXml(context, typedvalue, resources, i, j, final_fontcallback, handler, flag);
_L6:
        resources = TypefaceCompat.createFromResourcesFontFile(context, resources, i, s, j);
        context = resources;
        if (final_fontcallback == null) goto _L4; else goto _L33
_L33:
        if (resources == null) goto _L35; else goto _L34
_L34:
        if (handler != null)
        {
            break MISSING_BLOCK_LABEL_1024;
        }
        context = new Handler(Looper.getMainLooper());
_L37:
        context.post(resources. new FontCallback._cls1());
        return resources;
_L35:
        if (handler != null)
        {
            break MISSING_BLOCK_LABEL_1018;
        }
        context = new Handler(Looper.getMainLooper());
_L36:
        context.post(-3. new FontCallback._cls2());
        return resources;
        context = handler;
          goto _L36
        context = handler;
          goto _L37
_L14:
        context = handler;
          goto _L38
        return null;
          goto _L32
    }
}
