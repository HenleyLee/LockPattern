package com.henleylee.lockpattern.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import androidx.annotation.NonNull;

import com.henleylee.lockpattern.Cell;
import com.henleylee.lockpattern.DecoratorStyle;

import java.util.List;

/**
 * 手势解锁连接线默认绘制样式
 *
 * @author Henley
 * @since 2019/8/26 18:26
 */
public class DefaultLockerLinkedLineStyle implements ILinkedLineStyle {

    private Paint paint;
    private DecoratorStyle style;

    public DefaultLockerLinkedLineStyle(DecoratorStyle style) {
        this.style = style;
        this.paint = createPaint();
        this.paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    public void draw(@NonNull Canvas canvas, List<Cell> cells, float endX, float endY) {
        if (cells == null || cells.isEmpty()) {
            return;
        }
        int saveCount = canvas.save();
        Path path = new Path();
        for (Cell cell : cells) {
            if (path.isEmpty()) {
                path.moveTo(cell.getX(), cell.getY());
            } else {
                path.lineTo(cell.getX(), cell.getY());
            }
        }

        if ((endX != 0f || endY != 0f) && cells.size() < 9) {
            path.lineTo(endX, endY);
        }

        paint.setColor(style.getColor(cells.get(0).getStatus()));
        paint.setStrokeWidth(style.getLineWidth());
        canvas.drawPath(path, paint);

        canvas.restoreToCount(saveCount);
    }

}