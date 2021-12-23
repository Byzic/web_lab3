package org.lera.lab3;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pointYValidator")
public class PointYValidator implements Validator {
    private Double y;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) throw new ValidatorException(new FacesMessage("Введите значение Y"));
        try {
            y = Double.parseDouble(String.valueOf(o));
            if (y >= 3 || y <= -5)
                throw new ValidatorException(new FacesMessage("Значение Y не входит в интервал (-5,3)"));
        } catch (NumberFormatException exception) {
            throw new ValidatorException(new FacesMessage("Значение Y не является числом"));
        }
    }
}
