package com.server.backend.controller;

import com.server.backend.entity.New;
import com.server.backend.entity.SupplyShop;
import com.server.backend.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@RestController
@RequestMapping("/New")
@CrossOrigin

public class NewController {
    private static final String UPLOAD_DIR = "/app/images/";
    @Autowired
    private NewService newService;

//    @PostMapping("/uploadNew")
//    public ResponseEntity<New> createNew(@RequestBody New news){
//        New saveNew = newService.createNew(news);
//        return new ResponseEntity<>(saveNew, HttpStatus.CREATED);
//    }

//    @GetMapping("/getNew")
//    public List<New> getAllService(){
//        return newService.getAllNew();
//    }
//    @PutMapping("/updateNew")
//    public ResponseEntity<New> updateNew(@RequestParam(name = "id") long id, @RequestBody New news){
//        New updatedNew=newService.updateNew(news,id);
//        return new ResponseEntity<>(updatedNew, HttpStatus.OK);
//    }
//    @DeleteMapping("/deleteNew")
//    public ResponseEntity<New> deleteNew(@RequestParam (name = "id") long id){
//        New deletedNew= NewService.deleteNew(id);
//        return new ResponseEntity<>(deletedNew,HttpStatus.OK);
//    }
//
    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<New> uploadNewData(
            @RequestPart("newTitle") String newTitle,
            @RequestPart("subTitle") String subTitle,
            @RequestPart("description") String description,


            @RequestPart("file") MultipartFile file) {

        New news = new New();
        news.setNewTitle(newTitle);
        news.setSubTitle(subTitle);
        news.setDescription(description);


        // Process image and save shop data
        New createNews = newService.createNew(news , file);

        return ResponseEntity.ok(createNews);
    }




    @GetMapping("/all")
    public ResponseEntity<List<New>> getAllNews() {
        List<New> news = newService.getAllNew();
        return ResponseEntity.ok(news);
    }

    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<New> updateNews(@PathVariable long id,
                                                 @RequestBody New news) {
        New updatedNew = newService.updateNew(news, id);
        return ResponseEntity.ok(updatedNew);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable long id) {

        newService.deleteNew(id);
        return ResponseEntity.ok("New deleted successfully");
    }

}
