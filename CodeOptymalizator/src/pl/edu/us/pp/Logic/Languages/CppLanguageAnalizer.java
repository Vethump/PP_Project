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
public class CppLanguageAnalizer extends RootLanguage {

    private String file;
    private String[] splittedFile;
    private String coutPattern = "cout\\s*(<<[():'\"`\\w\\s\\.\\&\\\\]+)+;";
    private String cinPattern = "cin\\s*(>>[():'\"`\\w\\s\\.\\&\\\\]+)+;";
    private String multiplicationPattern = "\\d+\\s*\\*|\\*\\s\\d+|\\*=\\s*\\d+";
    private String numberPattern = "\\d+";
    private Pattern coutCompliedPattern = Pattern.compile(coutPattern);
    private Pattern cinCompliedPattern = Pattern.compile(cinPattern);
    private Pattern multiplicationCompiledPattern = Pattern.compile(multiplicationPattern);
    private Pattern numberCompiledPattern = Pattern.compile(numberPattern);
    private List<Message> messageList;
    private boolean status;

    private void exchangeCoutForPrintf(){
        Matcher m = coutCompliedPattern.matcher(file);
        while(m.find()){
            messageList.add(new Message("\""+m.group(0) + "\"\n Może być zastąpiony przy pomocy printf()"));
            status = false;
        }
    }

    private void exchangeCinForScanf(){
        Matcher m = cinCompliedPattern.matcher(file);
        while(m.find()){
            messageList.add(new Message("\""+m.group(0) + "\"\n Może być zastąpiony przy pomocy scanf()"));
            status = false;
        }
    }

    private void uglyfyMultiplication(){
        Matcher m1;
        int value;
        Matcher m;
        for (int i = 0 ; i < splittedFile.length ; ++i) {
            m = multiplicationCompiledPattern.matcher(splittedFile[i]);
            while (m.find()) {
                for (int j = 0; j <= m.groupCount(); ++j) {
                    m1 = numberCompiledPattern.matcher(m.group(j));
                    m1.find();
                    value = Integer.parseInt(m1.group());
                    if ((value & (value - 1)) == 0) {
                        messageList.add(new Message("\"" + m.group(j) + "\"\nW linii: \n\""+ i + ": "+ splittedFile[i] +"\"\nMoże być zastąpiony przy pomocy operacji << (jest to potęga 2ki)"));
                    }
                }
            }
        }

    }

    public CppLanguageAnalizer(String file) {
        this.file = file;
        this.splittedFile = file.split("\\r\\n");
    }

    @Override
    public Report analizeCode() {
        messageList = new ArrayList<>();
        status = true;
        exchangeCoutForPrintf();
        exchangeCinForScanf();
        uglyfyMultiplication();
        return new Report(status, messageList);
    }
}
