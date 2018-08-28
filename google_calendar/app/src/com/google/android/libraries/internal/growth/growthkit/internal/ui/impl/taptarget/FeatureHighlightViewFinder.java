// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import android.app.Activity;
import android.os.Parcel;
import android.view.View;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.TargetElementFinder;
import com.google.android.libraries.material.featurehighlight.ViewFinder;

public final class FeatureHighlightViewFinder extends ViewFinder
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private static final Logger logger = new Logger();
    private final String elementIdentifier;
    private final ElementIdentifierType elementIdentifierType;
    private final TargetElementFinder targetElementFinder;

    FeatureHighlightViewFinder(Parcel parcel)
    {
        elementIdentifier = parcel.readString();
        elementIdentifierType = (ElementIdentifierType)parcel.readSerializable();
        targetElementFinder = (TargetElementFinder)parcel.readSerializable();
    }

    FeatureHighlightViewFinder(TargetElementFinder targetelementfinder, com.google.identity.growth.proto.Promotion.TapTargetUi taptargetui)
    {
        int i = 0;
        super();
        targetElementFinder = targetelementfinder;
        switch (com.google.identity.growth.proto.Promotion.TapTargetUi.TargetCase.forNumber(taptargetui.targetCase_).ordinal())
        {
        default:
            logger.w("No tap target element was specified.", new Object[0]);
            elementIdentifier = "";
            elementIdentifierType = ElementIdentifierType.UNKNOWN;
            return;

        case 0: // '\0'
            targetelementfinder = "";
            if (taptargetui.targetCase_ == 1)
            {
                targetelementfinder = (String)taptargetui.target_;
            }
            elementIdentifier = targetelementfinder;
            elementIdentifierType = ElementIdentifierType.ID;
            return;

        case 1: // '\001'
            targetelementfinder = "";
            if (taptargetui.targetCase_ == 10)
            {
                targetelementfinder = (String)taptargetui.target_;
            }
            elementIdentifier = targetelementfinder;
            elementIdentifierType = ElementIdentifierType.TAG;
            return;

        case 2: // '\002'
            break;
        }
        if (taptargetui.targetCase_ == 11)
        {
            i = ((Integer)taptargetui.target_).intValue();
        }
        elementIdentifier = String.valueOf(i);
        elementIdentifierType = ElementIdentifierType.VE_ID;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final View find(Activity activity, View view)
    {
        switch (elementIdentifierType.ordinal())
        {
        default:
            return null;

        case 1: // '\001'
            return TargetElementFinder.findElementById(activity, view, elementIdentifier);

        case 2: // '\002'
            return TargetElementFinder.findElementByTag(activity, view, elementIdentifier);

        case 3: // '\003'
            return targetElementFinder.findElementByVeId(activity, view, Integer.parseInt(elementIdentifier));
        }
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(elementIdentifier);
        parcel.writeSerializable(elementIdentifierType);
        parcel.writeSerializable(targetElementFinder);
    }


    private class ElementIdentifierType extends Enum
    {

        private static final ElementIdentifierType $VALUES[];
        public static final ElementIdentifierType ID;
        public static final ElementIdentifierType TAG;
        public static final ElementIdentifierType UNKNOWN;
        public static final ElementIdentifierType VE_ID;

        public static ElementIdentifierType[] values()
        {
            return (ElementIdentifierType[])$VALUES.clone();
        }

        static 
        {
            UNKNOWN = new ElementIdentifierType("UNKNOWN", 0);
            ID = new ElementIdentifierType("ID", 1);
            TAG = new ElementIdentifierType("TAG", 2);
            VE_ID = new ElementIdentifierType("VE_ID", 3);
            $VALUES = (new ElementIdentifierType[] {
                UNKNOWN, ID, TAG, VE_ID
            });
        }

        private ElementIdentifierType(String s, int i)
        {
            super(s, i);
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FeatureHighlightViewFinder(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FeatureHighlightViewFinder[i];
        }

        _cls1()
        {
        }
    }

}
