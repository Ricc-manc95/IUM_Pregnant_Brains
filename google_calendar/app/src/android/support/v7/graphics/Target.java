// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics;


public final class Target
{

    public static final Target DARK_MUTED;
    public static final Target DARK_VIBRANT;
    public static final Target LIGHT_MUTED;
    public static final Target LIGHT_VIBRANT;
    public static final Target MUTED;
    public static final Target VIBRANT;
    public boolean mIsExclusive;
    public final float mLightnessTargets[] = new float[3];
    public final float mSaturationTargets[] = new float[3];
    public final float mWeights[] = new float[3];

    Target()
    {
        mIsExclusive = true;
        float af[] = mSaturationTargets;
        af[0] = 0.0F;
        af[1] = 0.5F;
        af[2] = 1.0F;
        af = mLightnessTargets;
        af[0] = 0.0F;
        af[1] = 0.5F;
        af[2] = 1.0F;
        mWeights[0] = 0.24F;
        mWeights[1] = 0.52F;
        mWeights[2] = 0.24F;
    }

    static 
    {
        Target target = new Target();
        LIGHT_VIBRANT = target;
        target.mLightnessTargets[0] = 0.55F;
        target.mLightnessTargets[1] = 0.74F;
        target = LIGHT_VIBRANT;
        target.mSaturationTargets[0] = 0.35F;
        target.mSaturationTargets[1] = 1.0F;
        target = new Target();
        VIBRANT = target;
        target.mLightnessTargets[0] = 0.3F;
        target.mLightnessTargets[1] = 0.5F;
        target.mLightnessTargets[2] = 0.7F;
        target = VIBRANT;
        target.mSaturationTargets[0] = 0.35F;
        target.mSaturationTargets[1] = 1.0F;
        target = new Target();
        DARK_VIBRANT = target;
        target.mLightnessTargets[1] = 0.26F;
        target.mLightnessTargets[2] = 0.45F;
        target = DARK_VIBRANT;
        target.mSaturationTargets[0] = 0.35F;
        target.mSaturationTargets[1] = 1.0F;
        target = new Target();
        LIGHT_MUTED = target;
        target.mLightnessTargets[0] = 0.55F;
        target.mLightnessTargets[1] = 0.74F;
        target = LIGHT_MUTED;
        target.mSaturationTargets[1] = 0.3F;
        target.mSaturationTargets[2] = 0.4F;
        target = new Target();
        MUTED = target;
        target.mLightnessTargets[0] = 0.3F;
        target.mLightnessTargets[1] = 0.5F;
        target.mLightnessTargets[2] = 0.7F;
        target = MUTED;
        target.mSaturationTargets[1] = 0.3F;
        target.mSaturationTargets[2] = 0.4F;
        target = new Target();
        DARK_MUTED = target;
        target.mLightnessTargets[1] = 0.26F;
        target.mLightnessTargets[2] = 0.45F;
        target = DARK_MUTED;
        target.mSaturationTargets[1] = 0.3F;
        target.mSaturationTargets[2] = 0.4F;
    }
}
