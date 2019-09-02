package ch.ceff.libgdx.tangram.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class Utils {
    public static float getShortestAngleBetween(float angle1, float angle2){
        ///diff entre les 2 angles
        float angle = (angle2 - angle1) % 360;
        ///analyse
        if(angle > 180){
            angle -= 360;
        } else if(angle < -180){
            angle += 180;
        }
        return angle;
    }

    public static float getAngleSign(float angle){
        return  angle >= 0f ? 1f : -1f;
    }

    public static void clearScreen(){
        clearScreen(Color.WHITE);
    }

    public static void clearScreen(Color color){
        Gdx.gl.glClearColor(color.r,color.g,color.b,color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private Utils(){}
}
