package ch.ceff.libgdx.tangram.entity;

/**
 * cree un actor pour le background
 */

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ch.ceff.libgdx.tangram.config.GameConfig;

public class Background extends Actor {
    private Image image;

    public Background(TextureRegion textureRegion) {
        this.image = new Image(textureRegion);
        image.setPosition(0, 0);
        image.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
    }
}
