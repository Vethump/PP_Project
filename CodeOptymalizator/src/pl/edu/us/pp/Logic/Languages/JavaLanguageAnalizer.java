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
public class JavaLanguageAnalizer extends RootLanguage{

    private String file;
    private String[] splittedFile;
    private String multiplicationPattern = "\\d+\\s*\\*|\\*\\s*\\d+|\\*=\\s*\\d+";
    private String numberPattern = "\\d+";
    private Pattern multiplicationCompiledPattern = Pattern.compile(multiplicationPattern);
    private Pattern numberCompiledPattern = Pattern.compile(numberPattern);
    private List<Message> messageList;
    private boolean status;

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
                        messageList.add(new Message("\"" + m.group(j) + "\"\nW linii: \n\""+ i + ": "+ (splittedFile[i] + 1)
                                +"\"\nMoże być zastąpiony przy pomocy operacji << (jest to potęga 2ki)"));
                    }
                }
            }
        }
    }

    public JavaLanguageAnalizer(String file) {
        this.file = file;
        this.splittedFile = file.split("\\r\\n");
    }

    @Override
    public Report analizeCode() {
        messageList = new ArrayList<>();
        status = true;
        uglyfyMultiplication();
        return new Report(status, messageList);
    }
}
