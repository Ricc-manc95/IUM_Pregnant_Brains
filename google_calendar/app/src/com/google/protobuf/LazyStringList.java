// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.List;

// Referenced classes of package com.google.protobuf:
//            ByteString

public interface LazyStringList
    extends List
{

    public abstract void add(ByteString bytestring);

    public abstract Object getRaw(int i);

    public abstract List getUnderlyingElements();

    public abstract LazyStringList getUnmodifiableView();
}
