package pl.edu.us.pp.Logic.Languages;

import pl.edu.us.pp.Logic.Utility.Report;

/**
 * Created by Adrian on 2016-05-19.
 */
public class JavaLanguageAnalizer extends RootLanguage{

    private String file;

    public JavaLanguageAnalizer(String file) {
        this.file = file;
    }

    @Override
    public Report analizeCode() {
        return null;
    }
}
