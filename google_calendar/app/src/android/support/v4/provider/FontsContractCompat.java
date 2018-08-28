// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatBaseImpl;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.util.LruCache;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.support.v4.provider:
//            SelfDestructiveThread, FontRequest

public final class FontsContractCompat
{

    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    private static final Comparator sByteArrayComparator = new _cls5();
    public static final Object sLock = new Object();
    public static final SimpleArrayMap sPendingReplies = new SimpleArrayMap();
    public static final LruCache sTypefaceCache = new LruCache(16);

    private static FontInfo[] getFontFromProvider(Context context, FontRequest fontrequest, String s, CancellationSignal cancellationsignal)
    {
        ArrayList arraylist;
        Uri uri;
        Uri uri1;
        arraylist = new ArrayList();
        uri = (new android.net.Uri.Builder()).scheme("content").authority(s).build();
        uri1 = (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("file").build();
        context = context.getContentResolver();
        fontrequest = fontrequest.mQuery;
        fontrequest = context.query(uri, new String[] {
            "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"
        }, "query = ?", new String[] {
            fontrequest
        }, null, cancellationsignal);
        if (fontrequest == null) goto _L2; else goto _L1
_L1:
        if (fontrequest.getCount() <= 0) goto _L2; else goto _L3
_L3:
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        l = fontrequest.getColumnIndex("result_code");
        s = new ArrayList();
        i1 = fontrequest.getColumnIndex("_id");
        j1 = fontrequest.getColumnIndex("file_id");
        k1 = fontrequest.getColumnIndex("font_ttc_index");
        l1 = fontrequest.getColumnIndex("font_weight");
        i2 = fontrequest.getColumnIndex("font_italic");
_L17:
        context = s;
        if (!fontrequest.moveToNext()) goto _L5; else goto _L4
_L4:
        if (l == -1) goto _L7; else goto _L6
_L6:
        int i = fontrequest.getInt(l);
_L18:
        if (k1 == -1) goto _L9; else goto _L8
_L8:
        int j = fontrequest.getInt(k1);
_L19:
        if (j1 != -1) goto _L11; else goto _L10
_L10:
        context = ContentUris.withAppendedId(uri, fontrequest.getLong(i1));
_L20:
        if (l1 == -1) goto _L13; else goto _L12
_L12:
        int k = fontrequest.getInt(l1);
_L21:
        if (i2 == -1) goto _L15; else goto _L14
_L14:
        if (fontrequest.getInt(i2) != 1) goto _L15; else goto _L16
_L16:
        boolean flag = true;
_L22:
        s.add(new FontInfo(context, j, k, flag, i));
          goto _L17
        context;
_L23:
        if (fontrequest != null)
        {
            fontrequest.close();
        }
        throw context;
_L7:
        i = 0;
          goto _L18
_L9:
        j = 0;
          goto _L19
_L11:
        context = ContentUris.withAppendedId(uri1, fontrequest.getLong(j1));
          goto _L20
_L13:
        k = 400;
          goto _L21
_L15:
        flag = false;
          goto _L22
_L2:
        context = arraylist;
_L5:
        if (fontrequest != null)
        {
            fontrequest.close();
        }
        return (FontInfo[])context.toArray(new FontInfo[0]);
        context;
        fontrequest = null;
          goto _L23
    }

    static TypefaceResult getFontInternal(Context context, FontRequest fontrequest, int i)
    {
        Object obj;
        ProviderInfo providerinfo;
        Object obj1;
        Object obj2;
        byte byte0;
        byte0 = -3;
        try
        {
            obj1 = context.getPackageManager();
            obj = context.getResources();
            obj2 = fontrequest.mProviderAuthority;
            providerinfo = ((PackageManager) (obj1)).resolveContentProvider(((String) (obj2)), 0);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return new TypefaceResult(null, -1);
        }
        if (providerinfo != null)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder("No package found for authority: ")).append(((String) (obj2))).toString());
        Signature asignature[];
        if (!providerinfo.packageName.equals(fontrequest.mProviderPackage))
        {
            throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder("Found content provider ")).append(((String) (obj2))).append(", but package was not ").append(fontrequest.mProviderPackage).toString());
        }
        asignature = ((PackageManager) (obj1)).getPackageInfo(providerinfo.packageName, 64).signatures;
        obj1 = new ArrayList();
        int j = 0;
_L2:
        if (j >= asignature.length)
        {
            break; /* Loop/switch isn't completed */
        }
        ((List) (obj1)).add(asignature[j].toByteArray());
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        Collections.sort(((List) (obj1)), sByteArrayComparator);
        if (fontrequest.mCertificates == null) goto _L4; else goto _L3
_L3:
        obj = fontrequest.mCertificates;
          goto _L5
_L15:
        int k;
        if (k >= ((List) (obj)).size())
        {
            break MISSING_BLOCK_LABEL_480;
        }
        asignature = new ArrayList((Collection)((List) (obj)).get(k));
        Collections.sort(asignature, sByteArrayComparator);
        if (((List) (obj1)).size() == asignature.size()) goto _L7; else goto _L6
