package sp;

public class RegularBehavior implements UserBehavior {
    private int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner) throws InvalidOperationException {
        throw new InvalidOperationException("Regular Users Can't Create A Playlist");
    }

    @Override
    public void playMusic(Music music) {
        if (playingLimit <= 0) {
            throw new InvalidOperationException("Playing Limit Has Been Reached.Cannot Play Music");
        }
        music.play();
        playingLimit--;
    }

    @Override
    public void buyPremium(User owner, int month) {
        owner.setBehavior(new PremiumBehavior(month));
    }

    public int getPlayingLimit() {
        return playingLimit;
    }

    public void setPlayingLimit(int playingLimit) {
        this.playingLimit = playingLimit;
    }
}
