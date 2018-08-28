// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.drive:
//            FixPermissionDialogState

public interface 
{

    public abstract void onPermissionsCheckFinished();

    public abstract void showFilesNotSharedDialog(int i);

    public abstract void showFixPermissionsDialog(FixPermissionDialogState fixpermissiondialogstate, ArrayList arraylist, int i, String s);
}
