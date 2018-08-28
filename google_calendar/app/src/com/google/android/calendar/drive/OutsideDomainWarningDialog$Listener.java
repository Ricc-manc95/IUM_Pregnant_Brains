// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.drive:
//            PotentialFix, FixPermissionDialogState

public interface 
{

    public abstract void fixPermissions(String s, PotentialFix potentialfix, String s1);

    public abstract void showFixPermissionsDialog(FixPermissionDialogState fixpermissiondialogstate, ArrayList arraylist, int i, String s);
}
