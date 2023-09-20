package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.tagRoom.entity.Item;
import A109.TikTagTalk.domain.tagRoom.repository.ItemRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    //AWS S3
    private final AmazonS3 awsS3Client;
    private final String bucketName="tiktagtalk"; //이건 awss3properties에 넣어보자
    @Override
    public void addItems() {
        String folderName="item/";
        ObjectListing objectListing=awsS3Client.listObjects(bucketName,folderName);

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String objectKey = objectSummary.getKey();
            if (!objectKey.equals(folderName)) { // 폴더 자체는 X
                String name=objectKey.substring(folderName.length(),objectKey.length()-4);
                String s3Url=awsS3Client.getUrl(bucketName,objectKey).toString();
                Item item=Item.builder()
                        .name(name)
                        .s3Url(s3Url)
                        .build();

                itemRepository.save(item);
            }
        }
    }
}
