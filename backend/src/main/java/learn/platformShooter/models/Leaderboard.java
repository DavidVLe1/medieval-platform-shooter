package learn.platformShooter.models;

import java.util.Objects;

public class Leaderboard {
    private int leaderboardId;
    private int userId;
    private String username;
    private int score;

    public Leaderboard() {
    }

    public Leaderboard(int leaderboardId , int userId , String username , int score) {
        this.leaderboardId = leaderboardId;
        this.userId = userId;
        this.username = username;
        this.score = score;
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Leaderboard that = (Leaderboard) o;
        return leaderboardId == that.leaderboardId && userId == that.userId && score == that.score && Objects.equals (username , that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash (leaderboardId , userId , username , score);
    }
}
