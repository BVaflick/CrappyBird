package com.benvaflick.crappybird.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.benvaflick.crappybird.Main;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Main.WIDTH;
        config.height = Main.HEIGHT;
        config.title = Main.TITLE;
        config.addIcon("sprites/ico32.png", Files.FileType.Internal);
        new LwjglApplication(new Main(), config);
    }
}
