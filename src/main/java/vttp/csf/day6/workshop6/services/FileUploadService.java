package vttp.csf.day6.workshop6.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vttp.csf.day6.workshop6.models.Post;
import vttp.csf.day6.workshop6.repositories.FileUploadRepository;

@Service
public class FileUploadService {
    @Autowired FileUploadRepository fur;

    public String uploadFile(MultipartFile file, String comments) throws SQLException, IOException{
        return fur.uploadFile(file, comments);
    }

    public Optional<Post> getPostById(String postId) throws SQLException{
        return fur.getPostById(postId);
    }
}
