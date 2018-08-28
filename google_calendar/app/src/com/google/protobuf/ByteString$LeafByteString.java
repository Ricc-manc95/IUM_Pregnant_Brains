// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            ByteString

abstract class  extends ByteString
{

    abstract boolean equalsRange(ByteString bytestring, int i, int j);

    protected final int getTreeDepth()
    {
        return 0;
    }

    protected final boolean isBalanced()
    {
        return true;
    }

    ()
    {
    }
}
