// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


// Referenced classes of package com.google.personalization.assist.annotate:
//            SuggestionType

final class A
    implements com.google.protobuf.fier
{

    public final boolean isInRange(int i)
    {
        return SuggestionType.forNumber(i) != null;
    }

    A()
    {
    }
}