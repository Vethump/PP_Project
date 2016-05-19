package pl.edu.us.pp.Logic.Languages;

import pl.edu.us.pp.Logic.Utility.Message;
import pl.edu.us.pp.Logic.Utility.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Adrian on 2016-05-19.
 */
public class CppLanguageAnalizer implements LanguageOptimizer {

    private String file;
    private String coutPattern = "cout *(<<[():'\"`\\w\\s\\.\\&\\\\]+)+;";
    private Pattern coutCompliedPattern = Pattern.compile(coutPattern);
    private List<Message> messageList;
    private boolean status;

    private void exchangeCoutForPrintf(){
        Matcher m = coutCompliedPattern.matcher(file);
        Message message;
        while(m.find()){
            message = new Message("\""+m.group(0) + "\"\n\n Może być zastąpiony przy pomocy printf()");
            messageList.add(message);
            status = false;
        }
    }

    public CppLanguageAnalizer(String file) {
        this.file = file;

    }

    @Override
    public Report analizeCode() {
        messageList = new ArrayList<>();
        exchangeCoutForPrintf();
        return new Report(status, messageList);
    }
}
