// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.content.ContentResolver;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;

// Referenced classes of package com.android.ex.chips:
//            PhotoManager, RecipientEntry

public final class DefaultPhotoManager
    implements PhotoManager
{

    public final ContentResolver contentResolver;
    public final LruCache photoCacheMap = new LruCache(20);

    public DefaultPhotoManager(ContentResolver contentresolver)
    {
        contentResolver = contentresolver;
    }

    public final void populatePhotoBytesAsync(final RecipientEntry entry, final PhotoManager.PhotoManagerCallback callback)
    {
        final Uri photoThumbnailUri = entry.photoThumbnailUri;
        if (photoThumbnailUri == null) goto _L2; else goto _L1
_L1:
        byte abyte0[] = (byte[])photoCacheMap.get(photoThumbnailUri);
        if (abyte0 == null) goto _L4; else goto _L3
_L3:
        entry.setPhotoBytes(abyte0);
        if (callback != null)
        {
            callback.onPhotoBytesPopulated();
        }
_L6:
        return;
_L4:
        (new _cls1()).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        return;
_L2:
        if (callback != null)
        {
            callback.onPhotoBytesAsyncLoadFailed();
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    private class _cls1 extends AsyncTask
    {

        private final DefaultPhotoManager this$0;
        private final PhotoManager.PhotoManagerCallback val$callback;
        private final RecipientEntry val$entry;
        private final Uri val$photoThumbnailUri;

        private final transient byte[] doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAAR88______0()
        {
            byte abyte0[];
            Object obj;
            abyte0 = null;
            obj = contentResolver.query(photoThumbnailUri, PhotoQuery.PROJECTION, null, null, null);
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
                obj = contentResolver.openInputStream(photoThumbnailUri);
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
            entry.setPhotoBytes(((byte []) (obj)));
            if (obj != null)
            {
                photoCacheMap.put(photoThumbnailUri, obj);
                if (callback != null)
                {
                    callback.onPhotoBytesAsynchronouslyPopulated();
                }
            } else
            if (callback != null)
            {
                callback.onPhotoBytesAsyncLoadFailed();
                return;
            }
        }

        _cls1()
        {
            this$0 = DefaultPhotoManager.this;
            photoThumbnailUri = uri;
            entry = recipiententry;
            callback = photomanagercallback;
            super();
        }

        private class PhotoQuery
        {

            public static final String PROJECTION[] = {
                "data15"
            };

        }

    }

}
