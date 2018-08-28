// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.phenotypesupport.firebase;


public final class PhenotypeCommitOperation extends Enum
    implements com.google.calendar.v2a.android.util.metric.MetricUtils.Operation
{

    private static final PhenotypeCommitOperation $VALUES[];
    public static final PhenotypeCommitOperation COMMIT;

    private PhenotypeCommitOperation(String s, int i)
    {
        super(s, 0);
    }

    public static PhenotypeCommitOperation[] values()
    {
        return (PhenotypeCommitOperation[])$VALUES.clone();
    }

    public final String getAction()
    {
        return "Commit";
    }

    public final String getCategory()
    {
        return "Phenotype";
    }

    public final String getFullName()
    {
        return com.google.calendar.v2a.android.util.metric.MetricUtils.Operation..CC.getFullName(this);
    }

    public final double getSampling()
    {
        return 1.0D;
    }

    static 
    {
        COMMIT = new PhenotypeCommitOperation("COMMIT", 0);
        $VALUES = (new PhenotypeCommitOperation[] {
            COMMIT
        });
    }
}
