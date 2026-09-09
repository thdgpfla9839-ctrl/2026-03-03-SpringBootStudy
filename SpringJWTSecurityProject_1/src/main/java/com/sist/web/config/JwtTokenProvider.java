package com.sist.web.config;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
// Payload
/* {
 *     sub: "admin",
 *     role: "ROLE_ADMIN" => 이게 토큰형식으로 암호화 돼 있어 => byte 형식 
 * }
 */
@Component
public class JwtTokenProvider {
	 // 실무에서는 자동으로 설정하는 키 설정이 있다 => 지금처럼 코딩하는 건 실무 방식이 아니라는 점
	 // application.yml에서 jwt: secret: ${JWT_SECRET} 이런식으로 키를 저장해 줘서 사용하면 됨
     private final String SECRET="my-secret-key-my-secret-key--my-secret-key--my-secret-key";
     public String createToken(String username,String role) {
    	 return Jwts.builder()
    			.setSubject(username) // 사용자 아이디 저장 {sub: admin} 이런식으로 저장됨
    			.claim("role", role) // 권한 추가 => ROLE_ADMIN으로 저장이 된다
    			.setIssuedAt(new Date()) // jwt 발급 시간 설정 => jwt 언제 발급됐냐
    			.setExpiration(new Date(System.currentTimeMillis()+3600000))
    			// 만료시간 등록하는 과정 =>3600000 : 1시간 후에는 자동으로 해제되게끔 
    			.signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
    			// SECRET의 키를 이용해서 JWT에 서명하는 과정
    			.compact();
     }
     // username이 들어오면 토큰 발급
     public String getUsername(String token)
     {
    	 return Jwts.parserBuilder()
    			.setSigningKey(SECRET.getBytes())
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.getSubject();
     }
     // validate 이름이 맞는지 틀린지 확인해주는 메소드 => 검증 및 추출을 해주는
     public boolean validate(String token)
     {
    	 try
    	 {
    		 Jwts.parserBuilder()
    		 .setSigningKey(SECRET.getBytes())
    		 .build()
    		 .parseClaimsJws(token);
    		 return true;
    	 }catch(Exception ex)
    	 {
    		 return false;
    	 }
     }
     
}