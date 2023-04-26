package com.learn.springmvc.controller;

import com.learn.springmvc.model.Employee;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;


@Controller
@RequestMapping("/resbody")
public class ResBodyController {

    @RequestMapping("")
    public String index() {
        return "ResBody";
    }

    @PostMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println("requestBody: " + requestBody);
        return "ResBody";
    }

    @PostMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity) {
        System.out.println("requestHeader: " + requestEntity.getHeaders());
        System.out.println("requestBody: " + requestEntity.getBody());
        return "ResBody";
    }

    @GetMapping("/testHttpResponse")
    public void testHttpResponse(HttpServletResponse response) throws IOException {
        response.getWriter().println("customized response, note this is not an html!");
    }

    @GetMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody() {
        // 被@ResponseBody修饰, 不再被解析成url, 而是直接作为响应体返回给浏览器
        return "ResBody";
    }

    @GetMapping("/testResponseBodyWithJson")
    @ResponseBody
    public Employee testResponseBodyWithJson() {
        return new Employee(1, "张三", "sss@qq.com", 0);
    }

    @PostMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username, String password) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "hello, ajax!";
    }

    @GetMapping("/testDownloadViaResponseEntity")
    public ResponseEntity<byte[]> testDownloadViaResponseEntity(HttpSession session) throws IOException {
        // 下载文件的本质是将服务器上的文件复制到浏览器
        // 故先创建服务器上下文
        ServletContext context = session.getServletContext();
        // 获取文件路径
        String path = context.getRealPath("/static/img/研究生成绩单.jpg");
        // 将文件放入io流
        byte[] bytes;
        try (InputStream is = new FileInputStream(path)) {
            bytes = new byte[is.available()];
            // 将文件内容读取到byte数组中
            is.read(bytes);
        }

        // 设置具体响应信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        String filename = "score.jpg";
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        HttpStatus statusCode = HttpStatus.OK;
        // 创建响应体
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        return responseEntity;
    }

    @PostMapping("testUpload")
    public String testUpload(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        // 上传文件同理, 需要文件名 + 目标路径
        // 获取原始文件名
        String filename = file.getOriginalFilename();
        // 为防止重名覆盖, 使用UUID作为文件名
        // 获取文件后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 令最终文件名为UUID + 后缀名
        filename = UUID.randomUUID() + suffix;

        // 获取目标路径
        ServletContext context = session.getServletContext();
        String path = context.getRealPath("/static/img/" + filename);
        System.out.println("path: " + path);
        // 为防止目录不存在, 先创建目录
        File dir = new File(path.substring(0, path.lastIndexOf("\\")));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        path = path + File.separator + filename;
        System.out.println("path: " + path);
        file.transferTo(new File(path));
        return "ResBody";
    }
}
