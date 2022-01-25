package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Assignment_1;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.Assignment_1_Repo;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.UserService;
import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.config.JHipsterProperties;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private Assignment_1_Repo repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    JHipsterProperties jHipsterProperties;

    JavaMailSender javaMailSender;

    private MailService mailService;

    //    UserService user;
    //    String studentID;
    //
    //    public MyController(UserService user) {
    //        this.user = user;
    //        studentID = user.getUserWithAuthorities().get().getLogin();
    //    }

    private static final String USER_NAME = "19IT132";

    //    @Value("${file.upload-dir}")
    //    @Value("${file.upload-dir}")
    String FILE_DIRECTORY = "D:\\demo_files\\";

    //
    private final UserService userService;

    //
    public MyController(UserService userService) {
        this.userService = userService;
    }

    //    SecurityUtils securityUtils;

    @GetMapping("/demoAPI")
    public String demo() {
        return "Demo API";
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String email = userService.getUserWithAuthorities().get().getEmail();
        String nameUser = userService.getUserWithAuthorities().get().getLogin();

        String[] arrOfStr = email.split("@", 2);
        String studentID = arrOfStr[0];

        System.out.println(studentID);
        System.out.println(email);

        File uploadedFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
        //        myFile.createNewFile();
        File renameFile = new File(FILE_DIRECTORY + studentID + ".key");
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
            FileWriter fw = new FileWriter("D:\\demo_files\\" + studentID + ".txt");
            fw.write(random_string);
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("File Writing Successfully.");
        System.out.println("Reading a File : ");
        readFile(studentID);
        System.out.println("File Reading Successfully");

        System.out.println("Adding Random string to Database");
        Assignment_1 a1 = new Assignment_1(studentID, random_string, new Date());
        repo.save(a1);
        System.out.println("Random String added to Database");

        System.out.println("File Encryption Started");
        encryptFile(file, random_string, studentID, email);
        //      String message =   mailService.sendEmailwithAttachment(email,"Cipher text file for CRNS Assignment-1","Hello Student \n" +
        //            "Use this attached txt file as your cipher text and complete your assignment according to given instruction \n" +
        //            "\n" +
        //            "Regards, \n" +
        //            "Assignment WebApp Team",false,studentID,false);
        //        System.out.println("File sent to student's registered email successfully");
        System.out.println("File Encrypted Successfully");

        //        Optional<String> na1me = SecurityUtils.getCurrentUserLogin();
        //        User name = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("Demo Email --> " + email);
        System.out.println("Demo Login --> " + nameUser);
        return new ResponseEntity<Object>("The File Uploaded Successfully.", HttpStatus.OK);
    }

    //    To Validate User String with studentID
    @PostMapping("/validate")
    public boolean is_valid_string(@RequestParam("str") String str) {
        String email = userService.getUserWithAuthorities().get().getEmail();
        String nameUser = userService.getUserWithAuthorities().get().getLogin();

        String[] arrOfStr = email.split("@", 2);
        String studentID = arrOfStr[0];

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(studentID));
        Assignment_1 a1 = mongoTemplate.findOne(query, Assignment_1.class);
        if (a1 != null) {
            System.out.println(a1.getRandom_string());
            System.out.println("USer String --> " + str);
            if (!a1.isStatus()) {
                String dbString = a1.getRandom_string();
                if (dbString.equals(str)) {
                    a1.setStatus(true);
                    mongoTemplate.save(a1);
                    System.out.println("Done Assignment Successfully");
                    return true;
                } else {
                    System.out.println("String doesen't match please provide valid string");
                    return false;
                }
            } else {
                System.out.println("You have already done your assignment");
                return false;
            }
        } else {
            System.out.println("First Complete the Uploadation part");
            return false;
        }
    }

    public static void readFile(String studentID) throws IOException {
        File file = new File("D:\\demo_files\\" + studentID + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) System.out.println(st);
    }

    // Logic to Encrypt File using public key
    private void encryptFile(MultipartFile file, String str, String studentID, String emailTo) throws IOException {
        File dir = new File("D:\\demo_files\\");
        System.out.println("Encryption Started");
        String command =
            "openssl rsautl -encrypt -in " + studentID + ".txt -pubin -inkey " + studentID + ".key -out " + studentID + "_cipher.txt";
        Process process = Runtime.getRuntime().exec(command, null, dir);
        System.out.println("Cipher Test Generated Successfully");
        //        mailService.sendEmailwithAttachment(emailTo,"Cipher text file for CRNS Assignment-1","Hello Student \n" +
        //            "Use this attached txt file as your cipher text and complete your assignment according to given instruction \n" +
        //            "\n" +
        //            "Regards, \n" +
        //            "Assignment WebApp Team",true,studentID,false);
        //        System.out.println("File sent to student's registered email successfully");
        //        MimeMessage message = javaMailSender.createMimeMessage();
        //        try {
        //            MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //            helper.setFrom(jHipsterProperties.getMail().getFrom());
        //            helper.setTo(emailTo);
        //            helper.setSubject("Your Cipher Text File for CRNS Assignment");
        //            helper.setText("Hello Students , Use this attached file for your assignment and do according to instructions.");
        //            helper.addAttachment(studentID+"_cipher.txt", new FileSystemResource("D:\\demo_files\\"+studentID+"_cipher.txt"));
        //            javaMailSender.send(message);
        //        } catch (MessagingException e) {
        //
        //            e.printStackTrace();
        //        }
        //        message.setFrom(new InternetAddress(from));
        //        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        //        message.setSubject("Test Mail Subject");
        //
        //        BodyPart messageBodyPart = new MimeBodyPart();
        //        messageBodyPart.setText("Mail Body");
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
