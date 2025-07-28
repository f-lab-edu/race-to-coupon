package com.rtc.app.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtc.app.auth.dto.internal.CustomUserDetails;
import com.rtc.app.auth.dto.internal.UserInfo;
import com.rtc.app.auth.exception.JwtAuthenticationException;
import com.rtc.app.auth.exception.JwtExpirationException;
import com.rtc.app.auth.service.authentication.JwtService;
import com.rtc.app.auth.type.ApiAuthResponseCode;
import com.rtc.app.common.exception.dto.ErrorResponse;
import com.rtc.app.common.response.ApiResponse;
import com.rtc.app.common.response.ResponseCode;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final JwtService jwtService;
    private static final List<String> WHITELIST = List.of("/auth/signup");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = getAccessTokenFromRequest(request);
            if (null != accessToken && !accessToken.isBlank()) {
                UserInfo userInfo = jwtService.getUserInfoFromAccessToken(accessToken);

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userInfo.getType()));
                UserDetails userDetails = new CustomUserDetails(userInfo.getId(), userInfo.getEmail(), null, authorities);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            // 로그 처리 필요
            handleException(response, new JwtExpirationException("토큰이 만료 되었습니다."));
        } catch (Exception e) {
            // 로그 처리 필요
            handleException(response, new JwtAuthenticationException("인증이 실패하였습니다."));
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessTokenFromRequest(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && !headerAuth.isBlank() && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

    private void handleException(HttpServletResponse response, RuntimeException e) throws IOException {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        ResponseCode code = ApiAuthResponseCode.AUTHENTICATION_FAILED;
        if (e instanceof JwtExpirationException jwtEx) {
            code = jwtEx.getCode();
        }

        ApiResponse<ErrorResponse> apiResponse = ApiResponse.error(code, e.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
