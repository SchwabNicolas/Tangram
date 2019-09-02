package ch.ceff.libgdx.tangram.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ch.ceff.libgdx.tangram.Tangram;
import ch.ceff.libgdx.tangram.config.GameConfig;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = GameConfig.TITLE;
        config.width = GameConfig.WIDTH;
        config.height = GameConfig.HEIGHT;
        config.resizable = false;
        new LwjglApplication(new Tangram(), config);
    }
}
