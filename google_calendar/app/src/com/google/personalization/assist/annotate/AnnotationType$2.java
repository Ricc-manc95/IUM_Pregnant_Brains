// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


// Referenced classes of package com.google.personalization.assist.annotate:
//            AnnotationType

final class A
    implements com.google.protobuf.fier
{

    public final boolean isInRange(int i)
    {
        return AnnotationType.forNumber(i) != null;
    }

    A()
    {
    }
}
