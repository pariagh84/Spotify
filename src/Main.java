import sp.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Spotify-like Application Test\n");

        try {
            // Test User Creation
            System.out.println("===== TESTING USER CREATION =====");
            User user1 = new User("alice", "password123");
            User user2 = new User("bob", "password456");
            User user3 = new User("charlie", "password789");

            System.out.println("Created users: " + user1.getUsername() + ", " + user2.getUsername() + ", " + user3.getUsername());

            // Try to create a user with short password
            try {
                User invalidUser = new User("invalid", "short");
                System.out.println("Should not reach here!");
            } catch (InvalidOperationException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            // Test following functionality
            System.out.println("\n===== TESTING USER FOLLOWING =====");
            user1.follow(user2);
            user1.follow(user3);
            user2.follow(user3);

            System.out.println(user1.getUsername() + " is following " + user1.getFollowingList().size() + " users");
            System.out.println(user2.getUsername() + " is following " + user2.getFollowingList().size() + " users");
            System.out.println(user3.getUsername() + " is following " + user3.getFollowingList().size() + " users");

            // Test Music Creation
            System.out.println("\n===== TESTING MUSIC CREATION =====");
            Music song1 = new Music();
            song1.setTitle("SomerSault");
            song1.setSinger(user1);

            Music song2 = new Music();
            song2.setTitle("Uptown Funk");
            song2.setSinger(user2);

            Music song3 = new Music();
            song3.setTitle("Blinding Lights");
            song3.setSinger(user3);

            Music song4 = new Music();
            song4.setTitle("SomerSault");  // Same title as song1 but different singer
            song4.setSinger(user2);

            // Add music to allMusics list
            Music.allMusics.add(song1);
            Music.allMusics.add(song2);
            Music.allMusics.add(song3);
            Music.allMusics.add(song4);

            System.out.println("Created songs: " + song1.getTitle() + ", " + song2.getTitle() + ", " + song3.getTitle());

            // Test Music Search
            System.out.println("\n===== TESTING MUSIC SEARCH =====");
            System.out.println("Searching for 'SomerSault':");
            for (Music m : song1.search("SomerSault")) {
                System.out.println("Found: " + m.getTitle() + " by " + m.getSinger().getUsername());
            }

            System.out.println("\nSearching for 'SomerSault' by bob:");
            for (Music m : song1.search("SomerSault", user2)) {
                System.out.println("Found: " + m.getTitle() + " by " + m.getSinger().getUsername());
            }

            System.out.println("\nSearching for non-existent song:");
            try {
                if (song1.search("Non-existent Song") == null) {
                    System.out.println("No songs found as expected");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }

            // Test Regular User Behavior
            System.out.println("\n===== TESTING REGULAR USER BEHAVIOR =====");
            System.out.println("Regular user attempts to play music 6 times (should fail on 6th):");
            try {
                for (int i = 1; i <= 6; i++) {
                    System.out.print("Attempt " + i + ": ");
                    user1.playMusic(song1);
                }
                System.out.println("Should not reach here!");
            } catch (InvalidOperationException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            // Test Regular User trying to create playlist
            System.out.println("\nRegular user tries to create playlist:");
            try {
                user1.createPlaylist("My Playlist", user1);
                System.out.println("Should not reach here!");
            } catch (InvalidOperationException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            // Test Premium User
            System.out.println("\n===== TESTING PREMIUM USER BEHAVIOR =====");
            System.out.println("User bob buys premium for 3 months");
            user2.buyPremium(user2, 3);
            System.out.println("Premium user creates playlist:");
            user2.createPlaylist("Bob's Favorites", user2);
            System.out.println("Bob now has " + user2.playLists.size() + " playlists");

            // Test playlist operations
            System.out.println("\n===== TESTING PLAYLIST OPERATIONS =====");
            Playlist bobPlaylist = user2.playLists.getFirst();

            // Test adding music to playlist
            System.out.println("Adding songs to Bob's playlist:");
            bobPlaylist.addMusic(song1, user2, "password456");
            bobPlaylist.addMusic(song2, user2, "password456");
            bobPlaylist.addMusic(song3, user2, "password456");

            System.out.println("Bob's playlist now has " + bobPlaylist.getPlaylist().size() + " songs");

            // Test playing entire playlist
            System.out.println("\nPlaying Bob's playlist:");
            bobPlaylist.playPlaylist();

            // Test removing song from playlist
            System.out.println("\nRemoving a song from Bob's playlist:");
            bobPlaylist.removeMusic(song2, user2, "password456");
            System.out.println("Bob's playlist now has " + bobPlaylist.getPlaylist().size() + " songs");

            // Test invalid password
            System.out.println("\nTrying to edit playlist with wrong password:");
            try {
                bobPlaylist.editTitle("New Title", user2, "wrongpassword");
                System.out.println("Should not reach here!");
            } catch (InvalidOperationException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            // Test editing playlist title
            System.out.println("\nEditing playlist title with correct password:");
            bobPlaylist.editTitle("Bob's Ultimate Mix", user2, "password456");
            System.out.println("Playlist title is now: " + bobPlaylist.getTitle());

            // Test playlist search
            System.out.println("\n===== TESTING PLAYLIST SEARCH =====");
            System.out.println("Searching for 'SomerSault' in playlist:");
            for (Music m : bobPlaylist.searchInPlaylist("SomerSault")) {
                System.out.println("Found: " + m.getTitle() + " by " + m.getSinger().getUsername());
            }

            // Test adding more premium months
            System.out.println("\n===== TESTING PREMIUM EXTENSION =====");
            System.out.println("User bob extends premium for 2 more months");
            user2.buyPremium(user2, 2);
            PremiumBehavior premiumBehavior = (PremiumBehavior) user2.getBehavior();
            System.out.println("Bob now has premium for " + premiumBehavior.month + " months");

            // Test unlimited plays for premium user
            System.out.println("\nTesting unlimited plays for premium user:");
            for (int i = 1; i <= 10; i++) {
                user2.playMusic(song3);
            }
            System.out.println("Premium user successfully played music 10 times");

            System.out.println("\nApplication Test Completed Successfully");

        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}