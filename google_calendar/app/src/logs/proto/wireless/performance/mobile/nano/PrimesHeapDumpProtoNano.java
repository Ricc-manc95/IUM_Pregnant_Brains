// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;


public class PrimesHeapDumpProtoNano
{

    public static int checkPrimitiveTypeOrThrow(int i)
    {
        if (i >= 4 && i <= 11)
        {
            return i;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(45)).append(i).append(" is not a valid enum PrimitiveType").toString());
        }
    }
}