_L6:
        j = 0;
          goto _L8
_L18:
        if (obj != null) goto _L10; else goto _L9
_L9:
        fontrequest = new FontFamilyResult(1, null);
        break MISSING_BLOCK_LABEL_285;
_L4:
        obj = FontResourcesParserCompat.readCerts(((android.content.res.Resources) (obj)), 0);
          goto _L5
_L19:
        if (j >= ((List) (obj1)).size()) goto _L12; else goto _L11
_L11:
        if (Arrays.equals((byte[])((List) (obj1)).get(j), (byte[])asignature.get(j))) goto _L14; else goto _L13
_L13:
        j = 0;
          goto _L8
_L10:
        fontrequest = new FontFamilyResult(0, getFontFromProvider(context, fontrequest, ((ProviderInfo) (obj)).authority, null));
        if (((FontFamilyResult) (fontrequest)).mStatusCode == 0)
        {
            fontrequest = ((FontFamilyResult) (fontrequest)).mFonts;
            context = TypefaceCompat.sTypefaceCompatImpl.createFromFontInfo(context, null, fontrequest, i);
            if (context != null)
            {
                i = 0;
            } else
            {
                i = -3;
            }
            return new TypefaceResult(context, i);
        }
        i = byte0;
        if (((FontFamilyResult) (fontrequest)).mStatusCode == 1)
        {
            i = -2;
        }
        return new TypefaceResult(null, i);
_L5:
        k = 0;
          goto _L15
_L8:
        if (j == 0) goto _L17; else goto _L16
_L16:
        obj = providerinfo;
          goto _L18
_L7:
        j = 0;
          goto _L19
_L14:
        j++;
          goto _L19
_L12:
        j = 1;
          goto _L8
