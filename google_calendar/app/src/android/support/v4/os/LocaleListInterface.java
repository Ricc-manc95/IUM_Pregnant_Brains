// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import java.util.Locale;

public interface LocaleListInterface
{

    public abstract boolean equals(Object obj);

    public abstract Locale get(int i);

    public abstract Object getLocaleList();

    public abstract int hashCode();

    public transient abstract void setLocaleList(Locale alocale[]);

    public abstract String toString();
}
