package sp;

import java.util.ArrayList;

public class Playlist {
    private String title;
    private User Owner;

    private final ArrayList<Music> playlist = new ArrayList<>();

    //Constructor
    public Playlist(String Title, User Owner) {}

    public void editTitle(String Title, User owner, String password) {
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        setTitle(Title);
    }

    public void addMusic(Music music, User owner, String password) {
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        if (playlist.contains(music)) {
            throw new InvalidOperationException("Music already exists");
        }
        playlist.add(music);
    }

    public void removeMusic(Music music, User owner, String password) {
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Invalid password");
        }
        if (!playlist.contains(music)) {
            throw new InvalidOperationException("Music Doesn't exists");
        }
        playlist.remove(music);
    }

    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> foundMusics = new ArrayList<>();
        for (Music music : playlist) {
            if (music.getTitle().equals(title)) {
                foundMusics.add(music);
            }
        }
        if (foundMusics.isEmpty()) {
            return null;
        }
        return foundMusics;
    }

    public ArrayList<Music> searchInPlaylist(String title, User Singer) {
        ArrayList<Music> foundMusics = new ArrayList<>();
        for (Music music : playlist) {
            if (music.getTitle().equals(title) && music.getSinger().equals(Singer)) {
                foundMusics.add(music);
            }
        }
        if (foundMusics.isEmpty()) {
            return null;
        }
        return foundMusics;
    }

    public void playPlaylist() {
        for (Music music : playlist) {
            music.play();
        }
    }

    //Getters And Setters
    public ArrayList<Music> getPlaylist() {
        return playlist;
    }

    public User getOwner() {
        return Owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOwner(User owner) {
        this.Owner = owner;
    }
}
