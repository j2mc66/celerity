package com.example.celerity.dto;

import com.example.celerity.domain.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	
	private Long id;

    private Integer version;

    private String username;
    
    public static UserDto convertToDto(User user) {

    	UserDto postDto = new UserDto();
    	postDto.setId(user.getId());
    	postDto.setUsername(user.getUsername());
    	postDto.setVersion(postDto.getVersion());
        return postDto;
    }

}
