package ch.ceff.libgdx.tangram.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;

import ch.ceff.libgdx.tangram.Tangram;
import ch.ceff.libgdx.tangram.config.GameConfig;
import ch.ceff.libgdx.tangram.entity.Shape;
import ch.ceff.libgdx.tangram.util.DebugCameraController;
import ch.ceff.libgdx.tangram.util.Utils;

public class GameRenderer implements Disposable {
    private final GameController controller;
    private Stage stage;///scene pour les acteurs
    private final ShapeRenderer renderer;

    ///pour le debug de la camera
    private final DebugCameraController debugCameraController;
    private OrthographicCamera camera;

    // Inputs
    private PointerPool pointerPool;
    private ArrayList<Pointer> pointers;
    private boolean dragging;

    private static final Logger log = new Logger(GameRenderer.class.getSimpleName(), Logger.DEBUG);

    public GameRenderer(Tangram context, final GameController gamecontroller) {
        this.controller = gamecontroller;
        renderer = context.getRenderer();
        pointerPool = new PointerPool();
        pointers = new ArrayList<>();
        stage = new Stage(context.getScreenViewport(), context.getSpriteBatch());

        for (Shape shape : gamecontroller.getShapes()) {
            stage.addActor(shape);
        }

        ///TODO: DEBUG
        debugCameraController = new DebugCameraController(new Vector2(GameConfig.WORLD_WIDTH / 2.0f, GameConfig.WORLD_HEIGHT / 2.0f));
        this.camera = context.getGameCamera();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                for (Shape shape : gamecontroller.getShapes()) {
                    shape.setInitialRotation(shape.getRotation());
                    Pointer touchPos = pointerPool.newObject(screenX, screenY);
                    pointers.add(touchPos);
                    stage.getViewport().unproject(touchPos.coords);
                    if (shape.getBounds().contains(touchPos.coords)) {
                        dragging = true;
                        shape.setSelected(true);
                    }
                }
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Shape selectedShape = null;
                if (!dragging) return false;

                Pointer originPointer = pointers.get(0);

                Pointer anglePointer = pointers.get(pointers.size() - 1);

                for (Shape shape : gamecontroller.getShapes()) {
                    if (shape.isSelected()) {
                        selectedShape = shape;
                        if (pointer > 0) {
                            anglePointer.coords.set(screenX, screenY);
                            stage.getViewport().unproject(anglePointer.coords);
                            selectedShape.rotate(anglePointer.coords.angle());
                            log.debug(" " + anglePointer.coords.angle() * Utils.getAngleSign(anglePointer.coords.angle()));
                        } else {
                            originPointer.coords.set(screenX, screenY);
                            stage.getViewport().unproject(originPointer.coords);
                            selectedShape.moveTo(originPointer.coords);
                        }
                        break;
                    }
                }
                if (selectedShape == null) return false;
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                for (Shape shape : controller.getShapes()) {
                    shape.setSelected(false);
                    shape.setInitialRotation(null);
                }
                pointers.remove(pointers.size() - 1);
                return true;
            }
        });
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render(float delta) {
        Utils.clearScreen(Color.BLUE);
        debugCameraController.handleKeyTouch(delta);
        debugCameraController.applyTo(camera);
        stage.act(delta);
        stage.draw();
        ///TODO: DEBUG
        renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(controller.getRect().x, controller.getRect().y, controller.getRect().width, controller.getRect().height);
        for (Shape shape : controller.getShapes()) {
            renderer.polygon(shape.getBounds().getTransformedVertices());
            renderer.circle(shape.getBounds().getOriginX(), shape.getBounds().getOriginY(), 0.5f);
        }
        renderer.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private class Pointer implements Pool.Poolable {
        Vector2 coords;

        public Pointer() {
        }

        public Pointer(Vector2 touchPos) {
            this.coords = touchPos;
        }

        public Pointer(float x, float y) {
            this.coords = new Vector2(x, y);
        }

        @Override
        public void reset() {
            coords.setZero();
        }
    }

    private class PointerPool extends Pool<Pointer> {

        @Override
        protected Pointer newObject() {
            return new Pointer();
        }

        protected Pointer newObject(Vector2 coords) {
            return new Pointer(coords);
        }

        protected Pointer newObject(float x, float y) {
            return new Pointer(x, y);
        }
    }
}
