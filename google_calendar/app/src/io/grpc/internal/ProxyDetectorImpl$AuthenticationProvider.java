// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.net.InetAddress;
import java.net.PasswordAuthentication;

// Referenced classes of package io.grpc.internal:
//            ProxyDetectorImpl

static interface 
{

    public abstract PasswordAuthentication requestPasswordAuthentication(String s, InetAddress inetaddress, int i, String s1, String s2, String s3);
}
