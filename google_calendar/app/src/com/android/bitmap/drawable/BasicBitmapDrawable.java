// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.util.DisplayMetrics;
import android.util.Log;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.DecodeTask;
import com.android.bitmap.NamedThreadFactory;
import com.android.bitmap.RequestKey;
import com.android.bitmap.ReusableBitmap;
import com.android.bitmap.util.BitmapUtils;
import com.android.bitmap.util.RectUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BasicBitmapDrawable extends Drawable
    implements android.graphics.drawable.Drawable.Callback, com.android.bitmap.DecodeTask.DecodeCallback, com.android.bitmap.RequestKey.Callback
{

    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final Executor EXECUTOR;
    private static final int MAXIMUM_POOL_SIZE;
    private static final Executor SMALL_POOL_EXECUTOR;
    private static final String TAG = com/android/bitmap/drawable/BasicBitmapDrawable.getSimpleName();
    private ReusableBitmap bitmap;
    private final BitmapCache cache;
    private com.android.bitmap.RequestKey.Cancelable createFileDescriptorFactoryOrByteArrayTask;
    private final float density;
    public int layoutDirection;
    private final boolean limitDensity;
    public RequestKey mCurrKey;
    public int mDecodeHeight;
    public int mDecodeWidth;
    public final Paint mPaint = new Paint();
    private RequestKey mPrevKey;
    private final Rect rect = new Rect();
    private DecodeTask task;

    public BasicBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag)
    {
        density = resources.getDisplayMetrics().density;
        cache = bitmapcache;
        limitDensity = flag;
        mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    private final void decode(com.android.bitmap.RequestKey.FileDescriptorFactory filedescriptorfactory, byte abyte0[])
    {
        Trace.beginSection("decode");
        com.android.bitmap.DecodeTask.DecodeOptions decodeoptions;
        int i;
        int j;
        if (limitDensity)
        {
            float f = Math.min(1.0F, 1.5F / density);
            i = (int)((float)mDecodeWidth * f);
            j = (int)(f * (float)mDecodeHeight);
        } else
        {
            i = mDecodeWidth;
            j = mDecodeHeight;
        }
        if (task != null)
        {
            DecodeTask decodetask = task;
            decodetask.cancel(true);
            decodetask.opts.requestCancelDecode();
        }
        decodeoptions = new com.android.bitmap.DecodeTask.DecodeOptions(i, j, getDecodeHorizontalCenter(), getDecodeVerticalCenter(), getDecodeStrategy());
        task = new DecodeTask(mCurrKey, decodeoptions, filedescriptorfactory, abyte0, this, cache);
        task.executeOnExecutor(getExecutor(), new Void[0]);
        Trace.endSection();
    }

    private final boolean hasBitmap()
    {
        if (bitmap == null || bitmap.bmp == null)
        {
            return false;
        }
        if (bitmap.bmp.isRecycled())
        {
            Log.e(TAG, String.format("Bitmap %s has been recycled (reusable=%s, ref count=%d)", new Object[] {
                bitmap.bmp, Boolean.valueOf(bitmap.reusable), Integer.valueOf(bitmap.refCount)
            }));
            return false;
        } else
        {
            return true;
        }
    }

    public void bind(RequestKey requestkey)
    {
        Trace.beginSection("bind");
        if (mCurrKey != null && mCurrKey.equals(requestkey))
        {
            Trace.endSection();
            return;
        } else
        {
            setImage(requestkey);
            Trace.endSection();
            return;
        }
    }

    public final void byteArrayCreated(RequestKey requestkey, byte abyte0[])
    {
        if (createFileDescriptorFactoryOrByteArrayTask == null)
        {
            onDecodeFailed();
        } else
        {
            createFileDescriptorFactoryOrByteArrayTask = null;
            if (requestkey.equals(mCurrKey))
            {
                decode(null, abyte0);
                return;
            }
        }
    }

    public void draw(Canvas canvas)
    {
        Rect rect1;
        for (rect1 = getBounds(); rect1.isEmpty() || !hasBitmap();)
        {
            return;
        }

        BitmapUtils.calculateCroppedSrcRect(bitmap.width, bitmap.height, rect1.width(), rect1.height(), rect1.height(), 0x7fffffff, getDrawHorizontalCenter(), getDrawVerticalCenter(), false, getDrawVerticalOffsetMultiplier(), rect);
        int i = bitmap.orientation;
        RectUtils.rotateRectForOrientation(i, new Rect(0, 0, bitmap.width, bitmap.height), rect);
        Rect rect2 = new Rect(rect1);
        int j = rect1.centerX();
        int k = rect1.centerY();
        RectF rectf = new RectF(rect2);
        Matrix matrix = new Matrix();
        matrix.setRotate(i, j, k);
        matrix.mapRect(rectf);
        rect2.set((int)rectf.left, (int)rectf.top, (int)rectf.right, (int)rectf.bottom);
        canvas.save();
        canvas.rotate(i, rect1.centerX(), rect1.centerY());
        onDrawBitmap(canvas, rect, rect2);
        canvas.restore();
    }

    public int getAlpha()
    {
        return mPaint.getAlpha();
    }

    public ReusableBitmap getBitmap()
    {
        return bitmap;
    }

    public ColorFilter getColorFilter()
    {
        return mPaint.getColorFilter();
    }

    public float getDecodeHorizontalCenter()
    {
        return 0.5F;
    }

    public int getDecodeStrategy()
    {
        return 0;
    }

    public float getDecodeVerticalCenter()
    {
        return 0.5F;
    }

    protected float getDrawHorizontalCenter()
    {
        return 0.5F;
    }

    protected float getDrawVerticalCenter()
    {
        return 0.5F;
    }

    protected float getDrawVerticalOffsetMultiplier()
    {
        return 1.0F;
    }

    public Executor getExecutor()
    {
        return EXECUTOR;
    }

    public int getOpacity()
    {
        return !hasBitmap() || !bitmap.bmp.hasAlpha() && mPaint.getAlpha() >= 255 ? -1 : -3;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        invalidateSelf();
    }

    protected void loadFileDescriptorFactoryOrByteArray()
    {
        if (mCurrKey == null || mDecodeWidth == 0 || mDecodeHeight == 0)
        {
            onDecodeFailed();
        } else
        {
            createFileDescriptorFactoryOrByteArrayTask = mCurrKey.createFileDescriptorFactoryOrByteArrayAsync$51666RRD5TGMSP3IDTKM8BR2D5Q6QOBG5T96ASBLCLPN8IR5F4TKOORFDKNM2RJ4E9NMIP1FC9KN8RB1E0NL4PBHELIN6T2BCLSI8GR1DHM64OB3DCTIIJ33DTMIUOBECHP6UQB45TH6IT3DC5O2UKJ5E5QMASRK9DINI923C5N66PBCC5H6OP9R0(this);
            if (createFileDescriptorFactoryOrByteArrayTask == null)
            {
                decode(null, null);
                return;
            }
        }
    }

    public void onDecodeBegin(RequestKey requestkey)
    {
    }

    public void onDecodeCancel(RequestKey requestkey)
    {
        task = null;
    }

    public void onDecodeComplete(RequestKey requestkey, ReusableBitmap reusablebitmap)
    {
        if (!requestkey.equals(mCurrKey)) goto _L2; else goto _L1
_L1:
        setBitmap(reusablebitmap);
_L4:
        task = null;
        return;
_L2:
        if (reusablebitmap != null)
        {
            reusablebitmap.releaseReference();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onDecodeFailed()
    {
        invalidateSelf();
    }

    public void onDrawBitmap(Canvas canvas, Rect rect1, Rect rect2)
    {
        if (hasBitmap())
        {
            canvas.drawBitmap(bitmap.bmp, rect1, rect2, mPaint);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public void setAlpha(int i)
    {
        int j = mPaint.getAlpha();
        mPaint.setAlpha(i);
        if (i != j)
        {
            invalidateSelf();
        }
    }

    public void setBitmap(ReusableBitmap reusablebitmap)
    {
        if (hasBitmap())
        {
            bitmap.releaseReference();
        }
        bitmap = reusablebitmap;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public void setDecodeDimensions(int i, int j)
    {
        if (mDecodeWidth == 0 || mDecodeHeight == 0)
        {
            mDecodeWidth = i;
            mDecodeHeight = j;
            setImage(mCurrKey);
        }
    }

    protected void setImage(RequestKey requestkey)
    {
        Trace.beginSection("set image");
        Trace.beginSection("release reference");
        if (bitmap != null)
        {
            bitmap.releaseReference();
            bitmap = null;
        }
        Trace.endSection();
        mPrevKey = mCurrKey;
        mCurrKey = requestkey;
        if (task != null)
        {
            DecodeTask decodetask = task;
            decodetask.cancel(true);
            decodetask.opts.requestCancelDecode();
            task = null;
        }
        if (createFileDescriptorFactoryOrByteArrayTask != null)
        {
            createFileDescriptorFactoryOrByteArrayTask.cancel();
            createFileDescriptorFactoryOrByteArrayTask = null;
        }
        if (requestkey == null)
        {
            onDecodeFailed();
            Trace.endSection();
            return;
        }
        requestkey = (ReusableBitmap)cache.get(requestkey, true);
        if (requestkey != null)
        {
            setBitmap(requestkey);
        } else
        {
            loadFileDescriptorFactoryOrByteArray();
        }
        Trace.endSection();
    }

    public final void unbind()
    {
        Trace.beginSection("unbind");
        setImage(null);
        mPrevKey = null;
        Trace.endSection();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    static 
    {
        int i = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = i;
        CORE_POOL_SIZE = i + 1;
        MAXIMUM_POOL_SIZE = (CPU_COUNT << 1) + 1;
        ThreadPoolExecutor threadpoolexecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new NamedThreadFactory("decode"));
        SMALL_POOL_EXECUTOR = threadpoolexecutor;
        EXECUTOR = threadpoolexecutor;
    }
}
