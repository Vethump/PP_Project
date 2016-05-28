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
    private Pattern multiplicationCompiledPattern = Pattern.compile(multiplicationPattern);

    private String numberPattern = "\\d+";
    private Pattern numberCompiledPattern = Pattern.compile(numberPattern);

    private String stringEqualsEmptyPattern = "\\w*\\.equals\\(\\s*\"\\\"\\s*\\)";
    private Pattern stringEqualsEmptyCompiledPattern = Pattern.compile(stringEqualsEmptyPattern);

    private String mathMethodsWithConstantsPattern = "Math\\s*\\.\\s*\\w*\\s*\\(\\s*-?\\s*\\d*(\\.\\d*\\s*)?\\)";
    private Pattern mathMethodsWithConstantsCompiledPattern = Pattern.compile(mathMethodsWithConstantsPattern);

    private String multipleAppendPattern = "\\w*\\s*(\\.\\s*append\\((\".*\"|\\d+)\\)){2,}";
    private Pattern multipleAppendCompiledPattern = Pattern.compile(multipleAppendPattern);

    private String insideForDeclarationPattern = "for\\([\\w*\\s*;<>+=\\-\\*\\/]+\\)\\{\\s*(.+\\s)*\\s*(int|double|String|float|byte)\\s.+";
    private Pattern insideForDeclarationCompiledPattern = Pattern.compile(insideForDeclarationPattern);



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
                                +"\"\nMoże być zastąpiony przy pomocy operacji << (jest to potęga 2ki)\n"));
                    }
                }
            }
        }
    }

    private void findComparisonWithEmptyString(){
        Matcher m = stringEqualsEmptyCompiledPattern.matcher(file);
        while(m.find()){
            messageList.add(new Message("\""+m.group(0) + "\"\n Unikaj porównań typu string.equals(\"\"). Zamiast tego wykorzystaj str.length() == 0 !\n"));
            status = false;
        }
    }

    private void findMathMethodsWithConstants(){
        Matcher m = mathMethodsWithConstantsCompiledPattern.matcher(file);
        while(m.find()){
            messageList.add(new Message("\""+m.group(0) + "\"\nUnikaj używania stałych jako argumentów metod matematycznych!\n"));
            status = false;
        }
    }

    private void findMultipleAppend(){
        Matcher m = multipleAppendCompiledPattern.matcher(file);
        while(m.find()){
            messageList.add(new Message("\""+m.group(0) + "\"\nUnikaj wielokrotnego używania append z literałami! Np.\n" +
                    "Zamiast: \n" + "StringBuffer buf = new StringBuffer();\nbuf.append(\"Hello\").append(\" \").append(\"World\");\n" +
                    "Zastosuj:\n" + "StringBuffer buf = new StringBuffer();\n" +
                    "buf.append(\"Hello World\");\n"));
            status = false;
        }
    }

    private void findInsideForDeclaration(){
        Matcher m = insideForDeclarationCompiledPattern.matcher(file);
        while(m.find()){
            messageList.add(new Message("\""+m.group(0) + "\n..." + "\"\nUnikaj deklarowania zmiennych wewnątrz pętli!\n"));
            status = false;
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
        findComparisonWithEmptyString();
        findMathMethodsWithConstants();
        findMultipleAppend();
        findInsideForDeclaration();
        return new Report(status, messageList);
    }
}
