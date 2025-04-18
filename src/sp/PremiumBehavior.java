package sp;

public class PremiumBehavior implements UserBehavior {
    public int month;

    public PremiumBehavior(int month) {
        this.month = month;
    }

    @Override
    public void createPlaylist(String Title, User Owner) {
        Playlist playlist = new Playlist(Title, Owner);
        Owner.playLists.add(playlist);
    }

    @Override
    public void playMusic(Music music) {
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) {
        this.month += month;
    }
}
