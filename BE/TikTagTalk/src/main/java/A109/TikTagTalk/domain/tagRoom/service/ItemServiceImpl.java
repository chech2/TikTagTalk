package A109.TikTagTalk.domain.tagRoom.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    //AWS S3
    private final AmazonS3 awsS3Client;
    private final String bucketName="tiktagtalk";

    @Override
    public void addItems() {
        String folderName="item/";
        ObjectListing objectListing=awsS3Client.listObjects(bucketName,folderName);

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String objectKey = objectSummary.getKey();
            if (!objectKey.equals(folderName)) { // 폴더 자체는 X

                System.out.println(awsS3Client.getUrl(bucketName, objectKey) + "!!!");
            }
        }
    }
}
