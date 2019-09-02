package ch.ceff.libgdx.tangram.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {
    private static final String RAW_ASSET_PATH = "desktop/raw";
    private static final String ASSET_PATH = "android/assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.debug = false;
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        settings.filterMin = Texture.TextureFilter.Linear;
        settings.filterMag = Texture.TextureFilter.Linear;

        TexturePacker.process(
                settings,
                RAW_ASSET_PATH + "/gameplay",
                ASSET_PATH + "/gameplay",
                "shapes"
        );
    }
}
