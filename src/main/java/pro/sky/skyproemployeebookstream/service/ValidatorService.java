package pro.sky.skyproemployeebookstream.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.skyproemployeebookstream.exception.IncorrectNameOfEmployee;

@Service
public class ValidatorService {

    public String validateFirstName(String firstName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new IncorrectNameOfEmployee();
        }
        return StringUtils.capitalize(firstName.toLowerCase());
    }

    public String validateLastName(String lastName) {
        String[] lastNames = lastName.split("-");
        for (int i = 0; i < lastNames.length; i++) {
            String name = lastNames[i];
            if (!StringUtils.isAlpha(name)) {
                throw new IncorrectNameOfEmployee();
            }
            lastNames[i]= StringUtils.capitalize(name.toLowerCase());
        }


        return String.join("-",lastNames);
    }
}
