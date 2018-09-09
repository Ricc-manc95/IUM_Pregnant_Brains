package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.unica.pregnantbrains.ddgridmanager.model.primitives.Line;

import java.util.ArrayList;
import java.util.List;

public class LineCollection {
    public List<Line> lines = new ArrayList<Line>();

    public void drawAllLines(Canvas canvas, CoordinateTransformer transformer) {
        for (int i = 0; i < lines.size(); ++i){
            lines.get(i).draw(canvas, transformer);
        }
    }

    public Line createLine(int newLineColor, int newLineStrokeWidth) {
        Line l = new Line(newLineColor, newLineStrokeWidth);
        lines.add(l);
        return l;
    }

    public void clear() {
        lines.clear();

    }

    // In world space
    public void erase(PointF location, float radius) {
        for (int i = 0; i < lines.size(); ++i) {
            lines.get(i).erase(location, radius);
        }
    }

    public void optimize() {
        List<Line> newLines = new ArrayList<Line>();
        for (int i = 0; i < lines.size(); ++i) {
            List<Line> optimizedLines = lines.get(i).removeErasedPoints();
            newLines.addAll(optimizedLines);
        }
        lines.clear();
        lines.addAll(newLines);
    }
}
