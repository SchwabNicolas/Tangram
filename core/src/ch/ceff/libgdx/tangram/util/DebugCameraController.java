package ch.ceff.libgdx.tangram.util;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Logger;

public class DebugCameraController {

    private static final int ZOOM_IN_KEY = Input.Keys.COMMA;
    private static final int ZOOM_OUT_KEY = Input.Keys.PERIOD;
    private static final float DEFAULT_ZOOM_SPEED = 2.0f;
    private static final float MAX_ZOOM_IN = 0.05f;
    private static final float MAX_ZOOM_OUT = 30f;

    private static final int UP_KEY = Input.Keys.W;
    private static final int RIGHT_KEY = Input.Keys.D;
    private static final int DOWN_KEY = Input.Keys.S;
    private static final int LEFT_KEY = Input.Keys.A;
    private static final float MOVEMENT_SPEED = 20.0f;

    private static final int RESET_CAMERA_KEY = Input.Keys.ENTER;

    private float zoom;
    private Vector2 startPosition;
    private Vector2 position;

    public DebugCameraController(Vector2 defaultPosition) {
        position = new Vector2(defaultPosition.x, defaultPosition.y);
        startPosition = new Vector2(defaultPosition.x, defaultPosition.y);
        zoom = 1.0f;
    }

    /**
     * Method handling key pressing.
     * @param delta
     */
    public void handleKeyTouch(float delta) {
        if(Gdx.app.getType() != Application.ApplicationType.Desktop) return;

        float zoomValue = DEFAULT_ZOOM_SPEED * delta;
        float movement = MOVEMENT_SPEED * delta;

        if (Gdx.input.isKeyPressed(UP_KEY)) {
            moveUp(movement);
        }

        if (Gdx.input.isKeyPressed(RIGHT_KEY)) {
            moveRight(movement);
        }

        if (Gdx.input.isKeyPressed(DOWN_KEY)) {
            moveDown(movement);
        }

        if (Gdx.input.isKeyPressed(LEFT_KEY)) {
            moveLeft(movement);
        }

        if (Gdx.input.isKeyPressed(RESET_CAMERA_KEY)) {
            resetCamera();
        }

        if (Gdx.input.isKeyPressed(ZOOM_IN_KEY)) {
            zoomIn(zoomValue);
        } else if (Gdx.input.isKeyPressed(ZOOM_OUT_KEY)) {
            zoomOut(zoomValue);
        }
    }

    /**
     * Zooms in camera.
     *
     * @param zoomValue : zoom value
     */
    private void zoomIn(float zoomValue) {
        setZoom(zoom + zoomValue);
    }

    /**
     * Zooms out camera.
     *
     * @param zoomValue : zoom value
     */
    private void zoomOut(float zoomValue) {
        setZoom(zoom - zoomValue);
    }

    private void setZoom(float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    /**
     * Applies changes to camera.
     *
     * @param camera : camera
     */
    public void applyTo(OrthographicCamera camera) {
        camera.zoom = zoom;
        camera.position.set(position, 0);
        camera.update();
    }

    private void moveUp(float movement) {
        position.y = position.y + movement;
    }

    private void moveRight(float movement) {
        position.x = position.x + movement;
    }

    private void moveDown(float movement) {
        position.y = position.y - movement;
    }

    private void moveLeft(float movement) {
        position.x = position.x - movement;
    }

    public void resetCamera() {
        position = startPosition;
        zoom = 1.0f;
    }
}
