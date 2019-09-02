package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityFactory {
    private final TextureAtlas textureAtlas;

    public EntityFactory(AssetManager assetManager) {
        textureAtlas = assetManager.get("gameplay/shapes.atlas", TextureAtlas.class);
    }

    public Parallelogram createParallelogram() {
        TextureRegion region = textureAtlas.findRegion("parallelogramme");
        return new Parallelogram(region);
    }

    public Square createSquare() {
        TextureRegion region = textureAtlas.findRegion("carre");
        return new Square(region);
    }

    public TriangleG1 createTriangleG1() {
        TextureRegion region = textureAtlas.findRegion("triangleG1");
        return new TriangleG1(region);
    }

    public TriangleG2 createTriangleG2() {
        TextureRegion region = textureAtlas.findRegion("triangleG2");
        return new TriangleG2(region);
    }

    public TriangleP1 createTriangleP1() {
        TextureRegion region = textureAtlas.findRegion("triangleP1");
        return new TriangleP1(region);
    }

    public TriangleP2 createTriangleP2() {
        TextureRegion region = textureAtlas.findRegion("triangleP2");
        return new TriangleP2(region);
    }

    public TriangleM createTriangleM() {
        TextureRegion region = textureAtlas.findRegion("triangleM");
        return new TriangleM(region);
    }
}
