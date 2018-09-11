package com.unica.pregnantbrains.ddgridmanager.model;

import java.io.IOException;
import java.io.Serializable;

public class PointF extends android.graphics.PointF implements Serializable {
    private static final long serialVersionUID = 1551629818855445800L;

    public PointF () {
        super();
    }

    public PointF (float x, float y) {
        super(x, y);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
    }
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        x = in.readFloat();
        y = in.readFloat();
    }
}
