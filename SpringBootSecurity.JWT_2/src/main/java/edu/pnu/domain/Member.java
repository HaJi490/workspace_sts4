package edu.pnu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Member {
	@Id
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	private boolean enabled; //활성화된 유저인가(삭제하지않고 로그인안되게 설정)
}
