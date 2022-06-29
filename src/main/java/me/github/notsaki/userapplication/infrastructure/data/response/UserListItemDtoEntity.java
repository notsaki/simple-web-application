package me.github.notsaki.userapplication.infrastructure.data.response;

import me.github.notsaki.userapplication.domain.data.response.UserListItemDto;

public record UserListItemDtoEntity(
		int id,
		String name,
		String surname
) implements UserListItemDto {
}
