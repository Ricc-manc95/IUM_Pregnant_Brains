// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.android.ex.chips:
//            DefaultPhotoManager, RecipientEntry

final class gerCallback extends AsyncTask
{

    private final DefaultPhotoManager this$0;
    private final gerCallback val$callback;
    private final RecipientEntry val$entry;
    private final Uri val$photoThumbnailUri;

    private final transient byte[] doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAAR88______0()
    {
        byte abyte0[];
        Object obj;
        abyte0 = null;
        obj = contentResolver.query(val$photoThumbnailUri, otoQuery.PROJECTION, null, null, null);
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (!((Cursor) (obj)).moveToFirst()) goto _L4; else goto _L3
_L3:
        abyte0 = ((Cursor) (obj)).getBlob(0);
        ((Cursor) (obj)).close();
_L6:
        return abyte0;
_L4:
        ((Cursor) (obj)).close();
        return null;
        Exception exception;
        exception;
        ((Cursor) (obj)).close();
        throw exception;
_L2:
        ByteArrayOutputStream bytearrayoutputstream;
        int i;
        try
        {
            obj = contentResolver.openInputStream(val$photoThumbnailUri);
        }
        catch (IOException ioexception)
        {
            return null;
        }
        if (obj == null) goto _L6; else goto _L5
_L5:
        exception = new byte[16384];
        bytearrayoutputstream = new ByteArrayOutputStream();
_L7:
        i = ((InputStream) (obj)).read(exception);
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_133;
        }
        bytearrayoutputstream.write(exception, 0, i);
          goto _L7
        exception;
        ((InputStream) (obj)).close();
        throw exception;
        ((InputStream) (obj)).close();
        exception = bytearrayoutputstream.toByteArray();
        return exception;
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        return doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAAR88______0();
    }

    protected final void onPostExecute(Object obj)
    {
        obj = (byte[])obj;
        val$entry.setPhotoBytes(((byte []) (obj)));
        if (obj != null)
        {
            photoCacheMap.put(val$photoThumbnailUri, obj);
            if (val$callback != null)
            {
                val$callback.onPhotoBytesAsynchronouslyPopulated();
            }
        } else
        if (val$callback != null)
        {
            val$callback.onPhotoBytesAsyncLoadFailed();
            return;
        }
    }

    gerCallback()
    {
        this$0 = final_defaultphotomanager;
        val$photoThumbnailUri = uri;
        val$entry = recipiententry;
        val$callback = gerCallback.this;
        super();
    }
}
