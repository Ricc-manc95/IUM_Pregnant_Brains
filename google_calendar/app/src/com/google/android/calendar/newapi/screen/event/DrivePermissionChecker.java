// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.drive.CheckPermissionProgressFragment;
import com.google.android.calendar.drive.FilesNotSharableDialog;
import com.google.android.calendar.drive.FixPermissionDialogState;
import com.google.android.calendar.drive.FixPermissionRunnable;
import com.google.android.calendar.drive.FixPermissionsDialogFragment;
import com.google.android.calendar.drive.OutsideDomainWarningDialog;
import com.google.android.calendar.drive.PotentialFix;
import com.google.android.calendar.utils.collection.Iterables2;
import com.google.android.calendar.utils.flow.Flow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DrivePermissionChecker extends Flow
    implements com.google.android.calendar.drive.CheckPermissionProgressFragment.Listener, com.google.android.calendar.drive.FixPermissionsDialogFragment.Listener, com.google.android.calendar.drive.OutsideDomainWarningDialog.Listener
{
    public static interface Listener
    {

        public abstract void onDrivePermissionsCheckFlowFinished();
    }


    public DrivePermissionChecker()
    {
    }

    static final String bridge$lambda$0$DrivePermissionChecker(Attendee attendee)
    {
        return attendee.attendeeDescriptor.email;
    }

    static final String lambda$getAttachmentIds$1$DrivePermissionChecker(Attachment attachment)
    {
        return attachment.fileId;
    }

    static final void lambda$start$0$DrivePermissionChecker(Event event, DrivePermissionChecker drivepermissionchecker)
    {
        String s = event.getCalendar().account.name;
        class .Lambda._cls4
            implements Function
        {

            public static final Function $instance = new .Lambda._cls4();

            public final Object apply(Object obj1)
            {
                return DrivePermissionChecker.bridge$lambda$0$DrivePermissionChecker((Attendee)obj1);
            }


            private .Lambda._cls4()
            {
            }
        }

        Object obj = new com.google.common.collect.Collections2.TransformedCollection(event.getAttendees(), .Lambda._cls4..instance);
        class .Lambda._cls3
            implements Function
        {

            public static final Function $instance = new .Lambda._cls3();

            public final Object apply(Object obj1)
            {
                return DrivePermissionChecker.lambda$getAttachmentIds$1$DrivePermissionChecker((Attachment)obj1);
            }


            private .Lambda._cls3()
            {
            }
        }

        CheckPermissionProgressFragment checkpermissionprogressfragment;
        Bundle bundle;
        if (obj instanceof Collection)
        {
            obj = new ArrayList((Collection)obj);
        } else
        {
            Iterator iterator = ((Iterable) (obj)).iterator();
            obj = new ArrayList();
            while (iterator.hasNext()) 
            {
                ((ArrayList) (obj)).add(iterator.next());
            }
        }
        event = new com.google.common.collect.Collections2.TransformedCollection(event.getAttachments(), .Lambda._cls3..instance);
        if (!(event instanceof Collection)) goto _L2; else goto _L1
_L1:
        event = new ArrayList((Collection)event);
_L4:
        checkpermissionprogressfragment = new CheckPermissionProgressFragment();
        bundle = new Bundle(4);
        bundle.putString("account", s);
        bundle.putStringArrayList("recipients", ((ArrayList) (obj)));
        bundle.putStringArrayList("fileIds", event);
        checkpermissionprogressfragment.setArguments(bundle);
        checkpermissionprogressfragment.setTargetFragment(drivepermissionchecker, 0);
        checkpermissionprogressfragment.show(((Fragment) (drivepermissionchecker)).mFragmentManager, "check-permissions-dialog");
        return;
_L2:
        Iterator iterator1 = event.iterator();
        ArrayList arraylist = new ArrayList();
        do
        {
            event = arraylist;
            if (!iterator1.hasNext())
            {
                continue;
            }
            arraylist.add(iterator1.next());
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void fixPermissions(String s, PotentialFix potentialfix, String s1)
    {
        CalendarExecutor calendarexecutor = CalendarExecutor.NET;
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        calendarexecutor.execute(new FixPermissionRunnable(((android.content.Context) (obj)), s, potentialfix, s1));
        onPermissionsCheckFinished();
    }

    public final void onPermissionsCheckFinished()
    {
        Object obj;
        Fragment fragment;
        class .Lambda._cls2
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls2();

            public final void accept(Object obj1)
            {
                ((Listener)obj1).onDrivePermissionsCheckFlowFinished();
            }


            private .Lambda._cls2()
            {
            }
        }

        obj = .Lambda._cls2..instance;
        fragment = super.mTarget;
        if (fragment == null) goto _L2; else goto _L1
_L1:
        if (fragment == null) goto _L4; else goto _L3
_L3:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = fragment.mFragmentManager;
        if (fragment == null) goto _L6; else goto _L5
_L5:
        boolean flag;
        if (fragment.mHost != null && fragment.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = false;
_L12:
        if (!flag) goto _L2; else goto _L8
_L8:
        flag = true;
_L14:
        if (flag)
        {
            ((Consumer) (obj)).accept(fragment);
        }
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_282;
        }
        obj = super.mFragmentManager;
        if (this == null) goto _L10; else goto _L9
_L9:
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L11; else goto _L10
_L10:
        flag = false;
_L15:
        if (flag)
        {
            super.mFragmentManager.beginTransaction().remove(this);
        }
        return;
_L7:
label0:
        {
            FragmentActivity fragmentactivity;
            if (fragment.mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)fragment.mHost.mActivity;
            }
            if (fragmentactivity != null && !fragmentactivity.isDestroyed() && !fragmentactivity.isFinishing())
            {
                break label0;
            }
            flag = false;
        }
          goto _L12
        if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L4; else goto _L13
_L13:
        flag = true;
          goto _L12
_L4:
        flag = false;
          goto _L12
_L2:
        flag = false;
          goto _L14
_L11:
        FragmentActivity fragmentactivity1;
        if (super.mHost == null)
        {
            fragmentactivity1 = null;
        } else
        {
            fragmentactivity1 = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity1 == null || fragmentactivity1.isDestroyed() || fragmentactivity1.isFinishing())
        {
            flag = false;
        } else
        {
            if (obj == null || ((FragmentManager) (obj)).isDestroyed())
            {
                break MISSING_BLOCK_LABEL_282;
            }
            flag = true;
        }
          goto _L15
        flag = false;
          goto _L15
    }

    public final void showFilesNotSharedDialog(int i)
    {
        FragmentActivity fragmentactivity;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity != null) goto _L2; else goto _L1
_L1:
        if (this == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L6; else goto _L5
_L5:
        if (super.mHost != null && super.mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L7; else goto _L6
_L6:
        i = 0;
_L8:
        if (i != 0)
        {
            super.mFragmentManager.beginTransaction().remove(this);
        }
        return;
_L7:
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            i = 0;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            i = 1;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        i = 0;
        if (true) goto _L8; else goto _L2
_L2:
        FilesNotSharableDialog filesnotsharabledialog = new FilesNotSharableDialog();
        Bundle bundle = new Bundle(2);
        bundle.putInt("numFiles", i);
        filesnotsharabledialog.setArguments(bundle);
        filesnotsharabledialog.show(fragmentactivity.mFragments.mHost.mFragmentManager, "files-not-shared-dialog");
        onPermissionsCheckFinished();
        return;
    }

    public final void showFixPermissionsDialog(FixPermissionDialogState fixpermissiondialogstate, ArrayList arraylist, int i, String s)
    {
        if (Iterables2.isNullOrEmpty(arraylist))
        {
            onPermissionsCheckFinished();
            return;
        } else
        {
            FixPermissionsDialogFragment fixpermissionsdialogfragment = new FixPermissionsDialogFragment();
            Bundle bundle = new Bundle(4);
            bundle.putParcelable("dialogState", fixpermissiondialogstate);
            bundle.putParcelableArrayList("potentialFixes", arraylist);
            bundle.putInt("numFiles", i);
            bundle.putString("accountName", s);
            fixpermissionsdialogfragment.setArguments(bundle);
            fixpermissionsdialogfragment.setTargetFragment(this, 0);
            fixpermissionsdialogfragment.show(super.mFragmentManager, "acl-fixer-dialog");
            return;
        }
    }

    public final void showOutsideDomainWarningDialog$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFCHP6ITJ55T86UT35DPQ6IOBC8PKNGEQCD9GNCO9FDHGMSPPFADQ74QBECSTKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM6OBCCLN68OBI5TI74QBMCKNKCQBOA1IN4RB9EDPMIRRE8HKM2R3FCT9N8OBKCKTKOQJ1EPGIUTBKD5M2UGBIE9GNIJ39EDQ3MIACC5N68SJFD5I2UORFDPQ6ARJK5T1MURJKCLS78EQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0(PotentialFix potentialfix, String s, FixPermissionDialogState fixpermissiondialogstate, ArrayList arraylist, int i, String s1)
    {
        if (potentialfix == null)
        {
            onPermissionsCheckFinished();
            return;
        }
        if (Iterables2.isNullOrEmpty(potentialfix.outOfDomainWarningEmailAddresses))
        {
            arraylist = CalendarExecutor.NET;
            if (super.mHost == null)
            {
                fixpermissiondialogstate = null;
            } else
            {
                fixpermissiondialogstate = (FragmentActivity)super.mHost.mActivity;
            }
            arraylist.execute(new FixPermissionRunnable(fixpermissiondialogstate, s1, potentialfix, s));
            onPermissionsCheckFinished();
            return;
        } else
        {
            OutsideDomainWarningDialog outsidedomainwarningdialog = new OutsideDomainWarningDialog();
            Bundle bundle = new Bundle(6);
            bundle.putParcelable("fix", potentialfix);
            bundle.putString("role", s);
            bundle.putParcelable("dialogState", fixpermissiondialogstate);
            bundle.putParcelableArrayList("potentialFixes", arraylist);
            bundle.putInt("numFiles", i);
            bundle.putString("accountName", s1);
            outsidedomainwarningdialog.setArguments(bundle);
            outsidedomainwarningdialog.setTargetFragment(this, 0);
            outsidedomainwarningdialog.show(super.mFragmentManager, "outside-domain-warning-dialog");
            return;
        }
    }
}
