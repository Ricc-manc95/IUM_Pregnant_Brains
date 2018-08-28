// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.os.Trace;
import android.util.Log;
import com.android.bitmap.util.BitmapUtils;
import com.android.bitmap.util.RectUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.android.bitmap:
//            BitmapCache, ReusableBitmap, RequestKey

public class DecodeTask extends AsyncTask
{
    public static interface DecodeCallback
    {

        public abstract void onDecodeBegin(RequestKey requestkey);

        public abstract void onDecodeCancel(RequestKey requestkey);

        public abstract void onDecodeComplete(RequestKey requestkey, ReusableBitmap reusablebitmap);
    }

    public static final class DecodeOptions
    {

        public int destH;
        public int destW;
        public float horizontalCenter;
        public int sampleSizeStrategy;
        public float verticalCenter;

        public DecodeOptions(int i, int j, float f, float f1, int k)
        {
            destW = i;
            destH = j;
            horizontalCenter = f;
            verticalCenter = f1;
            sampleSizeStrategy = k;
        }
    }


    private static final String TAG = com/android/bitmap/DecodeTask.getSimpleName();
    private byte byteArray[];
    private final BitmapCache cache;
    private final DecodeCallback decodeCallback;
    private final DecodeOptions decodeOpts;
    private final RequestKey.FileDescriptorFactory factory;
    private ReusableBitmap inBitmap;
    private final RequestKey key;
    public final android.graphics.BitmapFactory.Options opts = new android.graphics.BitmapFactory.Options();

    public DecodeTask(RequestKey requestkey, DecodeOptions decodeoptions, RequestKey.FileDescriptorFactory filedescriptorfactory, byte abyte0[], DecodeCallback decodecallback, BitmapCache bitmapcache)
    {
        inBitmap = null;
        byteArray = null;
        key = requestkey;
        decodeOpts = decodeoptions;
        factory = filedescriptorfactory;
        byteArray = abyte0;
        decodeCallback = decodecallback;
        cache = bitmapcache;
    }

