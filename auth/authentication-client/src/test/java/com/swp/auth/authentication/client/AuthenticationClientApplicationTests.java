package com.swp.auth.authentication.client;

import com.swp.auth.authentication.client.provider.AuthenticationProvider;
import com.swp.auth.authentication.client.service.AuthService;
import com.swp.auth.authentication.client.service.impl.AuthServiceImpl;
import com.swp.ncloud.common.core.entity.vo.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.mockito.Mockito.when;

public class AuthenticationClientApplicationTests {

    private static final String BEARER = "Bearer ";
    private static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJleHAiOjE2NDY5ODY2MzQsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6ImY0bGg3SHlOZ2w1eWNTXzRYZVJQRnRBUmVJYyIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0._G2oXSKzxUoajx93UN1ePEIwNwtoPUr54kLGnmHxVe0";

    /**
     * @Autowird 等方式完成自动注入。在单元测试中，没有启动 spring 框架，此时就需要通过 @ InjectMocks完成依赖注入。@InjectMocks会将带有@Spy 和@Mock 注解的对象尝试注入到被 测试的目标类中
     *
     */
    @InjectMocks
    AuthService authService;

    @Mock
    AuthenticationProvider authenticationProvider;

    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        authService = new AuthServiceImpl();
        setInstancePrivateField(authService,"signingKey", "123456");
        setInstancePrivateField(authService,"ignoreUrl", "/oauth,/open");
        MockitoAnnotations.initMocks(this);
    }

    private void setInstancePrivateField(Object instance, String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    @Test
    public void parseToken(){
        authService.parseToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJleHAiOjE2NDY5Nzg4MDcsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6InZ6UXlwZXctWEV6UkdsYjVTRUZoblRBcVpqUSIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.mbLgQ1PFTspaExXZmTKGZ9RQJNowLxE11BkXDbVa2n4");
    }

    @Test
    public void testInvalidAuthenticationUrl(){
        Assert.assertFalse(authService.invalidAuthorization(BEARER + token));
    }

    @Test
    public void testIgnoreAuthenticationUrl(){
        Assert.assertTrue(authService.ignoreAuthenticationUrl("/open"));
    }

    @Test
    public void testIgnoreAuthenticationUrl_只有前缀(){
        Assert.assertTrue(authService.ignoreAuthenticationUrl("/open/test"));
    }

    @Test
    public void testHasPermission(){
        when(authenticationProvider.auth(BEARER + token, "/user", "POST")).thenReturn(Result.success(true));
        Assert.assertTrue(authService.hasPermission(BEARER + token, "/user","POST"));
    }

}
