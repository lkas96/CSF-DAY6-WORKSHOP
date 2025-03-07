package vttp.csf.day6.workshop6.models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Post implements Serializable {
    private String postId;
    private String comments;
    private byte[] image;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public static Post populate(ResultSet rs)
        throws SQLException{
        
        Post post = new Post();
        post.setPostId(rs.getString("postId"));
        post.setComments(rs.getString("comments"));
        post.setImage(rs.getBytes("picture"));
    
        return post;
    }

}