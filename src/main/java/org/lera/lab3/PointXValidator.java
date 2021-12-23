package org.lera.lab3;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pointXValidator")
public class PointXValidator implements Validator {
    private Double x;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) throw new ValidatorException(new FacesMessage("Значение X не выбрано"));
        try {
            x = Double.parseDouble(String.valueOf(o));
            if (x > 3 ||  x< -5)
                throw new ValidatorException(new FacesMessage("Значение X не входит в [-5,3]"));
        } catch (NumberFormatException exception) {
            throw new ValidatorException(new FacesMessage("Координата должна быть числом"));
        }
    }
}
