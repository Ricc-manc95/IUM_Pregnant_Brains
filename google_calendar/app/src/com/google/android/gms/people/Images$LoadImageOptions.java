// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people;


public final class Builder.zzbTj
{

    public static final Builder DEFAULT = new <init>(new Builder());
    public final int avatarOptions;
    public final int imageSize;
    public final boolean useLargePictureForCp2Images = false;


    public Builder.zzbTj(Builder builder)
    {
        class Builder
        {

            public int zzbTi;
            public int zzbTj;

            public Builder()
            {
                zzbTi = 1;
                zzbTj = 0;
            }
        }

        imageSize = builder.zzbTi;
        avatarOptions = builder.zzbTj;
    }
}
