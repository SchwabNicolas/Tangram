package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ch.ceff.libgdx.tangram.config.GameConfig;

public class Logo extends Actor {
    private TextureRegion textureRegion;

    public Logo(TextureAtlas splashTextureAtlas) {
        textureRegion = splashTextureAtlas.findRegion("logo_ceff_512x231");
        setSize(textureRegion.getRegionWidth() / GameConfig.PPM, textureRegion.getRegionHeight() / GameConfig.PPM);

        setPosition(GameConfig.WORLD_WIDTH / 2 - getWidth() / 2.0f, (GameConfig.WORLD_HEIGHT / 2.0f - getHeight() / 2.0f) + 3);
        setScale(0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                textureRegion,
                getX(),
                getY(),
                getWidth() / 2.0f,
                getWidth() / 2.0f,
                getWidth(),
                getHeight(),
                getScaleX(),
                getScaleY(),
                getRotation()
        );
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
