package com.mycompany.myapp.controller;

import com.mycompany.myapp.domain.Assignment_1;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.Assignment_1_Repo;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.UserService;
import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MyController {

    @Autowired
    private Assignment_1_Repo repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    //    UserService user;
    //    String username;
    //
    //    public MyController(UserService user) {
    //        this.user = user;
    //        username = user.getUserWithAuthorities().get().getLogin();
    //    }

    private static final String USER_NAME = "19IT132";

    //    @Value("${file.upload-dir}")
    //    @Value("${file.upload-dir}")
    String FILE_DIRECTORY = "D:\\demo_files\\";

    //
    //    private final UserService userService;
    ////
    //    public MyController(UserService userService) {
    //        this.userService = userService;
    //    }
    //    SecurityUtils securityUtils;

    @PostMapping("/uploadFile")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File uploadedFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
        //        myFile.createNewFile();
        File renameFile = new File(FILE_DIRECTORY + USER_NAME + ".key");
        boolean renameFlag = uploadedFile.renameTo(renameFile);
        if (renameFlag) {
            System.out.println("File Successfully Renamed");
        } else {
            System.out.println("Rename Operation Failed");
        }

        FileOutputStream fos = new FileOutputStream(renameFile);
        fos.write(file.getBytes());
        fos.close();
        //        Generates Random String
        String random_string = randomString(10);
        System.out.println("Generated String : " + random_string);
        System.out.println("Writing a string into file");
        try {
            FileWriter fw = new FileWriter("D:\\demo_files\\" + USER_NAME + ".txt");
            fw.write(random_string);
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("File Writing Successfully.");
        System.out.println("Reading a File : ");
        readFile();
        System.out.println("File Reading Successfully");

        System.out.println("Adding Random string to Database");
        Assignment_1 a1 = new Assignment_1(USER_NAME, random_string, new Date());
        repo.save(a1);
        System.out.println("Random String added to Database");

        System.out.println("File Encryption Started");
        encryptFile(file, random_string);
        System.out.println("File Encrypted Successfully");
        //        System.out.println("Demo Demo Demo  "+username);
        return new ResponseEntity<Object>("The File Uploaded Successfully.", HttpStatus.OK);
    }

    //    To Validate User String with Username
    @PostMapping("/validate")
    public boolean is_valid_string(@RequestParam("username") String username, @RequestParam("str") String str) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        Assignment_1 a1 = mongoTemplate.findOne(query, Assignment_1.class);
        if (a1 != null) {
            if (!a1.isStatus()) {
                a1.setStatus(true);
                mongoTemplate.save(a1);
                System.out.println("Done Assignment Successfully");
                return true;
            } else {
                System.out.println("You have already done your assignment");
                return false;
            }
        } else {
            return false;
        }
    }

    public static void readFile() throws IOException {
        File file = new File("D:\\demo_files\\" + USER_NAME + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) System.out.println(st);
    }

    // Logic to Encrypt File using public key
    private void encryptFile(MultipartFile file, String str) throws IOException {
        File dir = new File("D:\\demo_files\\");
        System.out.println("Encryption Started");
        String command =
            "openssl rsautl -encrypt -in " + USER_NAME + ".txt -pubin -inkey " + USER_NAME + ".key -out " + USER_NAME + "_cipher.txt";
        Process process = Runtime.getRuntime().exec(command, null, dir);
        System.out.println("Cipher Test Generated Successfully");
    }

    // Code to generate Random String Logic
    public String randomString(int n) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
