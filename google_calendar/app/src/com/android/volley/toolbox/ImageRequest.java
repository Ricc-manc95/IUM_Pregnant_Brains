// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

// Referenced classes of package com.android.volley.toolbox:
//            HttpHeaderParser

public class ImageRequest extends Request
{

    private static final Object sDecodeLock = new Object();
    private final android.graphics.Bitmap.Config mDecodeConfig = null;
    private com.android.volley.Response.Listener mListener;
    private final Object mLock = new Object();
    private final int mMaxHeight;
    private final int mMaxWidth;
    private final android.widget.ImageView.ScaleType mScaleType;

    public ImageRequest(String s, com.android.volley.Response.Listener listener, int i, int j, android.widget.ImageView.ScaleType scaletype, android.graphics.Bitmap.Config config, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, s, errorlistener);
        super.mRetryPolicy = new DefaultRetryPolicy(1000, 2, 2.0F);
        mListener = listener;
        mMaxWidth = i;
        mMaxHeight = j;
        mScaleType = scaletype;
    }

    private static int getResizedDimension(int i, int j, int k, int l, android.widget.ImageView.ScaleType scaletype)
    {
        if (i != 0 || j != 0) goto _L2; else goto _L1
_L1:
        int i1 = k;
_L4:
        return i1;
_L2:
        if (scaletype != android.widget.ImageView.ScaleType.FIT_XY)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = i;
        if (i == 0)
        {
            return k;
        }
        if (true) goto _L4; else goto _L3
_L3:
        double d;
        if (i == 0)
        {
            return (int)(((double)j / (double)l) * (double)k);
        }
        i1 = i;
        if (j == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        d = (double)l / (double)k;
        if (scaletype != android.widget.ImageView.ScaleType.CENTER_CROP)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = i;
        if ((double)i * d < (double)j)
        {
            return (int)((double)j / d);
        }
        if (true) goto _L4; else goto _L5
_L5:
        i1 = i;
        if ((double)i * d > (double)j)
        {
            return (int)((double)j / d);
        }
        if (true) goto _L4; else goto _L6
_L6:
    }

    public final void cancel()
    {
        super.cancel();
        synchronized (mLock)
        {
            mListener = null;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final void deliverResponse(Object obj)
    {
        Bitmap bitmap = (Bitmap)obj;
        com.android.volley.Response.Listener listener;
        synchronized (mLock)
        {
            listener = mListener;
        }
        if (listener != null)
        {
            listener.onResponse(bitmap);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final com.android.volley.Request.Priority getPriority()
    {
        return com.android.volley.Request.Priority.LOW;
    }

    public final Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        Object obj2 = sDecodeLock;
        obj2;
        JVM INSTR monitorenter ;
        Object obj;
        Object obj1;
        obj = networkresponse.data;
        obj1 = new android.graphics.BitmapFactory.Options();
        if (mMaxWidth != 0 || mMaxHeight != 0) goto _L2; else goto _L1
_L1:
        obj1.inPreferredConfig = mDecodeConfig;
        obj = BitmapFactory.decodeByteArray(((byte []) (obj)), 0, obj.length, ((android.graphics.BitmapFactory.Options) (obj1)));
_L6:
        if (obj != null) goto _L4; else goto _L3
_L3:
        obj = new Response(new ParseError(networkresponse));
        networkresponse = ((NetworkResponse) (obj));
_L9:
        obj2;
        JVM INSTR monitorexit ;
        return networkresponse;
_L2:
        double d;
        int l;
        int i1;
        obj1.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(((byte []) (obj)), 0, obj.length, ((android.graphics.BitmapFactory.Options) (obj1)));
        int i = ((android.graphics.BitmapFactory.Options) (obj1)).outWidth;
        int k = ((android.graphics.BitmapFactory.Options) (obj1)).outHeight;
        l = getResizedDimension(mMaxWidth, mMaxHeight, i, k, mScaleType);
        i1 = getResizedDimension(mMaxHeight, mMaxWidth, k, i, mScaleType);
        obj1.inJustDecodeBounds = false;
        d = Math.min((double)i / (double)l, (double)k / (double)i1);
        float f;
        f = 1.0F;
        break MISSING_BLOCK_LABEL_358;
_L10:
        obj1.inSampleSize = (int)f;
        obj1 = BitmapFactory.decodeByteArray(((byte []) (obj)), 0, obj.length, ((android.graphics.BitmapFactory.Options) (obj1)));
        obj = obj1;
        if (obj1 == null) goto _L6; else goto _L5
_L5:
        if (((Bitmap) (obj1)).getWidth() > l) goto _L8; else goto _L7
_L7:
        obj = obj1;
        if (((Bitmap) (obj1)).getHeight() <= i1) goto _L6; else goto _L8
_L8:
        obj = Bitmap.createScaledBitmap(((Bitmap) (obj1)), l, i1, true);
        ((Bitmap) (obj1)).recycle();
          goto _L6
_L4:
        obj = new Response(obj, HttpHeaderParser.parseCacheHeaders(networkresponse));
        networkresponse = ((NetworkResponse) (obj));
          goto _L9
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        int j = networkresponse.data.length;
        networkresponse = super.mUrl;
        Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Caught OOM for %d byte image, url=%s", new Object[] {
            Integer.valueOf(j), networkresponse
        }));
        networkresponse = new Response(new ParseError(outofmemoryerror));
        obj2;
        JVM INSTR monitorexit ;
        return networkresponse;
        networkresponse;
        obj2;
        JVM INSTR monitorexit ;
        throw networkresponse;
        while ((double)(2.0F * f) <= d) 
        {
            f *= 2.0F;
        }
          goto _L10
    }

}
