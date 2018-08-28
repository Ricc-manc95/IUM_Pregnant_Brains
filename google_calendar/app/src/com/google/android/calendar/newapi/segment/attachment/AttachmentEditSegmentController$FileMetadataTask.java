// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.timely.ApiClientAsyncTask;
import com.google.android.calendar.viewedit.segment.attachment.AttachmentHelper;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.internal.zzaks;
import com.google.common.base.Platform;
import com.google.common.collect.Iterators;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentEditSegmentController

final class this._cls0 extends ApiClientAsyncTask
{

    private final AttachmentEditSegmentController this$0;

    protected final Object doInBackgroundConnected(Object aobj[])
    {
        boolean flag = false;
        aobj = (DriveId[])aobj;
        aobj = (com.google.android.gms.drive.troller.FileMetadataTask)Drive.DriveApi.getFile(super.client, ((DriveId) (aobj[0]))).getMetadata(super.client).await();
        if (aobj != null)
        {
            if (((com.google.android.gms.drive.troller.FileMetadataTask) (aobj)).FileMetadataTask().zzaEP <= 0)
            {
                flag = true;
            }
            if (flag)
            {
                return ((com.google.android.gms.drive.troller.FileMetadataTask) (aobj)).FileMetadataTask();
            }
        }
        return null;
    }

    protected final void onPostExecute(Object obj)
    {
        Metadata metadata = (Metadata)obj;
        if (metadata != null) goto _L2; else goto _L1
_L1:
        LogUtils.w(AttachmentEditSegmentController.TAG, "Metadata is null", new Object[0]);
_L8:
        return;
_L2:
        boolean flag;
        String s = ((DriveId)metadata.zza(zzaks.zzbbQ)).zzaWQ;
        obj = AttachmentEditSegmentController.this;
        Object obj1;
        if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        obj = AttachmentEditSegmentController.this;
        if (TextUtils.isEmpty(s)) goto _L6; else goto _L5
_L5:
        obj = ((EventModificationsHolder)((SegmentController) (obj)).model).getEventModifications().getAttachments();
        obj1 = new this._cls0(s);
        if (Iterators.indexOf(((Iterable) (obj)).iterator(), ((com.google.common.base.Predicate) (obj1))) == -1) goto _L6; else goto _L7
_L7:
        flag = true;
_L9:
        if (!flag)
        {
            obj = (String)metadata.zza(zzaks.zzbcy);
            if (obj == null)
            {
                obj = (String)metadata.zza(zzaks.zzbbR);
            }
            obj1 = new com.google.android.calendar.api.event.attachment.nit>();
            obj1.nit> = Platform.nullToEmpty(((String) (obj)));
            obj1.nit> = Platform.nullToEmpty(s);
            obj1.nit> = Platform.nullToEmpty((String)metadata.zza(zzaks.zzbcw));
            obj1.nit> = Platform.nullToEmpty(AttachmentHelper.getTypeIconUrl((String)metadata.zza(zzaks.zzbcn)));
            obj1.nit> = (String)metadata.zza(zzaks.zzbcn);
            obj = ((com.google.android.calendar.api.event.attachment.nit>) (obj1)).nit>();
            ((EventModificationsHolder)model).getEventModifications().getAttachmentModifications().addAttachment(((com.google.android.calendar.api.event.attachment.Attachment) (obj)));
            updateUi();
            return;
        }
_L4:
        if (true) goto _L8; else goto _L6
_L6:
        flag = false;
          goto _L9
    }

    public (Context context, String s)
    {
        this$0 = AttachmentEditSegmentController.this;
        super(context, s);
    }
}
