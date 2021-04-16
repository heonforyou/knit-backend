package com.project.knit.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.project.knit.config.AwsConfig;
import com.project.knit.config.S3Config;
import com.project.knit.dto.res.S3ImageResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

    private final AwsConfig awsConfig;
    private final S3Config s3Config;

    private AmazonS3 s3Client;

    @PostConstruct
    private void init() {
        this.s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(this.awsConfig.getAccessKey(), this.awsConfig.getSecretKey())))
                .withRegion(this.s3Config.getRegion())
                .build();
    }


    public S3ImageResDto uploadThumbnail(MultipartFile multipartFile, String filename) throws IOException {

        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("Failed to convert from MultipartFile to File"));

        this.s3Client.putObject(
                this.s3Config.getThumbnailDir(),
                filename, // 확장자 포함
                uploadFile
        );

        S3ImageResDto res = new S3ImageResDto();
        res.setUrl(this.s3Client.getUrl(this.s3Config.getThreadDir(), filename).toString());

        return res;
//        String s3filePathUrl = s3BaseUrl.concat(thumbnailDir).concat(filename).replaceAll("\s", "+");

    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    public S3ImageResDto uploadThreadFile(MultipartFile multipartFile, String filename) throws IOException {

        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("Failed to convert from MultipartFile to File"));

        this.s3Client.putObject(
                this.s3Config.getThreadDir(),
                filename, // 확장자 포함
                uploadFile
        );
        S3ImageResDto res = new S3ImageResDto();
        res.setUrl(s3Client.getUrl(this.s3Config.getThreadDir(), filename).toString());

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
