// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;


// Referenced classes of package com.google.android.calendar.newapi.model:
//            TitleHolder

public interface TitleModification
    extends TitleHolder
{

    public abstract boolean canModifyTitle();

    public abstract void setTitle(String s);
}
