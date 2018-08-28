// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class HprofAnalyzer
{

    public static final List NON_LEAK_CONTAINER = Collections.unmodifiableList(Arrays.asList(new String[] {
        "boolean", "boolean[]", "boolean[][]", "byte", "byte[]", "byte[][]", "byte[][][]", "char", "char[]", "char[][]", 
        "short", "short[]", "short[][]", "int", "int[]", "int[][]", "int[][][]", "long", "long[]", "long[][]", 
        "float", "float[]", "float[][]", "double", "double[]", "double[][]", "java.lang.Class", "java.lang.Class[]", "java.lang.Class[][]", "java.lang.Byte", 
        "java.lang.Byte[]", "java.lang.Character", "java.lang.Character[]", "java.lang.Boolean", "java.lang.Boolean[]", "java.lang.Short", "java.lang.Short[]", "java.lang.Integer", "java.lang.Integer[]", "java.lang.Long", 
        "java.lang.Long[]", "java.lang.Float", "java.lang.Float[]", "java.lang.Double", "java.lang.Double[]", "java.lang.String", "java.lang.String[]", "java.lang.String[][]", "java.lang.String[][][]"
    }));
    public static final List NON_LEAK_ROOT_TAGS = Collections.unmodifiableList(Arrays.asList(new Integer[] {
        Integer.valueOf(139), Integer.valueOf(138), Integer.valueOf(137), Integer.valueOf(255), Integer.valueOf(144)
    }));
    public final File hprofFile;

    public HprofAnalyzer(File file)
    {
        hprofFile = file;
    }

}
