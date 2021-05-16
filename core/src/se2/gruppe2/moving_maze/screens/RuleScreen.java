package se2.gruppe2.moving_maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se2.gruppe2.moving_maze.MovingMazeGame;

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

    //Textures and views
    private Texture bgImageTexture;
    private TextureRegion bgTextureRegion;

    //Texts
    private String txtAblauf;
    private String txtSpielende;
    private String txtSpielfigurZiehen;
    private String txtVerschieben;
    private String txtZiel;

    public RuleScreen(final MovingMazeGame game) {
        this.game = game;
        camera = MovingMazeGame.getStandardizedCamera();
    }

    @Override
    public void show() {
        //instantiate background textures
        bgImageTexture = new Texture(Gdx.files.internal("ui/bg_moss.jpeg"));
        bgTextureRegion = new TextureRegion(bgImageTexture);

        //Buttons
        backTexture = new Texture(Gdx.files.internal("ui/buttons/backButton.png"));
        textureRegion = new TextureRegion(backTexture);
        textureRegionDrawable = new TextureRegionDrawable(textureRegion);
        backButton = new ImageButton(textureRegionDrawable);
        backButton.setPosition(20f, camera.viewportHeight - 20f - backButton.getHeight()/2f, Align.left);

        stage = new Stage(new ScreenViewport());
        stage.addActor(backButton);
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Gdx.input.setInputProcessor(stage);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.mainMenuScreen);

            }
        });

        setUpTable();
        scrollPane = new ScrollPane(table, skin);
        scrollPane.setWidth(Gdx.graphics.getWidth());
        scrollPane.setHeight(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/5f);
        stage.addActor(scrollPane);
    }

    public void setUpTable() {
        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.defaults().padTop(25f);

        var lblZiel = new Label("Ziel:", skin);
        lblZiel.setAlignment(Align.left);
        lblZiel.setFontScale(2.5f);
        table.add(lblZiel).width(Gdx.graphics.getWidth()/2f).height(25f).align(Align.left).row();

        txtZiel = Gdx.files.internal("rules/ziel.txt").readString();
        var ziel = new Label(txtZiel, skin);
        ziel.setAlignment(Align.left);
        ziel.setFontScale(1f);
        table.add(ziel).width(Gdx.graphics.getWidth()/2f).height(150f).row();

        var lblAblauf = new Label("Ablauf:", skin);
        lblAblauf.setAlignment(Align.left);
        lblAblauf.setFontScale(2.5f);
        table.add(lblAblauf).width(Gdx.graphics.getWidth()/2f).height(25f).row();

        txtAblauf = Gdx.files.internal("rules/ablauf.txt").readString();
        var ablauf = new Label(txtAblauf, skin);
        ablauf.setAlignment(Align.left);
        ablauf.setFontScale(1f);
        table.add(ablauf).width(Gdx.graphics.getWidth()/2f).height(250f).row();

        var lblVerschieben = new Label("1. Gaenge verschieben:", skin);
        lblVerschieben.setAlignment(Align.left);
        lblVerschieben.setFontScale(2.5f);
        table.add(lblVerschieben).width(Gdx.graphics.getWidth()/2f).height(25f).row();

        txtVerschieben = Gdx.files.internal("rules/verschieben.txt").readString();
        var verschieben = new Label(txtVerschieben, skin);
        verschieben.setAlignment(Align.left);
        verschieben.setFontScale(1f);
        table.add(verschieben).width(Gdx.graphics.getWidth()/2f).height(250f).row();

        var lblSpielfigurZiehen = new Label("2. Spielfigur ziehen:", skin);
        lblSpielfigurZiehen.setAlignment(Align.left);
        lblSpielfigurZiehen.setFontScale(2.5f);
        table.add(lblSpielfigurZiehen).width(Gdx.graphics.getWidth()/2f).height(25f).row();

        txtSpielfigurZiehen = Gdx.files.internal("rules/spielfigur_ziehen.txt").readString();
        var spielfigurZiehen = new Label(txtSpielfigurZiehen, skin);
        spielfigurZiehen.setAlignment(Align.left);
        spielfigurZiehen.setFontScale(1f);
        table.add(spielfigurZiehen).width(Gdx.graphics.getWidth()/2f).height(250f).row();

        var lblSpielende = new Label("Spielende:", skin);
        lblSpielende.setAlignment(Align.left);
        lblSpielende.setFontScale(2.5f);
        table.add(lblSpielende).width(Gdx.graphics.getWidth()/2f).height(25f).row();

        txtSpielende = Gdx.files.internal("rules/spielende.txt").readString();
        var spielende = new Label(txtSpielende, skin);
        spielende.setAlignment(Align.left);
        spielende.setFontScale(1f);
        table.add(spielende).width(Gdx.graphics.getWidth()/2f).height(150f).row();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bgTextureRegion, 0, 0);
        game.batch.end();

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
