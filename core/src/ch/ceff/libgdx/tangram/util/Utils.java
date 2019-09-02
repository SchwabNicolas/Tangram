package ch.ceff.libgdx.tangram.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Utils {
    private Utils() {
    }

    public static float getShortestAngleBetween(float angle1, float angle2) {
        ///diff entre les 2 angles
        float angle = (angle2 - angle1) % 360;
        ///analyse
        if (angle > 180) {
            angle -= 360;
        } else if (angle < -180) {
            angle += 180;
        }
        return angle;
    }

    public static float getAngleSign(float angle) {
        return angle >= 0f ? 1f : -1f;
    }

    public static void clearScreen() {
        clearScreen(Color.WHITE);
    }

    public static float angleBetweenTwoPoints(Vector2 point1, Vector2 point2) {
        return angleBetweenTwoPoints(point1.x, point1.y, point2.x, point2.y);
    }

    public static float angleBetweenTwoPoints(float x1, float y1, float x2, float y2) {
        float angle = (float) (MathUtils.atan2(y1 - y2, x1 - x2) * 180.0d / Math.PI);
        return angle;
    }

    public static float distanceBetweenTwoPoints(Vector2 point1, Vector2 point2) {
        return distanceBetweenTwoPoints(point1.x, point1.y, point2.x, point2.y);
    }

    public static float distanceBetweenTwoPoints(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }

    public static void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static int roundTo(int toRound, int roundBy) {
        // Smaller multiple
        int a = (toRound / roundBy) * roundBy;

        // Larger multiple
        int b = a + roundBy;

        // Return of closest of two
        return (toRound - a > b - toRound) ? b : a;
    }
}
