package se_ii.gruppe2.moving_maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se_ii.gruppe2.moving_maze.MovingMazeGame;

public class RuleScreen implements Screen {

    final MovingMazeGame game;
    OrthographicCamera camera;

    //ui stuff
    private Stage stage;
    private Texture backTexture;
    private TextureRegion textureRegion;
    private TextureRegionDrawable textureRegionDrawable;
    private ImageButton backButton;
    private ScrollPane scrollPane;
    private Table table;
    private Skin skin;
    private SpriteBatch batch;

    //Textures and views
    private Texture bgImageTexture;
    private TextureRegion bgTextureRegion;
    private Texture background;

    public RuleScreen(final MovingMazeGame game) {
        this.game = game;
        batch = game.getBatch();
        camera = MovingMazeGame.getStandardizedCamera();
    }

    @Override
    public void show() {
        //instantiate background textures
        bgImageTexture = new Texture(Gdx.files.internal("ui/bg_moss.jpeg"));
        bgTextureRegion = new TextureRegion(bgImageTexture);
        background = new Texture(Gdx.files.internal("rules/background.png"));

        //Button
        backTexture = new Texture(Gdx.files.internal("ui/buttons/backButton.png"));
        textureRegion = new TextureRegion(backTexture);
        textureRegionDrawable = new TextureRegionDrawable(textureRegion);
        backButton = new ImageButton(textureRegionDrawable);
        backButton.setPosition(20f, camera.viewportHeight - 100f, Align.left);
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        stage = new Stage(new ScreenViewport());
        setUpTable();
        scrollPane = new ScrollPane(table, skin);
        scrollPane.setWidth(Gdx.graphics.getWidth());
        scrollPane.setHeight(Gdx.graphics.getHeight());
        scrollPane.setScrollingDisabled(true, false);
        stage.addActor(scrollPane);

        stage.addActor(backButton);
        Gdx.input.setInputProcessor(stage);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.getMainMenuScreen());

            }
        });


    }

    public void setUpTable() {
        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.setBackground(new TextureRegionDrawable(new TextureRegion(background)));
        table.defaults().width(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/6f);
        //table.debug();

        var lblZiel = new Label("Ziel:", skin);
        lblZiel.setAlignment(Align.left);
        lblZiel.setFontScale(3f);
        table.add(lblZiel).padTop(300f).row();

        String txtZiel = Gdx.files.internal("rules/ziel.txt").readString();
        var ziel = new Label(txtZiel, skin);
        ziel.setAlignment(Align.left);
        ziel.setFontScale(2f);
        table.add(ziel).align(Align.left).padBottom(100f).row();

        var lblAblauf = new Label("Ablauf:", skin);
        lblAblauf.setAlignment(Align.left);
        lblAblauf.setFontScale(3f);
        table.add(lblAblauf).row();

        //Texts
        String txtAblauf = Gdx.files.internal("rules/ablauf.txt").readString();
        var ablauf = new Label(txtAblauf, skin);
        ablauf.setAlignment(Align.left);
        ablauf.setFontScale(2f);
        table.add(ablauf).padBottom(100f).row();


        var lblVerschieben = new Label("1. Gaenge verschieben:", skin);
        lblVerschieben.setAlignment(Align.left);
        lblVerschieben.setFontScale(3f);
        table.add(lblVerschieben).row();

        String txtVerschieben = Gdx.files.internal("rules/verschieben.txt").readString();
        var verschieben = new Label(txtVerschieben, skin);
        verschieben.setAlignment(Align.left);
        verschieben.setFontScale(2f);
        table.add(verschieben).padBottom(100f).row();

        var lblSpielfigurZiehen = new Label("2. Spielfigur ziehen:", skin);
        lblSpielfigurZiehen.setAlignment(Align.left);
        lblSpielfigurZiehen.setFontScale(3f);
        table.add(lblSpielfigurZiehen).row();

        String txtSpielfigurZiehen = Gdx.files.internal("rules/spielfigur_ziehen.txt").readString();
        var spielfigurZiehen = new Label(txtSpielfigurZiehen, skin);
        spielfigurZiehen.setAlignment(Align.left);
        spielfigurZiehen.setFontScale(2f);
        table.add(spielfigurZiehen).padBottom(100f).row();

        var lblSpielende = new Label("Spielende:", skin);
        lblSpielende.setAlignment(Align.left);
        lblSpielende.setFontScale(3f);
        table.add(lblSpielende).row();

        String txtSpielende = Gdx.files.internal("rules/spielende.txt").readString();
        var spielende = new Label(txtSpielende, skin);
        spielende.setAlignment(Align.left);
        spielende.setFontScale(2f);
        table.add(spielende).padBottom(300f).row();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 2);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(bgTextureRegion, 0, 0);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
