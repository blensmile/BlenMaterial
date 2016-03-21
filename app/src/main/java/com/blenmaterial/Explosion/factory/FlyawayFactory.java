package com.blenmaterial.Explosion.factory;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.blenmaterial.Explosion.particle.FlyawayParticle;
import com.blenmaterial.Explosion.particle.Particle;


/**
 * Created by Administrator on 2015/11/29 0029.
 */
public class FlyawayFactory extends ParticleFactory{
    public static final int PART_WH = 8; //默认小球宽高

    public Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int w = bound.width();
        int h = bound.height();

        int partW_Count = w / PART_WH; //横向个数
        int partH_Count = h / PART_WH; //竖向个数

        int bitmap_part_w = bitmap.getWidth() / partW_Count;
        int bitmap_part_h = bitmap.getHeight() / partH_Count;

        Particle[][] particles = new Particle[partH_Count][partW_Count];
        for (int row = 0; row < partH_Count; row ++) { //行
            for (int column = 0; column < partW_Count; column ++) { //列
                //取得当前粒子所在位置的颜色
                int color = bitmap.getPixel(column * bitmap_part_w, row * bitmap_part_h);

                float x = bound.left + FlyawayFactory.PART_WH * column;
                float y = bound.top + FlyawayFactory.PART_WH * row;
                particles[row][column] = new FlyawayParticle(color,x,y,bound);
            }
        }

        return particles;
    }

}
