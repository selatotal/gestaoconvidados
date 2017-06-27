package br.edu.ulbra.gestaoconvidados.services;

import br.edu.ulbra.gestaoconvidados.entities.User;
import br.edu.ulbra.gestaoconvidados.enums.UserMessageEnum;
import br.edu.ulbra.gestaoconvidados.input.UserInput;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<String> validateNew(UserInput userInput) {

        ArrayList<String> errors = new ArrayList<>();
        boolean hasPassword = false;

        if (userInput.getEmail() == null || userInput.getEmail().length() == 0) {
            errors.add(UserMessageEnum.EMPTY_EMAIL.toString());
        }

        if (userInput.getPassword() == null || userInput.getPassword().length() == 0) {
            errors.add(UserMessageEnum.EMPTY_PASSWORD.toString());
        } else {
            hasPassword = true;
        }

        if (userInput.getPasswordConfirm() == null || userInput.getPasswordConfirm().length() == 0) {
            errors.add(UserMessageEnum.EMPTY_PASSWORD_CONFIRM.toString());
        } else {
            hasPassword = true;
        }

        if (hasPassword && userInput.getPassword() != null && userInput.getPasswordConfirm() != null && !userInput.getPassword().equals(userInput.getPasswordConfirm())) {
            errors.add(UserMessageEnum.PASSWORDS_MISMATCH.toString());
        }

        if (userInput.getName() == null || userInput.getName().length() == 0) {
            errors.add(UserMessageEnum.EMPTY_NAME.toString());
        }

        if (userInput.getProfileId() == null) {
            errors.add(UserMessageEnum.EMPTY_PROFILE.toString());
        }

        return errors;

    }

    public List<String> validateUpdate(UserInput userInput) {

        List<String> errors = validateNew(userInput);

        if (userInput.getId() == null) {
            errors.add(UserMessageEnum.USER_NOT_FOUND.toString());
        }

        return errors;

    }
}