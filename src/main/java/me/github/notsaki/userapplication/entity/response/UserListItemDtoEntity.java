package me.github.notsaki.userapplication.entity.response;

import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;

public record UserListItemDtoEntity(
		int id,
		String name,
		String surname
) implements UserListItemDto {
}
