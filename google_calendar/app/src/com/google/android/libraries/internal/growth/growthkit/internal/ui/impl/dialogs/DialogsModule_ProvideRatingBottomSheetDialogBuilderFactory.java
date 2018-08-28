// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import com.google.android.libraries.internal.growth.growthkit.internal.ui.DialogBuilder;
import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs:
//            MaterialDialogBuilder

public final class DialogsModule_ProvideRatingBottomSheetDialogBuilderFactory
    implements Factory
{

    public static final DialogsModule_ProvideRatingBottomSheetDialogBuilderFactory INSTANCE = new DialogsModule_ProvideRatingBottomSheetDialogBuilderFactory();

    public DialogsModule_ProvideRatingBottomSheetDialogBuilderFactory()
    {
    }

    public final Object get()
    {
        MaterialDialogBuilder materialdialogbuilder = new MaterialDialogBuilder();
        if (materialdialogbuilder == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (DialogBuilder)materialdialogbuilder;
        }
    }

}
