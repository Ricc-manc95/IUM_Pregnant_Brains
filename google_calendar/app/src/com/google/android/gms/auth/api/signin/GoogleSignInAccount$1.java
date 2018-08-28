// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (Scope)obj;
        obj1 = (Scope)obj1;
        return ((Scope) (obj)).zzaJs.compareTo(((Scope) (obj1)).zzaJs);
    }

    ()
    {
    }
}
