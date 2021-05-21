package se_ii.gruppe2.moving_maze.server;

import com.esotericsoftware.kryonet.Server;
import se_ii.gruppe2.moving_maze.network.Registry;
import se_ii.gruppe2.moving_maze.server.handlers.GameStateUpdateHandler;
import se_ii.gruppe2.moving_maze.server.handlers.JoinSessionHandler;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {

        int bufferSize = 4096;

        Server srv = new Server(bufferSize, bufferSize);

        Registry.registerClassesOnKryo(srv.getKryo());
        srv.start();

        // always create a default session for dev-purpose
        SessionManager.createSessionByKey("devgame");
        // SessionManager.getSessionByKey("devgame").getState().setBoard(GameBoardFactory.getStandardGameBoard());

        try {
            srv.bind(ServerConfiguration.PORT);
            srv.addListener(new JoinSessionHandler());
            srv.addListener(new GameStateUpdateHandler());
            System.out.println("Server successfully listening on port " + ServerConfiguration.PORT);
        } catch(IOException ioe) {
            System.err.println("Failed to bind port " + ServerConfiguration.PORT + " to server! Exiting ...");
        }

    }
}
