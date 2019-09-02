package ch.ceff.libgdx.tangram.screen.game;

import com.badlogic.gdx.ScreenAdapter;

import ch.ceff.libgdx.tangram.Tangram;

public class GameScreen extends ScreenAdapter {

    private final Tangram context;

    private GameController controller;
    private GameRenderer renderer;

    public GameScreen (Tangram context){
        this.context = context;
        controller = new GameController(context);
        renderer = new GameRenderer(context,controller);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void render(float delta) {
//        controller.update(delta);
        renderer.render(delta);
    }

    /**
     * appelee lorsque la screen quitte l'ecran
     */
    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
