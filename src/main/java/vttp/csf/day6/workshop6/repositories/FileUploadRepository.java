package vttp.csf.day6.workshop6.repositories;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import vttp.csf.day6.workshop6.Queries.PostQueries;
import vttp.csf.day6.workshop6.models.Post;

@Repository
public class FileUploadRepository {
    
    public static final String INSERT_POST = PostQueries.INSERT_POST;
    public static final String SELECT_POST = PostQueries.SELECT_POST;

    @Autowired JdbcTemplate template;

    //upload a file and reutrn a postId
    public String uploadFile(MultipartFile file, String comments){
        String postId = UUID.randomUUID().toString().replace(",", "").substring(0, 8);

        try {
            byte[] filesByte = file.getBytes();
            template.update(INSERT_POST, postId, comments, filesByte);

        } catch (IOException e) {
            throw new RuntimeException("Error uploading file");
        }

        return postId;
    }    

    //get a post by id
    public Optional<Post> getPostById(String postId){
        // SqlRowSet results = template.queryForRowSet(SELECT_POST, postId);
        // if (results.next()){
        //     Post p = new Post();
        //     p.setPostId(results.getString("postId"));
        //     p.setPicture(new byte[]{results.getByte("picture")});
        //     return Optional.of(p);
        // } 
        // return null;

        return template.query(SELECT_POST, (ResultSet rs) -> {
            if(rs.next()){
                return Optional.of(Post.populate(rs));
            } else {
                return Optional.empty();
            }
        }, postId);

    }
}
