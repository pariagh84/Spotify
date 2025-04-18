package sp;

import java.util.ArrayList;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream = 0;

    public static ArrayList<Music> allMusics = new ArrayList<>();

    public void play() {
        System.out.println("Playing " + title + " from " + singer.getUsername());
        numberOfStream++;
    }

    public ArrayList<Music> search(String Title) {
        ArrayList<Music> foundMusics = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.getTitle().equals(Title)) {
                foundMusics.add(music);
            }
        }
        if (foundMusics.isEmpty()) {
            return null;
        }
        return foundMusics;
    }

    public ArrayList<Music> search(String Title, User Singer) {
        ArrayList<Music> foundMusics = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.getTitle().equals(Title) && music.getSinger().equals(Singer)) {
                foundMusics.add(music);
            }
        }
        if (foundMusics.isEmpty()) {
            return null;
        }
        return foundMusics;
    }

    //Getters And Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getSinger() {
        return singer;
    }

    public void setSinger(User singer) {
        this.singer = singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }

    public void setNumberOfStream(int numberOfStream) {
        this.numberOfStream = numberOfStream;
    }
}