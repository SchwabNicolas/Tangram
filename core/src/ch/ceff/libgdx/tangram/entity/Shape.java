package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ch.ceff.libgdx.tangram.config.GameConfig;
import ch.ceff.libgdx.tangram.util.Utils;
// Ouaich ouaich les amis
public abstract class Shape extends Actor {
    private Polygon bounds;
    private TextureRegion textureRegion;
    private boolean selected;
    private Float initialRotation;
    private Color shapeColor;

    public Shape() {
    }

    public Shape(TextureRegion textureRegion, Polygon bounds) {
        this.textureRegion = textureRegion;
        this.bounds = bounds;
        setPosition(getX() - getWidth() / 2f, getY() - getHeight() / 2f);
        setSize(textureRegion.getRegionWidth() / GameConfig.PPM / GameConfig.IMAGE_SCALE, textureRegion.getRegionHeight() / GameConfig.PPM / GameConfig.IMAGE_SCALE);
    }

    public void setDefaultBoundsOrigin() {
        bounds.setOrigin(bounds.getX() + getWidth() / 2f, bounds.getY() + getHeight() / 2f);
    }

    public void setDefaultRotation() {
        bounds.setRotation(getRotation());
    }

    public void rotateAlongPoint(Vector2 pointer) {
        if (pointer == null) return;
        int rotation = (int) Math.floor(Utils.angleBetweenTwoPoints(pointer, new Vector2(getX(), getY())));
        setRotation(Utils.roundTo(rotation, GameConfig.ROTATION_STEP));
    }

    public void resetParameters() {
        initialRotation = null;
        selected = false;
    }

    public void roundPosition() {
        setPosition(Utils.roundTo((int) Math.floor(getX()), GameConfig.GRID_CELL_SIZE), Utils.roundTo((int) Math.floor(getY()), GameConfig.GRID_CELL_SIZE));
    }

    public void moveTo(float x, float y) {
        setPosition(x, y);
    }

    public void moveTo(Vector2 coords) {
        moveTo(coords.x, coords.y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        bounds.setPosition(getX() - getWidth() / 2, getY() - getHeight() / 2);
        bounds.setRotation(getRotation());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(
                textureRegion,
                getX() - getWidth() / 2f,
                getY() - getHeight() / 2f,
                getWidth() / 2,
                getHeight() / 2,
                getWidth(),
                getHeight(),
                1,
                1,
                getRotation()
        );
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public float getInitialRotation() {
        return initialRotation;
    }

    public void setInitialRotation(Float initialRotation) {
        this.initialRotation = initialRotation;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }
}
