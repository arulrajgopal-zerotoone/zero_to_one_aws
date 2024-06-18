package group1;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;


public class s3_get_lst_buckets {
    public static void main (String [] args) {

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
        List <Bucket> buckets = s3.listBuckets();

        buckets.stream().forEach(bucket -> { System.out.println(bucket.getName());});

    }
 }



