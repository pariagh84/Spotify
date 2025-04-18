package sp;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;

    private ArrayList<User> followerList = new ArrayList<>();
    private ArrayList<User> followingList = new ArrayList<>();
    public ArrayList<Playlist> playLists = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    private UserBehavior behavior;

    //Constructor
    public User(String username, String password) {
        if (allUsers.contains(this)) {
            throw new InvalidOperationException("This User Already Exists");
        }
        if (password.length() < 8) {
            throw new InvalidOperationException("Password must be at least 8 characters");
        }
        this.username = username;
        this.password = password;
        this.behavior = new RegularBehavior();
        allUsers.add(this);
    }

    public void follow(User user) {
        followingList.add(user);
        user.followingList.add(this);
    }

    public void createPlaylist(String Title) {
        this.behavior.createPlaylist(Title, this);
    }

    public void playMusic(Music music) {
        this.behavior.playMusic(music);
    }

    public void buyPremium(User owner, int month) {
        this.behavior.buyPremium(owner, month);
    }

    @Override
    public String toString() {
        return username;
    }

    //Getters And Setters
    public ArrayList<User> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(ArrayList<User> followerList) {
        this.followerList = followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(ArrayList<User> followingList) {
        this.followingList = followingList;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}