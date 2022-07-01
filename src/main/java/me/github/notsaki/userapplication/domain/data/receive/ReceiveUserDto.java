package me.github.notsaki.userapplication.domain.data.receive;

import me.github.notsaki.userapplication.domain.data.ValidatableEntity;
import me.github.notsaki.userapplication.domain.data.ValidationMessage;
import me.github.notsaki.userapplication.domain.data.error.ValidationInfo;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class ReceiveUserDto implements ValidatableEntity {
    public abstract String name();
    public abstract String surname();
    public abstract Gender gender();
    public abstract LocalDate birthdate();
    public abstract String workAddress();
    public abstract String homeAddress();
    public abstract User toUser();

    public List<ValidationInfo> validate() {
        var errors = new ArrayList<ValidationInfo>();

        if(this.name().length() < 1 || this.name().length() > 64)
            errors.add(new ValidationInfo("name", ValidationMessage.nameLength));

        if(this.surname().length() < 1 || this.surname().length() > 64)
            errors.add(new ValidationInfo("surnname", ValidationMessage.nameLength));

        if(this.birthdate().isAfter(LocalDate.now()))
            errors.add(new ValidationInfo("birthdate", ValidationMessage.datePast));

        if(this.workAddress().length() < 1 || this.workAddress().length() > 128)
            errors.add(new ValidationInfo("workAddress", ValidationMessage.addressLength));

        if(this.homeAddress().length() < 1 || this.workAddress().length() > 128)
            errors.add(new ValidationInfo("homeAddress", ValidationMessage.addressLength));

        return errors;
    }
}
