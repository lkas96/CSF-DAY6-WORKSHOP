package vttp.csf.day6.workshop6.Queries;

public class PostQueries {
    public static final String INSERT_POST = "INSERT INTO posts (postId, comments, picture) values (?,?,?)";

    public static final String SELECT_POST = "SELECT postId, comments, picture FROM posts WHERE postId = ?";
    
}
