package A109.TikTagTalk.domain.tagRoom.service;

import A109.TikTagTalk.domain.tag.entity.Tag;
import A109.TikTagTalk.domain.tag.repository.TagRepository;
import A109.TikTagTalk.domain.tagRoom.entity.Item;
import A109.TikTagTalk.domain.tagRoom.repository.ItemRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;
    //AWS S3
    private final AmazonS3 awsS3Client;
    private final String bucketName="tiktagtalk"; //이건 awss3properties에 넣어보자
    @Override
    @Transactional
    public void addItems() {
        String folderName="item/";
        ObjectListing objectListing=awsS3Client.listObjects(bucketName,folderName);

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String objectKey = objectSummary.getKey();
            if (!objectKey.equals(folderName)) { // 폴더 자체는 X
                String[] str=objectKey.split("/");
                Long tagId=Long.parseLong(str[1]);
                Tag tag=tagRepository.findById(tagId).get();
                String name="";
                int sizeX = 0;
                int sizeY = 0;
                boolean isSkin = false;
                boolean isShit=false;
                if(str[2].equals("shit")){
                    isShit=true;
                    name=str[3].split("[.]")[0];
                    if(name.equals("shit_coffee")) {
                        sizeX = 2;
                        sizeY = 2;
                    }else if(name.equals("shit_hair_dryer")){
                        sizeX=2;
                        sizeY=2;
                    }else if(name.equals("shit_bottled_car")){
                        sizeX=2;
                        sizeY=2;
                    }else if(name.equals("shit_snack_shelf")){
                        sizeX=2;
                        sizeY=4;
                    }else if(name.equals("shit_coins")){
                        sizeX=2;
                        sizeY=2;
                    }else if(name.equals("shit_pizza")){
                        sizeX=4;
                        sizeY=4;
                    }else if(name.equals("shit_first_aid_kit")){
                        sizeX=2;
                        sizeY=4;
                    }else if(name.equals("shit_red_gear_pc_gaming_controller")){
                        sizeX=2;
                        sizeY=2;
                    }else if(name.equals("shit_shiba")){
                        sizeX=2;
                        sizeY=5;
                    }else if(name.equals("shit_youtube_silver_play_button")){
                        sizeX=2;
                        sizeY=1;
                    }else if(name.equals("shit_shopping_cart")){
                        sizeX=2;
                        sizeY=2;
                    }else if(name.equals("shit_luggage")){
                        sizeX=2;
                        sizeY=4;
                    }else{
                        isSkin=true;
                        if(name.equals("shit_cute_coffee_cup")){
                            sizeX=2;
                            sizeY=2;
                        }else if(name.equals("shit_barbers_pole")){
                            sizeX=2;
                            sizeY=1;
                        }else if(name.equals("shit_porsche_911")){
                            sizeX=2;
                            sizeY=3;
                        }else if(name.equals("shit_vending_machine")){
                            sizeX=5;
                            sizeY=4;
                        }else if(name.equals("shit_safe")){
                            sizeX=3;
                            sizeY=4;
                        }else if(name.equals("shit_sculptober_burger")){
                            sizeX=2;
                            sizeY=2;
                        }else if(name.equals("shit_pubg_mobile_first_aid_kit")){
                            sizeX=2;
                            sizeY=4;
                        }else if(name.equals("shit_vintage_controller")){
                            sizeX=2;
                            sizeY=4;
                        }else if(name.equals("shit_robo_cat")){
                            sizeX=2;
                            sizeY=4;
                        }else if(name.equals("shit_youtube_gold_play_button")){
                            sizeX=2;
                            sizeY=1;
                        }else if(name.equals("shit_gucci")){
                            sizeX=3;
                            sizeY=4;
                        }else if(name.equals("shit_vintage_luggage")){
                            sizeX=2;
                            sizeY=4;
                        }
                    }
                }else {
                    name = str[2].split("[.]")[0];

                    if (name.equals("pizza")) {
                        sizeX = 4;
                        sizeY = 4;
                    } else if (name.equals("sculptober_burger")) {
                        sizeX = 2;
                        sizeY = 2;
                        isSkin = true;
                    } else if (name.equals("snack_shelf")) {
                        sizeX = 2;
                        sizeY = 4;
                    } else if (name.equals("vending_machine")) {
                        sizeX = 5;
                        sizeY = 4;
                        isSkin = true;
                    } else if (name.equals("bottled_car")) {
                        sizeX = 2;
                        sizeY = 2;
                    } else if (name.equals("porsche_911")) {
                        sizeX = 2;
                        sizeY = 3;
                        isSkin = true;
                    } else if (name.equals("gucci")) {
                        sizeX = 3;
                        sizeY = 3;
                        isSkin = true;
                    } else if (name.equals("shopping_cart")) {
                        sizeX = 2;
                        sizeY = 2;
                    } else if (name.equals("coffee")) {
                        sizeX = 2;
                        sizeY = 2;
                    } else if (name.equals("cute_coffee_cup")) {
                        sizeX = 2;
                        sizeY = 2;
                        isSkin = true;
                    } else if (name.equals("coins")) {
                        sizeX = 2;
                        sizeY = 2;
                    } else if (name.equals("red_gear_pc_gaming_controller")) {
                        sizeX = 2;
                        sizeY = 2;
                    } else if (name.equals("vintage_controller")) {
                        sizeX = 2;
                        sizeY = 4;
                        isSkin = true;
                    } else if (name.equals("barbers_pole")) {
                        sizeX = 2;
                        sizeY = 2;
                        isSkin = true;
                    } else if (name.equals("hair_dryer")) {
                        sizeX = 1;
                        sizeY = 1;
                    } else if (name.equals("first_aid_kit")) {
                        sizeX = 2;
                        sizeY = 4;
                    } else if (name.equals("pubg_mobile_first_aid_kit")) {
                        sizeX = 2;
                        sizeY = 4;
                        isSkin = true;
                    } else if (name.equals("safe")) {
                        sizeX = 3;
                        sizeY = 4;
                        isSkin = true;
                    } else if (name.equals("youtube_gold_play_button")) {
                        sizeX = 2;
                        sizeY = 1;
                        isSkin = true;
                    } else if (name.equals("youtube_silver_play_button")) {
                        sizeX = 2;
                        sizeY = 1;
                    } else if (name.equals("robo_cat")) {
                        sizeX = 2;
                        sizeY = 3;
                        isSkin = true;
                    } else if (name.equals("shiba")) {
                        sizeX = 2;
                        sizeY = 4;
                    } else if (name.equals("luggage")) {
                        sizeX = 2;
                        sizeY = 4;
                    } else if (name.equals("vintage_luggage")) {
                        sizeX = 2;
                        sizeY = 4;
                        isSkin = true;
                    }
                }
                String s3Url=awsS3Client.getUrl(bucketName,objectKey).toString();
                Item item=Item.builder()
                        .name(name)
                        .s3Url(s3Url)
                        .sizeX(sizeX)
                        .sizeY(sizeY)
                        .isSkin(isSkin)
                        .isShit(isShit)
                        .tag(tag)
                        .build();
                item.mappingItemAndTag(tag);
                itemRepository.save(item);
            }
        }
    }
}
