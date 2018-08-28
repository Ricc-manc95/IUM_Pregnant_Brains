// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.Images;
import com.google.android.gms.people.People;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class AccountPhotoLoader
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    public static AccountPhotoLoader instance;
    public static Bitmap placeholder;
    public GoogleApiClient client;
    public boolean closed;
    public Context context;
    public final HashMap images = new HashMap();
    public final List requests = new ArrayList();

    AccountPhotoLoader(Context context1)
    {
        Object obj = new com.google.android.gms.people.People.PeopleOptions1p.Builder();
        obj.zzbTn = 139;
        boolean flag;
        if (((com.google.android.gms.people.People.PeopleOptions1p.Builder) (obj)).zzbTn >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Must provide valid client application ID!"));
        }
        obj = new com.google.android.gms.people.People.PeopleOptions1p(((com.google.android.gms.people.People.PeopleOptions1p.Builder) (obj)));
        obj = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context1.getApplicationContext())).addApi(People.API_1P, ((com.google.android.gms.common.api.Api.ApiOptions.HasOptions) (obj)));
        if (this == null)
        {
            throw new NullPointerException(String.valueOf("Listener must not be null"));
        }
        ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).zzaJl.add(this);
        if (this == null)
        {
            throw new NullPointerException(String.valueOf("Listener must not be null"));
        } else
        {
            ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).zzaJm.add(this);
            client = ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).build();
            context = context1.getApplicationContext();
            return;
        }
    }

    public final void onConnected(Bundle bundle)
    {
        processNextRequest();
    }

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        LogUtils.e("TimelyAccountPhoto", "in onConnectionFailed: %s", new Object[] {
            connectionresult
        });
    }

    public final void onConnectionSuspended(int i)
    {
    }

    final void onImageLoaded(Status status, ParcelFileDescriptor parcelfiledescriptor, OwnerAvatarRequest owneravatarrequest)
    {
        boolean flag2 = true;
        boolean flag3;
        requests.remove(owneravatarrequest);
        flag3 = closed;
        if (!flag3) goto _L2; else goto _L1
_L1:
        if (!closed)
        {
            processNextRequest();
        }
_L4:
        return;
_L2:
        Object obj = owneravatarrequest.view.getTag();
        boolean flag;
        if (obj != owneravatarrequest)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        if (closed) goto _L4; else goto _L3
_L3:
        processNextRequest();
        return;
        boolean flag1;
        if (status.zzaEP <= 0)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
          goto _L5
_L7:
        LogUtils.d("TimelyAccountPhoto", "Avatar loaded: status=%s  pfd=%s", new Object[] {
            status, parcelfiledescriptor
        });
_L8:
        (new DecodeTask(owneravatarrequest, parcelfiledescriptor)).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        if (closed) goto _L4; else goto _L6
_L6:
        processNextRequest();
        return;
        status;
        if (!closed)
        {
            processNextRequest();
        }
        throw status;
_L5:
        if (flag1 && parcelfiledescriptor != null) goto _L8; else goto _L7
    }

    final void processNextRequest()
    {
        if (requests.isEmpty())
        {
            LogUtils.d("TimelyAccountPhoto", "processing next request, but list is empty", new Object[0]);
        } else
        if (client.isConnected())
        {
            OwnerAvatarRequest owneravatarrequest = (OwnerAvatarRequest)requests.get(0);
            People.ImageApi.loadOwnerAvatar(owneravatarrequest._fld0.client, owneravatarrequest.accountName, owneravatarrequest.pageId, owneravatarrequest.avatarSize, 1).setResultCallback(owneravatarrequest);
            return;
        }
    }

    private class OwnerAvatarRequest
        implements ResultCallback
    {

        public final String accountName;
        public final int avatarSize = 1;
        public final String pageId = null;
        public final AccountPhotoLoader this$0;
        public final ImageView view;

        public final void onResult(Result result)
        {
            result = (com.google.android.gms.people.Images.LoadImageResult)result;
            onImageLoaded(result.getStatus(), result.getParcelFileDescriptor(), this);
        }

        public OwnerAvatarRequest(ImageView imageview, String s, String s1, int i)
        {
            this$0 = AccountPhotoLoader.this;
            super();
            view = imageview;
            accountName = s;
        }
    }


    private class DecodeTask extends AsyncTask
    {

        private final ParcelFileDescriptor fileDescriptor;
        private final OwnerAvatarRequest request;
        private final AccountPhotoLoader this$0;

        private final transient Bitmap doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACC5N68SJFD5I2UPRIC5O6GQB3ECNK4QBKDLGN0EO_0()
        {
            if (fileDescriptor != null) goto _L2; else goto _L1
_L1:
            Object obj;
            obj = AccountPhotoLoader.this;
            if (AccountPhotoLoader.placeholder == null)
            {
                AccountPhotoLoader.placeholder = ImageUtils.frameBitmapInCircle(BitmapFactory.decodeResource(((AccountPhotoLoader) (obj)).context.getResources(), 0x7f020132));
            }
            obj = ImageUtils.frameBitmapInCircle(AccountPhotoLoader.placeholder);
_L4:
            if (fileDescriptor != null)
            {
                try
                {
                    fileDescriptor.close();
                }
                catch (IOException ioexception)
                {
                    LogUtils.e("TimelyAccountPhoto", ioexception, "IOExcpetion when closing resources", new Object[0]);
                    return ((Bitmap) (obj));
                }
            }
            return ((Bitmap) (obj));
_L2:
            obj = ImageUtils.frameBitmapInCircle(BitmapFactory.decodeStream(new FileInputStream(fileDescriptor.getFileDescriptor())));
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            if (fileDescriptor != null)
            {
                try
                {
                    fileDescriptor.close();
                }
                catch (IOException ioexception1)
                {
                    LogUtils.e("TimelyAccountPhoto", ioexception1, "IOExcpetion when closing resources", new Object[0]);
                }
            }
            throw exception;
        }

        protected final volatile Object doInBackground(Object aobj[])
        {
            return doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACC5N68SJFD5I2UPRIC5O6GQB3ECNK4QBKDLGN0EO_0();
        }

        protected final void onCancelled(Object obj)
        {
            if (fileDescriptor == null)
            {
                break MISSING_BLOCK_LABEL_14;
            }
            fileDescriptor.close();
            return;
            obj;
            LogUtils.e("TimelyAccountPhoto", ((Throwable) (obj)), "IOExcpetion when closing resources", new Object[0]);
            return;
        }

        protected final void onPostExecute(Object obj)
        {
            obj = (Bitmap)obj;
            Object obj1;
            Object obj2;
            obj1 = AccountPhotoLoader.this;
            obj1 = request;
            obj2 = ((OwnerAvatarRequest) (obj1)).view.getTag();
            boolean flag;
            if (obj2 != obj1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag) goto _L2; else goto _L1
_L1:
            if (fileDescriptor == null)
            {
                break MISSING_BLOCK_LABEL_50;
            }
            fileDescriptor.close();
_L4:
            return;
            obj;
            LogUtils.e("TimelyAccountPhoto", ((Throwable) (obj)), "IOExcpetion when closing resources", new Object[0]);
            return;
_L2:
            ImageView imageview = request.view;
            images.put(request.accountName, obj);
            imageview.setImageBitmap(((Bitmap) (obj)));
            if (fileDescriptor == null) goto _L4; else goto _L3
_L3:
            try
            {
                fileDescriptor.close();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                LogUtils.e("TimelyAccountPhoto", ((Throwable) (obj)), "IOExcpetion when closing resources", new Object[0]);
            }
            return;
            obj;
            if (fileDescriptor != null)
            {
                try
                {
                    fileDescriptor.close();
                }
                catch (IOException ioexception)
                {
                    LogUtils.e("TimelyAccountPhoto", ioexception, "IOExcpetion when closing resources", new Object[0]);
                }
            }
            throw obj;
        }

        DecodeTask(OwnerAvatarRequest owneravatarrequest, ParcelFileDescriptor parcelfiledescriptor)
        {
            this$0 = AccountPhotoLoader.this;
            super();
            request = owneravatarrequest;
            fileDescriptor = parcelfiledescriptor;
        }
    }

}
