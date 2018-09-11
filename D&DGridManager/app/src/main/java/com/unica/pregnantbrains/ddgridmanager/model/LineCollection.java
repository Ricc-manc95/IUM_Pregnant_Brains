package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LineCollection implements Serializable {
    private static final long serialVersionUID = 2637613694555001119L;

    public List<Line> lines = new LinkedList<Line>();

    public void drawAllLines(Canvas canvas, CoordinateTransformer transformer) {
        canvas.save();
        transformer.setMatrix(canvas);
        for (int i = 0; i < lines.size(); ++i){
            lines.get(i).draw(canvas/*, transformer*/);
        }
        canvas.restore();
    }

    public Line createLine(int newLineColor, float newLineStrokeWidth) {
        Line l = new Line(newLineColor, newLineStrokeWidth);
        insertLine(l);
        return l;
    }

    /**
     * Inserts a new line into the list of lines, making sure that the lines are
     * sorted by line width.
     * @param line The line to add.
     */
    private void insertLine(final Line line) {
        if (lines.isEmpty()) {
            lines.add(line);
            return;
        }

        ListIterator<Line> it = lines.listIterator();
        while (it.hasNext()
                && lines.get(it.nextIndex()).getStrokeWidth()
                >= line.getStrokeWidth()) {
            it.next();
        }
        it.add(line);
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
        List<Line> newLines = new LinkedList<Line>();
        for (int i = 0; i < lines.size(); ++i) {
            List<Line> optimizedLines = lines.get(i).removeErasedPoints();
            newLines.addAll(optimizedLines);
        }
        lines.clear();
        lines.addAll(newLines);
    }
    /**
     * @return True if this collection has no lines in it, False otherwise.
     */
    public boolean isEmpty() {
        return lines.isEmpty();
    }
}
