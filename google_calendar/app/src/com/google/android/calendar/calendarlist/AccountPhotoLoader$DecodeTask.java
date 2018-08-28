// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.ImageUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            AccountPhotoLoader

final class fileDescriptor extends AsyncTask
{

    private final ParcelFileDescriptor fileDescriptor;
    private final Request request;
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
        obj2 = ((Request) (obj1)).view.getTag();
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

    Request(Request request1, ParcelFileDescriptor parcelfiledescriptor)
    {
        this$0 = AccountPhotoLoader.this;
        super();
        request = request1;
        fileDescriptor = parcelfiledescriptor;
    }
}
