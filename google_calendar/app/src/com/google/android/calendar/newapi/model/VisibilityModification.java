// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            VisibilityHolder

public interface VisibilityModification
    extends VisibilityHolder
{

    public abstract List getAllowedVisibilityValues();

    public abstract void setVisibility(int i);
}