_L17:
        k++;
          goto _L15
        obj = null;
          goto _L18
    }

    public static Typeface getFontSync(final Context context, final FontRequest request, final android.support.v4.content.res.ResourcesCompat.FontCallback fontCallback, final Handler handler, boolean flag, int i, final int style)
    {
        final Object id = (new StringBuilder()).append(request.mIdentifier).append("-").append(style).toString();
        Typeface typeface = (Typeface)sTypefaceCache.get(id);
        if (typeface != null)
        {
            if (fontCallback != null)
            {
                fontCallback.onFontRetrieved(typeface);
            }
            return typeface;
        }
        if (flag && i == -1)
        {
            request = getFontInternal(context, request, style);
            if (fontCallback != null)
            {
                if (((TypefaceResult) (request)).mResult == 0)
                {
                    id = ((TypefaceResult) (request)).mTypeface;
                    context = handler;
                    if (handler == null)
                    {
                        context = new Handler(Looper.getMainLooper());
                    }
                    context.post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls1(fontCallback, ((Typeface) (id))));
                } else
                {
                    i = ((TypefaceResult) (request)).mResult;
                    context = handler;
                    if (handler == null)
                    {
                        context = new Handler(Looper.getMainLooper());
                    }
                    context.post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls2(fontCallback, i));
                }
            }
            return ((TypefaceResult) (request)).mTypeface;
        }
        request = new _cls1();
        if (flag)
        {
            try
            {
                context = ((TypefaceResult)sBackgroundThread.postAndWait(request, i)).mTypeface;
            }
            // Misplaced declaration of an exception variable
            catch (final Context context)
            {
                return null;
            }
            return context;
        }
        if (fontCallback == null)
        {
            context = null;
        } else
        {
            context = new _cls2();
        }
        fontCallback = ((android.support.v4.content.res.ResourcesCompat.FontCallback) (sLock));
        fontCallback;
        JVM INSTR monitorenter ;
        if (!sPendingReplies.containsKey(id))
        {
            break MISSING_BLOCK_LABEL_277;
        }
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_255;
        }
        ((ArrayList)sPendingReplies.get(id)).add(context);
        fontCallback;
        JVM INSTR monitorexit ;
        return null;
        context;
        fontCallback;
        JVM INSTR monitorexit ;
        throw context;
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        handler = new ArrayList();
        handler.add(context);
        sPendingReplies.put(id, handler);
        fontCallback;
        JVM INSTR monitorexit ;
        context = sBackgroundThread;
        fontCallback = new _cls3();
        context.post(new SelfDestructiveThread._cls2(context, request, new Handler(), fontCallback));
        return null;
    }

    public static Map prepareFontData(Context context, FontInfo afontinfo[], CancellationSignal cancellationsignal)
    {
        HashMap hashmap = new HashMap();
        int j = afontinfo.length;
        for (int i = 0; i < j; i++)
        {
            Object obj = afontinfo[i];
            if (((FontInfo) (obj)).mResultCode != 0)
            {
                continue;
            }
            obj = ((FontInfo) (obj)).mUri;
            if (!hashmap.containsKey(obj))
            {
                hashmap.put(obj, TypefaceCompatUtil.mmap(context, cancellationsignal, ((Uri) (obj))));
            }
        }

        return Collections.unmodifiableMap(hashmap);
    }


    private class FontInfo
    {

        public final boolean mItalic;
        public final int mResultCode;
        public final int mTtcIndex;
        public final Uri mUri;
        public final int mWeight;

        public FontInfo(Uri uri, int i, int j, boolean flag, int k)
        {
            if (uri == null)
            {
                throw new NullPointerException();
            } else
            {
                mUri = (Uri)uri;
                mTtcIndex = i;
                mWeight = j;
                mItalic = flag;
                mResultCode = k;
                return;
            }
        }
    }


    private class TypefaceResult
    {

        public final int mResult;
        public final Typeface mTypeface;

        TypefaceResult(Typeface typeface, int i)
        {
            mTypeface = typeface;
            mResult = i;
        }
    }


    private class FontFamilyResult
    {

        public final FontInfo mFonts[];
        public final int mStatusCode;

        public FontFamilyResult(int i, FontInfo afontinfo[])
        {
            mStatusCode = i;
            mFonts = afontinfo;
        }
    }


    private class _cls1
        implements Callable
    {

        private final Context val$context;
        private final String val$id;
        private final FontRequest val$request;
        private final int val$style;

        public final Object call()
            throws Exception
        {
            TypefaceResult typefaceresult = FontsContractCompat.getFontInternal(context, request, style);
            if (typefaceresult.mTypeface != null)
            {
                FontsContractCompat.sTypefaceCache.put(id, typefaceresult.mTypeface);
            }
            return typefaceresult;
        }

        _cls1()
        {
            context = context1;
            request = fontrequest;
            style = i;
            id = s;
            super();
        }
    }


    private class _cls2
        implements SelfDestructiveThread.ReplyCallback
    {

        private final android.support.v4.content.res.ResourcesCompat.FontCallback val$fontCallback;
        private final Handler val$handler;

        public final void onReply(Object obj)
        {
            obj = (TypefaceResult)obj;
            if (obj == null)
            {
                android.support.v4.content.res.ResourcesCompat.FontCallback fontcallback = fontCallback;
                Handler handler1 = handler;
                obj = handler1;
                if (handler1 == null)
                {
                    obj = new Handler(Looper.getMainLooper());
                }
                ((Handler) (obj)).post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls2(fontcallback, 1));
                return;
            }
            if (((TypefaceResult) (obj)).mResult == 0)
            {
                android.support.v4.content.res.ResourcesCompat.FontCallback fontcallback1 = fontCallback;
                Typeface typeface = ((TypefaceResult) (obj)).mTypeface;
                Handler handler2 = handler;
                obj = handler2;
                if (handler2 == null)
                {
                    obj = new Handler(Looper.getMainLooper());
                }
                ((Handler) (obj)).post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls1(fontcallback1, typeface));
                return;
            }
            android.support.v4.content.res.ResourcesCompat.FontCallback fontcallback2 = fontCallback;
            int i = ((TypefaceResult) (obj)).mResult;
            Handler handler3 = handler;
            obj = handler3;
            if (handler3 == null)
            {
                obj = new Handler(Looper.getMainLooper());
            }
            ((Handler) (obj)).post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls2(fontcallback2, i));
        }

        _cls2()
        {
            fontCallback = fontcallback;
            handler = handler1;
            super();
        }
    }


    private class _cls3
        implements SelfDestructiveThread.ReplyCallback
    {

        private final String val$id;

        public final void onReply(Object obj)
        {
            TypefaceResult typefaceresult = (TypefaceResult)obj;
            obj = FontsContractCompat.sLock;
            obj;
            JVM INSTR monitorenter ;
            ArrayList arraylist = (ArrayList)FontsContractCompat.sPendingReplies.get(id);
            if (arraylist != null)
            {
                break MISSING_BLOCK_LABEL_32;
            }
            obj;
            JVM INSTR monitorexit ;
            return;
            FontsContractCompat.sPendingReplies.remove(id);
            obj;
            JVM INSTR monitorexit ;
            for (int i = 0; i < arraylist.size(); i++)
            {
                ((SelfDestructiveThread.ReplyCallback)arraylist.get(i)).onReply(typefaceresult);
            }

            break MISSING_BLOCK_LABEL_86;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls3()
        {
            id = s;
            super();
        }
    }


    private class _cls5
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            boolean flag;
            flag = false;
            obj = (byte[])obj;
            obj1 = (byte[])obj1;
            if (obj.length == obj1.length) goto _L2; else goto _L1
_L1:
            int i = obj.length - obj1.length;
_L4:
            return i;
_L2:
            int j = 0;
            do
            {
                i = ((flag) ? 1 : 0);
                if (j >= obj.length)
                {
                    continue;
                }
                if (obj[j] != obj1[j])
                {
                    return obj[j] - obj1[j];
                }
                j++;
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
        }

        _cls5()
        {
        }
    }

}
