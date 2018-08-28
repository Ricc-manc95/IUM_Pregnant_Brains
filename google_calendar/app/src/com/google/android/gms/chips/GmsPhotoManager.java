// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import android.content.ContentResolver;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import com.android.ex.chips.DefaultPhotoManager;
import com.android.ex.chips.PhotoManager;
import com.android.ex.chips.RecipientEntry;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.people.model.AvatarReference;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.google.android.gms.chips:
//            GmsRecipientEntry

public final class GmsPhotoManager
    implements PhotoManager
{

    public final GoogleApiClient mClient;
    private boolean mClosed;
    private final DefaultPhotoManager mDefaultPhotoManager;
    public final LruCache mPhotoCacheMap = new LruCache(20);

    public GmsPhotoManager(GoogleApiClient googleapiclient, ContentResolver contentresolver)
    {
        mClosed = false;
        mClient = googleapiclient;
        mDefaultPhotoManager = new DefaultPhotoManager(contentresolver);
    }

    protected static byte[] readBytesFromInputStreamAndClose(InputStream inputstream)
    {
        Object obj1;
        byte abyte0[];
        abyte0 = new byte[16384];
        obj1 = new ByteArrayOutputStream();
_L2:
        Object obj = obj1;
        int i = inputstream.read(abyte0);
        if (i == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = obj1;
        ((ByteArrayOutputStream) (obj1)).write(abyte0, 0, i);
        if (true) goto _L2; else goto _L1
        abyte0;
_L6:
        obj = obj1;
        Log.e("GmsPhotoManager", "Error reading photo input stream", abyte0);
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            Log.e("GmsPhotoManager", "Error closing photo input stream", inputstream);
        }
        if (obj1 != null)
        {
            try
            {
                ((ByteArrayOutputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream)
            {
                Log.e("GmsPhotoManager", "Error closing photo output stream", inputstream);
                return null;
            }
        }
        return null;
_L1:
        obj = obj1;
        abyte0 = ((ByteArrayOutputStream) (obj1)).toByteArray();
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            Log.e("GmsPhotoManager", "Error closing photo input stream", inputstream);
        }
        try
        {
            ((ByteArrayOutputStream) (obj1)).close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            Log.e("GmsPhotoManager", "Error closing photo output stream", inputstream);
            return abyte0;
        }
        return abyte0;
        obj1;
        obj = null;
_L4:
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            Log.e("GmsPhotoManager", "Error closing photo input stream", inputstream);
        }
        if (obj != null)
        {
            try
            {
                ((ByteArrayOutputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream)
            {
                Log.e("GmsPhotoManager", "Error closing photo output stream", inputstream);
            }
        }
        throw obj1;
        obj1;
        if (true) goto _L4; else goto _L3
_L3:
        abyte0;
        obj1 = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void populatePhotoBytesAsync(RecipientEntry recipiententry, com.android.ex.chips.PhotoManager.PhotoManagerCallback photomanagercallback)
    {
        if (recipiententry == null || (recipiententry instanceof GmsRecipientEntry)) goto _L2; else goto _L1
_L1:
        mDefaultPhotoManager.populatePhotoBytesAsync(recipiententry, photomanagercallback);
_L4:
        return;
_L2:
        GmsRecipientEntry gmsrecipiententry;
        gmsrecipiententry = (GmsRecipientEntry)recipiententry;
        if (gmsrecipiententry != null)
        {
            break; /* Loop/switch isn't completed */
        }
        Log.wtf("GmsPhotoManager", "GmsRecipientEntry is null", new Error());
        if (photomanagercallback != null)
        {
            photomanagercallback.onPhotoBytesAsyncLoadFailed();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (gmsrecipiententry == null || gmsrecipiententry.mAvatarReference == null)
        {
            recipiententry = null;
        } else
        {
            recipiententry = gmsrecipiententry.mAvatarReference.toString();
        }
        if (recipiententry == null)
        {
            flag = true;
        } else
        {
            recipiententry = (byte[])mPhotoCacheMap.get(recipiententry);
            if (recipiententry != null)
            {
                gmsrecipiententry.setPhotoBytes(recipiententry);
                flag = true;
            } else
            {
                flag = false;
            }
        }
        if (flag)
        {
            if (photomanagercallback != null)
            {
                if (gmsrecipiententry.getPhotoBytes() != null)
                {
                    photomanagercallback.onPhotoBytesPopulated();
                    return;
                } else
                {
                    photomanagercallback.onPhotoBytesAsyncLoadFailed();
                    return;
                }
            }
        } else
        {
            (new PhotoLoadTask(gmsrecipiententry, photomanagercallback)).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    private class PhotoLoadTask extends AsyncTask
    {

        private final com.android.ex.chips.PhotoManager.PhotoManagerCallback mCallback;
        private final GmsRecipientEntry mEntry;
        private final GmsPhotoManager this$0;

        protected final Object doInBackground(Object aobj[])
        {
            aobj = mClient;
            Object obj = mEntry;
            if (!((GoogleApiClient) (aobj)).isConnecting() && !((GoogleApiClient) (aobj)).isConnected())
            {
                return null;
            }
            obj = ((GmsRecipientEntry) (obj)).mAvatarReference;
            if (obj == null)
            {
                return null;
            }
            Object obj1 = new com.google.android.gms.people.Images.LoadImageOptions.Builder();
            obj1.zzbTi = 1;
            obj1.zzbTj = 1;
            obj1 = new com.google.android.gms.people.Images.LoadImageOptions(((com.google.android.gms.people.Images.LoadImageOptions.Builder) (obj1)));
            obj = (com.google.android.gms.people.Images.LoadImageResult)People.ImageApi.loadByReference(((GoogleApiClient) (aobj)), ((AvatarReference) (obj)), ((com.google.android.gms.people.Images.LoadImageOptions) (obj1))).await(5L, TimeUnit.SECONDS);
            aobj = ((com.google.android.gms.people.Images.LoadImageResult) (obj)).getParcelFileDescriptor();
            obj = ((com.google.android.gms.people.Images.LoadImageResult) (obj)).getStatus();
            int i = ((Status) (obj)).zzaEP;
            if (((Status) (obj)).zzaEP <= 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (!i || aobj == null)
            {
                return null;
            } else
            {
                return GmsPhotoManager.readBytesFromInputStreamAndClose(new FileInputStream(((ParcelFileDescriptor) (aobj)).getFileDescriptor()));
            }
        }

        protected final void onPostExecute(Object obj)
        {
            byte abyte0[] = (byte[])obj;
            obj = GmsPhotoManager.this;
            mEntry.setPhotoBytes(abyte0);
            if (abyte0 != null)
            {
                obj = mEntry;
                if (obj == null || ((GmsRecipientEntry) (obj)).mAvatarReference == null)
                {
                    obj = null;
                } else
                {
                    obj = ((GmsRecipientEntry) (obj)).mAvatarReference.toString();
                }
                if (obj != null)
                {
                    mPhotoCacheMap.put(obj, abyte0);
                }
                if (mCallback != null)
                {
                    mCallback.onPhotoBytesAsynchronouslyPopulated();
                }
            } else
            if (mCallback != null)
            {
                mCallback.onPhotoBytesAsyncLoadFailed();
                return;
            }
        }

        public PhotoLoadTask(GmsRecipientEntry gmsrecipiententry, com.android.ex.chips.PhotoManager.PhotoManagerCallback photomanagercallback)
        {
            this$0 = GmsPhotoManager.this;
            super();
            mEntry = gmsrecipiententry;
            mCallback = photomanagercallback;
        }
    }

}
