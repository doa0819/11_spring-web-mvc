package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileuploadController {

    @Autowired
    private ResourceLoader resourceLoader;      // ResourceLoader class 를 controller 에서 사용할 준비를 함

    @PostMapping("single-file")
    // @RequestParam : 파일 하나랑 파일에 대한 설명을 받아 온다
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   Model model) throws IOException {

        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);
        //  @RequestParam 으로 html 파일 잡아오는지 확인


        /* 파일을 저장할 경로 설정 -> 데이터베이스 연동 안해서 저장할 경로 지정해주는것 */
        Resource resource = resourceLoader.getResource("classpath:static/img/single");
        // classpath  -> build 에 업로드할것들을 다 저장할 것이다.(중복이 되더라도)
        // resources 아래에는 중복되는게 없게 할 것이다.

        String filepath = null;
        if (!resource.exists()) {
            /* 만약 static 폴더에 파일이 없는 경우 만들어준다. */
            String root = "src/main/resources/static/img/single";
            File file = new File(root);     // file 만들어서 저장할 사진 저장

            file.mkdirs();      // mkdirs : 폴더를 만들어 준다. (mkdir : 하나의 폴더를 만든다 / 뒤에 s 붙이면 여러개 만듬)
            filepath = file.getAbsolutePath();      // 절대 경로
        } else {
            filepath = resourceLoader.getResource("classpath:static/img/single").getFile().getAbsolutePath();
        }

        /* 파일명 변경 처리*/
        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);       // 원본파일 이름 반환

        /* 확장자 제거 */
        String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 뒤에서 부터 . 이라는 거를 찾을때 까지 라는 의미(ex) 너구리.jpg)
        System.out.println("ext = " + ext);

//        String saveName = UUID.randomUUID().toString();   // 문자열숫자들이 나옴
        String saveName = UUID.randomUUID().toString().replace("-","") + ext;  // - 없애줌
        System.out.println("saveName = " + saveName);

        /* 파일을 저장 */
        singleFile.transferTo(new File(filepath + "/" + saveName));     // saveName 에 위에 filepath 경로를 넣어준다
        model.addAttribute("message", "파일 업로드 성공!!!");
        model.addAttribute("img", "static/img/single/" + saveName);

        return "result";

    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles,
                                  @RequestParam String multiFileDescription,
                                  Model model) throws IOException {

        System.out.println("multiFiles = " + multiFiles);
        System.out.println("multiFileDescription = " + multiFileDescription);

        // single / multi 다르게 저장위해 디렉토리 만듬
        Resource resource = resourceLoader.getResource("classpath:static/img/multi");
        String filepath = null;

        if (!resource.exists()) {
            String root = "src/main/resources/static/img/multi";
            File file = new File(root);
            file.mkdirs();
            filepath = file.getAbsolutePath();
        }else {
            filepath = resourceLoader.getResource("classpath:static/img/multi").getFile().getAbsolutePath();
        }

        System.out.println("filepath = " + filepath);

        List<FileDTO> files = new ArrayList<>();
        List<String> saveFiles = new ArrayList<>();

        for (MultipartFile file : multiFiles){
            /* 파일명 변경 처리 */
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-","") + ext;

            files.add(new FileDTO(originFileName,savedName,filepath,multiFileDescription));

            file.transferTo(new File(filepath + "/" +savedName));
            saveFiles.add("static/img/multi/"+savedName);
        }

        model.addAttribute("message","파일 업로드 성공!!!!");
        model.addAttribute("imgs",saveFiles);


        return "result";

    }

}
