// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.text.BidiFormatter;
import android.text.Html;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.drive:
//            PotentialFix, FixPermissionDialogState

public final class OutsideDomainWarningDialog extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{

    private String accountName;
    private FixPermissionDialogState dialogState;
    private PotentialFix fix;
    private int numFiles;
    private ArrayList potentialFixes;
    private String role;

    public OutsideDomainWarningDialog()
    {
    }

    public final void onCancel(DialogInterface dialoginterface)
    {
        dialoginterface = super.mTarget;
        if (dialoginterface instanceof Listener)
        {
            dialoginterface = (Listener)dialoginterface;
        } else
        {
            dialoginterface = null;
        }
        dialoginterface.showFixPermissionsDialog(dialogState, potentialFixes, numFiles, accountName);
    }

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == -1)
        {
            dialoginterface = super.mTarget;
            if (dialoginterface instanceof Listener)
            {
                dialoginterface = (Listener)dialoginterface;
            } else
            {
                dialoginterface = null;
            }
            dialoginterface.fixPermissions(accountName, fix, role);
        } else
        if (i == -2)
        {
            dialoginterface.cancel();
            return;
        }
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        boolean flag = true;
        Object obj;
        StringBuilder stringbuilder;
        BidiFormatter bidiformatter;
        Object obj1;
        int i;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        obj = getArguments();
        fix = (PotentialFix)((Bundle) (obj)).getParcelable("fix");
        role = ((Bundle) (obj)).getString("role");
        dialogState = (FixPermissionDialogState)((Bundle) (obj)).getParcelable("dialogState");
        potentialFixes = ((Bundle) (obj)).getParcelableArrayList("potentialFixes");
        numFiles = ((Bundle) (obj)).getInt("numFiles");
        accountName = ((Bundle) (obj)).getString("accountName");
        obj1 = fix.outOfDomainWarningEmailAddresses;
        obj = bundle.getResources();
        i = numFiles;
        stringbuilder = new StringBuilder();
        bidiformatter = BidiFormatter.getInstance();
        obj1 = ((Iterable) (obj1)).iterator();
        while (((Iterator) (obj1)).hasNext()) 
        {
            String s = (String)((Iterator) (obj1)).next();
            if (flag)
            {
                flag = false;
            } else
            {
                stringbuilder.append("<br>");
            }
            stringbuilder.append(bidiformatter.unicodeWrap(s));
        }
        obj = Html.fromHtml(((Resources) (obj)).getQuantityString(0x7f12001b, i, new Object[] {
            stringbuilder.toString()
        }));
        return (new android.app.AlertDialog.Builder(bundle)).setTitle(0x7f13022e).setMessage(((CharSequence) (obj))).setPositiveButton(0x7f130439, this).setNegativeButton(0x7f130100, this).create();
    }

    private class Listener
    {

        public abstract void fixPermissions(String s, PotentialFix potentialfix, String s1);

        public abstract void showFixPermissionsDialog(FixPermissionDialogState fixpermissiondialogstate, ArrayList arraylist, int i, String s);
    }

}
