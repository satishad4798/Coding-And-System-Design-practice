package Threads.Synchronized.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MainSynchronizedCollections {
    public static void main(String[] args) throws InterruptedException {
        PlaylistManager playlistManager = new PlaylistManager();

        // TODO: Create a thread to add songs to the playlist.
        Thread addSpngThread = new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                playlistManager.addSong("song" + (i + 1));

            }
        });

        Thread removeSongThread = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                playlistManager.removeSong("song" + (i + 1));

            }
        });

        addSpngThread.start();
        removeSongThread.start();

        addSpngThread.join();
        removeSongThread.join();

        // TODO: Create a thread to play and remove songs from the playlist.

        // TODO: Start both threads and join them.

        // Print remaining playlist
        System.out.println("Remaining playlist: " + playlistManager.getPlaylist());
    }
}

public class PlaylistManager {
    private final List<String> playlist = Collections.synchronizedList(new ArrayList<>());

    // TODO: Implement a thread-safe method to add a song to the playlist.
    void addSong(String song) {
        synchronized (playlist) {
            playlist.add(song);
            System.out.println("added song" + song);
        }
    }

    void removeSong(String song) {
        synchronized (playlist) {
            boolean status = playlist.remove(song);
            if (status) {
                System.out.println("removed song" + song);

            } else {
                System.out.println("no song");
            }

        }
    }
    // TODO: Implement a thread-safe method to remove a song after it is played.

    // Method to get the current playlist
    public List<String> getPlaylist() {
        return playlist;
    }
}