    private final Bitmap decode(ParcelFileDescriptor parcelfiledescriptor, InputStream inputstream)
    {
        if (parcelfiledescriptor != null)
        {
            return BitmapFactory.decodeFileDescriptor(parcelfiledescriptor.getFileDescriptor(), null, opts);
        }
        if (byteArray != null)
        {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opts);
        } else
        {
            return BitmapFactory.decodeStream(inputstream, null, opts);
        }
    }

    private final ReusableBitmap decode()
    {
        if (!isCancelled()) goto _L2; else goto _L1
_L1:
        Object obj1 = null;
_L61:
        return ((ReusableBitmap) (obj1));
_L2:
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj6;
        obj6 = null;
        obj = null;
        obj4 = null;
        obj2 = null;
        obj3 = null;
        obj1 = obj;
        if (factory == null) goto _L4; else goto _L3
_L3:
        obj1 = obj;
        Trace.beginSection("create fd");
        obj1 = obj;
        obj = factory.createFileDescriptor();
        obj1 = obj;
        Trace.endSection();
        obj1 = obj;
_L65:
        if (obj1 != null) goto _L6; else goto _L5
_L5:
        obj = obj3;
        obj2 = obj3;
        if (byteArray != null) goto _L6; else goto _L7
_L7:
        boolean flag = true;
_L9:
        obj = obj3;
        obj2 = obj3;
        int j;
        boolean flag3;
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (!j)
        {
            break MISSING_BLOCK_LABEL_169;
        }
        obj = obj3;
        obj2 = obj3;
        Trace.beginSection("poll for reusable bitmap");
        obj = obj3;
        obj2 = obj3;
        inBitmap = (ReusableBitmap)cache.poll();
        obj = obj3;
        obj2 = obj3;
        Trace.endSection();
        obj = obj3;
        obj2 = obj3;
        flag3 = isCancelled();
        break MISSING_BLOCK_LABEL_183;
_L4:
        obj1 = obj;
        obj3 = obj2;
        if (byteArray != null)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = obj;
        obj = reset(null);
        int i;
        if (obj == null)
        {
            double d;
            float f;
            Object obj5;
            Rect rect;
            boolean flag1;
            boolean flag2;
            int k;
            int l;
            boolean flag4;
            if (obj != null)
            {
                try
                {
                    ((InputStream) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        flag3 = isCancelled();
        obj3 = obj;
        if (flag3)
        {
            if (obj != null)
            {
                try
                {
                    ((InputStream) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        break; /* Loop/switch isn't completed */
_L6:
        flag = false;
        if (true) goto _L9; else goto _L8
        if (flag3)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj = obj3;
        obj2 = obj3;
        Trace.beginSection("get bytesize");
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_450;
        }
        obj = obj3;
        obj2 = obj3;
        ((ParcelFileDescriptor) (obj1)).getStatSize();
        obj = obj3;
        obj2 = obj3;
        Trace.endSection();
        obj = obj3;
        obj2 = obj3;
        Trace.beginSection("get orientation");
        obj = obj3;
        obj2 = obj3;
        key.hasOrientationExif();
        obj = obj3;
        obj2 = obj3;
        Trace.endSection();
        obj = obj3;
        obj2 = obj3;
        flag4 = isCancelled();
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj = obj3;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_767;
        }
        obj = obj3;
        obj2 = obj3;
        obj3 = reset(((InputStream) (obj3)));
        if (obj3 == null)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj = obj3;
        obj2 = obj3;
        flag4 = isCancelled();
        obj = obj3;
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj3 = obj;
        obj2 = obj3;
        obj4 = obj3;
        Trace.beginSection("decodeBounds");
        obj2 = obj3;
        obj4 = obj3;
        opts.inJustDecodeBounds = true;
        if (obj1 == null) goto _L11; else goto _L10
_L10:
        obj2 = obj3;
        obj4 = obj3;
        BitmapFactory.decodeFileDescriptor(((ParcelFileDescriptor) (obj1)).getFileDescriptor(), null, opts);
_L14:
        obj2 = obj3;
        obj4 = obj3;
        Trace.endSection();
        obj2 = obj3;
        obj4 = obj3;
        flag4 = isCancelled();
        break MISSING_BLOCK_LABEL_852;
_L11:
        obj2 = obj3;
        obj4 = obj3;
        if (byteArray == null) goto _L13; else goto _L12
_L12:
        obj2 = obj3;
        obj4 = obj3;
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opts);
          goto _L14
_L49:
        ThrowableExtension.STRATEGY.printStackTrace(((Throwable) (obj2)));
          goto _L15
_L13:
        obj2 = obj3;
        obj4 = obj3;
        BitmapFactory.decodeStream(((InputStream) (obj3)), null, opts);
          goto _L14
_L63:
        if (obj1 != null)
        {
            try
            {
                ((ParcelFileDescriptor) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (obj2 != null)
        {
            try
            {
                ((InputStream) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (!isCancelled())
        {
            cache.put(key, obj3);
        }
        if (obj3 != null)
        {
            ((ReusableBitmap) (obj3)).acquireReference();
        } else
        if (inBitmap != null)
        {
            cache.offer(inBitmap);
        }
        throw obj;
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj2 = obj3;
        obj4 = obj3;
        k = opts.outWidth;
        obj2 = obj3;
        obj4 = obj3;
        l = opts.outHeight;
        obj2 = obj3;
        obj4 = obj3;
        f = Math.min((float)k / (float)decodeOpts.destW, (float)l / (float)decodeOpts.destH);
        obj2 = obj3;
        obj4 = obj3;
        decodeOpts.sampleSizeStrategy;
        JVM INSTR tableswitch 1 2: default 3724
    //                   1 1504
    //                   2 1511;
           goto _L16 _L17 _L18
_L16:
        obj2 = obj3;
        obj4 = obj3;
        i = (int)Math.pow(2D, (int)(0.5D + Math.log(f) / Math.log(2D)));
_L24:
        obj2 = obj3;
        obj4 = obj3;
        opts.inSampleSize = Math.max(1, i);
        obj2 = obj3;
        obj4 = obj3;
        opts.inJustDecodeBounds = false;
        obj2 = obj3;
        obj4 = obj3;
        opts.inMutable = true;
        i = 0;
        flag2 = false;
        if (!j) goto _L20; else goto _L19
_L19:
        obj2 = obj3;
        obj4 = obj3;
        if (inBitmap != null) goto _L22; else goto _L21
_L21:
        obj2 = obj3;
        obj4 = obj3;
        Trace.beginSection("create reusable bitmap");
        obj2 = obj3;
        obj4 = obj3;
        inBitmap = new ReusableBitmap(Bitmap.createBitmap(decodeOpts.destW, decodeOpts.destH, android.graphics.Bitmap.Config.ARGB_8888));
        obj2 = obj3;
        obj4 = obj3;
        Trace.endSection();
        obj2 = obj3;
        obj4 = obj3;
        flag4 = isCancelled();
        i = ((flag2) ? 1 : 0);
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
          goto _L23
_L17:
        i = (int)f;
          goto _L24
_L18:
        d = f;
        obj2 = obj3;
        obj4 = obj3;
        i = (int)Math.ceil(d);
          goto _L24
        obj;
        obj2 = obj3;
        obj4 = obj3;
        obj5 = TAG;
        obj2 = obj3;
        obj4 = obj3;
        obj = String.valueOf(((OutOfMemoryError) (obj)).getMessage());
        obj2 = obj3;
        obj4 = obj3;
        if (((String) (obj)).length() == 0) goto _L26; else goto _L25
_L25:
        obj2 = obj3;
        obj4 = obj3;
        obj = "allocate new bitmap in decode thread failed: reason=".concat(((String) (obj)));
_L27:
        obj2 = obj3;
        obj4 = obj3;
        Log.e(((String) (obj5)), ((String) (obj)));
        if (obj1 != null)
        {
            try
            {
                ((ParcelFileDescriptor) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        if (obj3 != null)
        {
            try
            {
                ((InputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        if (!isCancelled())
        {
            cache.put(key, null);
        }
        if (inBitmap != null)
        {
            cache.offer(inBitmap);
        }
        return null;
_L26:
        obj2 = obj3;
        obj4 = obj3;
        obj = new String("allocate new bitmap in decode thread failed: reason=");
        if (true) goto _L27; else goto _L23
_L23:
        obj2 = obj3;
        obj4 = obj3;
        opts.inBitmap = inBitmap.bmp;
_L20:
        obj2 = obj3;
        obj4 = obj3;
        flag4 = isCancelled();
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj = obj3;
        if (!flag) goto _L29; else goto _L28
_L28:
        obj2 = obj3;
        obj4 = obj3;
        obj3 = reset(((InputStream) (obj3)));
        if (obj3 == null)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj2 = obj3;
        obj4 = obj3;
        flag4 = isCancelled();
        obj = obj3;
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
_L29:
        obj2 = obj;
        obj4 = obj;
        rect = new Rect();
        j = opts.inSampleSize;
        Trace.beginSection((new StringBuilder(24)).append("decodeCropped").append(j).toString());
        if (obj1 == null) goto _L31; else goto _L30
_L30:
        obj2 = BitmapRegionDecoder.newInstance(((ParcelFileDescriptor) (obj1)).getFileDescriptor(), true);
_L37:
        if (!isCancelled()) goto _L33; else goto _L32
_L32:
        obj5 = null;
_L39:
        ((BitmapRegionDecoder) (obj2)).recycle();
        obj2 = obj;
        obj4 = obj;
        Trace.endSection();
        obj3 = obj;
        obj = obj5;
_L48:
        obj2 = obj3;
        obj4 = obj3;
        flag4 = isCancelled();
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
          goto _L34
_L31:
        if (byteArray == null) goto _L36; else goto _L35
_L35:
        obj2 = BitmapRegionDecoder.newInstance(byteArray, 0, byteArray.length, true);
          goto _L37
_L36:
        obj2 = BitmapRegionDecoder.newInstance(((InputStream) (obj)), true);
          goto _L37
_L33:
        BitmapUtils.calculateCroppedSrcRect(k, l, decodeOpts.destW, decodeOpts.destH, decodeOpts.destH, opts.inSampleSize, decodeOpts.horizontalCenter, decodeOpts.verticalCenter, true, 1.0F, rect);
        RectUtils.rotateRectForOrientation(0, new Rect(0, 0, k, l), rect);
        obj3 = ((BitmapRegionDecoder) (obj2)).decodeRegion(rect, opts);
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj5 = obj3;
        if (flag1) goto _L39; else goto _L38
_L38:
        obj5 = obj3;
        if (i == 0) goto _L39; else goto _L40
_L40:
        obj5 = obj3;
        if (obj3 == null) goto _L39; else goto _L41
_L41:
        ((Bitmap) (obj3)).setPixel(0, 0, ((Bitmap) (obj3)).getPixel(0, 0));
        obj5 = obj3;
          goto _L39
        obj2;
        if (!flag) goto _L43; else goto _L42
_L42:
        obj4 = reset(((InputStream) (obj)));
        if (obj4 != null) goto _L45; else goto _L44
_L44:
        obj = obj4;
        obj2 = obj4;
        Trace.endSection();
        if (obj1 != null)
        {
            try
            {
                ((ParcelFileDescriptor) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        if (obj4 != null)
        {
            try
            {
                ((InputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        if (!isCancelled())
        {
            cache.put(key, null);
        }
        if (inBitmap != null)
        {
            cache.offer(inBitmap);
        }
        return null;
_L45:
        obj = obj4;
        flag4 = isCancelled();
        obj3 = obj4;
        if (!flag4) goto _L47; else goto _L46
_L46:
        obj = obj4;
        obj2 = obj4;
        Trace.endSection();
        if (obj1 != null)
        {
            try
            {
                ((ParcelFileDescriptor) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        if (obj4 != null)
        {
            try
            {
                ((InputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
        }
        if (!isCancelled())
        {
            cache.put(key, null);
        }
        if (inBitmap != null)
        {
            cache.offer(inBitmap);
        }
        return null;
_L43:
        obj3 = obj;
_L47:
        obj = obj3;
        ThrowableExtension.STRATEGY.printStackTrace(((Throwable) (obj2)));
        obj = obj3;
        obj2 = obj3;
        Trace.endSection();
        obj = null;
          goto _L48
_L64:
        obj = obj3;
        obj2 = obj3;
        Trace.endSection();
        obj = obj3;
        obj2 = obj3;
        try
        {
            throw obj4;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj2)
        {
            obj3 = obj;
        }
        finally
        {
            obj3 = obj6;
        }
        obj = null;
          goto _L49
_L34:
        if (obj != null) goto _L51; else goto _L50
_L50:
        obj2 = obj3;
        obj4 = obj3;
        flag4 = isCancelled();
        if (flag4) goto _L51; else goto _L52
_L52:
        i = opts.inSampleSize;
        Trace.beginSection((new StringBuilder(17)).append("decode").append(i).toString());
        if (inBitmap != null)
        {
            cache.offer(inBitmap);
            inBitmap = null;
            opts.inBitmap = null;
        }
        obj2 = decode(((ParcelFileDescriptor) (obj1)), ((InputStream) (obj3)));
        obj = obj2;
        obj2 = obj3;
        obj4 = obj3;
        Trace.endSection();
_L54:
        obj2 = obj3;
        obj4 = obj3;
        flag4 = isCancelled();
        obj5 = obj;
        if (flag4)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        break; /* Loop/switch isn't completed */
        obj4;
        obj2 = TAG;
        obj4 = ((IllegalArgumentException) (obj4)).getMessage();
        i = opts.inSampleSize;
        Log.e(((String) (obj2)), (new StringBuilder(String.valueOf(obj4).length() + 39)).append("decode failed: reason='").append(((String) (obj4))).append("' ss=").append(i).toString());
        if (opts.inSampleSize > 1)
        {
            opts.inSampleSize = 1;
            obj = decode(((ParcelFileDescriptor) (obj1)), ((InputStream) (obj3)));
        }
        obj2 = obj3;
        obj4 = obj3;
        Trace.endSection();
        if (true) goto _L54; else goto _L53
        obj;
        obj2 = obj3;
        obj4 = obj3;
        Trace.endSection();
        obj2 = obj3;
        obj4 = obj3;
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj4 = null;
            obj3 = obj2;
            obj2 = obj;
            obj = obj4;
        }
        finally
        {
            obj3 = obj6;
            obj2 = obj4;
        }
          goto _L49
_L51:
        obj5 = obj;
_L53:
        if (obj5 == null)
        {
            if (obj1 != null)
            {
                try
                {
                    ((ParcelFileDescriptor) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (obj3 != null)
            {
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
            }
            if (!isCancelled())
            {
                cache.put(key, null);
            }
            if (inBitmap != null)
            {
                cache.offer(inBitmap);
            }
            return null;
        }
        obj2 = obj3;
        obj4 = obj3;
        if (inBitmap == null) goto _L56; else goto _L55
_L55:
        obj2 = obj3;
        obj4 = obj3;
        obj = inBitmap;
        if (rect.isEmpty()) goto _L58; else goto _L57
_L57:
        obj.width = (rect.right - rect.left) / opts.inSampleSize;
        obj.height = (rect.bottom - rect.top) / opts.inSampleSize;
_L60:
        obj.orientation = 0;
        if (obj1 != null)
        {
            try
            {
                ((ParcelFileDescriptor) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (obj3 != null)
        {
            try
            {
                ((InputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (!isCancelled())
        {
            cache.put(key, obj);
        }
        if (obj != null)
        {
            ((ReusableBitmap) (obj)).acquireReference();
            return ((ReusableBitmap) (obj));
        }
        break; /* Loop/switch isn't completed */
_L58:
        try
        {
            obj.width = opts.outWidth;
            obj.height = opts.outHeight;
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Object obj2) { }
        finally
        {
            obj4 = obj;
        }
          goto _L49
_L56:
        obj2 = obj3;
        obj4 = obj3;
        obj = new ReusableBitmap(((Bitmap) (obj5)), false);
        obj.width = ((Bitmap) (obj5)).getWidth();
        obj.height = ((Bitmap) (obj5)).getHeight();
        if (true) goto _L60; else goto _L59
_L59:
        obj1 = obj;
        if (inBitmap != null)
        {
            cache.offer(inBitmap);
            return ((ReusableBitmap) (obj));
        }
          goto _L61
_L15:
        if (obj1 != null)
        {
            try
            {
                ((ParcelFileDescriptor) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (obj3 != null)
        {
            try
            {
                ((InputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (!isCancelled())
        {
            cache.put(key, obj);
        }
        if (obj != null)
        {
            ((ReusableBitmap) (obj)).acquireReference();
            return ((ReusableBitmap) (obj));
        }
        obj1 = obj;
        if (inBitmap == null) goto _L61; else goto _L62
_L62:
        cache.offer(inBitmap);
        return ((ReusableBitmap) (obj));
        obj;
        obj2 = null;
        obj1 = null;
        obj3 = obj6;
          goto _L63
        obj3;
        obj2 = null;
        obj1 = obj;
        obj = obj3;
        obj3 = obj6;
          goto _L63
        obj3;
        obj2 = obj;
        obj1 = null;
        obj = obj3;
        obj3 = obj6;
          goto _L63
        obj = obj2;
        obj2 = obj3;
        obj3 = obj4;
          goto _L63
        obj2;
        obj4 = obj;
        obj = obj2;
        obj2 = obj3;
        obj3 = obj4;
          goto _L63
        obj2;
        obj4 = obj;
        obj = obj2;
        obj2 = obj3;
        obj3 = obj4;
          goto _L63
        obj2;
        obj4 = obj;
        obj = obj2;
        obj2 = obj3;
        obj3 = obj4;
          goto _L63
        obj2;
        obj3 = null;
        obj = null;
          goto _L49
        obj2;
        obj3 = obj;
        obj = null;
        obj1 = obj4;
          goto _L49
        obj2;
          goto _L49
        obj2;
          goto _L49
        obj4;
        obj3 = obj;
          goto _L64
_L8:
        obj1 = null;
          goto _L65
_L22:
        i = 1;
          goto _L23
        obj4;
        obj3 = obj;
          goto _L64
    }

    private final InputStream reset(InputStream inputstream)
        throws IOException
    {
        Trace.beginSection("create stream");
        if (inputstream == null)
        {
            inputstream = key.createInputStream();
        } else
        if (inputstream.markSupported())
        {
            inputstream.reset();
        } else
        {
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream) { }
            inputstream = key.createInputStream();
        }
        Trace.endSection();
        return inputstream;
    }

    protected Object doInBackground(Object aobj[])
    {
        publishProgress(new Void[0]);
        return decode();
    }

    protected void onCancelled(Object obj)
    {
        obj = (ReusableBitmap)obj;
        decodeCallback.onDecodeCancel(key);
        if (obj != null)
        {
            ((ReusableBitmap) (obj)).releaseReference();
            if (inBitmap == null)
            {
                ((ReusableBitmap) (obj)).bmp.recycle();
            }
        }
    }

    public void onPostExecute(Object obj)
    {
        obj = (ReusableBitmap)obj;
        decodeCallback.onDecodeComplete(key, ((ReusableBitmap) (obj)));
    }

    protected void onProgressUpdate(Object aobj[])
    {
        decodeCallback.onDecodeBegin(key);
    }

}
