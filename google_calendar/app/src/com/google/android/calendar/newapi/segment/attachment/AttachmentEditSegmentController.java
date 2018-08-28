// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.api.event.attachment.AttachmentPermissions;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.ApiClientAsyncTask;
import com.google.android.calendar.timely.DriveFilePickerActivity;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.viewedit.segment.attachment.AttachmentHelper;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.internal.zzaks;
import com.google.android.libraries.view.horizontalcarousel.HorizontalCarouselAdapter;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentEditSegment, AttachmentUtils, AttachmentTileView, AttachmentCarouselAdapter

public class AttachmentEditSegmentController extends EditSegmentController
    implements AttachmentEditSegment.Listener
{
    final class FileMetadataTask extends ApiClientAsyncTask
    {

        private final AttachmentEditSegmentController this$0;

        protected final Object doInBackgroundConnected(Object aobj[])
        {
            boolean flag = false;
            aobj = (DriveId[])aobj;
            aobj = (com.google.android.gms.drive.DriveResource.MetadataResult)Drive.DriveApi.getFile(super.client, ((DriveId) (aobj[0]))).getMetadata(super.client).await();
            if (aobj != null)
            {
                if (((com.google.android.gms.drive.DriveResource.MetadataResult) (aobj)).getStatus().zzaEP <= 0)
                {
                    flag = true;
                }
                if (flag)
                {
                    return ((com.google.android.gms.drive.DriveResource.MetadataResult) (aobj)).getMetadata();
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
            class .Lambda._cls0
                implements Predicate
            {

                private final String arg$1;

                public final boolean apply(Object obj2)
                {
                    return AttachmentEditSegmentController.lambda$containsAttachment$0$AttachmentEditSegmentController(arg$1, (Attachment)obj2);
                }

                .Lambda._cls0(String s)
                {
                    arg$1 = s;
                }
            }

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
            obj1 = new .Lambda._cls0(s);
            if (Iterators.indexOf(((Iterable) (obj)).iterator(), ((Predicate) (obj1))) == -1) goto _L6; else goto _L7
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
                obj1 = new com.google.android.calendar.api.event.attachment.Attachment.Builder();
                obj1.fileUrl = Platform.nullToEmpty(((String) (obj)));
                obj1.fileId = Platform.nullToEmpty(s);
                obj1.title = Platform.nullToEmpty((String)metadata.zza(zzaks.zzbcw));
                obj1.iconLink = Platform.nullToEmpty(AttachmentHelper.getTypeIconUrl((String)metadata.zza(zzaks.zzbcn)));
                obj1.mimeType = (String)metadata.zza(zzaks.zzbcn);
                obj = ((com.google.android.calendar.api.event.attachment.Attachment.Builder) (obj1)).build();
                ((EventModificationsHolder)model).getEventModifications().getAttachmentModifications().addAttachment(((Attachment) (obj)));
                updateUi();
                return;
            }
_L4:
            if (true) goto _L8; else goto _L6
_L6:
            flag = false;
              goto _L9
        }

        public FileMetadataTask(Context context, String s)
        {
            this$0 = AttachmentEditSegmentController.this;
            super(context, s);
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/attachment/AttachmentEditSegmentController);
    private Account account;

    public AttachmentEditSegmentController()
    {
    }

    static final boolean lambda$containsAttachment$0$AttachmentEditSegmentController(String s, Attachment attachment)
    {
        return s.equals(attachment.fileId);
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (AttachmentEditSegment)layoutinflater.inflate(0x7f0500bd, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        if (i != 1001)
        {
            super.onActivityResult(i, j, intent);
        } else
        if (j == -1 && intent != null)
        {
            DriveId driveid = DriveId.decodeFromString(intent.getStringExtra("driveIdExtra"));
            if (super.mHost == null)
            {
                intent = null;
            } else
            {
                intent = (FragmentActivity)super.mHost.mActivity;
            }
            (new FileMetadataTask(intent, ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account.name)).execute(new DriveId[] {
                driveid
            });
            return;
        }
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        Object obj = null;
        boolean flag3 = false;
        Object obj1;
        Object obj2;
        int i;
        boolean flag2;
        if (!((EventModificationsHolder)super.model).getEventModifications().getAttachments().isEmpty())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj1 = account;
        obj2 = ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!i || !flag2)
        {
            updateUi();
            return;
        }
        obj1 = (ImmutableList)((EventModificationsHolder)super.model).getEventModifications().getAttachments();
        int j = ((ImmutableList) (obj1)).size();
        obj2 = (UnmodifiableIterator)null;
        for (i = ((flag3) ? 1 : 0); i < j; i++)
        {
            Attachment attachment = (Attachment)((ImmutableList) (obj1)).get(i);
            ((EventModificationsHolder)super.model).getEventModifications().getAttachmentModifications().removeAttachment(attachment);
        }

        updateUi();
        if (AccountUtil.isGoogleAccount(((EventModificationsHolder)super.model).getEventModifications().getCalendar().account))
        {
            i = 0x7f13007a;
        } else
        {
            i = 0x7f13035c;
        }
        if (super.mHost != null)
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        Toast.makeText(((Context) (obj)), i, 1).show();
    }

    protected final void onInitialize()
    {
        updateUi();
    }

    public final void onNewAttachmentClicked()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = new Intent(((Context) (obj)), com/google/android/calendar/timely/DriveFilePickerActivity);
        ((Intent) (obj)).putExtra("accountNameExtra", ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account.name);
        startActivityForResult(((Intent) (obj)), 1001);
    }

    public final void onOpenAttachment(Attachment attachment)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        AttachmentUtils.openAttachment(((Context) (obj)), attachment.fileUrl, ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account.name);
    }

    public final void onRemoveAttachment(Attachment attachment)
    {
        ((EventModificationsHolder)super.model).getEventModifications().getAttachmentModifications().removeAttachment(attachment);
        updateUi();
    }

    final void updateUi()
    {
        boolean flag1 = false;
        View view = super.view;
        AttachmentPermissions attachmentpermissions = ((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().getAttachmentPermissions();
        boolean flag;
        if (!attachmentpermissions.isReadOnly() && attachmentpermissions.canAddAttachment() && attachmentpermissions.canRemoveAttachment())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (view != null)
        {
            int j;
            if (flag)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            view.setVisibility(j);
        }
        if (flag)
        {
            account = ((EventModificationsHolder)super.model).getEventModifications().getCalendar().account;
            Object obj = (AttachmentEditSegment)super.view;
            ImmutableList immutablelist = ((EventModificationsHolder)super.model).getEventModifications().getAttachments();
            Object obj1 = ((AttachmentEditSegment) (obj)).attachmentTile;
            AttachmentCarouselAdapter attachmentcarouseladapter = ((AttachmentTileView) (obj1)).adapter;
            if (immutablelist != null)
            {
                attachmentcarouseladapter.items.clear();
                attachmentcarouseladapter.items.addAll(immutablelist);
                ((android.support.v7.widget.RecyclerView.Adapter) (attachmentcarouseladapter)).mObservable.notifyChanged();
                if (((HorizontalCarouselAdapter) (attachmentcarouseladapter)).horizontalCarousel != null)
                {
                    ((HorizontalCarouselAdapter) (attachmentcarouseladapter)).horizontalCarousel.scrollToPosition(0);
                }
            }
            ((android.support.v7.widget.RecyclerView.Adapter) (((AttachmentTileView) (obj1)).adapter)).mObservable.notifyChanged();
            obj1 = ((AttachmentEditSegment) (obj)).newAttachmentTextTile;
            int i;
            if (immutablelist.isEmpty())
            {
                i = 0x7f1301a1;
            } else
            {
                i = 0x7f1301a2;
            }
            ((TextTileView) (obj1)).setPrimaryText(new CharSequence[] {
                ((TextTileView) (obj1)).getResources().getString(i, new Object[0])
            });
            obj1 = ((AttachmentEditSegment) (obj)).attachmentTile;
            if (!immutablelist.isEmpty())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (obj1 != null)
            {
                boolean flag2;
                if (i != 0)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                ((View) (obj1)).setVisibility(i);
            }
            obj = ((AttachmentEditSegment) (obj)).newAttachmentTextTile.getIcon();
            flag2 = immutablelist.isEmpty();
            if (obj != null)
            {
                if (flag2)
                {
                    i = ((flag1) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                ((View) (obj)).setVisibility(i);
                return;
            }
        }
    }

}
