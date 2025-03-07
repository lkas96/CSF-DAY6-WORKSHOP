package vttp.csf.day6.workshop6.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    @Autowired AmazonS3 s3Client;

    @Value("${do.storage.bucket}")
    private String bucketName;

    @Value("${do.storage.endpoint}")
    private String endPoint;

    public String upload(MultipartFile file, 
        String comments, 
        String postId) throws IOException{
        Map<String, String> metadata = Map.of(
            "comments", comments,
            "postId", postId,
            "uploadDatetime", String.valueOf(System.currentTimeMillis())
        );

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setUserMetadata(metadata);
        String origFilename = file.getOriginalFilename();
        String finalFilename = "";
        if(origFilename.equals("blob")){
            finalFilename = postId + ".png";
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(
            bucketName, finalFilename, file.getInputStream(), objectMetadata);
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.putObject(putObjectRequest);
        System.out.println("https://%s.%s/%s"
                    .formatted(bucketName, endPoint, finalFilename));
        return "https://%s.%s/%s"
                    .formatted(bucketName, endPoint, finalFilename);
    }

}
