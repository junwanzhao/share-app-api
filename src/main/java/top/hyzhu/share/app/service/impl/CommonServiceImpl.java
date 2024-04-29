package top.hyzhu.share.app.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.hyzhu.share.app.common.cache.RedisCache;
import top.hyzhu.share.app.common.cache.RedisKeys;
import top.hyzhu.share.app.common.config.CloopenConfig;
import top.hyzhu.share.app.common.config.OssConfig;
import top.hyzhu.share.app.common.exception.ErrorCode;
import top.hyzhu.share.app.common.exception.ServerException;
import top.hyzhu.share.app.service.CommonService;
import top.hyzhu.share.app.utils.CommonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author zhy
 * @Date 2024/4/28
 * @Description
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    private CloopenConfig cloopenConfig;

    @Resource
    private RedisCache redisCache;

    @Resource
    private OssConfig ossConfig;

    private static final String[] IMAGE_TYPE = new String[]{".bmp",".jpg", ".jpeg", ".gif",".png"};

    @Override
    public void sendSms(String phone){
        if( !CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }

        int code = CommonUtils.generateCode();
        redisCache.set(RedisKeys.getSmsKey(phone), code,60);
        boolean result = cloopenSendSms(phone, code);

        if(result) {
            log.info("============= 短信发送成功 ===========");
        }
    }

    private boolean cloopenSendSms(String phone, int code) {
        try {
            log.info("================ 创建短信发送通道 ==================== \nphone is {}, code is {}", phone, code);
            String serverIp = cloopenConfig.getServerIp();
            String serverPort = cloopenConfig.getPort();
            String accountSId = cloopenConfig.getAccountSId();
            String accountToken = cloopenConfig.getAccountToken();
            String appId = cloopenConfig.getAppId();
            CCPRestSmsSDK sdk = new CCPRestSmsSDK();
            sdk.init(serverIp,serverPort);
            sdk.setAccount(accountSId,accountToken);
            sdk.setAppId(appId);
            sdk.setBodyType(BodyType.Type_JSON);
            String templateId = cloopenConfig.getTemplateId();
            String[] datas = {String.valueOf(code),"1"};
            HashMap<String, Object> result = sdk.sendTemplateSMS(phone,templateId,datas,"1234", UUID.randomUUID().toString());
            if("000000".equals(result.get("statusCode"))){
                HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for(String key:keySet) {
                    Object object = data.get(key);
                    log.info("{} = {}", key, object);

                }
            } else {
                log.error("错误码={} 错误信息={}", result.get("statusCode"), result.get("statusMsg"));
                throw new ServerException(ErrorCode.CODE_SEND_FAIL);
            }
        } catch (Exception e) {
            throw new ServerException(ErrorCode.CODE_SEND_FAIL);
        }
        return true;
    }

    @Override
    public String upload(MultipartFile file) {
        String returnImgUrl;

        boolean isLegal = false;
        for(String type : IMAGE_TYPE) {
            if(StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)) {
                isLegal = true;
                break;
            }
        }
        if(!isLegal) {
            throw new ServerException("图片格式不正确");
        }

        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileType;
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String uploadUrl = filePath + "/" + newFileName;

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpeg");

        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName,uploadUrl,inputStream,meta);

        returnImgUrl = "https://" + bucketName + "." + endpoint + "/" + uploadUrl;
        return returnImgUrl;
    }

}
