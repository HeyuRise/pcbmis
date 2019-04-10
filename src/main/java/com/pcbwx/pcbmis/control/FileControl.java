package com.pcbwx.pcbmis.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.common.ConfigProperties;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ConfigEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.enums.FileEnum;
import com.pcbwx.pcbmis.model.PcbFile;
import com.pcbwx.pcbmis.service.FileService;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.RandomUtil;

/**
 * 文件上传
 *
 * @author jisx
 * @date 2017-12-19
 */
@Controller
@RequestMapping("/file")
public class FileControl extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(FileControl.class);

    @Autowired
    private FileService fileService;
    @Autowired
    private AuthService authService;
    @Autowired
    private LogService logService;
    @Autowired
    private SupportService supportService;

    /**
     * 上传图片
     * @param request
     * @param images
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> uploadImage(HttpServletRequest request, @RequestParam("file")  MultipartFile[] images)
            throws Exception {
        final AkAuthUser wxtbUser = authService.getLoginUser();

        // 行为记录
        logService.addAction("", ActionTypeEnum.UPLOAD_FILE.getCode(), wxtbUser.getUserCode(), "");
        supportService.doSystemLog(request, wxtbUser.getUsername(), "上传图片", 0);

        Map<String, Object> response = new HashMap<>();

        List<PcbFile> list = new ArrayList<>();
        boolean flag = saveFile(images, list, FileEnum.IMAGE.getType(), wxtbUser.getUserCode());

        response.put("content", list);
        response.put("result", flag ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
        return response;
    }

    /**
     * 上传文件
     * @param request
     * @param unqualifiedId
     * @param images
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> uploadFile(HttpServletRequest request, @RequestParam int unqualifiedId, @RequestParam("file")  MultipartFile[] images)
            throws Exception {
        final AkAuthUser wxtbUser = authService.getLoginUser();

        // 行为记录
        logService.addAction("", ActionTypeEnum.UPLOAD_FILE.getCode(), wxtbUser.getUserCode(), "");
        supportService.doSystemLog(request, wxtbUser.getUsername(), "上传文件", 0);

        Map<String, Object> response = new HashMap<>();

        List<PcbFile> list = new ArrayList<>();
        if (images == null || images.length == 0) {
            response.put("content", "至少上传一个文件");
            response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
            return response;
        }

        boolean flag = saveFile(images, list, FileEnum.FILE.getType(),wxtbUser.getUserCode());

        if (flag) {
            flag = fileService.saveFiles(unqualifiedId, list);
        }

        response.put("content", list);
        response.put("result", flag ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
        return response;
    }

    /**
     * 保存文件的具体方法
     * @param images
     * @param list
     * @param fileType
     * @param userCode
     * @return
     * @throws IOException
     */
    private boolean saveFile(MultipartFile[] images, List<PcbFile> list, int fileType, String userCode) throws IOException {
        PcbFile pcbFile;

        String path = ConfigProperties.getProperty(ConfigEnum.LOCAL_FILE_PATH);
        File file;
        long timeInMillis;
        for (MultipartFile img : images) {

            if (img.getSize() > 0) {
                pcbFile = new PcbFile();
                timeInMillis = Calendar.getInstance().getTimeInMillis();

                String fileName = img.getOriginalFilename();
                pcbFile.setName(fileName);
                //文件名重命名 规则是  时间戳+ “-” + 随机6位数 + 文件后缀名
                pcbFile.setSuffix(fileName.substring(fileName.lastIndexOf("."), fileName.length()));
                pcbFile.setRename(timeInMillis + "-" + RandomUtil.createRandom(6) + pcbFile.getSuffix());
                pcbFile.setSize(img.getSize() + "");
                pcbFile.setUserCode(userCode);
                pcbFile.setStatus(1);
                pcbFile.setType(fileType);
                pcbFile.setCreateTime(new Date(timeInMillis));

                file = new File(path, pcbFile.getRename());
                img.transferTo(file);

                pcbFile.setPath(file.getPath());
                list.add(pcbFile);
            }

        }
        return fileService.saveImages(list);
    }

    /**
     * 用于客户端展现图片
     * @param request
     * @param imageId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
    @ResponseBody
    public void seeImage(HttpServletRequest request, @PathVariable Integer imageId, HttpServletResponse response)
            throws Exception {

        final AkAuthUser wxtbUser = authService.getLoginUser();
        // 行为记录
        logService.addAction("", ActionTypeEnum.SEE_FILE.getCode(), wxtbUser.getUserCode(), "");
        PcbFile pcbFile = fileService.selectByImageId(imageId);
        supportService.doSystemLog(request, wxtbUser.getUsername(), "查看文件" + pcbFile.getPath(), 0);


        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(pcbFile.getPath());
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 删除某个文件，可以是图片也可以是附件
     * @param request
     * @param fileId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{fileId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteFile(HttpServletRequest request, @PathVariable Integer fileId)
            throws Exception {

        final AkAuthUser wxtbUser = authService.getLoginUser();
        supportService.doSystemLog(request, wxtbUser.getUsername(), "删除文件" + fileId, 0);

        boolean flag = fileService.deleteFileId(fileId);

        Map<String, Object> response = new HashMap<>();

        response.put("content", null);
        response.put("result", flag ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
        return response;
    }


    /**
     * 下载文件，包括图片和附件
     * @param request
     * @param fileId
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(HttpServletRequest request, @PathVariable Integer fileId, HttpServletResponse response)
            throws Exception {
        final AkAuthUser wxtbUser = authService.getLoginUser();

        // 行为记录
        logService.addAction("", ActionTypeEnum.DOWNLOAD_FILE.getCode(), wxtbUser.getUserCode(), "");
        supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.DOWNLOAD_FILE.getDesc(), 0);

        PcbFile pcbFile = fileService.selectByImageId(fileId);

        downLoadFile(response, new File(pcbFile.getPath()),pcbFile.getName());
    }

    public static void downLoadFile(HttpServletResponse response, File file,String fileName) {
        if (file == null || !file.exists()) {
            return;
        }
        OutputStream out = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

}
