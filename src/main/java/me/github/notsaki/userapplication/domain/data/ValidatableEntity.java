package me.github.notsaki.userapplication.domain.data;

import me.github.notsaki.userapplication.domain.data.error.ValidationInfo;

import java.util.List;

public interface ValidatableEntity {
    List<ValidationInfo> validate();
}
