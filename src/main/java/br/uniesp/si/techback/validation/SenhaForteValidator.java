package br.uniesp.si.techback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaForteValidator implements ConstraintValidator<SenhaForte, String> {

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {
        if (senha == null || senha.isBlank()) {
            return false;
        }
        // Regra: Mínimo 8 caracteres, 1 maiúscula, 1 minúscula, 1 número e 1 caractere especial
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()_+\\-\\[\\]{}|;':\",./<>?`~])(?=\\S+$).{8,}$";
        return senha.matches(regex);
    }
}