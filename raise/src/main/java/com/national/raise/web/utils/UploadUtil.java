//package com.national.raise.web.utils;
//
//import static com.google.common.base.Charsets.ISO_8859_1;
//import static com.google.common.base.Charsets.UTF_8;
//
//import com.its.ione.v2.io.Writer;
//import com.its.ione.v2.web.cache.Writable;
//import com.its.itone.core.jsonrpc.engine.AppException;
//
//import com.google.common.base.CharMatcher;
//import com.google.common.base.Charsets;
//import com.google.common.base.Preconditions;
//import com.google.common.base.StandardSystemProperty;
//import com.google.common.base.Strings;
//import com.oreilly.servlet.MultipartRequest;
//import org.apache.tika.Tika;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 文件上传下载工具类
// *
// * @author LiLingbei
// */
//@SuppressWarnings({"WeakerAccess", "unused"})
//public class UploadUtil {
//
//    /**
//     * 上传文件采用字段名
//     */
//    public static final String FILE_NAME = "fileName";
//    /**
//     * 文件路径
//     */
//    public static final String FILE_PATH;
//    /**
//     * 文件仓库
//     */
//    public static final String FILE_REPOSITORY;
//    /**
//     * 返回前台的错误信息
//     */
//    public static final String SYS_ERR = "系统内部错误！";
//    /**
//     * 有数据返回的错误码
//     */
//    public static final int HAS_DATA_CODE = 1;
//    /**
//     * 不能作为文件名的字符匹配器
//     */
//    public static final CharMatcher NOT_FILE_NAME = CharMatcher.anyOf("\\/:*?\"<>|");
//    /**
//     * 上传文件大小
//     */
//    public static final int MAX_POST_SIZE = 100 * 1024 * 1024; // 100M
//    private static final String LIC_EXT = "lic";
//    private static final String LIC_EXPECTED_MIME_TYPE = "text/plain";
//    private static final Logger LOGGER = LoggerFactory.getLogger(UploadUtil.class);
//    private static final Map<String, String> mimeMap = new HashMap<>();
//    private static final Map<String, String> rejectFileTypes = new HashMap<>();
//
//    static {
//        String tmpdir = StandardSystemProperty.JAVA_IO_TMPDIR.value();
//        Preconditions.checkNotNull(tmpdir, "{java.io.tmpdir} can't be null !");
//        FILE_PATH = new File(tmpdir).getPath() + File.separator;
//        try {
//            FILE_REPOSITORY = getUserPath();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Get  FileRepository Error!", e);
//        }
//
//        mimeMap.put("png", "image/png");
//        mimeMap.put("jpeg", "image/jpeg");
//        mimeMap.put("jpg", "image/jpeg");
//        mimeMap.put("gif", "image/gif");
//        mimeMap.put("bmp", "image/bmp");
//        mimeMap.put("ico", "image/vnd.microsoft.icon");
//        mimeMap.put("wma", "audio/x-ms-wma");
//        mimeMap.put("mp3", "audio/mpeg");
//        mimeMap.put("wav", "aaudio/vnd.wave");
//        mimeMap.put("mp4", "video/mp4");
//        mimeMap.put("mid", "audio/midi");
//        mimeMap.put("avi", "video/x-msvideo");
//        mimeMap.put("txt", "text/plain");
//        mimeMap.put("log", "text/x-log");
//        mimeMap.put("lic", "text/plain");
//        mimeMap.put("zip", "application/zip");
//        mimeMap.put("tar", "application/x-gtar");
//        mimeMap.put("gz", "application/gzip");
//        mimeMap.put("rar", "application/x-rar-compressed");
//        mimeMap.put("xls", "application/vnd.ms-excel");
//        mimeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        mimeMap.put("pdf", "application/pdf");
//        mimeMap.put("doc", "application/msword");
//        mimeMap.put("docx",
//                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        mimeMap.put("ppt", "application/vnd.ms-powerpoint");
//        mimeMap.put("pptx",
//                    "application/vnd.openxmlformats-officedocument.presentationml.presentation");
//        mimeMap.put("jsp", "text/plain");
//    }
//
//    /**
//     * 获取上传文件的方法
//     */
//    public static File uploadFile(HttpServletRequest req) {
//        MultipartRequest mpr;
//        try {
//            mpr = new MultipartRequest(req, FILE_PATH, MAX_POST_SIZE);
//        } catch (IOException e) {
//            LOGGER.error("转换MultipartRequest异常！", e);
//            throw new AppException("上传文件失败！");
//        }
//        String fileName = mpr.getParameter(FILE_NAME);
//        if (Strings.isNullOrEmpty(fileName)) {
//            throw new AppException("文件名获取失败！");
//        }
//        File file = mpr.getFile(fileName);
//        if (!file.exists() || !file.isFile()) {
//            throw new AppException("文件获取失败！");
//        }
//        // 校验mime-type
//        if (!checkMimeType(file)) {
//            LOGGER.error("文件类型不正确");
//            throw new AppException("文件类型不正确！");
//        }
//        return file;
//    }
//
//    /**
//     * 获取MultipartRequest
//     *
//     * @param req HTTP请求
//     * @return 文件
//     */
//    public static MultipartRequest getMultipartRequest(HttpServletRequest req) {
//        MultipartRequest mpr;
//        try {
//            mpr = new MultipartRequest(req, FILE_PATH, MAX_POST_SIZE);
//        } catch (IOException e) {
//            LOGGER.error("转换MultipartRequest异常！", e);
//            throw new AppException(SYS_ERR);
//        }
//
//        return mpr;
//    }
//
//    /**
//     * 获取文件
//     *
//     * @param mpr HTTP请求
//     * @return 文件
//     */
//    public static File getFile(MultipartRequest mpr) {
//        String fileName = mpr.getParameter(FILE_NAME);
//        if (Strings.isNullOrEmpty(fileName)) {
//            throw new AppException("文件名获取失败！");
//        }
//        File file = mpr.getFile(fileName);
//        if (!file.exists() || !file.isFile()) {
//            throw new AppException("文件获取失败！");
//        }
//
//        // 校验mime-type
//        if (!checkMimeType(file)) {
//            LOGGER.error("文件类型不正确");
//            throw new AppException("文件类型不正确");
//        }
//
//        return file;
//    }
//
//
//    /**
//     * 加载文件
//     *
//     * @param fileName 文件名
//     * @param resp     HTTP响应
//     */
//    public static void loadFile(String fileName, String uploadFileName, HttpServletResponse resp) {
//        String tempFileName = new String(uploadFileName.getBytes(UTF_8), ISO_8859_1);
//        resp.setHeader("Content-Disposition", "attachment;fileName=\"" + tempFileName + "\"");
//        writeTo(Writer.of(new File(fileName)), resp);
//    }
//
//    /**
//     * 发送下载文件的方法
//     */
//    public static void returnFile(byte[] bytes, String fileName, HttpServletResponse resp) {
//        returnFile(Writer.of(bytes), fileName, resp);
//    }
//
//    /**
//     * 发送下载文件的方法
//     */
//    public static void returnFile(String fileName, HttpServletResponse resp) {
//        returnFile(new File(FILE_PATH + fileName), fileName, resp);
//    }
//
//    /**
//     * 发送下载文件的方法
//     *
//     * @param file     要下载文件
//     * @param fileName 使用文件名
//     * @param resp     HttpServletResponse
//     */
//    public static void returnFile(File file, String fileName, HttpServletResponse resp) {
//        returnFile(Writer.of(file), fileName, resp);
//    }
//
//    /**
//     * 发送下载文件的方法
//     *
//     * @param writable 可写对象
//     * @param fileName 使用文件名
//     * @param resp     HttpServletResponse
//     */
//    public static void returnFile(Writable writable, String fileName, HttpServletResponse resp) {
//        resp.setHeader("Content-Disposition", "attachment;fileName=" + encodeFileName(fileName));
//        writeTo(writable, resp);
//    }
//
//    /**
//     * 发送下载文件的方法
//     *
//     * @param writer   可写对象
//     * @param fileName 使用文件名
//     * @param resp     HttpServletResponse
//     */
//    public static void returnFile(Writer<?> writer, String fileName, HttpServletResponse resp) {
//        resp.setHeader("Content-Disposition", "attachment;fileName=" + encodeFileName(fileName));
//        writeTo(writer, resp);
//    }
//
//    /**
//     * 下载文件名重新编码
//     *
//     * @param fileName 文件名
//     * @return 编码后文件名
//     */
//    public static String encodeFileName(String fileName) {
//        return new String(fileName.getBytes(), ISO_8859_1);
//    }
//
//    /**
//     * 检查文件名，去除特殊字符
//     *
//     * @param fileName 文件名
//     * @return 去除特殊字符后的文件名
//     */
//    public static String checkFileName(String fileName) {
//        return NOT_FILE_NAME.replaceFrom(fileName, '_');
//    }
//
//    /**
//     * 获取用户路径
//     *
//     * @return 用户路径
//     */
//    public static String getUserPath() {
//        File file = new File(System.getProperty("user.dir")).getParentFile();
//        String path = file.getPath() + "/workspace/user/file";
//        checkPath(file.getPath() + "/workspace");
//        checkPath(file.getPath() + "/workspace/user");
//        checkPath(path);
//        return path;
//    }
//
//    /**
//     * 检查路径是否存在
//     *
//     * @param path 路径
//     */
//    private static void checkPath(String path) {
//        File dir = new File(path);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//    }
//
//    private static void writeTo(Writable writable, HttpServletResponse resp) {
//        try (Writable writer = writable; OutputStream out = resp.getOutputStream()) {
//            writer.write(out);
//        } catch (Exception e) {
//            String msg = "下载文件失败！";
//            LOGGER.error(msg, e);
//            throw new AppException(msg);
//        }
//    }
//
//    private static void writeTo(Writer<?> writer, HttpServletResponse resp) {
//        try (Writer<?> _writer = writer; OutputStream out = resp.getOutputStream()) {
//            _writer.writeTo(out);
//        } catch (Exception e) {
//            String msg = "下载文件失败！";
//            LOGGER.error(msg, e);
//            throw new AppException(msg);
//        }
//    }
//
//    /**
//     * 校验文件mimetype
//     *
//     * @param file 待校验文件对象
//     * @return 成功：true，失败：false
//     */
//    public static boolean checkMimeType(File file) {
//        String fileName = new String(file.getName().getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
//        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//        String contentType = getMimeType(file);
//        if (contentType == null || contentType.length() == 0) {
//            LOGGER.info("未识别到文件类型，fileName = {}", fileName);
//            return false;
//        }
//
//        String mimeType = getMimeType(ext);
//        if (mimeType == null || mimeType.length() == 0) {
//            // 未配置的文件类型，暂不做限制
//            LOGGER.info("未配置的文件类型，fileName = {}, contentType = {}", fileName, contentType);
//            return true;
//        }
//
//        if (!contentType.equals(mimeType)) {
//            LOGGER.info("文件mimetype不一致，fileName = {}, contentType = {}, mimeType = {} ", fileName,
//                        contentType, mimeType);
//            return false;
//        }
//        return true;
//    }
//
//    private static String getMimeType(String ext) {
//        if (mimeMap.containsKey(ext)) {
//            return mimeMap.get(ext);
//        }
//        return null;
//    }
//
//    private static String getMimeType(File file) {
//        String type;
//        try {
//            Tika tika = new Tika();
//            type = tika.detect(file);
//        } catch (IOException e) {
//            LOGGER.warn("获取文件的MIME-type失败, {}", file.getName());
//            throw new AppException("获取文件的MIME-type失败", e);
//        }
//        return type;
//    }
//
//    public static boolean checkLicenseMimeType(File file) {
//        String fileName = file.getName();
//        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//        String mimeType = getMimeType(file);
//        if (!LIC_EXT.equals(ext) || !LIC_EXPECTED_MIME_TYPE.equals(mimeType)) {
//            LOGGER.warn("非法的License文件, {}", fileName);
//            throw new AppException("非法的License文件");
//        }
//        return true;
//    }
//}
