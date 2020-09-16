package com.basicbackend.infrastructure.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.basicbackend.infrastructure.configuration.oss.AliOssproperties;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Objects;

/**
 * 如果使用阿里云oss。
 * 使用之前需要去配置相关参数（application.yml中关于阿里云oss的配置）
 */
@RequiredArgsConstructor
public class OssService {
    private final OSSClient ossClient;
    private final AliOssproperties aliOssproperties;

    public void upload() {
        File file = new File("filepath");
        String newName = IdWorker.get32UUID() + "." + Files.getFileExtension(file.getName());
        PutObjectResult putObjectResult = ossClient.putObject(aliOssproperties.getBucketName(), newName, file);
        if (Objects.isNull(putObjectResult)) {
            throw new OssException("error when upload ");
        }
    }

    public void download() {
        OSSObject object = ossClient.getObject(aliOssproperties.getBucketName(), "filename");
        if (Objects.isNull(object)) {
            throw new OssException("error when download ");
        }
    }
}
