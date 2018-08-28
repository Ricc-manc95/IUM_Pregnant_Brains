// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            ContactPhotoCache

final class val.target extends AsyncTask
{

    private final ContactPhotoCache this$0;
    private final Uri val$contactPhotoUri;
    private final ContentResolver val$contentResolver;
    private final ImageView val$target;

    protected final Object doInBackground(Object aobj[])
    {
        aobj = null;
        java.io.InputStream inputstream = android.provider.tacts.openContactPhotoInputStream(val$contentResolver, val$contactPhotoUri);
        if (inputstream != null)
        {
            aobj = BitmapFactory.decodeStream(inputstream);
            photoCache.put(val$contactPhotoUri, ((Object) (aobj)));
        }
        return ((Object) (aobj));
    }

    public final void onPostExecute(Object obj)
    {
        obj = (Bitmap)obj;
        if (obj != null && val$target.getTag() == val$contactPhotoUri)
        {
            val$target.setImageDrawable(ContactPhotoCache.createRoundDrawable(context.getResources(), ((Bitmap) (obj))));
        }
    }

    ()
    {
        this$0 = final_contactphotocache;
        val$contentResolver = contentresolver;
        val$contactPhotoUri = uri;
        val$target = ImageView.this;
        super();
    }
}
