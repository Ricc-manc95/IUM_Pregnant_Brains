// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.List;

// Referenced classes of package com.google.android.calendar.event.scope:
//            ScopeSelectionDialog

public static abstract class I
    implements Parcelable
{

    public abstract Bundle additionalArguments();

    abstract int positiveButton();

    abstract List scopes();

    abstract int title();

    public I()
    {
    }
}
