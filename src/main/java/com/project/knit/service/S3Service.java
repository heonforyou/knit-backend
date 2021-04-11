package com.project.knit.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.project.knit.dto.res.S3ImageResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

    @Value("aws.access-key")
    private String accessKey;

    @Value("aws.secret-key")
    private String secretKey;

    @Value("s3.dir.thread")
    private String threadDir;
    @Value("s3.dir.thumbnail")
    private String thumbnailDir;

    public S3ImageResDto uploadThumbnail(MultipartFile multipartFile, String filename) {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        s3Client.putObject(
                thumbnailDir,
                filename, // 확장자 포함
                (File) multipartFile
        );

        S3ImageResDto res = new S3ImageResDto();
        res.setUrl(s3Client.getUrl(thumbnailDir, filename).toString());

        return res;
//        String s3filePathUrl = s3BaseUrl.concat(thumbnailDir).concat(filename).replaceAll("\s", "+");

    }

    public S3ImageResDto uploadThreadFile(MultipartFile multipartFile, String filename) {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        s3Client.putObject(
                threadDir,
                filename, // 확장자 포함
                (File) multipartFile
        );
        S3ImageResDto res = new S3ImageResDto();
        res.setUrl(s3Client.getUrl(threadDir, filename).toString());

        return res;
    }

//    // Create a bucket by using a S3Waiter object
////    public static void createBucket(S3Client s3Client, String bucketName, Region region) {
//    public void createBucket(S3Client s3Client, String bucketName, Region region) {
//        try {
//            S3Waiter s3Waiter = s3Client.waiter();
//            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
//                    .bucket(bucketName)
//                    .createBucketConfiguration(
//                            CreateBucketConfiguration.builder()
//                                    .locationConstraint(region.id())
//                                    .build())
//                    .build();
//
//            s3Client.createBucket(bucketRequest);
//            HeadBucketRequest bucketRequestWait = HeadBucketRequest.builder()
//                    .bucket(bucketName)
//                    .build();
//
//            // Wait until the bucket is created and print out the response
//            WaiterResponse<HeadBucketResponse> waiterResponse = s3Waiter.waitUntilBucketExists(bucketRequestWait);
//            waiterResponse.matched().response().ifPresent(System.out::println);
//            log.info(bucketName + " is ready");
//
//        } catch (S3Exception e) {
//            log.error(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }
//    }
}
