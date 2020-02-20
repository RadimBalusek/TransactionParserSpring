package cz.rbalusek.transactionparser;

import cz.rbalusek.transactionparser.service.BuildFinalList;
import cz.rbalusek.transactionparser.service.ConvertFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class TransactionParserApplication {


    public static void main(String[] args) {
        if (args[0] == null || args[0] == "") {
            throw new IllegalArgumentException("It is required to provide argument referencing the file to process.");
        }
        ApplicationContext applicationContext = SpringApplication.run(TransactionParserApplication.class, args);

        Path path = Paths.get(args[0]);

       // Path path = Paths.get("/home/radim/IdeaProjects/TransactionParser/src/cz//resources/transactions");

        BuildFinalList buildFinalList = applicationContext.getBean(BuildFinalList.class);
        ConvertFile convertFileToList = applicationContext.getBean(ConvertFile.class);

        buildFinalList.resultListBuilder(convertFileToList.convertFileToList(path));

        // print to console output string

        System.out.println(buildFinalList.resultListBuilder(convertFileToList.convertFileToList(path)));

    }

}
