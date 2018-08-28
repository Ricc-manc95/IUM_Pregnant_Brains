// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.support.v4.util.LruCache;
import com.android.ex.chips.RecipientEntry;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.Images;
import com.google.android.gms.people.People;
import com.google.android.gms.people.model.AvatarReference;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.chips:
//            GmsPhotoManager, GmsRecipientEntry

final class mCallback extends AsyncTask
{

    private final com.android.ex.chips.ack mCallback;
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
        Object obj1 = new com.google.android.gms.people.r();
        obj1.zzbTi = 1;
        obj1.zzbTj = 1;
        obj1 = new com.google.android.gms.people.(((com.google.android.gms.people.r) (obj1)));
        obj = (com.google.android.gms.people.r)People.ImageApi.loadByReference(((GoogleApiClient) (aobj)), ((AvatarReference) (obj)), ((com.google.android.gms.people.r) (obj1))).await(5L, TimeUnit.SECONDS);
        aobj = ((com.google.android.gms.people.r) (obj)).elFileDescriptor();
        obj = ((com.google.android.gms.people.elFileDescriptor) (obj)).us();
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

    public er(GmsRecipientEntry gmsrecipiententry, com.android.ex.chips.ack ack)
    {
        this$0 = GmsPhotoManager.this;
        super();
        mEntry = gmsrecipiententry;
        mCallback = ack;
    }
}
